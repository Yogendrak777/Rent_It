package com.example.rentit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    BottomNavigationView bottomNavigationView;
    TextView userName;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (TextView)findViewById(R.id.displayUserName);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("userEmailDb").getValue().equals(user.getEmail())){
                        userName.setText(ds.child("userNameDb").getValue(String.class));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "error in userName", Toast.LENGTH_SHORT).show();

            }
        });

        databaseReference = firebaseDatabase.getReference("RentIt").child("BookList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("UserId").getValue().equals(user.getUid())) {

                        Toast.makeText(MainActivity.this, "wait it loading", Toast.LENGTH_SHORT).show();
                       final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("your Itenm is Booked");
                        builder.setMessage("someone booked ur item please chick here to see details");

                        builder.setPositiveButton("see Detail", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(MainActivity.this, BookContainer.class));
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        //builder.show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "sorry ", Toast.LENGTH_SHORT).show();
            }
        });


        progressBar = findViewById(R.id.progress);

        bottomNavigationView = findViewById(R.id.buttomNavigationBar);

        bottomNavigationView.setSelectedItemId(R.id.menuHome);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuSearch:
                        //MenuItem temp = findViewById(R.id.menuSearch);
                        startActivity(new Intent(MainActivity.this,SearchItemPage.class));
                        overridePendingTransition(0,0);
                        return true;
                        case R.id.menuAccount:
                        startActivity(new Intent(MainActivity.this,AccountPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuChart:
                        startActivity(new Intent(MainActivity.this,ChartMainPage.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

}