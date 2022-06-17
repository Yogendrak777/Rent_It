package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class BikeInfo2 extends AppCompatActivity {

    String MILLAGE,CARAGE,FASTTAG,BODYTYPE;
    Button NextButton,PrevButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_info2);

        Spinner Millage = (Spinner) findViewById(R.id.MillageSpinner);
        Spinner CarAge = (Spinner)findViewById(R.id.AgeSpinner);
        Spinner FastTag = (Spinner)findViewById(R.id.FastTagSpinner);
        Spinner BodyType = (Spinner)findViewById(R.id.BodyTypeSpinner);

        NextButton = (Button)findViewById(R.id.NextButton1);
        PrevButton = (Button)findViewById(R.id.PrevButton);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Millage, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Millage.setAdapter(adapter2);
        Millage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MILLAGE = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.CarAge, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CarAge.setAdapter(adapter5);
        CarAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CARAGE = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.FastTag, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FastTag.setAdapter(adapter7);
        FastTag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FASTTAG = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.BikeType, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BodyType.setAdapter(adapter8);
        BodyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BODYTYPE = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Address = intent.getStringExtra("Address");
        String Advance = intent.getStringExtra("Advance");
        String Rent = intent.getStringExtra("Rent");
        String Model = intent.getStringExtra("Model");
        String Area = intent.getStringExtra("Area");
        String Desc = intent.getStringExtra("Desc");
        String Service = intent.getStringExtra("Service");


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BikeInfo2.this,BikeInfo3.class);
                intent1.putExtra("Name",Name);
                intent1.putExtra("Address",Address);
                intent1.putExtra("Advance",Advance);
                intent1.putExtra("Rent",Rent);
                intent1.putExtra("Model",Model);
                intent1.putExtra("Area",Area);
                intent1.putExtra("MILLAGE",MILLAGE);
                intent1.putExtra("CARAGE",CARAGE);
                intent1.putExtra("FASTTAG",FASTTAG);
                intent1.putExtra("BODYTYPE",BODYTYPE);
                intent1.putExtra("Desc",Desc);
                intent1.putExtra("Service",Service);

                startActivity(intent1);

            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BikeInfo2.this,BikeInfo1.class));
            }
        });

    }
}