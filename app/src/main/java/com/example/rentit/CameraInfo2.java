package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CameraInfo2 extends AppCompatActivity {
    String TRASNMISSION,FUEL,MILLAGE,AIRBAG,SEATS,CARAGE;
    Button NextButton,PrevButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_info2);

        Spinner Trans = (Spinner) findViewById(R.id.GearSpinner);
        Spinner Fuel = (Spinner) findViewById(R.id.FuelSpinner);
        Spinner Millage = (Spinner) findViewById(R.id.MillageSpinner);
        Spinner AirBag = (Spinner) findViewById(R.id.AirBagSpinner);
        Spinner Seats = (Spinner) findViewById(R.id.SeatsSpinner);
        Spinner CarAge = (Spinner)findViewById(R.id.AgeSpinner);


        NextButton = (Button)findViewById(R.id.NextButton1);
        PrevButton = (Button)findViewById(R.id.PrevButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.CameraFlash, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Trans.setAdapter(adapter);
        Trans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TRASNMISSION = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Led, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Fuel.setAdapter(adapter1);
        Fuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FUEL = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Focus, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.MovieFormat, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AirBag.setAdapter(adapter3);
        AirBag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AIRBAG = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.CameraType, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Seats.setAdapter(adapter4);
        Seats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SEATS = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.Zoom, android.R.layout.simple_spinner_item);
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
                Intent intent1 = new Intent(CameraInfo2.this,CameraInfo3.class);
                intent1.putExtra("Name",Name);
                intent1.putExtra("Address",Address);
                intent1.putExtra("Advance",Advance);
                intent1.putExtra("Rent",Rent);
                intent1.putExtra("Model",Model);
                intent1.putExtra("Area",Area);
                intent1.putExtra("TRASNMISSION",TRASNMISSION);
                intent1.putExtra("FUEL",FUEL);
                intent1.putExtra("MILLAGE",MILLAGE);
                intent1.putExtra("AIRBAG",AIRBAG);
                intent1.putExtra("SEATS",SEATS);
                intent1.putExtra("CARAGE",CARAGE);
                intent1.putExtra("Desc",Desc);
                intent1.putExtra("Service",Service);

                startActivity(intent1);

            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CameraInfo2.this,CameraInfo1.class));
            }
        });

    }
}