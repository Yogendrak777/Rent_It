package com.example.rentit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CarInfo4 extends AppCompatActivity {
    EditText Name1,Address1,Advance1,Rent1,Area1,Desc1,model,Service1;
    String TRASNMISSION1,FUEL1,MILLAGE1,AIRBAG1,SEATS1,CARAGE1,GEARBOX1,FASTTAG1,BODYTYPE1,OwnerNo;
    ImageView Img2,Img3,Img4,profileImg;

    Button upload;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info4);

        Name1 = (EditText)findViewById(R.id.Name);
        Address1 = (EditText)findViewById(R.id.Address);
        Advance1 = (EditText)findViewById(R.id.Advance);
        Rent1 = (EditText)findViewById(R.id.Rent);
        Area1 = (EditText)findViewById(R.id.Area);
        Desc1 = (EditText)findViewById(R.id.CarDesc);
        model = (EditText)findViewById(R.id.modelName);
        Service1 = findViewById(R.id.service);

        upload = (Button)findViewById(R.id.Upload);

        Spinner Trans = (Spinner) findViewById(R.id.GearSpinner);
        Spinner Fuel = (Spinner) findViewById(R.id.FuelSpinner);
        Spinner Millage = (Spinner) findViewById(R.id.MillageSpinner);
        Spinner AirBag = (Spinner) findViewById(R.id.AirBagSpinner);
        Spinner Seats = (Spinner) findViewById(R.id.SeatsSpinner);
        Spinner CarAge = (Spinner)findViewById(R.id.AgeSpinner);
        Spinner GearBox = (Spinner)findViewById(R.id.BoxSpeedSpinner);
        Spinner FastTag = (Spinner)findViewById(R.id.FastTagSpinner);
        Spinner BodyType = (Spinner)findViewById(R.id.BodyTypeSpinner);

        profileImg = findViewById(R.id.objImg1);
        Img2 = (ImageView)findViewById(R.id.objImg2);
        Img3 = (ImageView)findViewById(R.id.objImg3);
        Img4 = (ImageView)findViewById(R.id.objImg4);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Address = intent.getStringExtra("Address");
        String Advance = intent.getStringExtra("Advance");
        String Rent = intent.getStringExtra("Rent");
        String Model = intent.getStringExtra("Model");
        String Area = intent.getStringExtra("Area");
        String Desc = intent.getStringExtra("Desc");
        String TRASNMISSION = intent.getStringExtra("TRASNMISSION");
        String FUEL = intent.getStringExtra("FUEL");
        String MILLAGE = intent.getStringExtra("MILLAGE");
        String AIRBAG = intent.getStringExtra("AIRBAG");
        String SEATS = intent.getStringExtra("SEATS");
        String CARAGE = intent.getStringExtra("CARAGE");
        String GEARBOX = intent.getStringExtra("GEARBOX");
        String FASTTAG = intent.getStringExtra("FASTTAG");
        String BODYTYPE = intent.getStringExtra("BODYTYPE");
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");
        String Service = intent.getStringExtra("Service");

        Name1.setText(Name);
        Address1.setText(Address);
        Advance1.setText(Advance);
        Rent1.setText(Rent);
        Area1.setText(Area);
        Desc1.setText(Desc);
        model.setText(Model);
        Service1.setText(Service);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Transmission, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Trans.setAdapter(adapter);
        int Tra;
        Tra = adapter.getPosition(TRASNMISSION);
        Trans.setSelection(Tra);
        Trans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TRASNMISSION1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Fuel, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Fuel.setAdapter(adapter1);
        int Ful;
        Ful = adapter1.getPosition(FUEL);
        Fuel.setSelection(Ful);
        Fuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FUEL1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Millage, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Millage.setAdapter(adapter2);
        int mil;
        mil = adapter2.getPosition(MILLAGE);
        Millage.setSelection(mil);
        Millage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MILLAGE1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.AirBags, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AirBag.setAdapter(adapter3);
        int air;
        air = adapter3.getPosition(AIRBAG);
        AirBag.setSelection(air);
        AirBag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AIRBAG1 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.Seats, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Seats.setAdapter(adapter4);
        int set;
        set = adapter4.getPosition(SEATS);
        Seats.setSelection(set);
        Seats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SEATS1 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.CarAge, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CarAge.setAdapter(adapter5);
        int car;
        car = adapter5.getPosition(CARAGE);
        CarAge.setSelection(car);
        CarAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CARAGE1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.GearBox, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GearBox.setAdapter(adapter6);
        int gear;
        gear = adapter6.getPosition(GEARBOX);
        GearBox.setSelection(gear);
        GearBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GEARBOX1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.FastTag, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FastTag.setAdapter(adapter7);
        int fast;
        fast =  adapter7.getPosition(FASTTAG);
        FastTag.setSelection(fast);
        FastTag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FASTTAG1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.BodyType, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BodyType.setAdapter(adapter8);
        int body;
        body = adapter8.getPosition(BODYTYPE);
        BodyType.setSelection(body);

        BodyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BODYTYPE1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("userEmailDb").getValue().equals(user.getEmail())) {
                        OwnerNo = ds.child("userPhoneDb").getValue(String.class);

                        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + ImgUrl1);
                        StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + ImgUrl2);
                        StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + ImgUrl3);
                        StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + ImgUrl4);
                        try {
                            File file1 = File.createTempFile("randomKey", "");
                            File file2 = File.createTempFile("randomKey", "");
                            File file3 = File.createTempFile("randomKey", "");
                            File file4 = File.createTempFile("randomKey", "");
                            storageReference1.getFile(file1);
                            storageReference2.getFile(file2);
                            storageReference3.getFile(file3);
                            storageReference4.getFile(file4)
                                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(CarInfo4.this, "please wait", Toast.LENGTH_SHORT).show();
                                            Bitmap bitmap1 = BitmapFactory.decodeFile(file1.getAbsolutePath());
                                            Bitmap bitmap2 = BitmapFactory.decodeFile(file2.getAbsolutePath());
                                            Bitmap bitmap3 = BitmapFactory.decodeFile(file3.getAbsolutePath());
                                            Bitmap bitmap4 = BitmapFactory.decodeFile(file4.getAbsolutePath());
                                            profileImg.setImageBitmap(bitmap1);
                                            Img2.setImageBitmap(bitmap2);
                                            Img3.setImageBitmap(bitmap3);
                                            Img4.setImageBitmap(bitmap4);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(CarInfo4.this, "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CarInfo4.this, "Data is Verifying", Toast.LENGTH_SHORT).show();
                Toast.makeText(CarInfo4.this, "Data is Processing", Toast.LENGTH_SHORT).show();


                Map<String,Object> map = new HashMap<>();
                map.put("carAddress",Address1.getText().toString().trim());
                map.put("carAdvance",Advance1.getText().toString().trim());
                map.put("carAge",CARAGE1);
                map.put("carAirbag",AIRBAG1);
                map.put("carArea",Area1.getText().toString().trim());
                map.put("carBodyType",BODYTYPE1);
                map.put("carDescription",Desc1.getText().toString());
                map.put("carFastTag",FASTTAG1);
                map.put("carFuel",FUEL1);
                map.put("carGearBox",GEARBOX1);
                map.put("carMilage",MILLAGE1);
                map.put("carModel",model.getText().toString());
                map.put("carPrice",Rent1.getText().toString());
                map.put("carSeats",SEATS1);
                map.put("carServiceDate",Service1.getText().toString());
                map.put("carTransmission",TRASNMISSION1);
                map.put("carUrl",ImgUrl1);
                map.put("carUrl1",ImgUrl2);
                map.put("carUrl2",ImgUrl3);
                map.put("carUrl3",ImgUrl4);
                map.put("type","CAR");
                map.put("OwnerEmail",user.getEmail());
                map.put("UserId",user.getUid());
                map.put("PhoneNo",OwnerNo);


                FirebaseDatabase.getInstance().getReference().child("RentIt").child("car").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(CarInfo4.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CarInfo4.this,CartPage.class));
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CarInfo4.this, "Data Upload Failed"+ e, Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


    }
}

