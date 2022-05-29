package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CameraDetailsPage extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    CameraRvAdapter cameraRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_details_page);

        recyclerView = (RecyclerView)findViewById(R.id.Rv3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = (SearchView)findViewById(R.id.search3);

        FirebaseRecyclerOptions<CameraRvModel> options =
                new FirebaseRecyclerOptions.Builder<CameraRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("camera"),CameraRvModel.class)
                        .build();

        cameraRvAdapter = new CameraRvAdapter(options);
        recyclerView.setAdapter(cameraRvAdapter);


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
        cameraRvAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cameraRvAdapter.stopListening();
    }

    public void txtSearch (String str){
        FirebaseRecyclerOptions<CameraRvModel> options =
                new FirebaseRecyclerOptions.Builder<CameraRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("camera").orderByChild("cameraArea").startAt(str).endAt(str+"~"),CameraRvModel.class)
                        .build();

        cameraRvAdapter = new CameraRvAdapter(options);
        cameraRvAdapter.startListening();

        recyclerView.setAdapter(cameraRvAdapter);
    }
}