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

public class CartPage extends AppCompatActivity {

    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6, recyclerView7;
    RvAdapter1 rvAdapter1;
    carRvAdapter CarRvAdapter;
    BikeRvAdapter bikeRvAdapter;
    CameraRvAdapter cameraRvAdapter;
    ClothRvAdapter clothRvAdapter;
    SpeakerRvAdapter speakerRvAdapter;
    SportsRvAdapter sportsRvAdapter;
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
        recyclerView3 = (RecyclerView) findViewById(R.id.Rv3);
        recyclerView4 = (RecyclerView) findViewById(R.id.Rv4);
        recyclerView5 = (RecyclerView) findViewById(R.id.Rv5);
        recyclerView6 = (RecyclerView) findViewById(R.id.Rv6);
        recyclerView7 = (RecyclerView) findViewById(R.id.Rv7);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));
        recyclerView5.setLayoutManager(new LinearLayoutManager(this));
        recyclerView6.setLayoutManager(new LinearLayoutManager(this));
        recyclerView7.setLayoutManager(new LinearLayoutManager(this));
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

        FirebaseRecyclerOptions<bikeRvModel> options3 =
                new FirebaseRecyclerOptions.Builder<bikeRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Bike"),bikeRvModel.class)
                        .build();

        bikeRvAdapter = new BikeRvAdapter(options3);
        recyclerView3.setAdapter(bikeRvAdapter);

        FirebaseRecyclerOptions<CameraRvModel> options4 =
                new FirebaseRecyclerOptions.Builder<CameraRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("camera"),CameraRvModel.class)
                        .build();

        cameraRvAdapter = new CameraRvAdapter(options4);
        recyclerView4.setAdapter(cameraRvAdapter);

        FirebaseRecyclerOptions<ClothRvModel> options5 =
                new FirebaseRecyclerOptions.Builder<ClothRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("clothes"),ClothRvModel.class)
                        .build();

        clothRvAdapter = new ClothRvAdapter(options5);
        recyclerView5.setAdapter(clothRvAdapter);

        FirebaseRecyclerOptions<SpeakerRvModel> options6 =
                new FirebaseRecyclerOptions.Builder<SpeakerRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("speakers"),SpeakerRvModel.class)
                        .build();

        speakerRvAdapter = new SpeakerRvAdapter(options6);
        recyclerView6.setAdapter(speakerRvAdapter);

        FirebaseRecyclerOptions<SportRvModel> options7 =
                new FirebaseRecyclerOptions.Builder<SportRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("sports"),SportRvModel.class)
                        .build();

        sportsRvAdapter = new SportsRvAdapter(options7);
        recyclerView7.setAdapter(sportsRvAdapter);
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

        FirebaseRecyclerOptions<bikeRvModel> options3 =
                new FirebaseRecyclerOptions.Builder<bikeRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("Bike").orderByChild("bikeArea").startAt(str).endAt(str+"~"),bikeRvModel.class)
                        .build();

        bikeRvAdapter = new BikeRvAdapter(options3);
        bikeRvAdapter.startListening();

        recyclerView3.setAdapter(bikeRvAdapter);

        FirebaseRecyclerOptions<CameraRvModel> options4 =
                new FirebaseRecyclerOptions.Builder<CameraRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("camera").orderByChild("cameraArea").startAt(str).endAt(str+"~"),CameraRvModel.class)
                        .build();

        cameraRvAdapter = new CameraRvAdapter(options4);
        cameraRvAdapter.startListening();

        recyclerView4.setAdapter(cameraRvAdapter);

        FirebaseRecyclerOptions<ClothRvModel> options5 =
                new FirebaseRecyclerOptions.Builder<ClothRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("clothes").orderByChild("clothArea").startAt(str).endAt(str+"~"),ClothRvModel.class)
                        .build();

        clothRvAdapter = new ClothRvAdapter(options5);
        clothRvAdapter.startListening();

        recyclerView5.setAdapter(clothRvAdapter);

        FirebaseRecyclerOptions<SpeakerRvModel> options6 =
                new FirebaseRecyclerOptions.Builder<SpeakerRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("speakers").orderByChild("speArea").startAt(str).endAt(str+"~"),SpeakerRvModel.class)
                        .build();

        speakerRvAdapter = new SpeakerRvAdapter(options6);
        speakerRvAdapter.startListening();

        recyclerView6.setAdapter(speakerRvAdapter);

        FirebaseRecyclerOptions<SportRvModel> options7 =
                new FirebaseRecyclerOptions.Builder<SportRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("sports").orderByChild("spoArea").startAt(str).endAt(str+"~"),SportRvModel.class)
                        .build();

        sportsRvAdapter = new SportsRvAdapter(options7);
        sportsRvAdapter.startListening();

        recyclerView7.setAdapter(sportsRvAdapter);

    }

}