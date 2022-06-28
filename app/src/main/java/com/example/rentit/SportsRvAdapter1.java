package com.example.rentit;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class SportsRvAdapter1  extends FirebaseRecyclerAdapter<SportRvModel,SportsRvAdapter1.myViewHolder> {

    EditText SportAddress1, SportBrand1, SportColor1, SportMeteral1, SportName1, SportAdvance1, SportPack1, SportRent1, SportIdel1, SportArea1, SportDesc1;
    Button Next;
    String OwnerNo;

    ImageView Img2,Img3,Img4,profileImg;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SportsRvAdapter1(@NonNull FirebaseRecyclerOptions<SportRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull SportRvModel model) {
        holder.Address.setText(model.getSpoModel());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.Address.getContext());
                builder.setTitle("Are You Sure!!!");
                builder.setMessage("item will Delete Permanently");


                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("car")
                                .child(getRef(position).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.Address.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.Address.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getSpoUrl1());
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
                            Toast.makeText(holder.img.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.camera_rv_details))
                        .setExpanded(true, 2200)
                        .create();
                View view = dialogPlus.getHolderView();

                SportAddress1 =view.findViewById(R.id.SportAddress);
                SportBrand1 = view.findViewById(R.id.SportBrand);
                SportColor1 = view.findViewById(R.id.SportColor);
                SportMeteral1 = view.findViewById(R.id.SportMeteral);
                SportName1 = view.findViewById(R.id.SportName);
                SportAdvance1 = view.findViewById(R.id.SportAdvance);
                SportPack1 = view.findViewById(R.id.SportPack);
                SportRent1 = view.findViewById(R.id.SportRent);
                SportIdel1 = view.findViewById(R.id.SportIdel);
                SportArea1 = view.findViewById(R.id.SportArea);
                SportDesc1 = view.findViewById(R.id.SportDesc);
                Next = view.findViewById(R.id.NextButton);

                profileImg = view.findViewById(R.id.objImg1);
                Img2 = (ImageView)view.findViewById(R.id.objImg2);
                Img3 = (ImageView)view.findViewById(R.id.objImg3);
                Img4 = (ImageView)view.findViewById(R.id.objImg4);

                SportAddress1.setText(model.getSpoAddress());
                SportBrand1.setText(model.getSpoBrand());
                SportColor1.setText(model.getSpoColor());
                SportMeteral1.setText(model.getSpoMeterial());
                SportName1.setText(model.getSpoName());
                SportAdvance1.setText(model.getSpoAdvance());
                SportPack1.setText(model.getSpoPacks());
                SportRent1.setText(model.getSpoRent());
                SportIdel1.setText(model.getSpoIdelFor());
                SportArea1.setText(model.getSpoArea());
                SportDesc1.setText(model.getSpoDesc());

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

                                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getSpoUrl1());
                                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + model.getSpoUrl2());
                                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + model.getSpoUrl3());
                                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + model.getSpoUrl4());
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
                                                    Toast.makeText(SportBrand1.getContext(), "please wait", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(SportBrand1.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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
                        map.put("spoAddress", SportAddress1.getText().toString().trim());
                        map.put("spoAdvance", SportAdvance1.getText().toString().trim());
                        map.put("spoArea", SportArea1.getText().toString().trim());
                        map.put("spoBrand", SportBrand1.getText().toString().trim());
                        map.put("spoColor", SportColor1.getText().toString().trim());
                        map.put("spoDesc", SportDesc1.getText().toString().trim());
                        map.put("spoIdelFor", SportIdel1.getText().toString().trim());
                        map.put("spoMeterial", SportMeteral1.getText().toString().trim());
                        map.put("spoName", SportName1.getText().toString().trim());
                        map.put("spoPacks", SportPack1.getText().toString().trim());
                        map.put("spoRent", SportRent1.getText().toString().trim());
                        map.put("type", "Sports");

                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("sports")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(SportBrand1.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SportBrand1.getContext(), "Failed updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_rv_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView Address;
        CardView cardView;
        ImageButton delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.objImgCart);
            Address = (TextView) itemView.findViewById(R.id.ObjNameCart);
            delete = itemView.findViewById(R.id.delete);

            cardView = (CardView) itemView.findViewById(R.id.CardCart);
        }
    }
}