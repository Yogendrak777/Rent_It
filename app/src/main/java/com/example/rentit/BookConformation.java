package com.example.rentit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookConformation extends AppCompatActivity {

    String PhoneNo;
    TextView num;
    Button phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_conformation);

        Intent intent = getIntent();
        PhoneNo = intent.getStringExtra("PhoneNo");
        num = findViewById(R.id.num);
        num.setText(PhoneNo);
        phone = findViewById(R.id.button4);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dial = "tel:"+"9353437216";
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        });

    }
}