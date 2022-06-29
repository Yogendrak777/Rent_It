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

public class ClothRvAdapter extends FirebaseRecyclerAdapter<ClothRvModel,ClothRvAdapter.myViewHolder> {
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
    public ClothRvAdapter(@NonNull FirebaseRecyclerOptions<ClothRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ClothRvModel model) {
        holder.ClothName.setText(model.getClothName());
        holder.ClothBrand.setText(model.getClothBrand());
        holder.ClothPrise.setText(model.getClothRent());
        holder.ClothArea.setText(model.getClothArea());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1;
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("FavouriteCloth");

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getClothUrl1());
        try {
            File file = File.createTempFile("randomKey", "");
            storageReference1.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(holder.imgOfCloth.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            holder.imgOfCloth.setImageBitmap(bitmap);

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


        holder.cardClot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfCloth.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cloth_detail_page))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                final String randomKey = UUID.randomUUID().toString();
                TextView DClothName = view.findViewById(R.id.DClotheName);
                TextView DClothAddress = view.findViewById(R.id.DClothAddress);
                TextView DClothAdv = view.findViewById(R.id.DClothAdvance);
                TextView DClothRent = view.findViewById(R.id.DClothRent);
                TextView DClothBrand = view.findViewById(R.id.DClothBrand);
                TextView DClothColor = view.findViewById(R.id.DClothColor);
                TextView DClothSize = view.findViewById(R.id.DClothSize);
                TextView DClothMeterial = view.findViewById(R.id.DClothMeterial);
                TextView DClothFit = view.findViewById(R.id.DClothFitType);
                TextView DClothType = view.findViewById(R.id.DClothType);
                TextView DClothDesc = view.findViewById(R.id.DclothDescription);

                ImageView imgC1 = view.findViewById(R.id.objClothImg1);
                ImageView imgC2 = view.findViewById(R.id.objClothImg2);
                ImageView imgC3 = view.findViewById(R.id.objClothImg3);
                ImageView imgC4 = view.findViewById(R.id.objClothImg4);

                DClothName.setText(model.getClothName()+", "+model.getClothMetal());
                DClothAddress.setText(model.getClothAddress()+", "+model.getClothArea());
                DClothAdv.setText(model.getClothAdvance());
                DClothRent.setText(model.getClothRent());
                DClothBrand.setText(model.getClothBrand());
                DClothColor.setText(model.getClothColor());
                DClothSize.setText(model.getClothSizeNo());
                DClothMeterial.setText(model.getClothMetal());
                DClothFit.setText(model.getClothFitSize());
                DClothType.setText(model.getClothType());
                DClothDesc.setText(model.getClothDesc());

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getClothUrl1());
                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + model.getClothUrl2());
                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + model.getClothUrl3());
                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + model.getClothUrl4());
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
                        intent.putExtra(Intent.EXTRA_TEXT,model.getClothBrand() + "," + model.getClothColor() + "," + model.getClothRent());
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
                                    if (ds.child("ObjUrl1").getValue().equals(model.getClothUrl1())) {
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
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteCloth")
                                .child(ran).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.imgOfCloth.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
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
                        map.put("Random", randomKey);
                        map.put("ObjUrl1", model.getClothUrl1());
                        map.put("clothAddress",model.getClothAddress());
                        map.put("clothAdvance",model.getClothAdvance());
                        map.put("clothArea",model.getClothArea());
                        map.put("clothBrand",model.getClothBrand());
                        map.put("clothColor",model.getClothColor());
                        map.put("clothDesc",model.getClothDesc());
                        map.put("clothFitSize",model.getClothFitSize());
                        map.put("clothMetal",model.getClothMetal());
                        map.put("clothName",model.getClothName());
                        map.put("clothRent",model.getClothRent());
                        map.put("ClothFit",model.getClothFit());
                        map.put("clothUrl1",model.getClothUrl1());
                        map.put("clothUrl2",model.getClothUrl2());
                        map.put("clothUrl3",model.getClothUrl3());
                        map.put("clothUrl4",model.getClothUrl4());
                        map.put("type","CLOTHS");
                        map.put("OwnerEmail",user.getEmail());
                        map.put("UserId",user.getUid());
                        map.put("PhoneNo", model.getPhoneNo());

                        assert currentUser != null;
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteCloth").child(randomKey)
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cloth_rv_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfCloth;
        TextView ClothName,ClothPrise,ClothArea,ClothBrand;
        CardView cardClot;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfCloth = (ImageView)itemView.findViewById(R.id.objImgCloth);
            ClothName = (TextView)itemView.findViewById(R.id.ObjClothModel);
            ClothPrise = (TextView)itemView.findViewById(R.id.ObjClothPrise);
            ClothArea = (TextView)itemView.findViewById(R.id.ObjClothArea);
            ClothBrand = (TextView) itemView.findViewById(R.id.ObjClothBrand);
            cardClot = (CardView)itemView.findViewById(R.id.CardClot);

        }
    }
}
