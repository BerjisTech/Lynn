package tech.berjis.lynn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubscriptionActivity extends AppCompatActivity {

    TextView paymentText, payNow;

    FirebaseAuth mAuth;
    DatabaseReference dbRef, depositRef, transactionRef;
    String UID;
    long amount = 50, nowUnix, expiryUnix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_subscription);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        UID = mAuth.getCurrentUser().getUid();
        dbRef.keepSynced(true);

        paymentText = findViewById(R.id.paymentText);
        payNow = findViewById(R.id.payNow);
        Date now = new Date();
        Date expiry = new Date();
        DateFormat currentDate = DateFormat.getDateInstance();

        Date expiryDate = addDays(expiry, 30);

        nowUnix = now.getTime() / 1000;
        expiryUnix = expiryDate.getTime() / 1000;

        if (nowUnix > expiryUnix) {
            Toast.makeText(this, "Expired", Toast.LENGTH_SHORT).show();
        }

        String expiryDay = currentDate.format(expiryDate);
        String nowDate = currentDate.format(now);

        paymentText.setText(
                HtmlCompat.fromHtml(
                        "<h1>$5</h1>" +
                                "<p>A monthly payment of $5 is required to access the app's full services.</p>" +
                                "<p><b>NO HIDDEN CHARGES</b></p>" +
                                "<p>Subscription starts on: " + nowDate + "<br />" +
                                "And ends on " + expiryDay + "</p>",
                        HtmlCompat.FROM_HTML_MODE_LEGACY));

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserSubscription();
            }
        });

    }

    public static Date addDays(Date d, int days) {
        d.setTime(d.getTime() + days * 1000L * 60 * 60 * 24);
        return d;
    }

    private void checkUserSubscription() {
        dbRef.child("Users").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String first_name = dataSnapshot.child("first_name").getValue().toString();
                String last_name = dataSnapshot.child("last_name").getValue().toString();
                String email = dataSnapshot.child("user_email").getValue().toString();
                String phone_number = dataSnapshot.child("user_phone").getValue().toString();
                validateEntries(first_name, last_name, email, phone_number);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void validateEntries(String fName, String lName, String email, String phone_number) {
        depositRef = dbRef.child("Subscriptions").child(UID).push();
        transactionRef = dbRef.child("Transactions").push();
        String text_ref = depositRef.getKey();
        String trans_ref = transactionRef.getKey();
        long time_start = System.currentTimeMillis() / 1000L;
        long final_amount = amount;
        depositRef.child("time_start").setValue(time_start);
        transactionRef.child("time_start").setValue(time_start);

        String publicKey = getString(R.string.public_key);
        String encryptionKey = getString(R.string.encryption_key);
        String narration = fName + " " + lName + "'s Lynn client subscription";

        depositRef.child("user").setValue(UID);
        depositRef.child("type").setValue("deposit");
        depositRef.child("narration").setValue(narration);
        depositRef.child("amount").setValue(final_amount);
        depositRef.child("text_ref").setValue(text_ref);

        transactionRef.child("user").setValue(UID);
        transactionRef.child("type").setValue("subscription");
        transactionRef.child("narration").setValue("Lynn client subscription");
        transactionRef.child("amount").setValue(final_amount);
        transactionRef.child("text_ref").setValue(trans_ref);

        boolean valid = true;

        //isAmountValid for compulsory fields

        if (valid) {
            RavePayManager ravePayManager = new RavePayManager(this).setAmount(amount)
                    .setCountry("KE")
                    .setCurrency("KES")
                    .setEmail(email)
                    .setfName(fName)
                    .setlName(lName)
                    .setPhoneNumber(phone_number)
                    .setNarration(narration)
                    .setPublicKey(publicKey)
                    .setEncryptionKey(encryptionKey)
                    .setTxRef(text_ref)
                    .acceptMpesaPayments(true)
                    .acceptAccountPayments(true)
                    .acceptCardPayments(true)
                    .allowSaveCardFeature(true)
                    .acceptAchPayments(false)
                    .acceptGHMobileMoneyPayments(false)
                    .acceptUgMobileMoneyPayments(false)
                    .acceptZmMobileMoneyPayments(false)
                    .acceptRwfMobileMoneyPayments(false)
                    .acceptUkPayments(false)
                    .acceptSaBankPayments(false)
                    .acceptFrancMobileMoneyPayments(false)
                    .acceptBankTransferPayments(false)
                    .acceptUssdPayments(false)
                    .acceptBarterPayments(false)
                    .onStagingEnv(false)
                    .isPreAuth(true)
                    .showStagingLabel(false)
//                    .setMeta(meta)
//                    .withTheme(R.style.TestNewTheme)
                    .shouldDisplayFee(false);

            ravePayManager.initialize();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {

            String message = data.getStringExtra("response");

            if (message != null) {
                Log.d("rave response", message);
            }

            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                long end_time = System.currentTimeMillis() / 1000L;
                depositRef.child("end_time").setValue(end_time);
                depositRef.child("status").setValue("success");

                transactionRef.child("end_time").setValue(end_time);
                transactionRef.child("status").setValue("success");

                dbRef.child("Users").child(UID).child("last_paid").setValue(nowUnix);
                dbRef.child("Users").child(UID).child("user_expiry").setValue(expiryUnix);

                Toast.makeText(this, "Subscription successful", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SubscriptionActivity.this, ModelsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                //Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                long end_time = System.currentTimeMillis() / 1000L;
                depositRef.child("end_time").setValue(end_time);
                depositRef.child("status").setValue("error");

                transactionRef.child("end_time").setValue(end_time);
                transactionRef.child("status").setValue("error");

                Toast.makeText(this, "Something went wrong. Try again", Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                long end_time = System.currentTimeMillis() / 1000L;
                depositRef.child("end_time").setValue(end_time);
                depositRef.child("status").setValue("cancelled");

                transactionRef.child("end_time").setValue(end_time);
                transactionRef.child("status").setValue("cancelled");

                Toast.makeText(this, "You cancelled your subscription payment", Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
