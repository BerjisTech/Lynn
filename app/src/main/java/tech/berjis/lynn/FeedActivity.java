package tech.berjis.lynn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    ImageView home, chats, profile;
    RecyclerView postsRecycler;
    NestedScrollView nestedScroll;
    ImageView modelImage;
    EmojiTextView modelDescription, modelName, pageTitle;

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    List<Posts> listData;
    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_feed);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();

        home = findViewById(R.id.home);
        chats = findViewById(R.id.chats);
        profile = findViewById(R.id.profile);
        pageTitle = findViewById(R.id.pageTitle);
        postsRecycler = findViewById(R.id.postsRecycler);
        nestedScroll = findViewById(R.id.nestedScroll);
        modelImage = findViewById(R.id.modelImage);
        modelName = findViewById(R.id.modelName);
        modelDescription = findViewById(R.id.modelDescription);

        postsRecycler.setFocusable(false);
        modelImage.requestFocus();

        listData = new ArrayList<>();
        postsRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        getModel();
    }

    private void getModel() {
        Intent modelIntent = getIntent();
        Bundle modelBundle = modelIntent.getExtras();
        String model = modelBundle.getString("model");
        loadModelData(model);
        loadPosts(model);
        unloggedState(model);
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
        } else {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedActivity.this, ModelsActivity.class));
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

    private void loadModelData(String model) {
        dbRef.child("Users").child(model).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("user_name").getValue().toString();
                String userimage = dataSnapshot.child("user_image").getValue().toString();
                String description = dataSnapshot.child("user_description").getValue().toString();

                if (!userimage.equals("")) {
                    Picasso.get().load(userimage).into(modelImage);
                }
                if (!username.equals("")) {
                    modelName.setText(username);
                    pageTitle.setText(username);
                }
                if (!description.equals("")) {
                    modelDescription.setText(description);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
