package com.example.rentit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SportsInfo3 extends AppCompatActivity {
    EditText SportAddress1, SportBrand1, SportColor1, SportMeteral1, SportName1, SportAdvance1, SportPack1, SportRent1, SportIdel1, SportArea1, SportDesc1;
    Button Next;
    String OwnerNo;

    ImageView Img2,Img3,Img4,profileImg;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_info3);

        SportAddress1 = findViewById(R.id.SportAddress);
        SportBrand1 = findViewById(R.id.SportBrand);
        SportColor1 = findViewById(R.id.SportColor);
        SportMeteral1 = findViewById(R.id.SportMeteral);
        SportName1 = findViewById(R.id.SportName);
        SportAdvance1 = findViewById(R.id.SportAdvance);
        SportPack1 = findViewById(R.id.SportPack);
        SportRent1 = findViewById(R.id.SportRent);
        SportIdel1 = findViewById(R.id.SportIdel);
        SportArea1 = findViewById(R.id.SportArea);
        SportDesc1 = findViewById(R.id.SportDesc);
        Next = findViewById(R.id.NextButton);

        profileImg = findViewById(R.id.objImg1);
        Img2 = (ImageView) findViewById(R.id.objImg2);
        Img3 = (ImageView) findViewById(R.id.objImg3);
        Img4 = (ImageView) findViewById(R.id.objImg4);

        Intent intent = getIntent();
        String SportAddress = intent.getStringExtra("SportAddress");
        String SportBrand = intent.getStringExtra("SportBrand");
        String SportColor = intent.getStringExtra("SportColor");
        String SportMeteral = intent.getStringExtra("SportMeteral");
        String SportName = intent.getStringExtra("SportName");
        String SportAdvance = intent.getStringExtra("SportAdvance");
        String SportPack = intent.getStringExtra("SportPack");
        String SportRent = intent.getStringExtra("SportRent");
        String SportIdel = intent.getStringExtra("SportIdel");
        String SportArea = intent.getStringExtra("SportArea");
        String SportDesc = intent.getStringExtra("SportDesc");
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");

        SportAddress1.setText(SportAddress);
        SportBrand1.setText(SportBrand);
        SportColor1.setText(SportColor);
        SportMeteral1.setText(SportMeteral);
        SportName1.setText(SportName);
        SportAdvance1.setText(SportAdvance);
        SportPack1.setText(SportPack);
        SportRent1.setText(SportRent);
        SportIdel1.setText(SportIdel);
        SportArea1.setText(SportArea);
        SportDesc1.setText(SportDesc);

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
                                            Toast.makeText(SportsInfo3.this, "please wait", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(SportsInfo3.this, "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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
                Map<String, Object> map = new HashMap<>();
                map.put("spoAddress",SportAddress1.getText().toString().trim());
                map.put("spoAdvance",SportAdvance1.getText().toString().trim());
                map.put("spoArea",SportArea1.getText().toString().trim());
                map.put("spoBrand",SportBrand1.getText().toString().trim());
                map.put("spoColor",SportColor1.getText().toString().trim());
                map.put("spoDesc",SportDesc1.getText().toString().trim());
                map.put("spoIdelFor",SportIdel1.getText().toString().trim());
                map.put("spoMeterial",SportMeteral1.getText().toString().trim());
                map.put("spoName",SportName1.getText().toString().trim());
                map.put("spoPacks",SportPack1.getText().toString().trim());
                map.put("spoRent",SportRent1.getText().toString().trim());
                map.put("spoUrl1",ImgUrl1);
                map.put("spoUrl2",ImgUrl2);
                map.put("spoUrl3",ImgUrl3);
                map.put("spoUrl4",ImgUrl4);
                map.put("type","Sports");
                map.put("OwnerEmail",user.getEmail());
                map.put("phoneNo",OwnerNo);
                map.put("UserId",user.getUid());

                FirebaseDatabase.getInstance().getReference().child("RentIt").child("sports").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SportsInfo3.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SportsInfo3.this,CartPage.class));
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SportsInfo3.this, "Data Upload Failed"+ e, Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


    }
}









