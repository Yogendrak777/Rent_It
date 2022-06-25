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

public class serviceInfo3 extends AppCompatActivity {

    EditText Name1, Address1, Lang1, Rent1, Area1, Desc1, Exp1, ArddharNum1, workTime1;
    String job1, OwnerNo;

    ImageView Img2, Img3, Img4, profileImg;
    Button upload;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info3);

        Spinner bhk = (Spinner) findViewById(R.id.BhkSpinner);

        Name1 = findViewById(R.id.Name);
        Address1 = findViewById(R.id.Address);
        Exp1 = findViewById(R.id.Exp);
        Area1 = findViewById(R.id.Area);
        workTime1 = findViewById(R.id.workingTime);
        Lang1 = findViewById(R.id.Lang);
        ArddharNum1 = findViewById(R.id.Ardhar);
        Rent1 = findViewById(R.id.Charge);
        Desc1 = findViewById(R.id.Desc);
        upload = (Button) findViewById(R.id.Upload);

        profileImg = findViewById(R.id.objImg1);
        Img2 = (ImageView) findViewById(R.id.objImg2);
        Img3 = (ImageView) findViewById(R.id.objImg3);
        Img4 = (ImageView) findViewById(R.id.objImg4);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Address = intent.getStringExtra("Address");
        String job = intent.getStringExtra("job");
        String Rent = intent.getStringExtra("Rent");
        String Exp = intent.getStringExtra("Exp");
        String Area = intent.getStringExtra("Area");
        String Desc = intent.getStringExtra("Desc");
        String Lang = intent.getStringExtra("Lang");
        String ArdharNum = intent.getStringExtra("ArdharNum");
        String workTime = intent.getStringExtra("workTime");
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");

        Name1.setText(Name);
        Address1.setText(Address);
        Address1.setText(Address);
        Rent1.setText(Rent);
        Exp1.setText(Exp);
        Area1.setText(Area);
        Desc1.setText(Desc);
        Lang1.setText(Lang);
        ArddharNum1.setText(ArdharNum);
        workTime1.setText(workTime);

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
                                            Toast.makeText(serviceInfo3.this, "please wait", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(serviceInfo3.this, "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Services, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bhk.setAdapter(adapter1);
        int ser;
        ser = adapter1.getPosition(job);
        bhk.setSelection(ser);
        bhk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                job1 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(serviceInfo3.this, "Data is Verifying", Toast.LENGTH_SHORT).show();
                Toast.makeText(serviceInfo3.this, "Data is Processing", Toast.LENGTH_SHORT).show();


                Map<String, Object> map = new HashMap<>();
                map.put("Name", Name1.getText().toString().trim());
                map.put("Experience", Exp1.getText().toString().trim());
                map.put("Address", Address1.getText().toString().trim());
                map.put("Charge",Rent1.getText().toString().trim());
                map.put("jobName", job1);
                map.put("Area", Area1.getText().toString().trim());
                map.put("workingTime", workTime1.getText().toString().trim());
                map.put("LangaugeKnow",Lang1.getText().toString().trim());
                map.put("Desc", Desc1.getText().toString().trim());
                map.put("ArdharNum", ArddharNum1.getText().toString().trim());
                map.put("Img1",ImgUrl1);
                map.put("Img2",ImgUrl2);
                map.put("Img3",ImgUrl3);
                map.put("ArdharPic",ImgUrl4);
                map.put("type","SERVICES");
                map.put("OwnerEmail",user.getEmail());
                map.put("UserId",user.getUid());
                map.put("PhoneNo",OwnerNo);

                if(job1.equals("Mechanical")) {

                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("Mechanical").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });
                }else if (job1.equals("Plumber")){
                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("Plumber").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });

                }else if(job1.equals("Electrical")){
                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("Electrical").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });

                } else if(job1.equals("ConstructionWorks")){
                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("ConstructionWorks").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });
                } else if (job1.equals("householdworks")){
                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("householdworks").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });
                }else if(job1.equals("Beatuician")){
                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("Beatuician").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });
                } else if(job1.equals("Paints")){
                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("Paints").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });
                } else if(job1.equals("InteriorDesigners")){
                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("InteriorDesigners").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(serviceInfo3.this, CartPage.class));
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(serviceInfo3.this, "Data Upload Failed" + e, Toast.LENGTH_SHORT).show();

                                }
                            });
                }


            }
        });
    }
}



