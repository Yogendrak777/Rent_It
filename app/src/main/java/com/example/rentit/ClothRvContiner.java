package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ClothRvContiner extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    ClothRvAdapter clothRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_rv_continer);

        recyclerView = (RecyclerView)findViewById(R.id.Rv5);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView =(SearchView)findViewById(R.id.search5);

        FirebaseRecyclerOptions<ClothRvModel> options =
                new FirebaseRecyclerOptions.Builder<ClothRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("clothes"),ClothRvModel.class)
                        .build();

        clothRvAdapter = new ClothRvAdapter(options);
        recyclerView.setAdapter(clothRvAdapter);


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
        clothRvAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clothRvAdapter.stopListening();
    }

    public void txtSearch (String str){
        FirebaseRecyclerOptions<ClothRvModel> options =
                new FirebaseRecyclerOptions.Builder<ClothRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("clothes").orderByChild("clothArea").startAt(str).endAt(str+"~"),ClothRvModel.class)
                        .build();

        clothRvAdapter = new ClothRvAdapter(options);
        clothRvAdapter.startListening();

        recyclerView.setAdapter(clothRvAdapter);
    }
}