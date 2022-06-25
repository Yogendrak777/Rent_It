package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ServiceAddPage extends AppCompatActivity {

    CardView Mechanical, Plumber, Electrical, Painter, Construction, HouseHold, Beautial, InterDesign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add_page);

        Mechanical = findViewById(R.id.cardMechanical);
        Plumber = findViewById(R.id.cardPlumber);
        Electrical = findViewById(R.id.CardElectrical);
        Painter = findViewById(R.id.CardPaints);
        Construction = findViewById(R.id.CardConstruction);
        HouseHold = findViewById(R.id.CardHouseHold);
        Beautial = findViewById(R.id.CardBeatuician);
        InterDesign = findViewById(R.id.CardInterior);

        Mechanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,MechanicalAddPage.class));
            }
        });

        Plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,PlumberPage.class));
            }
        });

        Electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,ElectricalPage.class));
            }
        });

        Painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,PaintsPages.class));
            }
        });

        Construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,ConstructionWorksPage.class));
            }
        });

        HouseHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,householdworksPage.class));
            }
        });

        Beautial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,BeatuicianPage.class));
            }
        });

        InterDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceAddPage.this,InteriorDesignersPage.class));
            }
        });
    }
}