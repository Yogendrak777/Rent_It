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

public class serviceInfo1 extends AppCompatActivity {

    EditText Name,Address,Lang,Rent,Area,Desc,Exp,ArddharNum,workTime;
    String job;

    Button NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info1);

        Spinner bhk = (Spinner) findViewById(R.id.BhkSpinner);

        Name = findViewById(R.id.Name);
        Address = findViewById(R.id.Address);
        Exp = findViewById(R.id.Exp);
        Area = findViewById(R.id.Area);
        workTime = findViewById(R.id.workingTime);
        Lang= findViewById(R.id.Lang);
        ArddharNum = findViewById(R.id.Ardhar);
        Rent = findViewById(R.id.Charge);
        Desc = findViewById(R.id.Desc);
        NextButton = findViewById(R.id.NextButton);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Services, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bhk.setAdapter(adapter1);
        bhk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                job = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(serviceInfo1.this, serviceInfo2.class);
                intent.putExtra("Name",Name.getText().toString().trim());
                intent.putExtra("Address",Address.getText().toString().trim());
                intent.putExtra("Exp",Exp.getText().toString().trim());
                intent.putExtra("Rent",Rent.getText().toString().trim());
                intent.putExtra("Area",Area.getText().toString().trim());
                intent.putExtra("Lang",Lang.getText().toString().trim());
                intent.putExtra("Desc",Desc.getText().toString().trim());
                intent.putExtra("job",job);
                intent.putExtra("ArdharNum",ArddharNum.getText().toString().trim());
                intent.putExtra("workTime",workTime.getText().toString().trim());

                startActivity(intent);


            }
        });

    }
}