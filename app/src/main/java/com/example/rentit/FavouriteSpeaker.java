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

public class FavouriteSpeaker extends AppCompatActivity {
    RecyclerView recyclerView1;
    SpeakerRvAdapter rvAdapter1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_speaker);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView1 = (RecyclerView) findViewById(R.id.Rrv1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        email = user.getUid();

        FirebaseRecyclerOptions<SpeakerRvModel> options =
                new FirebaseRecyclerOptions.Builder<SpeakerRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteSpeaker"), SpeakerRvModel.class)
                        .build();

        rvAdapter1 = new SpeakerRvAdapter(options);
        recyclerView1.setAdapter(rvAdapter1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        txtSearch11(email);
        rvAdapter1.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        rvAdapter1.stopListening();
    }
    private void txtSearch11(String str) {
        FirebaseRecyclerOptions<SpeakerRvModel> options =
                new FirebaseRecyclerOptions.Builder<SpeakerRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteSpeaker").orderByChild("currentUsers").startAt(str).endAt(str + "~"), SpeakerRvModel.class)
                        .build();

        rvAdapter1 = new SpeakerRvAdapter(options);
        rvAdapter1.startListening();
        recyclerView1.setAdapter(rvAdapter1);
    }
}