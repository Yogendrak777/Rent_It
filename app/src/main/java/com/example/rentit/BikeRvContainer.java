package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BikeRvContainer extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    BikeRvAdapter bikeRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_rv_container);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) findViewById(R.id.Rv2);
        searchView = (SearchView) findViewById(R.id.search2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<bikeRvModel> options =
                new FirebaseRecyclerOptions.Builder<bikeRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Bike"),bikeRvModel.class)
                        .build();

        bikeRvAdapter = new BikeRvAdapter(options);
        recyclerView.setAdapter(bikeRvAdapter);

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
        bikeRvAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bikeRvAdapter.stopListening();
    }

    public void txtSearch (String str){
        FirebaseRecyclerOptions<bikeRvModel> options =
                new FirebaseRecyclerOptions.Builder<bikeRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Bike").orderByChild("bikeArea").startAt(str).endAt(str+"~"),bikeRvModel.class)
                        .build();

        bikeRvAdapter = new BikeRvAdapter(options);
        bikeRvAdapter.startListening();

        recyclerView.setAdapter(bikeRvAdapter);
    }

}