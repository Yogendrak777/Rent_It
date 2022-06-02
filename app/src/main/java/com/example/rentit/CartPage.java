package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartPage extends AppCompatActivity {

    RecyclerView recyclerView1, recyclerView2;
    RvAdapter1 rvAdapter1;
    carRvAdapter CarRvAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);


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
         email = user.getEmail();


        FirebaseRecyclerOptions<houseRvModel> options =
                new FirebaseRecyclerOptions.Builder<houseRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("house"), houseRvModel.class)
                        .build();



        rvAdapter1 = new RvAdapter1(options);
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("house").orderByChild("OwnerEmail").startAt(str).endAt(str + "~"), houseRvModel.class)
                        .build();

        rvAdapter1 = new RvAdapter1(options);
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