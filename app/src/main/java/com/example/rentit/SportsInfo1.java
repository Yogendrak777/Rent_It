package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SportsInfo1 extends AppCompatActivity {
    EditText SportAddress, SportBrand, SportColor, SportMeteral, SportName, SportAdvance, SportPack, SportRent, SportIdel, SportArea, SportDesc;
    Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_info1);

        SportAddress = findViewById(R.id.SportAddress);
        SportBrand = findViewById(R.id.SportBrand);
        SportColor = findViewById(R.id.SportColor);
        SportMeteral = findViewById(R.id.SportMeteral);
        SportName = findViewById(R.id.SportName);
        SportAdvance = findViewById(R.id.SportAdvance);
        SportPack = findViewById(R.id.SportPack);
        SportRent = findViewById(R.id.SportRent);
        SportIdel = findViewById(R.id.SportIdel);
        SportArea = findViewById(R.id.SportArea);
        SportDesc = findViewById(R.id.SportDesc);
        Next = findViewById(R.id.NextButton);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SportsInfo1.this,SportsInfo2.class);
                intent.putExtra("SportAddress",SportAddress.getText().toString().trim());
                intent.putExtra("SportBrand",SportBrand.getText().toString().trim());
                intent.putExtra("SportColor",SportColor.getText().toString().trim());
                intent.putExtra("SportMeteral",SportMeteral.getText().toString().trim());
                intent.putExtra("SportName",SportName.getText().toString().trim());
                intent.putExtra("SportAdvance",SportAdvance.getText().toString().trim());
                intent.putExtra("SportPack",SportPack.getText().toString().trim());
                intent.putExtra("SportRent",SportRent.getText().toString().trim());
                intent.putExtra("SportIdel",SportIdel.getText().toString().trim());
                intent.putExtra("SportArea",SportArea.getText().toString().trim());
                intent.putExtra("SportDesc",SportDesc.getText().toString().trim());

                startActivity(intent);

            }
        });


    }
}