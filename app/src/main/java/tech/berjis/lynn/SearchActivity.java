package tech.berjis.lynn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    List<Models> modelsList;
    ModelsAdapter modelsAdapter;
    RecyclerView modelsRecycler;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.keepSynced(true);

        modelsRecycler = findViewById(R.id.searchResults);
        search = findViewById(R.id.search);

        modelsList = new ArrayList<>();
        StaggeredGridLayoutManager sgManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        sgManager.setReverseLayout(false);
        sgManager.scrollToPositionWithOffset(0, 0);
        modelsRecycler.setLayoutManager(sgManager);
        modelsRecycler.setAdapter(modelsAdapter);

        searchText();

        loadModels();
    }

    private void loadModels() {
        modelsList.clear();
        dbRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot modelsSnapshot : dataSnapshot.getChildren()) {
                        Models models = modelsSnapshot.getValue(Models.class);
                        modelsList.add(models);
                    }
                    Collections.shuffle(modelsList);
                    modelsAdapter = new ModelsAdapter(SearchActivity.this, modelsList, "search");
                    modelsRecycler.setAdapter(modelsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchText(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                search.setQueryHint("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                search.setQueryHint("done!");
            }

        }.start();
    }
}
