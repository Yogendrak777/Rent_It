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

public class CameraInfo4 extends AppCompatActivity {

    EditText Name1,Address1,Advance1,Rent1,Area1,Desc1,model;
    String TRASNMISSION1,FUEL1,MILLAGE1,AIRBAG1,SEATS1,CARAGE1,OwnerNo;
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
        setContentView(R.layout.activity_camera_info4);

        Name1 = (EditText)findViewById(R.id.Name);
        Address1 = (EditText)findViewById(R.id.Address);
        Advance1 = (EditText)findViewById(R.id.Advance);
        Rent1 = (EditText)findViewById(R.id.Rent);
        Area1 = (EditText)findViewById(R.id.Area);
        Desc1 = (EditText)findViewById(R.id.CarDesc);
        model = (EditText)findViewById(R.id.modelName);


        upload = (Button)findViewById(R.id.Upload);

        Spinner Trans = (Spinner) findViewById(R.id.GearSpinner);
        Spinner Fuel = (Spinner) findViewById(R.id.FuelSpinner);
        Spinner Millage = (Spinner) findViewById(R.id.MillageSpinner);
        Spinner AirBag = (Spinner) findViewById(R.id.AirBagSpinner);
        Spinner Seats = (Spinner) findViewById(R.id.SeatsSpinner);
        Spinner CarAge = (Spinner)findViewById(R.id.AgeSpinner);

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
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");


        Name1.setText(Name);
        Address1.setText(Address);
        Advance1.setText(Advance);
        Rent1.setText(Rent);
        Area1.setText(Area);
        Desc1.setText(Desc);
        model.setText(Model);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.CameraFlash, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Led, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Fuel.setAdapter(adapter1);
        int Led;
        Led = adapter1.getPosition(FUEL);
        Fuel.setSelection(Led);
        Fuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FUEL1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Focus, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Millage.setAdapter(adapter2);
        int Focus;
        Focus = adapter2.getPosition(MILLAGE);
        Millage.setSelection(Focus);
        Millage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MILLAGE1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.MovieFormat, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AirBag.setAdapter(adapter3);
        int Mirror;
        Mirror = adapter3.getPosition(AIRBAG);
        AirBag.setSelection(Mirror);
        AirBag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AIRBAG1 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.CameraType, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Seats.setAdapter(adapter4);
        int type;
        type = adapter4.getPosition(SEATS);
        Seats.setSelection(type);
        Seats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SEATS1 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.Zoom, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CarAge.setAdapter(adapter5);
        int zoom;
        zoom = adapter5.getPosition(CARAGE);
        CarAge.setSelection(zoom);

        CarAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CARAGE1 = adapterView.getItemAtPosition(i).toString();
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
                                            Toast.makeText(CameraInfo4.this, "please wait", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(CameraInfo4.this, "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(CameraInfo4.this, "Data is Verifying", Toast.LENGTH_SHORT).show();
                Toast.makeText(CameraInfo4.this, "Data is Processing", Toast.LENGTH_SHORT).show();


                Map<String,Object> map = new HashMap<>();
                map.put("cameraAddress",Address1.getText().toString().trim());
                map.put("cameraAdvance",Advance1.getText().toString().trim());
                map.put("cameraArea",Area1.getText().toString().trim());
                map.put("cameraDescription",Desc1.getText().toString());
                map.put("cameraBrand",model.getText().toString());
                map.put("cameraRent",Rent1.getText().toString());
                map.put("camUrl1",ImgUrl1);
                map.put("camUrl2",ImgUrl2);
                map.put("camUrl3",ImgUrl3);
                map.put("cameraFlash",TRASNMISSION1);
                map.put("cameraLedMonitor",FUEL1);
                map.put("cameraManualFous",MILLAGE1);
                map.put("cameraMovieFormat",AIRBAG1);
                map.put("cameraOptialZoom",CARAGE1);
                map.put("cameraType",SEATS1);
                map.put("camUrl4",ImgUrl4);
                map.put("type","CAMERA");
                map.put("OwnerEmail",user.getEmail());
                map.put("UserId",user.getUid());
                map.put("PhoneNo",OwnerNo);


                FirebaseDatabase.getInstance().getReference().child("RentIt").child("camera").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(CameraInfo4.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CameraInfo4.this,CartPage.class));
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CameraInfo4.this, "Data Upload Failed"+ e, Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

    }
}

