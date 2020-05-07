package tech.berjis.lynn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountType extends AppCompatActivity {

    TextView escort, client;

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();

        escort = findViewById(R.id.escort);
        client = findViewById(R.id.client);

        escort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = mAuth.getCurrentUser().getUid();
                String phone = mAuth.getCurrentUser().getPhoneNumber();

                dbRef.child("Users").child(uid).child("user_phone").setValue(phone.substring(phone.length() - 12));
                dbRef.child("Users").child(uid).child("user_type").setValue("escort");
                dbRef.child("Users").child(uid).child("user_id").setValue(uid);

                startActivity(new Intent(AccountType.this, EditProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }
}
