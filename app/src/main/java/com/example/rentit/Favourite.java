package com.example.rentit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Favourite extends AppCompatActivity {

    RecyclerView recyclerView1, recyclerView2;
    RvAdapterFavourite rvAdapter1;
    carRvAdapter CarRvAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView1 = (RecyclerView) findViewById(R.id.Rv1);
        recyclerView2 = (RecyclerView) findViewById(R.id.Rv2);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        email = user.getEmail();




        FirebaseRecyclerOptions<houseRvModel> options =
                new FirebaseRecyclerOptions.Builder<houseRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Favourite"), houseRvModel.class)
                        .build();


        rvAdapter1 = new RvAdapterFavourite(options);
        recyclerView1.setAdapter(rvAdapter1);

        FirebaseRecyclerOptions<carRvModel> options1 =
                new FirebaseRecyclerOptions.Builder<carRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("car"), carRvModel.class)
                        .build();

        CarRvAdapter = new carRvAdapter(options1);
        recyclerView2.setAdapter(CarRvAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        txtSearch(email);
        CarRvAdapter.startListening();
        rvAdapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        CarRvAdapter.stopListening();
        rvAdapter1.stopListening();
    }

    public void txtSearch(String str) {
        FirebaseRecyclerOptions<houseRvModel> options =
                new FirebaseRecyclerOptions.Builder<houseRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Favourite").orderByChild("currentUsers").startAt(str).endAt(str + "~"), houseRvModel.class)
                        .build();

        rvAdapter1 = new RvAdapterFavourite(options);
        rvAdapter1.startListening();

        recyclerView1.setAdapter(rvAdapter1);

        FirebaseRecyclerOptions<carRvModel> options2 =
                new FirebaseRecyclerOptions.Builder<carRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("car").orderByChild("OwnerEmail").startAt(str).endAt(str+"~"),carRvModel.class)
                        .build();

        CarRvAdapter = new carRvAdapter(options2);
        CarRvAdapter.startListening();

        recyclerView2.setAdapter(CarRvAdapter);

    }

}