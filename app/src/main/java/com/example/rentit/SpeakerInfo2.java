package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SpeakerInfo2 extends AppCompatActivity {
    String BLUETOOTH, DISPLAY, MEMORY, POWER, SPETYPE;
    Spinner Bluetooth,Display,Memory,Power,SpeType;
    Button Next,Previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_info2);
        Bluetooth = findViewById(R.id.SpeBluetoothSpinner);
        Display = findViewById(R.id.SpeDisplaySpinner);
        Memory = findViewById(R.id.SpeMemorySpinner);
        Power = findViewById(R.id.SpePowerSpinner);
        SpeType = findViewById(R.id.SpeTypeSpinner);
        Next = findViewById(R.id.NextButton1);
        Previous = findViewById(R.id.PrevButton);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.SpeakerBluetooth, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bluetooth.setAdapter(adapter2);
        Bluetooth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BLUETOOTH = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.SpeakerDisplay, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Display.setAdapter(adapter5);
        Display.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DISPLAY = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.SpeakerPower, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Power.setAdapter(adapter7);
        Power.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                POWER = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.SpeakerMemory, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Memory.setAdapter(adapter8);
        Memory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MEMORY = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,R.array.SpeakerType, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpeType.setAdapter(adapter9);
        SpeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SPETYPE = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Intent intent = getIntent();
        String SpeAddress = intent.getStringExtra("SpeAddress");
        String SpeModel = intent.getStringExtra("SpeModel");
        String SpeRent = intent.getStringExtra("SpeRent");
        String SpeAdvance = intent.getStringExtra("SpeAdvance");
        String SpeArea = intent.getStringExtra("SpeArea");
        String SpeDesc = intent.getStringExtra("SpeDesc");


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeakerInfo2.this,SpeakerInfo3.class);
                intent.putExtra("SpeAddress",SpeAddress);
                intent.putExtra("SpeModel",SpeModel);
                intent.putExtra("SpeRent",SpeRent);
                intent.putExtra("SpeAdvance",SpeAdvance);
                intent.putExtra("SpeArea",SpeArea);
                intent.putExtra("SpeDesc",SpeDesc);
                intent.putExtra("BLUETOOTH",BLUETOOTH);
                intent.putExtra("DISPLAY",DISPLAY);
                intent.putExtra("MEMORY",MEMORY);
                intent.putExtra("POWER",POWER);
                intent.putExtra("SPETYPE",SPETYPE);
                startActivity(intent);
            }
        });

        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SpeakerInfo2.this,SpeakerInfo1.class));
            }
        });


    }
}