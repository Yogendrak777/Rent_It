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

public class ClothInfo3 extends AppCompatActivity {
    EditText ClothName1, ClothAddress1, ClothBrand1, ClothAdvance1,ClothRent1,ClothArea1, ClothColor1,ClothType1, ClothDesc1;
    Spinner Size, Meterial,Fit;
    String SIZE1,METERIAL1,FIT1,OwnerNo;
    Button Next;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;
    ImageView Img2, Img3, Img4,profileImg;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_info3);

        ClothName1 = findViewById(R.id.ClothName);
        ClothAddress1 = findViewById(R.id.ClothAddress);
        ClothBrand1 = findViewById(R.id.ClothBrand);
        ClothAdvance1 = findViewById(R.id.ClothAdvance);
        ClothRent1 = findViewById(R.id.ClothRent);
        ClothArea1 = findViewById(R.id.ClothArea);
        ClothColor1 = findViewById(R.id.ClothColor);
        ClothType1 = findViewById(R.id.ClothType);
        ClothDesc1 = findViewById(R.id.ClothDesc);

        profileImg = findViewById(R.id.objImg1);
        Img2 = (ImageView) findViewById(R.id.objImg2);
        Img3 = (ImageView) findViewById(R.id.objImg3);
        Img4 = (ImageView) findViewById(R.id.objImg4);

        Size = findViewById(R.id.ColorSizeSpinner);
        Meterial = findViewById(R.id.ColorMeteralSpinner);
        Fit = findViewById(R.id.ClothFitSpinner);

        Next = findViewById(R.id.NextButton);

        Intent intent = getIntent();
        String ClothName = intent.getStringExtra("ClothName");
        String ClothAddres = intent.getStringExtra("ClothAddres");
        String ClothBrand = intent.getStringExtra("ClothBrand");
        String ClothAdvance = intent.getStringExtra("ClothAdvance");
        String ClothRent = intent.getStringExtra("ClothRent");
        String ClothArea = intent.getStringExtra("ClothArea");
        String ClothColor = intent.getStringExtra("ClothColor");
        String ClothType = intent.getStringExtra("ClothType");
        String ClothDesc = intent.getStringExtra("ClothDesc");
        String ClothSize = intent.getStringExtra("ClothSize");
        String ClothMeterial = intent.getStringExtra("ClothMeterial");
        String ClothFit = intent.getStringExtra("ClothFit");
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");


        ClothName1.setText(ClothName);
        ClothAddress1.setText(ClothAddres);
        ClothAdvance1.setText(ClothAdvance);
        ClothBrand1.setText(ClothBrand);
        ClothRent1.setText(ClothRent);
        ClothArea1.setText(ClothArea);
        ClothColor1.setText(ClothColor);
        ClothType1.setText(ClothType);
        ClothDesc1.setText(ClothDesc);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.ClothSize, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int size;
        size = adapter2.getPosition(ClothSize);
        Size.setSelection(size);
        Size.setAdapter(adapter2);
        Size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SIZE1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.ClothMeterial, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int meteral;
        meteral = adapter5.getPosition(ClothMeterial);
        Meterial.setSelection(meteral);
        Meterial.setAdapter(adapter5);
        Meterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                METERIAL1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.ClothFit, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int fit;
        fit = adapter7.getPosition(ClothFit);
        Fit.setSelection(fit);
        Fit.setAdapter(adapter7);
        Fit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FIT1 = adapterView.getItemAtPosition(i).toString();
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
                                            Toast.makeText(ClothInfo3.this, "please wait", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(ClothInfo3.this, "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClothInfo3.this, "Data is Verifying", Toast.LENGTH_SHORT).show();
                Toast.makeText(ClothInfo3.this, "Data is Processing", Toast.LENGTH_SHORT).show();


                Map<String,Object> map = new HashMap<>();
                map.put("clothOwnerName",ClothName1.getText().toString().trim());
                map.put("clothAddress",ClothAddress1.getText().toString().trim());
                map.put("clothAdvance",ClothAdvance1.getText().toString().trim());
                map.put("clothArea",ClothArea1.getText().toString().trim());
                map.put("clothBrand",ClothBrand1.getText().toString().trim());
                map.put("clothColor",ClothColor1.getText().toString().trim());
                map.put("clothDesc",ClothDesc1.getText().toString().trim());
                map.put("clothFitSize",SIZE1);
                map.put("clothMetal",METERIAL1);
                map.put("clothName",ClothType1.getText().toString().trim());
                map.put("clothRent",ClothRent1.getText().toString());
                map.put("ClothFit",FIT1);
                map.put("clothUrl1",ImgUrl1);
                map.put("clothUrl2",ImgUrl2);
                map.put("clothUrl3",ImgUrl3);
                map.put("clothUrl4",ImgUrl4);
                map.put("type","CLOTHS");
                map.put("OwnerEmail",user.getEmail());
                map.put("UserId",user.getUid());
                map.put("PhoneNo",OwnerNo);


                FirebaseDatabase.getInstance().getReference().child("RentIt").child("clothes").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ClothInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ClothInfo3.this,CartPage.class));
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ClothInfo3.this, "Data Upload Failed"+ e, Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });



    }
}