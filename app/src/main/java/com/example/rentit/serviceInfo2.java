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

public class serviceInfo2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_service_info2);

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
        String Name = intent.getStringExtra("Name");
        String Address = intent.getStringExtra("Address");
        String job = intent.getStringExtra("job");
        String Rent = intent.getStringExtra("Rent");
        String Exp = intent.getStringExtra("Exp");
        String Area = intent.getStringExtra("Area");
        String Desc = intent.getStringExtra("Desc");
        String Lang = intent.getStringExtra("Lang");
        String ArdharNum = intent.getStringExtra("ArdharNum");
        String workTime= intent.getStringExtra("workTime");


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(serviceInfo2.this, serviceInfo3.class);
                intent1.putExtra("Name",Name);
                intent1.putExtra("Address",Address);
                intent1.putExtra("Exp",Exp);
                intent1.putExtra("Rent",Rent);
                intent1.putExtra("Area",Area);
                intent1.putExtra("Lang",Lang);
                intent1.putExtra("Desc",Desc);
                intent1.putExtra("job",job);
                intent1.putExtra("ArdharNum",ArdharNum);
                intent1.putExtra("workTime",workTime);
                intent1.putExtra("ImgUrl1", ImgUrl1);
                intent1.putExtra("ImgUrl2", ImgUrl2);
                intent1.putExtra("ImgUrl3", ImgUrl3);
                intent1.putExtra("ImgUrl4", ImgUrl4);

                startActivity(intent1);

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