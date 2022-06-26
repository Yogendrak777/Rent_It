package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddItemPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CardView cardCar,CardHome,CardBike,CardSpeaker,CardSports,CardCamera,CardCloths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_page);

        cardCar = (CardView)findViewById(R.id.cardCar);
        CardHome = (CardView)findViewById(R.id.cardHome);
        CardBike = (CardView)findViewById(R.id.bike);
        CardCamera = (CardView)findViewById(R.id.camera);
        CardSpeaker = (CardView)findViewById(R.id.speakers);
        CardSports = (CardView)findViewById(R.id.sports);
        CardCloths = (CardView)findViewById(R.id.cloths);



        CardBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddItemPage.this,BikeInfo1.class));
            }
        });

        CardCloths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(AddItemPage.this,ClothRvContiner.class));
            }
        });

        CardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddItemPage.this,CameraInfo1.class));
            }
        });

        CardSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddItemPage.this,SpeakerInfo1.class));
            }
        });

        CardSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddItemPage.this,SportsInfo1.class));
            }
        });


        cardCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddItemPage.this,carInfo1.class));
            }
        });

        CardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddItemPage.this,HouseInfo1.class));
            }
        });
    }
}