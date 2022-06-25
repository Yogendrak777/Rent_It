package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SpeakerInfo1 extends AppCompatActivity {

    EditText SpeAddress, SpeModel, SpeRent, SpeAdvance, SpeArea, SpeDec;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_info1);

        SpeAddress = findViewById(R.id.SpeAddress);
        SpeModel = findViewById(R.id.SpemodelName);
        SpeRent = findViewById(R.id.SpeRent);
        SpeAdvance = findViewById(R.id.SpeAdvance);
        SpeArea = findViewById(R.id.SpeArea);
        SpeDec = findViewById(R.id.SpeDesc);
        next = findViewById(R.id.NextButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeakerInfo1.this,SpeakerInfo2.class);
                intent.putExtra("SpeAddress",SpeAddress.getText().toString().trim());
                intent.putExtra("SpeModel",SpeModel.getText().toString().trim());
                intent.putExtra("SpeRent",SpeRent.getText().toString().trim());
                intent.putExtra("SpeAdvance",SpeAdvance.getText().toString().trim());
                intent.putExtra("SpeArea",SpeArea.getText().toString().trim());
                intent.putExtra("SpeDesc",SpeDec.getText().toString().trim());
                startActivity(intent);
            }
        });

    }
}