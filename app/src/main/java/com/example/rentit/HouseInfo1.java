package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class HouseInfo1 extends AppCompatActivity {

    EditText Name,Address,Advance,Rent,SqFt,Area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info1);

        Name = (EditText)findViewById(R.id.Name);
        Address = (EditText)findViewById(R.id.Address);
        Advance = (EditText)findViewById(R.id.Advance);
        Rent = (EditText)findViewById(R.id.Rent);
        SqFt = (EditText)findViewById(R.id.SqFt);
        Area = (EditText)findViewById(R.id.Area);

        Intent intent = new Intent(HouseInfo1.this, HouseInfo2.class);
        intent.putExtra("Name",Name.getText().toString().trim());
        intent.putExtra("Address",Address.getText().toString().trim());
        intent.putExtra("Advance",Advance.getText().toString().trim());
        intent.putExtra("Rent",Rent.getText().toString().trim());
        intent.putExtra("SqFt",SqFt.getText().toString().trim());
        intent.putExtra("Area",Area.getText().toString().trim());

        startActivity(intent);


    }
}