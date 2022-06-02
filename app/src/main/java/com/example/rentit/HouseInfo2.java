package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class HouseInfo2 extends AppCompatActivity {

    String parking,BHK,WATER,FLOOR,FACING,BATHROOMS,FAMILY,FOOD,PET;
    Button NextButton,PrevButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info2);

        Spinner park = (Spinner) findViewById(R.id.ParkingSpinner);
        Spinner bhk = (Spinner) findViewById(R.id.BhkSpinner);
        Spinner water = (Spinner) findViewById(R.id.waterSpinner);
        Spinner floor = (Spinner) findViewById(R.id.FloorSpinner);
        Spinner Facing = (Spinner) findViewById(R.id.FacingSpinner);
        Spinner Bathrooms = (Spinner)findViewById(R.id.BathroomsSpinner);
        Spinner Family = (Spinner)findViewById(R.id.FamilySpinner);
        Spinner Food = (Spinner)findViewById(R.id.FoodTypeSpinner);
        Spinner Pet = (Spinner)findViewById(R.id.PetsSpinner);

        NextButton = (Button)findViewById(R.id.NextButton1);
        PrevButton = (Button)findViewById(R.id.PrevButton);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.parking, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        park.setAdapter(adapter);
        park.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                parking = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, parking, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.bhk, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bhk.setAdapter(adapter1);
        bhk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BHK = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, BHK, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.water, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        water.setAdapter(adapter2);
        water.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                WATER = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, WATER, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.Floor, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floor.setAdapter(adapter3);
        floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FLOOR = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, FLOOR, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.Facing, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Facing.setAdapter(adapter4);
        Facing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FACING = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, FACING, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.bathrooms, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bathrooms.setAdapter(adapter5);
        Bathrooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BATHROOMS = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, BATHROOMS, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.Family, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Family.setAdapter(adapter6);
        Family.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FAMILY = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, FAMILY, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.Food, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Food.setAdapter(adapter7);
        Food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FOOD = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, FOOD, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.Pet, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Pet.setAdapter(adapter8);
        Pet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PET = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo2.this, PET, Toast.LENGTH_SHORT).show();
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
        String SqFt = intent.getStringExtra("SqFt");
        String Area = intent.getStringExtra("Area");
        String Desc = intent.getStringExtra("Desc");


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HouseInfo2.this,HouseInfo3.class);
                intent1.putExtra("Name",Name);
                intent1.putExtra("Address",Address);
                intent1.putExtra("Advance",Advance);
                intent1.putExtra("Rent",Rent);
                intent1.putExtra("SqFt",SqFt);
                intent1.putExtra("Area",Area);
                intent1.putExtra("parking",parking);
                intent1.putExtra("BHK",BHK);
                intent1.putExtra("WATER",WATER);
                intent1.putExtra("FLOOR",FLOOR);
                intent1.putExtra("FACING",FACING);
                intent1.putExtra("BATHROOM",BATHROOMS);
                intent1.putExtra("FAMILY",FAMILY);
                intent1.putExtra("FOOD",FOOD);
                intent1.putExtra("PET",PET);
                intent1.putExtra("Desc",Desc);

                startActivity(intent1);

            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseInfo2.this,HouseInfo1.class));
            }
        });
    }

}