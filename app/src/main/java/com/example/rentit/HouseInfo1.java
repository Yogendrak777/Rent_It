package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HouseInfo1 extends AppCompatActivity {

    EditText Name,Address,Advance,Rent,SqFt,Area,Desc;

    Button NextButton;

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
        NextButton = (Button)findViewById(R.id.NextButton);
        Desc = (EditText)findViewById(R.id.houseDesc);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HouseInfo1.this, HouseInfo2.class);
                intent.putExtra("Name",Name.getText().toString().trim());
                intent.putExtra("Address",Address.getText().toString().trim());
                intent.putExtra("Advance",Advance.getText().toString().trim());
                intent.putExtra("Rent",Rent.getText().toString().trim());
                intent.putExtra("SqFt",SqFt.getText().toString().trim());
                intent.putExtra("Area",Area.getText().toString().trim());
                intent.putExtra("Desc",Desc.getText().toString().trim());


                startActivity(intent);


            }
        });


    }
}