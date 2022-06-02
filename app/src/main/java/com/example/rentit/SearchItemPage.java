package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchItemPage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView cardCar,CardHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item_page);

        bottomNavigationView = findViewById(R.id.buttomNavigationBar);
        cardCar = (CardView)findViewById(R.id.cardCar);
        CardHome = (CardView)findViewById(R.id.cardHome);

        bottomNavigationView.setSelectedItemId(R.id.menuSearch);

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
                }
                return false;
            }
        });

    }
}