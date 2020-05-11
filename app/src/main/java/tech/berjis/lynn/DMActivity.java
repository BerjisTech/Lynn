package tech.berjis.lynn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

public class DMActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference dbRef;
    String UID;

    ImageView back;
    EmojiTextView userName;
    ImageView dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_m);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        UID = mAuth.getCurrentUser().getUid();
        dbRef.keepSynced(true);

        back = findViewById(R.id.back);
        userName = findViewById(R.id.userName);
        dp = findViewById(R.id.dp);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DMActivity.super.finish();
            }
        });
        getModel();
    }

    private void getModel() {
        Intent modelIntent = getIntent();
        Bundle modelBundle = modelIntent.getExtras();
        String model = modelBundle.getString("model");
        loadModelData(model);
    }

    private void loadModelData(String model) {
        dbRef.child("Users").child(model).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("user_name").getValue().toString();
                String userimage = dataSnapshot.child("user_image").getValue().toString();

                if (!userimage.equals("")) {
                    Picasso.get().load(userimage).into(dp);
                }
                if (!username.equals("")) {
                    userName.setText(username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
