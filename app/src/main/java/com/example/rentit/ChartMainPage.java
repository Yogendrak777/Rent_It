package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChartMainPage extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button;
    UserAdapter userAdapter;
    List<personInfoRvModel> mpersonInfoRvModels;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    List<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_main_page);
        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChartMainPage.this,UesrChartActivity.class));
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    ChartRvModel chartRvModel = snapshot1.getValue(ChartRvModel.class);
                    if(chartRvModel.getSender().equals(firebaseUser.getUid())){
                        userList.add(chartRvModel.getReceiver());
                    }
                    if(chartRvModel.getReceiver().equals(firebaseUser.getUid())){
                        userList.add(chartRvModel.getSender());
                    }
                }
                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void readChats(){
        mpersonInfoRvModels = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("RentIt").child("RentBy");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mpersonInfoRvModels.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    personInfoRvModel personInfoRvModel1 = snapshot1.getValue(personInfoRvModel.class);

                    for (String id : userList){
                        if(personInfoRvModel1.getUserId().equals(id)){
                            if(mpersonInfoRvModels.size() != 0){
                                for(personInfoRvModel personInfoRvModel11 :mpersonInfoRvModels){
                                    if(!personInfoRvModel1.getUserId().equals(personInfoRvModel11.getUserId())){
                                        mpersonInfoRvModels.add(personInfoRvModel1);
                                    }
                                }
                            } else {
                                mpersonInfoRvModels.add(personInfoRvModel1);
                            }
                        }
                    }
                }
                userAdapter = new UserAdapter(getApplicationContext(),mpersonInfoRvModels,true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void status(String stasus){
        databaseReference = FirebaseDatabase.getInstance().getReference("RentIt").child("RentBy").child(firebaseUser.getUid());
        HashMap<String,Object> map = new HashMap<>();
        map.put("Status",stasus);
        databaseReference.updateChildren(map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }
}