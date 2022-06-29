package com.example.rentit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BookConformation extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    String PhoneNo;
    TextView num;
    Button phone;
    String num1 = "+919353437216";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_conformation);

        Intent intent = getIntent();
        PhoneNo = intent.getStringExtra("PhoneNo");
        num = findViewById(R.id.num);
        num.setText(PhoneNo);
        phone = findViewById(R.id.button4);

        //String num1 = "+919353437216";

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Intent intent1 = new Intent (Intent.ACTION_CALL);
//               intent1.setData(Uri.parse("tel:"+num));
//               startActivity(intent1);
                makePhoneCall();

            }
        });

    }
    private void makePhoneCall() {
        if (num1.length() > 0) {

            if (ContextCompat.checkSelfPermission(BookConformation.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(BookConformation.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + num1;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(BookConformation.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
