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

public class SpeakerRvAdapter extends FirebaseRecyclerAdapter<SpeakerRvModel,SpeakerRvAdapter.myViewHolder> {
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

    public SpeakerRvAdapter(@NonNull FirebaseRecyclerOptions<SpeakerRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull SpeakerRvModel model) {
        holder.SpeModel.setText(model.getSpeBrand()+", "+model.getSpeModel());
        holder.SpePrise.setText(model.getSpeRent());
        holder.SpeArea.setText(model.getSpeArea());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1;
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("FavouriteSpeaker");

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getSpeUrl1());
        try {
            File file = File.createTempFile("randomKey", "");
            storageReference1.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(holder.imgOfSpe.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            holder.imgOfSpe.setImageBitmap(bitmap);

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

        holder.cardSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfSpe.getContext())
                        .setContentHolder(new ViewHolder(R.layout.speaker_rv_details_page))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                final String randomKey = UUID.randomUUID().toString();
                TextView DSpeModel = view.findViewById(R.id.DSpeModel);
                TextView DSpeAddress = view.findViewById(R.id.DSpeAddress);
                TextView DSpeAdv = view.findViewById(R.id.DSpeAdvance);
                TextView DSpeRent = view.findViewById(R.id.DSpeRent);
                TextView DSpeBlue = view.findViewById(R.id.DSpeBlue);
                TextView DSprDis = view.findViewById(R.id.DSpeDis);
                TextView DSpePow = view.findViewById(R.id.DSpePow);
                TextView DSpeType = view.findViewById(R.id.DSpeType);
                TextView DSpeMeo = view.findViewById(R.id.DSpeMemoSlot);
                TextView DSpeDisc = view.findViewById(R.id.DSpeDesc);

                ImageView imgC1 = view.findViewById(R.id.objSpeImg1);
                ImageView imgC2 = view.findViewById(R.id.objSpeImg2);
                ImageView imgC3 = view.findViewById(R.id.objSpeImg3);
                ImageView imgC4 = view.findViewById(R.id.objSpeImg4);

                DSpeModel.setText(model.getSpeBrand()+", "+model.getSpeModel());
                DSpeAddress.setText(model.getSpeAddress()+", "+model.getSpeArea());
                DSpeAdv.setText(model.getSpeAdvance());
                DSpeRent.setText(model.getSpeRent());
                DSpeBlue.setText(model.getSpeBluetooth());
                DSprDis.setText(model.getSpeDisplayType());
                DSpePow.setText(model.getSpePowerSource());
                DSpeType.setText(model.getSpeType());
                DSpeMeo.setText(model.getSpeMemoryCardSlot());
                DSpeDisc.setText(model.getSpeDescription());

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getSpeUrl1());
                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + model.getSpeUrl2());
                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + model.getSpeUrl3());
                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + model.getSpeUrl4());
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
                        intent.putExtra(Intent.EXTRA_TEXT,model.getSpeBrand() + "," + model.getSpeRent() + "," + model.getSpeModel());
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
                                    if (ds.child("ObjUrl1").getValue().equals(model.getSpeUrl1())) {
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
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteSpeaker")
                                .child(ran).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.imgOfSpe.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
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
                        map.put("ObjUrl1", model.getSpeUrl1());
                        map.put("speAddress",model.getSpeAddress());
                        map.put("speAdvance", model.getSpeAdvance());
                        map.put("speArea", model.getSpeArea());
                        map.put("speBluetooth",model.getSpeBluetooth());
                        map.put("speDescription", model.getSpeDescription());
                        map.put("speDisplayType", model.getSpeDisplayType());
                        map.put("speMemoryCardSlot", model.getSpeMemoryCardSlot());
                        map.put("speModel",model.getSpeModel());
                        map.put("spePowerSource", model.getSpePowerSource());
                        map.put("speRent", model.getSpeRent());
                        map.put("speType", model.getSpeType());
                        map.put("speUrl1", model.getSpeUrl1());
                        map.put("speUrl2", model.getSpeUrl2());
                        map.put("speUrl3", model.getSpeUrl3());
                        map.put("speUrl4", model.getSpeUrl4());
                        map.put("type", "SPEAKER");
                        map.put("OwnerEmail", user.getEmail());
                        map.put("phoneNo", model.getPhoneNo());
                        map.put("UserId", user.getUid());


                        assert currentUser != null;
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteSpeaker").child(randomKey)
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speaker_rv_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfSpe;
        TextView SpeModel,SpePrise,SpeArea;
        CardView cardSpeaker;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfSpe = (ImageView)itemView.findViewById(R.id.objSpeImg);
            SpeModel = (TextView)itemView.findViewById(R.id.ObjSpeName);
            SpePrise = (TextView)itemView.findViewById(R.id.ObjSpePrise);
            SpeArea = (TextView)itemView.findViewById(R.id.ObjSpeArea);
            cardSpeaker = (CardView)itemView.findViewById(R.id.CardSpeak);

        }
    }
}
