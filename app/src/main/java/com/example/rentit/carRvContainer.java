package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class carRvContainer extends AppCompatActivity {
    RecyclerView recyclerView;
    carRvAdapter carrvadapter;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rv_container);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView)findViewById(R.id.Rv1);
        searchView = (SearchView)findViewById(R.id.search1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<carRvModel> options =
                new FirebaseRecyclerOptions.Builder<carRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("car"),carRvModel.class)
                        .build();

        carrvadapter = new carRvAdapter(options);
        recyclerView.setAdapter(carrvadapter);


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
        carrvadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        carrvadapter.stopListening();
    }

        public void txtSearch (String str){
            FirebaseRecyclerOptions<carRvModel> options =
                    new FirebaseRecyclerOptions.Builder<carRvModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("car").orderByChild("carArea").startAt(str).endAt(str+"~"),carRvModel.class)
                            .build();

            carrvadapter = new carRvAdapter(options);
            carrvadapter.startListening();

            recyclerView.setAdapter(carrvadapter);
    }
}