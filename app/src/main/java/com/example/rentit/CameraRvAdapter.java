package com.example.rentit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CameraRvAdapter extends FirebaseRecyclerAdapter<CameraRvModel,CameraRvAdapter.myViewHolder> {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    public static String currentUser;
    public static String ran;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CameraRvAdapter(@NonNull FirebaseRecyclerOptions<CameraRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CameraRvModel model) {
        holder.CamModel.setText(model.getCameraBrand()+", "+model.getCameraModel());
        holder.CamPrise.setText(model.getCameraRent());
        holder.CamArea.setText(model.getCameraArea());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1;
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("FavouriteCamera");

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getCamUrl1());
        try {
            File file = File.createTempFile("randomKey", "");
            storageReference1.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(holder.imgOfCamera.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            holder.imgOfCamera.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(holder.img.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.cardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfCamera.getContext())
                        .setContentHolder(new ViewHolder(R.layout.camera_rv_details))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                final String randomKey = UUID.randomUUID().toString();
                TextView DcamModel = view.findViewById(R.id.DCamModel);
                TextView DcamAddress = view.findViewById(R.id.DCamAddress);
                TextView DcamAdvance = view.findViewById(R.id.DcamAdvance);
                TextView DcamRent = view.findViewById(R.id.DcamRent);
                TextView DcamFlash = view.findViewById(R.id.DcamFlash);
                TextView DcamLed = view.findViewById(R.id.DcamLed);
                TextView DcamManual = view.findViewById(R.id.DcamFocus);
                TextView DcamMovie = view.findViewById(R.id.DcamFormat);
                TextView Dcamzoom = view.findViewById(R.id.DcamOptionzoom);
                TextView DcamType = view.findViewById(R.id.DcamType);
                TextView DcamDesc = view.findViewById(R.id.DCamDescription);

                ImageView imgC1 = view.findViewById(R.id.objCamImg1);
                ImageView imgC2 = view.findViewById(R.id.objCamImg2);
                ImageView imgC3 = view.findViewById(R.id.objCamImg3);
                ImageView imgC4 = view.findViewById(R.id.objCamImg4);

                DcamModel.setText(model.getCameraBrand()+", "+model.getCameraModel());
                DcamAddress.setText(model.getCameraAddress()+", "+model.getCameraArea());
                DcamAdvance.setText(model.getCameraAdvance());
                DcamRent.setText(model.getCameraRent());
                DcamFlash.setText(model.getCameraFlash());
                DcamLed.setText(model.getCameraLedMonitor());
                DcamManual.setText(model.getCameraManualFous());
                DcamMovie.setText(model.getCameraMovieFormat());
                Dcamzoom.setText(model.getCameraOptialZoom());
                DcamType.setText(model.getType());
                DcamDesc.setText(model.getCameraDescription());

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getCamUrl1());
                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + model.getCamUrl2());
                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + model.getCamUrl3());
                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + model.getCamUrl4());
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
                                    Toast.makeText(imgC1.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                                    Bitmap bitmap1 = BitmapFactory.decodeFile(file1.getAbsolutePath());
                                    Bitmap bitmap2 = BitmapFactory.decodeFile(file2.getAbsolutePath());
                                    Bitmap bitmap3 = BitmapFactory.decodeFile(file3.getAbsolutePath());
                                    Bitmap bitmap4 = BitmapFactory.decodeFile(file4.getAbsolutePath());
                                    imgC1.setImageBitmap(bitmap1);
                                    imgC2.setImageBitmap(bitmap2);
                                    imgC3.setImageBitmap(bitmap3);
                                    imgC4.setImageBitmap(bitmap4);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(imgC1.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }


                TextView next = view.findViewById(R.id.next);
                ImageButton favOn,share,favof;

                favOn = view.findViewById(R.id.favOn);
                share = view.findViewById(R.id.share);
                favof = view.findViewById(R.id.favOf);

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT,"House Info \n");
                        intent.putExtra(Intent.EXTRA_TEXT,model.getCameraModel() + "," + model.getCameraRent() + "," + model.getCameraBrand());
                        imgC1.getContext().startActivity(Intent.createChooser(intent,"share Via"));

                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(imgC1.getContext(), BookEnter.class);
                        intent.putExtra("UserId", model.getUserId());
                        imgC1.getContext().startActivity(intent);

                    }
                });

                FirebaseUser user = firebaseAuth.getCurrentUser();
                String currentUser1 = user.getUid();
//                databaseReference3 = firebaseDatabase.getReference("RentIt").child("Favourite");
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("currentUsers").getValue().equals(currentUser1)) {
                                if (ds.child("ownerUId").getValue().equals(model.getUserId())) {
                                    if (ds.child("ObjUrl1").getValue().equals(model.getCamUrl1())) {
                                        ran = ds.child("Random").getValue().toString();
                                        favof.setVisibility(View.VISIBLE);
                                        favOn.setVisibility(View.GONE);


                                    }
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(imgC1.getContext(), "sorry " + error, Toast.LENGTH_SHORT).show();
                    }
                });

                favof.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        favof.setVisibility(View.GONE);
                        favOn.setVisibility(View.VISIBLE);
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteCamera")
                                .child(ran).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.imgOfCamera.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

                favOn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        favof.setVisibility(View.VISIBLE);
                        favOn.setVisibility(View.GONE);

                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        currentUser = user.getUid();

                        Map<String, Object> map = new HashMap<>();
                        map.put("currentUsers", currentUser);
                        map.put("ObjUrl1",model.getCamUrl1());
                        map.put("Random", randomKey);
                        map.put("ownerUId", model.getUserId());
                        map.put("cameraAddress",model.getCameraAddress());
                        map.put("cameraAdvance",model.getCameraAdvance());
                        map.put("cameraArea",model.getCameraArea());
                        map.put("cameraDescription",model.getCameraDescription());
                        map.put("cameraBrand",model.getCameraBrand());
                        map.put("cameraRent",model.getCameraRent());
                        map.put("camUrl1",model.getCamUrl1());
                        map.put("camUrl2",model.getCamUrl2());
                        map.put("camUrl3",model.getCamUrl3());
                        map.put("cameraFlash",model.getCameraFlash());
                        map.put("cameraLedMonitor",model.getCameraLedMonitor());
                        map.put("cameraManualFous",model.getCameraManualFous());
                        map.put("cameraMovieFormat",model.getCameraMovieFormat());
                        map.put("cameraOptialZoom",model.getCameraOptialZoom());
                        map.put("cameraType",model.getCameraType());
                        map.put("camUrl4",model.getCamUrl4());
                        map.put("type","CAMERA");
                        map.put("OwnerEmail",user.getEmail());
                        map.put("UserId",user.getUid());
                        map.put("PhoneNo",model.getPhoneNo());


                        assert currentUser != null;
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteCamera").child(randomKey)
                                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(imgC1.getContext(), "Item Added to Favourite list", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                });

                dialogPlus.show();

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_rv_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfCamera;
        TextView CamModel,CamPrise,CamArea;
        CardView cardCamera;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfCamera = (ImageView)itemView.findViewById(R.id.objImgCamera);
            CamModel = (TextView) itemView.findViewById(R.id.ObjCamModel);
            CamPrise = (TextView)itemView.findViewById(R.id.ObjCamPrise);
            CamArea = (TextView) itemView.findViewById(R.id.ObjCamArea);
            cardCamera = (CardView)itemView.findViewById(R.id.CardCamer);


        }
    }

}
