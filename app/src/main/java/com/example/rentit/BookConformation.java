package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookConformation extends AppCompatActivity {

    String PhoneNo;
    TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_conformation);

        Intent intent = getIntent();
        PhoneNo = intent.getStringExtra("PhoneNo");
        num = findViewById(R.id.num);

        num.setText(PhoneNo);

    }
}