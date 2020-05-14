package tech.berjis.lynn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {


    DatabaseReference dbRef;
    FirebaseAuth mAuth;

    String post_id, UID;
    EmojiEditText ed_emoji;
    ImageView link, send, btn_emoji, back;
    RecyclerView commentsRecycler;
    List<Comments> listData;
    CommentsAdapter adapter;
    ConstraintLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_comments);

        back = findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.keepSynced(true);

        UID = mAuth.getCurrentUser().getUid();

        Intent postIntent = getIntent();
        Bundle postBundle = postIntent.getExtras();
        post_id = postBundle.getString("post_id");

        send = findViewById(R.id.send);
        commentsRecycler = findViewById(R.id.commentsRecycler);
        ed_emoji = findViewById(R.id.ed_emoji);
        btn_emoji = findViewById(R.id.btn_emoji);
        rootView = findViewById(R.id.root_view);

        final EmojiPopup emojiPopup = EmojiPopup.Builder.fromRootView(rootView).build(ed_emoji);
        btn_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggles visibility of the Popup.
                if (emojiPopup.isShowing()) {
                    Picasso.get().load(R.drawable.emoji).into(btn_emoji);
                    emojiPopup.toggle();
                } else {
                    Picasso.get().load(R.drawable.keyboard).into(btn_emoji);
                    emojiPopup.toggle();
                }
            }
        });

        //emojiPopup.dismiss();  Dismisses the Popup.
        //emojiPopup.isShowing();  Returns true when Popup is showing.

        listData = new ArrayList<>();

        commentsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CommentsAdapter(listData);
        commentsRecycler.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentsActivity.super.finish();
            }
        });

        loadChats();
    }

    public void chat() {

        if (TextUtils.isEmpty(ed_emoji.getText().toString())) {
            Toast.makeText(this, "You can't send an empty message", Toast.LENGTH_SHORT).show();
        } else {

            long unixTime = System.currentTimeMillis() / 1000L;
            final DatabaseReference commentsRef_1 = dbRef.child("Comments").child(post_id).push();
            final String comment_key = commentsRef_1.getKey();
            HashMap<String, Object> sendChats_1 = new HashMap<>();

            sendChats_1.put("type", "text");
            sendChats_1.put("text", ed_emoji.getText().toString());
            sendChats_1.put("sender", UID);
            sendChats_1.put("post_id", post_id);
            sendChats_1.put("chat_id", comment_key);
            sendChats_1.put("date", unixTime);
            commentsRef_1.updateChildren(sendChats_1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        ed_emoji.setText("");
                    } else {
                        Toast.makeText(CommentsActivity.this, "An error occured while sending your message", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public void loadChats() {
        listData.clear();
        dbRef.child("Comments").child(post_id).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Comments messages = dataSnapshot.getValue(Comments.class);

                listData.add(messages);

                adapter.notifyDataSetChanged();

                commentsRecycler.smoothScrollToPosition(listData.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
