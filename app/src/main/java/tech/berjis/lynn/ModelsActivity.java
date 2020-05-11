package tech.berjis.lynn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelsActivity extends AppCompatActivity {

    ImageView home, chats, profile, camera, notifications;

    RecyclerView modelsRecycler;
    List<Models> modelsList;
    ModelsAdapter modelsAdapter;

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_models);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.keepSynced(true);

        modelsRecycler = findViewById(R.id.modelsRecycler);
        modelsList = new ArrayList<>();

        home = findViewById(R.id.home);
        chats = findViewById(R.id.chats);
        profile = findViewById(R.id.profile);
        camera = findViewById(R.id.camera);
        notifications = findViewById(R.id.notifications);
        unloggedState();

        modelsRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        loadModels();
    }

    private void unloggedState() {
        if (mAuth.getCurrentUser() == null) {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, RegisterActivity.class));
                }
            });
            chats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, RegisterActivity.class));
                }
            });
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, RegisterActivity.class));
                }
            });
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, RegisterActivity.class));
                }
            });
            notifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, RegisterActivity.class));
                }
            });
        } else {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            chats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, DMsActivity.class));
                }
            });
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, ProfileActivity.class));
                }
            });
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, CreatePost.class));
                }
            });
            notifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ModelsActivity.this, NotificationsActivity.class));
                }
            });
        }
    }

    private void loadModels() {
        modelsList.clear();
        dbRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot modelsSnapshot : dataSnapshot.getChildren()) {
                        if (modelsSnapshot.child("user_type").getValue().toString().equals("escort")) {
                            Models models = modelsSnapshot.getValue(Models.class);
                            modelsList.add(models);
                        }
                    }
                    Collections.reverse(modelsList);
                    modelsAdapter = new ModelsAdapter(ModelsActivity.this, modelsList);
                    modelsRecycler.setAdapter(modelsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}
