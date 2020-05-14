package tech.berjis.lynn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_search);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.keepSynced(true);

        modelsRecycler = findViewById(R.id.searchResults);
        search = findViewById(R.id.search);

        int magId = getResources().getIdentifier("android:id/search_mag_icon", null, null);
        ImageView magImage = search.findViewById(magId);
        magImage.setLayoutParams(new LinearLayout.LayoutParams(0, 0));

        modelsList = new ArrayList<>();
        StaggeredGridLayoutManager sgManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        sgManager.setReverseLayout(false);
        sgManager.scrollToPositionWithOffset(0, 0);
        modelsRecycler.setLayoutManager(sgManager);
        modelsRecycler.setAdapter(modelsAdapter);

        loadModels(" ");

        final CountDownTimer c_timer = new CountDownTimer(50000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                final int min = 1;
                final int max = 5;
                final int random = new Random().nextInt((max - min) + 1) + min;
                String searchHint = "";
                if (random == 1) {
                    search.setQueryHint("Search for escort");
                }
                if (random == 2) {
                    search.setQueryHint("Massage, Quickie, Sneaky, Anal");
                }
                if (random == 3) {
                    search.setQueryHint("Search by location");
                }
                if (random == 4) {
                    search.setQueryHint("Straight, Bi, Lesbian");
                }
                if (random == 5) {
                    search.setQueryHint("Threesome, Gangbang");
                }
            }

            @Override
            public void onFinish() {

            }
        };
        c_timer.start();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                c_timer.cancel();
                loadModels(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void loadModels(final String query) {
        modelsList.clear();
        dbRef.child("Users").orderByChild("user_type").equalTo("escort").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    modelsList.clear();
                    for (DataSnapshot modelsSnapshot : dataSnapshot.getChildren()) {
                        if (
                                modelsSnapshot.child("user_description").getValue().toString().toLowerCase().contains(query.toLowerCase()) ||
                                        modelsSnapshot.child("user_name").getValue().toString().toLowerCase().contains(query.toLowerCase()) ||
                                        modelsSnapshot.child("first_name").getValue().toString().toLowerCase().contains(query.toLowerCase()) ||
                                        modelsSnapshot.child("last_name").getValue().toString().toLowerCase().contains(query.toLowerCase())
                        ) {
                            Models models = modelsSnapshot.getValue(Models.class);
                            modelsList.add(models);
                        }
                    }
                    Collections.shuffle(modelsList);
                    modelsAdapter = new ModelsAdapter(SearchActivity.this, modelsList, "search");
                    modelsAdapter.notifyDataSetChanged();
                    modelsRecycler.setAdapter(modelsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchText() {
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
