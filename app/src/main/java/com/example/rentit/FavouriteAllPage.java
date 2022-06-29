package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavouriteAllPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CardView cardCar,CardHome,CardBike,CardSpeaker,CardSports,CardCamera,CardCloths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_all_page);
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
                startActivity(new Intent(FavouriteAllPage.this,FavouriteBike.class));
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
                startActivity(new Intent(FavouriteAllPage.this,FavouriteCamera.class));
            }
        });

        CardSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouriteAllPage.this,FavouriteSpeaker.class));
            }
        });

        CardSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouriteAllPage.this,SportsInfo1.class));
            }
        });


        cardCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouriteAllPage.this,FavouriteCar.class));
            }
        });

        CardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouriteAllPage.this,Favourite.class));
            }
        });
    }
}