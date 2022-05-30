package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SpeakerRvContainer extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    SpeakerRvAdapter speakerRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_rv_container);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView)findViewById(R.id.Rv4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = (SearchView)findViewById(R.id.search4);

        FirebaseRecyclerOptions<SpeakerRvModel> options =
                new FirebaseRecyclerOptions.Builder<SpeakerRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("speakers"),SpeakerRvModel.class)
                        .build();

        speakerRvAdapter = new SpeakerRvAdapter(options);
        recyclerView.setAdapter(speakerRvAdapter);


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
        speakerRvAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        speakerRvAdapter.stopListening();
    }

    public void txtSearch (String str){
        FirebaseRecyclerOptions<SpeakerRvModel> options =
                new FirebaseRecyclerOptions.Builder<SpeakerRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("speakers").orderByChild("speArea").startAt(str).endAt(str+"~"),SpeakerRvModel.class)
                        .build();

        speakerRvAdapter = new SpeakerRvAdapter(options);
        speakerRvAdapter.startListening();

        recyclerView.setAdapter(speakerRvAdapter);
    }
}