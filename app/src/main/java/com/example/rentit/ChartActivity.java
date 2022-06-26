package com.example.rentit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChartActivity extends AppCompatActivity {

    TextView clientName;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ImageButton btn_send;
    EditText text_send;
    CircleImageView profile_image;
    //String PhoneNo;
    String UserId;
    MessageAdapter messageAdapter;
    List<ChartRvModel> mchart;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();
        UserId = intent.getStringExtra("UserId");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        clientName = findViewById(R.id.username);
        text_send = findViewById(R.id.text_send);
        profile_image = findViewById(R.id.profile_image);
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
                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+PersonInfoRvModel.getUserIMage());
                try {
                    File file = File.createTempFile("randomKey","");
                    storageReference1.getFile(file)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(ChartActivity.this, "please wait", Toast.LENGTH_SHORT).show();
                                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                    profile_image.setImageBitmap(bitmap);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Toast.makeText(holder.img.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }


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


        reference.child("Chats").push().setValue(hashMap);

        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(firebaseUser.getUid())
                .child(UserId);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    chatRef.child("id").setValue(UserId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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