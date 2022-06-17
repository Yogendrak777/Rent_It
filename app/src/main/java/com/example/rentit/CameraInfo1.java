package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CameraInfo1 extends AppCompatActivity {

    EditText Name,Address,Advance,Rent,Area,Desc,model;

    Button NextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_info1);

        Name = (EditText)findViewById(R.id.Name);
        Address = (EditText)findViewById(R.id.Address);
        Advance = (EditText)findViewById(R.id.Advance);
        Rent = (EditText)findViewById(R.id.Rent);
        Area = (EditText)findViewById(R.id.Area);
        NextButton = (Button)findViewById(R.id.NextButton);
        Desc = (EditText)findViewById(R.id.CarDesc);
        model = (EditText)findViewById(R.id.modelName);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CameraInfo1.this, CameraInfo2.class);
                intent.putExtra("Name",Name.getText().toString().trim());
                intent.putExtra("Address",Address.getText().toString().trim());
                intent.putExtra("Advance",Advance.getText().toString().trim());
                intent.putExtra("Rent",Rent.getText().toString().trim());
                intent.putExtra("Area",Area.getText().toString().trim());
                intent.putExtra("Desc",Desc.getText().toString().trim());
                intent.putExtra("Model",model.getText().toString().trim());


                startActivity(intent);


            }
        });
    }
}