package com.example.rentit;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RvAdapter extends FirebaseRecyclerAdapter<houseRvModel,RvAdapter.myViewHolder> {

    CardView chart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    public static String currentUser;


    int i = 0;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RvAdapter(@NonNull FirebaseRecyclerOptions<houseRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull houseRvModel model) {
        holder.Address.setText(model.getHouseBHK() + ", " + model.getHouseAddress());
        holder.Prise.setText("\u20B9" + " " + model.getHousePrise() + " /month");
        holder.Area.setText(model.getHouseArea());

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getHouseUrl1());
        try {
            File file = File.createTempFile("randomKey","");
            storageReference1.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(holder.img.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            holder.img.setImageBitmap(bitmap);

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


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.details_page))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView Daddress = view.findViewById(R.id.Daddress);
                TextView Dbhk = view.findViewById(R.id.DBhk);
                TextView Dprice = view.findViewById(R.id.Dprise);
                TextView Dparking = view.findViewById(R.id.Dparking);
                TextView DAdvance = view.findViewById(R.id.DAdvance);
                TextView Dfloor = view.findViewById(R.id.DFloor);
                TextView DArea = view.findViewById(R.id.DArea);
                TextView Dsqrt = view.findViewById(R.id.DSqft);
                TextView Dfacing = view.findViewById(R.id.DFacing);
                TextView DBathroom = view.findViewById(R.id.DBathroom);
                TextView DFood = view.findViewById(R.id.DFood);
                TextView Dpet = view.findViewById(R.id.Dpet);
                TextView Dfamily = view.findViewById(R.id.DFamily);
                TextView Dwater = view.findViewById(R.id.Dwater);
                chart = view.findViewById(R.id.chart);
                TextView next = view.findViewById(R.id.next);
                ImageButton favOn,share;

                favOn = view.findViewById(R.id.favOn);
                share = view.findViewById(R.id.share);


                ImageView img1 = view.findViewById(R.id.objImg1);
                ImageView img2 = view.findViewById(R.id.objImg2);
                ImageView img3 = view.findViewById(R.id.objImg3);
                ImageView img4 = view.findViewById(R.id.objImg4);

                TextView Dmall = view.findViewById(R.id.DMalls);
                TextView DmallDis = view.findViewById(R.id.DMallDistance);
                TextView Dhospital = view.findViewById(R.id.DHospital);
                TextView DhosDis = view.findViewById(R.id.DHostipalDistance);
                TextView DSchool = view.findViewById(R.id.Dschool);
                TextView DSchooldis = view.findViewById(R.id.DSchoolDistance);
                TextView DbusStand = view.findViewById(R.id.DbusStand);
                TextView Dfuel = view.findViewById(R.id.Dpetrol);


                Daddress.setText(model.getHouseBHK() + "," + model.getHouseAddress() + "," + model.getHouseArea());
                Dbhk.setText(model.getHouseBHK());
                Dprice.setText(model.getHousePrise());
                Dparking.setText(model.getHouseParking());
                DAdvance.setText(model.getHouseAdvance());
                Dfloor.setText(model.getHouseFloor());
                DArea.setText(model.getHouseArea());
                Dsqrt.setText(model.getHouseSqFt());
                Dfacing.setText(model.getHouseFacing());
                DBathroom.setText(model.getHouseBathroom());
                DFood.setText(model.getHouseFoodPrefer());
                Dpet.setText(model.getHousePetPrefer());
                Dfamily.setText(model.getHousePrefer());
                Dwater.setText(model.getHouseWaterSupply());

                Dmall.setText(model.getNearMallsName());
                DmallDis.setText(model.getMallDistance());
                Dhospital.setText(model.getNearHospitalName());
                DhosDis.setText(model.getHospitalDistance());
                DSchool.setText(model.getNearSchoolName());
                DSchooldis.setText(model.getSchoolDistance());
                DbusStand.setText(model.getNearbusStopDistance());
                Dfuel.setText(model.getNearPetrolBunkDistance());

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getHouseUrl1());
                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/"+model.getHouseUrl2());
                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/"+model.getHouseUrl3());
                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/"+model.getHouseUrl4());
                try {
                    File file1 = File.createTempFile("randomKey","");
                    File file2 = File.createTempFile("randomKey","");
                    File file3 = File.createTempFile("randomKey","");
                    File file4 = File.createTempFile("randomKey","");
                    storageReference1.getFile(file1);
                    storageReference2.getFile(file2);
                    storageReference3.getFile(file3);
                    storageReference4.getFile(file4)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(img1.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                                    Bitmap bitmap1 = BitmapFactory.decodeFile(file1.getAbsolutePath());
                                    Bitmap bitmap2 = BitmapFactory.decodeFile(file2.getAbsolutePath());
                                    Bitmap bitmap3 = BitmapFactory.decodeFile(file3.getAbsolutePath());
                                    Bitmap bitmap4 = BitmapFactory.decodeFile(file4.getAbsolutePath());
                                    img1.setImageBitmap(bitmap1);
                                    img2.setImageBitmap(bitmap2);
                                    img3.setImageBitmap(bitmap3);
                                    img4.setImageBitmap(bitmap4);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(img1.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                chart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(img1.getContext(), ChartActivity.class);
                        intent.putExtra("UserId", model.getOwnerUId());
                        intent.putExtra("PhoneNo",model.getHousePhoneNo());
                        img1.getContext().startActivity(intent);

                    }
                });

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      Intent intent = new Intent(Intent.ACTION_SEND);
                      intent.setType("text/plain");
                      intent.putExtra(Intent.EXTRA_SUBJECT,"House Info \n");
                      intent.putExtra(Intent.EXTRA_TEXT,model.getHouseBHK() + "," + model.getHouseAddress() + "," + model.getHouseArea());
                      img1.getContext().startActivity(Intent.createChooser(intent,"share Via"));

                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(img1.getContext(), BookEnter.class);
                        intent.putExtra("UserId", model.getOwnerUId());
                        img1.getContext().startActivity(intent);

                    }
                });

                favOn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i++;
//                        firebaseAuth = FirebaseAuth.getInstance();
//                                    firebaseDatabase = FirebaseDatabase.getInstance();
//                                    databaseReference = firebaseDatabase.getReference("RentIt").child("Favourite");
//                        databaseReference.removeValue();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(i==2) {
                                    firebaseAuth = FirebaseAuth.getInstance();
                                    firebaseDatabase = FirebaseDatabase.getInstance();
                                    databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    currentUser = user.getEmail();


                                    Map<String, Object> map = new HashMap<>();
                                    map.put("currentUsers", currentUser);;
                                    map.put("houseArea",model.getHouseArea());
                                    map.put("houseSqFt",model.getHouseSqFt());
                                    map.put("houseBHK",model.getHouseBHK());
                                    map.put("housePrise",model.getHousePrise());
                                    map.put("houseUrl1",model.getHouseUrl1());
                                    map.put("houseOwnerName",model.getHouseOwnerName());
                                    map.put("OwnerEmail",model.getOwnerEmail());
                                    map.put("HouseBillUrl",model.getHouseBillUrl());
                                    map.put("HouseDescription",model.getHouseDescription());
                                    map.put("NearHospitalName",model.getNearHospitalName());
                                    map.put("NearMallsName",model.getNearMallsName());
                                    map.put("NearSchoolName",model.getNearSchoolName());
                                    map.put("hospitalDistance",model.getHospitalDistance());
                                    map.put("houseAdvance",model.getHouseAdvance());
                                    map.put("houseBathroom",model.getHouseBathroom());
                                    map.put("houseFacing",model.getHouseFacing());
                                    map.put("houseFloor",model.getHouseFloor());
                                    map.put("houseFoodPrefer",model.getHouseFoodPrefer());
                                    map.put("houseParking",model.getHouseParking());
                                    map.put("housePetPrefer",model.getHousePetPrefer());
                                    map.put("ownerUId",model.getOwnerUId());
                                    map.put("housePhoneNo",model.getHousePhoneNo());
                                    map.put("housePrefer",model.getHousePrefer());
                                    map.put("houseUrl2",model.getHouseUrl2());
                                    map.put("houseUrl3",model.getHouseUrl3());
                                    map.put("houseUrl4",model.getHouseUrl4());
                                    map.put("houseWaterSupply",model.getHouseWaterSupply());
                                    map.put("mallDistance",model.getMallDistance());
                                    map.put("nearPetrolBunkDistance",model.getNearPetrolBunkDistance());
                                    map.put("nearbusStopDistance",model.getNearbusStopDistance());
                                    map.put("schoolDistance",model.getSchoolDistance());
                                    map.put("houseAddress",model.getHouseAddress());
                                    map.put("type","HOUSE");


                                    assert currentUser != null;
                                    FirebaseDatabase.getInstance().getReference().child("RentIt").child("Favourite")
                                            .push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(Daddress.getContext(), "Item Added to Favourite list", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                               }
                                    i = 0;
                                }
                        },1000);

                        }

                });

                dialogPlus.show();
            }
        });


    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_rv_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        //CircleImageView img;
        ImageView img;
        TextView Address,Prise,Area;
        CardView cardView;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

           // img = (CircleImageView)itemView.findViewById(R.id.objImg);
            img = (ImageView)itemView.findViewById(R.id.objImg);
            Address = (TextView) itemView.findViewById(R.id.ObjName);
            Prise = (TextView) itemView.findViewById(R.id.ObjPrise);
            Area = (TextView)itemView.findViewById(R.id.ObjArea);

           cardView = (CardView)itemView.findViewById(R.id.card1);

        }
    }
}
