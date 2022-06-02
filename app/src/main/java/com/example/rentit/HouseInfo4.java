package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class HouseInfo4 extends AppCompatActivity {

    EditText MallName,SchoolName,HospitalName;
    Spinner MallDis,SchoolDis,HospitalDis,FuelDis,BusDis;
    String MallDistance,SchoolDistance,HospitalDistance,FuelDistance,BusDistance;
    Button NextButton,PrevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info4);

        MallName = (EditText)findViewById(R.id.MallName);
        SchoolName = (EditText)findViewById(R.id.SchoolName);
        HospitalName = (EditText)findViewById(R.id.HospitalName);

        MallDis = (Spinner)findViewById(R.id.MAllDisSpinner);
        SchoolDis = (Spinner)findViewById(R.id.SchoolDisSpinner);
        HospitalDis = (Spinner)findViewById(R.id.HospitalDisSpinner);
        FuelDis = (Spinner)findViewById(R.id.FuelDisSpinner);
        BusDis = (Spinner)findViewById(R.id.BusDisSpinner);

        NextButton = (Button)findViewById(R.id.NextButton3);
        PrevButton = (Button)findViewById(R.id.PrevButton2);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MallDis.setAdapter(adapter);
        MallDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MallDistance = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo4.this, MallDistance, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolDis.setAdapter(adapter1);
        SchoolDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SchoolDistance = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo4.this, SchoolDistance, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HospitalDis.setAdapter(adapter2);
        HospitalDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HospitalDistance = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo4.this, HospitalDistance, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FuelDis.setAdapter(adapter3);
        FuelDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FuelDistance = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo4.this, FuelDistance, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BusDis.setAdapter(adapter4);
        BusDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BusDistance = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HouseInfo4.this, BusDistance, Toast.LENGTH_SHORT).show();
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
        String Area= intent.getStringExtra("Area");
        String parking = intent.getStringExtra("parking");
        String BHK = intent.getStringExtra("BHK");
        String WATER = intent.getStringExtra("WATER");
        String FLOOR = intent.getStringExtra("FLOOR");
        String FACING = intent.getStringExtra("FACING");
        String BATHROOM = intent.getStringExtra("BATHROOM");
        String FAMILY = intent.getStringExtra("FAMILY");
        String FOOD = intent.getStringExtra("FOOD");
        String PET = intent.getStringExtra("PET");
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");
        String ImgUrlBill = intent.getStringExtra("ImgUrlBill");


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HouseInfo4.this,HouseInfo5.class);
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
                intent1.putExtra("FAMILY",FAMILY);
                intent1.putExtra("BATHROOM",BATHROOM);
                intent1.putExtra("FACING",FACING);
                intent1.putExtra("FOOD",FOOD);
                intent1.putExtra("PET",PET);
                intent1.putExtra("ImgUrl1",ImgUrl1);
                intent1.putExtra("ImgUrl2",ImgUrl2);
                intent1.putExtra("ImgUrl3",ImgUrl3);
                intent1.putExtra("ImgUrl4",ImgUrl4);
                intent1.putExtra("ImgUrlBill",ImgUrlBill);
                intent1.putExtra("MallDis",MallDistance);
                intent1.putExtra("SchoolDis",SchoolDistance);
                intent1.putExtra("HospitalDis",HospitalDistance);
                intent1.putExtra("FuelDis",FuelDistance);
                intent1.putExtra("BusDis",BusDistance);
                intent1.putExtra("MallName",MallName.getText().toString().trim());
                intent1.putExtra("SchoolName",SchoolName.getText().toString().trim());
                intent1.putExtra("HospitalName",HospitalName.getText().toString().trim());

                startActivity(intent1);

            }
        });

        PrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseInfo4.this,HouseInfo3.class));
            }
        });
    }
}
