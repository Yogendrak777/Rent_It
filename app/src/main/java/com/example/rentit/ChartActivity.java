package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class ChartActivity extends AppCompatActivity {

    TextView clientName;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ImageButton btn_send;
    EditText text_send;
    //String PhoneNo;
    MessageAdapter messageAdapter;
    List<ChartRvModel> mchart;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();
        String UserId = intent.getStringExtra("UserId");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        clientName = findViewById(R.id.username);
        text_send = findViewById(R.id.text_send);
        btn_send = findViewById(R.id.btn_send);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("RentIt").child("RentBy").child(UserId);


        //clientName.setText(PhoneNo);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = text_send.getText().toString();
                if (!msg.equals("")){
                    sendMessage(firebaseUser.getUid(), UserId, msg);
                } else {
                    Toast.makeText(ChartActivity.this, "You can't send empty message", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                personInfoRvModel PersonInfoRvModel = snapshot.getValue(personInfoRvModel.class);
                assert PersonInfoRvModel != null;
                clientName.setText(PersonInfoRvModel.getUserNameDb());
                readMessages(firebaseUser.getUid(),UserId);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(String sender, final String receiver, String message) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        //hashMap.put("isseen", false);

        reference.child("Chats").push().setValue(hashMap);

    }
    private void readMessages(String myid,String phoneNo1){
        mchart = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mchart.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    ChartRvModel chartRvModel = snapshot1.getValue(ChartRvModel.class);
                    if(chartRvModel.getReceiver().equals(myid)&& chartRvModel.getSender().equals(phoneNo1)||
                    chartRvModel.getReceiver().equals(phoneNo1)&& chartRvModel.getSender().equals(myid)){
                        mchart.add(chartRvModel);

                    }
                    messageAdapter = new MessageAdapter(ChartActivity.this,mchart);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}