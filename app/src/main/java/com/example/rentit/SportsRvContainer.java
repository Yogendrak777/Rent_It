package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SportsRvContainer extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    SportsRvAdapter sportsRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_rv_container);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView)findViewById(R.id.Rv6);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = (SearchView)findViewById(R.id.search6);
        FirebaseRecyclerOptions<SportRvModel> options =
                new FirebaseRecyclerOptions.Builder<SportRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("sports"),SportRvModel.class)
                        .build();

        sportsRvAdapter = new SportsRvAdapter(options);
        recyclerView.setAdapter(sportsRvAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query.toUpperCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query.toUpperCase());
                return false;
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        sportsRvAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sportsRvAdapter.stopListening();
    }

    public void txtSearch (String str){
        FirebaseRecyclerOptions<SportRvModel> options =
                new FirebaseRecyclerOptions.Builder<SportRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("sports").orderByChild("spoArea").startAt(str).endAt(str+"~"),SportRvModel.class)
                        .build();

        sportsRvAdapter = new SportsRvAdapter(options);
        sportsRvAdapter.startListening();

        recyclerView.setAdapter(sportsRvAdapter);
    }
}

//    DisplayMetrics metrics = new DisplayMetrics();
//    getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
//        params.width = metrics.widthPixels;
//        params.height = metrics.heightPixels;
//        params.leftMargin = 0;
//        videoView.setLayoutParams(params);
//        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.logo);
//        videoView.start();