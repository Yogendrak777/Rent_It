package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CardView myCart,AddItem,MyPay;
    TextView NameT,EmailT,PhoneNoT;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        bottomNavigationView = findViewById(R.id.buttomNavigationBar);
        myCart = (CardView)findViewById(R.id.MyCart);
        AddItem = (CardView)findViewById(R.id.RentItem);
        MyPay = (CardView)findViewById(R.id.Mypay);
        NameT = (TextView)findViewById(R.id.Name);
        EmailT = (TextView)findViewById(R.id.Email);
        PhoneNoT = (TextView)findViewById(R.id.PhoneNumber);

        bottomNavigationView.setSelectedItemId(R.id.menuAccount);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("userEmailDb").getValue().equals(user.getEmail())){
                        PhoneNoT.setText(ds.child("userPhoneDb").getValue(String.class));
                        EmailT.setText(ds.child("userEmailDb").getValue(String.class));
                        NameT.setText(ds.child("userNameDb").getValue(String.class));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MyPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountPage.this,PayMentPage.class));
            }
        });


        myCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountPage.this,CartPage.class));
            }
        });

        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountPage.this,HouseInfo1.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(AccountPage.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuSearch:
                        startActivity(new Intent(AccountPage.this,SearchItemPage.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}