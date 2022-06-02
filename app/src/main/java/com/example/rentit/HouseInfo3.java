package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HouseInfo3 extends AppCompatActivity {

    ImageView Img1,Img2,Img3,Img4,ImgBill;
    Button NextButton,PrevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info3);

        Img1 = (ImageView)findViewById(R.id.objImg1);
        Img2 = (ImageView)findViewById(R.id.objImg2);
        Img3 = (ImageView)findViewById(R.id.objImg3);
        Img4 = (ImageView)findViewById(R.id.objImg4);
        ImgBill = (ImageView)findViewById(R.id.objImgBill);

        NextButton = (Button)findViewById(R.id.NextButton2);
        PrevButton = (Button)findViewById(R.id.PrevButton1);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Address = intent.getStringExtra("Address");
        String Advance = intent.getStringExtra("Advance");
        String Rent = intent.getStringExtra("Rent");
        String SqFt = intent.getStringExtra("SqFt");
        String Area = intent.getStringExtra("Area");
        String parking = intent.getStringExtra("parking");
        String BHK = intent.getStringExtra("BHK");
        String WATER = intent.getStringExtra("WATER");
        String FLOOR = intent.getStringExtra("FLOOR");
        String FACING = intent.getStringExtra("FACING");
        String BATHROOM = intent.getStringExtra("BATHROOM");
        String FAMILY = intent.getStringExtra("FAMILY");
        String FOOD = intent.getStringExtra("FOOD");
        String PET = intent.getStringExtra("PET");

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HouseInfo3.this,HouseInfo4.class);
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
                intent1.putExtra("ImgUrl1",String.valueOf(Img1));
                intent1.putExtra("ImgUrl2",String.valueOf(Img2));
                intent1.putExtra("ImgUrl3",String.valueOf(Img3));
                intent1.putExtra("ImgUrl4",String.valueOf(Img4));
                intent1.putExtra("ImgUrlBill",String.valueOf(ImgBill));

                startActivity(intent1);

            }
        });
        PrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseInfo3.this,HouseInfo2.class));
            }
        });
    }
}
