package com.example.rentit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class displayContentPage extends AppCompatActivity {
    RecyclerView recyclerView;
    RvAdapter rvAdapter;
    SearchView searchView;
    ProgressBar progressBar;
    String BHK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_content_page);

        Spinner bhk = (Spinner) findViewById(R.id.BhkSpinner);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.Rv);
        searchView = findViewById(R.id.search);
        progressBar = findViewById(R.id.progress);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Searchouse, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bhk.setAdapter(adapter1);
        bhk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BHK = adapterView.getItemAtPosition(i).toString();
                //change(BHK);
                //Toast.makeText(displayContentPage.this, BHK, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        FirebaseRecyclerOptions<houseRvModel> options =
                new FirebaseRecyclerOptions.Builder<houseRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("house"),houseRvModel.class)
                        .build();



        rvAdapter = new RvAdapter(options);
        recyclerView.setAdapter(rvAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query.toUpperCase());
                //change(BHK);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query.toUpperCase());
               // change(BHK);
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        rvAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        rvAdapter.stopListening();
    }


    public void txtSearch (String str){
        FirebaseRecyclerOptions<houseRvModel> options =
                new FirebaseRecyclerOptions.Builder<houseRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("house").orderByChild(BHK).startAt(str).endAt(str+"~"),houseRvModel.class)
                        .build();



        rvAdapter = new RvAdapter(options);
        rvAdapter.startListening();

        recyclerView.setAdapter(rvAdapter);


    }

//    public void change(String str) {
//        FirebaseRecyclerOptions<houseRvModel> options1 =
//                new FirebaseRecyclerOptions.Builder<houseRvModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("house").orderByChild("houseBHK").startAt(str).endAt(str + "~"), houseRvModel.class)
//                        .build();
//        rvAdapter = new RvAdapter(options1);
//        rvAdapter.startListening();
//
//        recyclerView.setAdapter(rvAdapter);
//    }
}