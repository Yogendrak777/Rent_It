package com.example.rentit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class AccountPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CardView myCart,AddItem,Fav,logOut,ChartUs;
    TextView NameT;
    //EmailT,PhoneNoT;
    //String PhoneNoT;
    ImageView profileImg;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private TextView uploadProfileImgBtn, saveImgBtn;
    private Uri imgURI;
    private int imageUpdated = 0;
    final String randomKey = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        bottomNavigationView = findViewById(R.id.buttomNavigationBar);
        myCart = (CardView)findViewById(R.id.MyCart);
        AddItem = (CardView)findViewById(R.id.RentItem);
        Fav = (CardView)findViewById(R.id.Fav);
        NameT = (TextView)findViewById(R.id.Name);
        logOut = (CardView)findViewById(R.id.logOut);
        //EmailT = (TextView)findViewById(R.id.Email);
        //PhoneNoT = (TextView)findViewById(R.id.PhoneNumber);
        profileImg = findViewById(R.id.profile_img_view);
        uploadProfileImgBtn = findViewById(R.id.upload_profile_img_btn);
        saveImgBtn = findViewById(R.id.img_save_btn);
        bottomNavigationView.setSelectedItemId(R.id.menuAccount);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
       ChartUs = findViewById(R.id.chartUs);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();


        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("userEmailDb").getValue().equals(user.getEmail())){

                       // PhoneNoT = ds.child("userPhoneDb").getValue(String.class);
                        String img = ds.child("UserIMage").getValue(String.class);
                        NameT.setText(ds.child("userNameDb").getValue(String.class));

                       StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+img);
                        try {
                            File file = File.createTempFile("randomKey","");
                            storageReference1.getFile(file)
                                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(AccountPage.this, "please wait", Toast.LENGTH_SHORT).show();
                                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                            profileImg.setImageBitmap(bitmap);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(AccountPage.this, "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(AccountPage.this,welcomePage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

        Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountPage.this,Favourite.class));
            }
        });

        ChartUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountPage.this, ChartActivity.class);
                intent.putExtra("UserId", "hSPtoSo5B2esv6lMQXFWZgqokUX2");
                startActivity(intent);
            }
        });


        myCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountPage.this,CartPage.class));
            }
        });

        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountPage.this,HouseInfo1.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(AccountPage.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuSearch:
                        startActivity(new Intent(AccountPage.this,SearchItemPage.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        uploadProfileImgBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
//                boolean pick = true;
//                if(pick){
//                    if(!checkCameraPermission()){
//                        requestCameraPermission();
//                    } else
//                        PickImage();
//                } else {
//                    if(!checkStoragePermission()){
//                        requestStoragePermission();
//                    } else
//                        PickImage();
//                }
                PickImage();
            }
        });
        saveImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUpdated == 0){
                    Toast.makeText(view.getContext(), "Change the Photo to Update", Toast.LENGTH_SHORT).show();
                } else {
                    upload_profile_img();
                    HashMap<String,Object> map1 = new HashMap<>();
                    map1.put("UserIMage",randomKey);

                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("RentBy")
                            .child(user.getUid()).updateChildren(map1)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(AccountPage.this, "Successfully updated", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AccountPage.this, "Failed updated", Toast.LENGTH_SHORT).show();

                                }
                            });

                }
            }
        });
    }

    private void upload_profile_img(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
       // final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = this.storageReference.child("images/"+randomKey);
        storageReference.putFile(imgURI)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                        imageUpdated = 0;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Percentage : " + (int) progressPercent + "%");
                    }
                });
    }

    ActivityResultLauncher<Intent> imgUploadResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        imgURI = data.getData();
                        profileImg.setImageURI(data.getData());
                    }
                }
            }
    );
    private void PickImage() {
        Intent img = new Intent(Intent.ACTION_PICK);
        img.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imgUploadResultLaunch.launch(img);
        imageUpdated = 1;
    }



//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void requestStoragePermission() {
//        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//    }

    //@RequiresApi(api = Build.VERSION_CODES.M)
//    private void requestCameraPermission() {
//        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//    }
//
//    private boolean checkStoragePermission() {
//        boolean res1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
//        return res1;
//    }
//
//    private boolean checkCameraPermission() {
//        boolean res1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
//        boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
//        return  res1 && res2;
//    }
}