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

public class FeedActivity extends AppCompatActivity {

    ImageView home, chats, profile, camera, notifications;
    RecyclerView postsRecycler;

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    List<Posts> listData;
    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();

        home = findViewById(R.id.home);
        chats = findViewById(R.id.chats);
        profile = findViewById(R.id.profile);
        camera = findViewById(R.id.camera);
        notifications = findViewById(R.id.notifications);
        postsRecycler = findViewById(R.id.postsRecycler);

        listData = new ArrayList<>();
        postsRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        getModel();
    }

    private void getModel() {
        Intent modelIntent = getIntent();
        Bundle modelBundle = modelIntent.getExtras();
        String model = modelBundle.getString("model");
        prependModelCard(model);
        //loadPosts(model);
        unloggedState(model);
    }

    private void prependModelCard(String model) {
        listData.add(new Posts(23452345, "free", "dgfdfgsdfg", model, "pbulished", "dfgsdfg", "photo"));

        postsAdapter = new PostsAdapter(FeedActivity.this, listData, "card");
        postsAdapter.notifyDataSetChanged();
        postsRecycler.setAdapter(postsAdapter);

    }

    private void unloggedState(final String model) {
        if (mAuth.getCurrentUser() == null) {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, RegisterActivity.class));
                }
            });
            chats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, RegisterActivity.class));
                }
            });
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, RegisterActivity.class));
                }
            });
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, RegisterActivity.class));
                }
            });
            notifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, RegisterActivity.class));
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
                    Intent modelIntent = new Intent(FeedActivity.this, DMActivity.class);
                    Bundle modelBundle = new Bundle();
                    modelBundle.putString("model", model);
                    modelIntent.putExtras(modelBundle);
                    startActivity(modelIntent);
                }
            });
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, ProfileActivity.class));
                }
            });
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, CreatePost.class));
                }
            });
            notifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, NotificationsActivity.class));
                }
            });
        }
    }

    private void loadPosts(final String model) {
        listData.clear();
        dbRef.child("Posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshots : dataSnapshot.getChildren()) {
                        if (postSnapshots.child("status").getValue().toString().equals("published") &&
                                postSnapshots.child("availability").getValue().toString().equals("free") &&
                                postSnapshots.child("user").getValue().toString().equals(model)) {
                            Posts posts = postSnapshots.getValue(Posts.class);
                            listData.add(posts);
                        }
                    }
                    Collections.reverse(listData);
                    postsAdapter = new PostsAdapter(FeedActivity.this, listData, "post");
                    postsAdapter.notifyDataSetChanged();
                    postsRecycler.setAdapter(postsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
