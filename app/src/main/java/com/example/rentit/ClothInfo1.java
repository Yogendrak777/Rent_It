package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ClothInfo1 extends AppCompatActivity {
    EditText ClothName, ClothAddress, ClothBrand, ClothAdvance,ClothRent,ClothArea, ClothColor,ClothType, ClothDesc;
    Spinner Size, Meterial,Fit;
    String SIZE,METERIAL,FIT;
    Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_info1);

        ClothName = findViewById(R.id.ClothName);
        ClothAddress = findViewById(R.id.ClothAddress);
        ClothBrand = findViewById(R.id.ClothBrand);
        ClothAdvance = findViewById(R.id.ClothAdvance);
        ClothRent = findViewById(R.id.ClothRent);
        ClothArea = findViewById(R.id.ClothArea);
        ClothColor = findViewById(R.id.ClothColor);
        ClothType = findViewById(R.id.ClothType);
        ClothDesc = findViewById(R.id.ClothDesc);

        Size = findViewById(R.id.ColorSizeSpinner);
        Meterial = findViewById(R.id.ColorMeteralSpinner);
        Fit = findViewById(R.id.ClothFitSpinner);

        Next = findViewById(R.id.NextButton);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.ClothSize, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Size.setAdapter(adapter2);
        Size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SIZE = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.ClothMeterial, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Meterial.setAdapter(adapter5);
        Meterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                METERIAL = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.ClothFit, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Fit.setAdapter(adapter7);
        Fit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FIT = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothInfo1.this,ClothInfo2.class);
                intent.putExtra("ClothName",ClothName.getText().toString().trim());
                intent.putExtra("ClothAddress",ClothAddress.getText().toString().trim());
                intent.putExtra("ClothBrand",ClothBrand.getText().toString().trim());
                intent.putExtra("ClothAdvance",ClothAdvance.getText().toString().trim());
                intent.putExtra("ClothRent",ClothRent.getText().toString().trim());
                intent.putExtra("ClothArea",ClothArea.getText().toString().trim());
                intent.putExtra("ClothColor",ClothColor.getText().toString().trim());
                intent.putExtra("ClothType",ClothType.getText().toString().trim());
                intent.putExtra("ClothDesc",ClothDesc.getText().toString().trim());
                intent.putExtra("ClothSize",SIZE);
                intent.putExtra("ClothMeterial",METERIAL);
                intent.putExtra("ClothFit",FIT);

                startActivity(intent);
            }
        });
    }
}