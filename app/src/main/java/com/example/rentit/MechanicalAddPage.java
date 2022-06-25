package com.example.rentit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MechanicalAddPage extends AppCompatActivity {
    RecyclerView recyclerView;
    serviceRVAdapter rvAdapter;
    SearchView searchView;
   // String work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechical_add_page);
       // Spinner bhk = (Spinner) findViewById(R.id.BhkSpinner);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.Rv);
        searchView = findViewById(R.id.search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.SearchServices, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        bhk.setAdapter(adapter1);
//        bhk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                work = adapterView.getItemAtPosition(i).toString();
//               // change(work);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        FirebaseRecyclerOptions<ServiceRVModel> options =
                new FirebaseRecyclerOptions.Builder<ServiceRVModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Mechanical"),ServiceRVModel.class)
                        .build();

        rvAdapter = new serviceRVAdapter(options);
        recyclerView.setAdapter(rvAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query.toUpperCase());
                //change(work);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query.toUpperCase());
                //change(work);
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
        FirebaseRecyclerOptions<ServiceRVModel> options =
                new FirebaseRecyclerOptions.Builder<ServiceRVModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Mechanical").orderByChild("Area").startAt(str).endAt(str+"~"),ServiceRVModel.class)
                        .build();

        rvAdapter = new serviceRVAdapter(options);
        rvAdapter.startListening();

        recyclerView.setAdapter(rvAdapter);

    }

//    public void change(String str) {
//        FirebaseRecyclerOptions<ServiceRVModel> options1 =
//                new FirebaseRecyclerOptions.Builder<ServiceRVModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Service").orderByChild("jobName").startAt(str).endAt(str + "~"), ServiceRVModel.class)
//                        .build();
//        rvAdapter = new serviceRVAdapter(options1);
//        rvAdapter.startListening();
//
//        recyclerView.setAdapter(rvAdapter);
//    }
}