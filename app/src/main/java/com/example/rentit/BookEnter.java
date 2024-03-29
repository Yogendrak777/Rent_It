package com.example.rentit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class BookEnter extends AppCompatActivity {

    String UserId,PhoneNo,ImageUrl;
    EditText Name,Address,ArddharNum;
    TextView save,Next,save1;
    ImageView img,img1;
    byte[] imageByte;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Uri imgURI;
    private int imageUpdated = 0;
    final String randomKey = UUID.randomUUID().toString();
    final String randomKey1 = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_enter);

        Intent intent = getIntent();
        UserId = intent.getStringExtra("UserId");
        ImageUrl = intent.getStringExtra("ImageUrl1");
        PhoneNo = intent.getStringExtra("PhoneNo");

        Name = findViewById(R.id.Name);
        Address = findViewById(R.id.Address);
        save = findViewById(R.id.img_save_btn);
        img = findViewById(R.id.objImg1);
        save1 = findViewById(R.id.img_save_btn1);
        img1 = findViewById(R.id.objImgPic);
        Next = findViewById(R.id.next);
        ArddharNum = findViewById(R.id.ArddharNum);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> map1 = new HashMap<>();
                map1.put("BuyerAdharIMage",randomKey);
                map1.put("UserId",UserId);
                map1.put("PhoneNo",PhoneNo);
                map1.put("ImageUrl1",randomKey1);
                map1.put("BuyerUid",user.getUid());
                map1.put("BuyerName",Name.getText().toString().trim());
                map1.put("BuyerAddress",Address.getText().toString().trim());
                map1.put("ArdharNum",ArddharNum.getText().toString().trim());
                map1.put("Status","show");

                FirebaseDatabase.getInstance().getReference().child("RentIt").child("BookList")
                        .child(user.getUid()).updateChildren(map1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(BookEnter.this, "Successfully updated", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookEnter.this, "Failed updated", Toast.LENGTH_SHORT).show();

                            }
                        });

                Intent intent = new Intent(BookEnter.this, BookConformation.class);
                intent.putExtra("PhoneNo",PhoneNo);
                startActivity(intent);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                PickImage();
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                PickImage1();
            }
        });

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUpdated == 0){
                    Toast.makeText(view.getContext(), "Change the Photo to Update", Toast.LENGTH_SHORT).show();
                } else {

                    upload_profile_img1(imageByte);


                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUpdated == 0){
                    Toast.makeText(view.getContext(), "Change the Photo to Update", Toast.LENGTH_SHORT).show();
                } else {

                    upload_profile_img(imageByte);

                }
            }
        });
    }
    private void upload_profile_img(byte[] imageByte){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        // final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = this.storageReference.child("images/"+randomKey);
        storageReference.putBytes(imageByte)
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

    private void upload_profile_img1(byte[] imageByte){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        // final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = this.storageReference.child("images/"+randomKey1);
        storageReference.putBytes(imageByte)
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
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgURI);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG,25,stream);
                            img.setImageBitmap(bitmap);
                            imageByte = stream.toByteArray();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // profileImg.setImageURI(data.getData());
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

    ActivityResultLauncher<Intent> imgUploadResultLaunch1 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        imgURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgURI);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG,25,stream);
                            img1.setImageBitmap(bitmap);
                            imageByte = stream.toByteArray();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // profileImg.setImageURI(data.getData());
                    }
                }
            }
    );
    private void PickImage1() {
        Intent img = new Intent(Intent.ACTION_PICK);
        img.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imgUploadResultLaunch1.launch(img);
        imageUpdated = 1;
    }
}