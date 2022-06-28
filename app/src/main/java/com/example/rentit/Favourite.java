package com.example.rentit;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

    RecyclerView recyclerView1;
    RvAdapter rvAdapter1;
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
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        email = user.getUid();

        ImageButton delete = findViewById(R.id.delete);

        delete.setVisibility(View.VISIBLE);

        FirebaseRecyclerOptions<houseRvModel> options =
                new FirebaseRecyclerOptions.Builder<houseRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("house"), houseRvModel.class)
                        .build();


        rvAdapter1 = new RvAdapter(options);
        recyclerView1.setAdapter(rvAdapter1);

//        FirebaseRecyclerOptions<carRvModel> options1 =
//                new FirebaseRecyclerOptions.Builder<carRvModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("car"), carRvModel.class)
//                        .build();
//
//        CarRvAdapter = new carRvAdapter(options1);
//        recyclerView2.setAdapter(CarRvAdapter);
//
//
//        FirebaseRecyclerOptions<bikeRvModel> options2 =
//                new FirebaseRecyclerOptions.Builder<bikeRvModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Bike"),bikeRvModel.class)
//                        .build();
//
//        bikeRvAdapter = new BikeRvAdapter(options2);
//        recyclerView3.setAdapter(bikeRvAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        txtSearch(email);
        rvAdapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        rvAdapter1.stopListening();

    }

    public void txtSearch(String str) {
        FirebaseRecyclerOptions<houseRvModel> options =
                new FirebaseRecyclerOptions.Builder<houseRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Favourite").orderByChild("currentUsers").startAt(str).endAt(str + "~"), houseRvModel.class)
                        .build();

        rvAdapter1 = new RvAdapter(options);
        rvAdapter1.startListening();

        recyclerView1.setAdapter(rvAdapter1);

//        FirebaseRecyclerOptions<carRvModel> options2 =
//                new FirebaseRecyclerOptions.Builder<carRvModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("car").orderByChild("OwnerEmail").startAt(str).endAt(str+"~"),carRvModel.class)
//                        .build();
//
//        CarRvAdapter = new carRvAdapter(options2);
//        CarRvAdapter.startListening();
//
//        recyclerView2.setAdapter(CarRvAdapter);

    }

}