package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchItemPage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView cardCar,CardHome,CardBike,CardSpeaker,CardSports,CardCamera,CardCloths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item_page);

        bottomNavigationView = findViewById(R.id.buttomNavigationBar);
        cardCar = (CardView)findViewById(R.id.cardCar);
        CardHome = (CardView)findViewById(R.id.cardHome);
        CardBike = (CardView)findViewById(R.id.bike);
        CardCamera = (CardView)findViewById(R.id.camera);
        CardSpeaker = (CardView)findViewById(R.id.speakers);
        CardSports = (CardView)findViewById(R.id.sports);
        CardCloths = (CardView)findViewById(R.id.cloths);

        bottomNavigationView.setSelectedItemId(R.id.menuSearch);

        CardBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchItemPage.this,BikeRvContainer.class));
            }
        });

        CardCloths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchItemPage.this,ClothRvContiner.class));
            }
        });

        CardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchItemPage.this,CameraDetailsPage.class));
            }
        });

        CardSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchItemPage.this,SpeakerRvContainer.class));
            }
        });

        CardSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchItemPage.this,SportsRvContainer.class));
            }
        });


        cardCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchItemPage.this,carRvContainer.class));
            }
        });

        CardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchItemPage.this,displayContentPage.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(SearchItemPage.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menuAccount:
                        startActivity(new Intent(SearchItemPage.this,AccountPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuService:
                        startActivity(new Intent(SearchItemPage.this,ServicesAddPage.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}