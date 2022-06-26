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
import android.widget.Button;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class SportsInfo2 extends AppCompatActivity {

    ImageView Img2, Img3, Img4;
    Button NextButton, PrevButton;
    String ImgUrl1, ImgUrl2, ImgUrl3, ImgUrl4;

    ImageView profileImg;
    byte[] imageByte;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private TextView saveImgBtn1, saveImgBtn, saveImgBtn2, saveImgBtn3;
    private Uri imgURI;
    private int imageUpdated = 0;
    final String randomKey = UUID.randomUUID().toString();
    final String randomKey1 = UUID.randomUUID().toString();
    final String randomKey2 = UUID.randomUUID().toString();
    final String randomKey3 = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_info2);
        saveImgBtn = findViewById(R.id.img_save_btn);
        saveImgBtn1 = findViewById(R.id.img_save_btn1);
        saveImgBtn2 = findViewById(R.id.img_save_btn2);
        saveImgBtn3 = findViewById(R.id.img_save_btn3);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        profileImg = findViewById(R.id.objImg1);
        Img2 = (ImageView) findViewById(R.id.objImg2);
        Img3 = (ImageView) findViewById(R.id.objImg3);
        Img4 = (ImageView) findViewById(R.id.objImg4);


        NextButton = (Button) findViewById(R.id.NextButton2);
        PrevButton = (Button) findViewById(R.id.PrevButton1);

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

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SportsInfo2.this,SportsInfo3.class);
                intent.putExtra("SportAddress",SportAddress);
                intent.putExtra("SportBrand",SportBrand);
                intent.putExtra("SportColor",SportColor);
                intent.putExtra("SportMeteral",SportMeteral);
                intent.putExtra("SportName",SportName);
                intent.putExtra("SportAdvance",SportAdvance);
                intent.putExtra("SportPack",SportPack);
                intent.putExtra("SportRent",SportRent);
                intent.putExtra("SportIdel",SportIdel);
                intent.putExtra("SportArea",SportArea);
                intent.putExtra("SportDesc",SportDesc);
                intent.putExtra("ImgUrl1", ImgUrl1);
                intent.putExtra("ImgUrl2", ImgUrl2);
                intent.putExtra("ImgUrl3", ImgUrl3);
                intent.putExtra("ImgUrl4", ImgUrl4);
                startActivity(intent);
            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                PickImage();
            }
        });

        Img2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                PickImage1();
            }
        });

        Img3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                PickImage2();
            }
        });

        Img4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                PickImage3();
            }
        });


        saveImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUpdated == 0) {
                    Toast.makeText(view.getContext(), "Change the Photo to Update", Toast.LENGTH_SHORT).show();
                } else {

                    upload_profile_img(imageByte);

                    ImgUrl1 = randomKey;

                }
            }
        });

        saveImgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUpdated == 0) {
                    Toast.makeText(view.getContext(), "Change the Photo to Update", Toast.LENGTH_SHORT).show();
                } else {

                    upload_profile_img1(imageByte);

                    ImgUrl2 = randomKey1;
                }
            }
        });
        saveImgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUpdated == 0) {
                    Toast.makeText(view.getContext(), "Change the Photo to Update", Toast.LENGTH_SHORT).show();
                } else {

                    upload_profile_img2(imageByte);

                    ImgUrl3 = randomKey2;
                }
            }
        });

        saveImgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUpdated == 0) {
                    Toast.makeText(view.getContext(), "Change the Photo to Update", Toast.LENGTH_SHORT).show();
                } else {

                    upload_profile_img3(imageByte);

                    ImgUrl4 = randomKey3;
                }
            }
        });


    }

    private void upload_profile_img(byte[] imageByte) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        // final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = this.storageReference.child("images/" + randomKey);
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

    private void upload_profile_img1(byte[] imageByte) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        // final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = this.storageReference.child("images/" + randomKey1);
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

    private void upload_profile_img2(byte[] imageByte) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        // final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = this.storageReference.child("images/" + randomKey2);
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

    private void upload_profile_img3(byte[] imageByte) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        // final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = this.storageReference.child("images/" + randomKey3);
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
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imgURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgURI);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream);
                            profileImg.setImageBitmap(bitmap);
                            imageByte = stream.toByteArray();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // profileImg.setImageURI(data.getData());
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> imgUploadResultLaunch1 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imgURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgURI);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream);
                            Img2.setImageBitmap(bitmap);
                            imageByte = stream.toByteArray();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // profileImg.setImageURI(data.getData());
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> imgUploadResultLaunch2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imgURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgURI);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream);
                            Img3.setImageBitmap(bitmap);
                            imageByte = stream.toByteArray();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // profileImg.setImageURI(data.getData());
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> imgUploadResultLaunch3 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imgURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgURI);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream);
                            Img4.setImageBitmap(bitmap);
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

    private void PickImage1() {
        Intent img = new Intent(Intent.ACTION_PICK);
        img.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imgUploadResultLaunch1.launch(img);
        imageUpdated = 1;
    }

    private void PickImage2() {
        Intent img = new Intent(Intent.ACTION_PICK);
        img.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imgUploadResultLaunch2.launch(img);
        imageUpdated = 1;
    }

    private void PickImage3() {
        Intent img = new Intent(Intent.ACTION_PICK);
        img.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imgUploadResultLaunch3.launch(img);
        imageUpdated = 1;

    }
}