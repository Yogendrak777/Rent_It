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

public class SpeakerInfo4 extends AppCompatActivity {
    EditText SpeAddress1, SpeModel1, SpeRent1, SpeAdvance1, SpeArea1, SpeDec1;
    String BLUETOOTH1, DISPLAY1, MEMORY1, POWER1, SPETYPE1;
    Spinner Bluetooth,Display,Memory,Power,SpeType;
    Button uplaod;
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
        setContentView(R.layout.activity_speaker_info4);

        SpeAddress1 = findViewById(R.id.SpeAddress);
        SpeModel1 = findViewById(R.id.SpemodelName);
        SpeRent1 = findViewById(R.id.SpeRent);
        SpeAdvance1 = findViewById(R.id.SpeAdvance);
        SpeArea1 = findViewById(R.id.SpeArea);
        SpeDec1 = findViewById(R.id.SpeDesc);

        profileImg = findViewById(R.id.objImg1);
        Img2 = (ImageView) findViewById(R.id.objImg2);
        Img3 = (ImageView) findViewById(R.id.objImg3);
        Img4 = (ImageView) findViewById(R.id.objImg4);

        Bluetooth = findViewById(R.id.SpeBluetoothSpinner);
        Display = findViewById(R.id.SpeDisplaySpinner);
        Memory = findViewById(R.id.SpeMemorySpinner);
        Power = findViewById(R.id.SpePowerSpinner);
        SpeType = findViewById(R.id.SpeTypeSpinner);
        uplaod = findViewById(R.id.upload);

        Intent intent = getIntent();
        String SpeAddress = intent.getStringExtra("SpeAddress");
        String SpeModel = intent.getStringExtra("SpeModel");
        String SpeRent = intent.getStringExtra("SpeRent");
        String SpeAdvance = intent.getStringExtra("SpeAdvance");
        String SpeArea = intent.getStringExtra("SpeArea");
        String SpeDesc = intent.getStringExtra("SpeDesc");
        String BLUETOOTH = intent.getStringExtra("BLUETOOTH");
        String DISPLAY = intent.getStringExtra("DISPLAY");
        String MEMORY = intent.getStringExtra("MEMORY");
        String POWER = intent.getStringExtra("POWER");
        String SPETYPE = intent.getStringExtra("SPETYPE");
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");

        SpeAddress1.setText(SpeAddress);
        SpeModel1.setText(SpeModel);
        SpeRent1.setText(SpeRent);
        SpeAdvance1.setText(SpeAdvance);
        SpeArea1.setText(SpeArea);
        SpeDec1.setText(SpeDesc);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.SpeakerBluetooth, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bluetooth.setAdapter(adapter2);
        int blue;
        blue = adapter2.getPosition(BLUETOOTH);
        Bluetooth.setSelection(blue);
        Bluetooth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BLUETOOTH1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.SpeakerDisplay, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Display.setAdapter(adapter5);
        int dis;
        dis = adapter5.getPosition(DISPLAY);
        Display.setSelection(dis);
        Display.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DISPLAY1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.SpeakerPower, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Power.setAdapter(adapter7);
        int pow;
        pow = adapter7.getPosition(POWER);
        Power.setSelection(pow);
        Power.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                POWER1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.SpeakerMemory, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Memory.setAdapter(adapter8);
        int mem;
        mem = adapter8.getPosition(MEMORY);
        Memory.setSelection(mem);
        Memory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MEMORY1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,R.array.SpeakerType, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpeType.setAdapter(adapter9);
        int type;
        type = adapter9.getPosition(SPETYPE);
        SpeType.setSelection(type);
        SpeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SPETYPE1 = adapterView.getItemAtPosition(i).toString();
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
                                                                                     Toast.makeText(SpeakerInfo4.this, "please wait", Toast.LENGTH_SHORT).show();
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
                                                                                     Toast.makeText(SpeakerInfo4.this, "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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

                        uplaod.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("speAddress", SpeAddress1.getText().toString().trim());
                                map.put("speAdvance", SpeAdvance1.getText().toString().trim());
                                map.put("speArea", SpeArea1.getText().toString().trim());
                                map.put("speBluetooth", BLUETOOTH1);
                                map.put("speDescription", SpeDec1.getText().toString().trim());
                                map.put("speDisplayType", DISPLAY1);
                                map.put("speMemoryCardSlot", MEMORY1);
                                map.put("speModel", SpeModel1.getText().toString().trim());
                                map.put("spePowerSource", POWER1);
                                map.put("speRent", SpeRent1.getText().toString().trim());
                                map.put("speType", SPETYPE1);
                                map.put("speUrl1", ImgUrl1);
                                map.put("speUrl2", ImgUrl2);
                                map.put("speUrl3", ImgUrl3);
                                map.put("speUrl4", ImgUrl4);
                                map.put("type", "SPEAKER");
                                map.put("OwnerEmail", user.getEmail());
                                map.put("phoneNo", OwnerNo);
                                map.put("UserId", user.getUid());

                                FirebaseDatabase.getInstance().getReference().child("RentIt").child("speakers").push()
                                        .setValue(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(SpeakerInfo4.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SpeakerInfo4.this,CartPage.class));
                                                finish();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(SpeakerInfo4.this, "Data Upload Failed"+ e, Toast.LENGTH_SHORT).show();

                                            }
                                        });
                            }
                        });

                    }
                }
