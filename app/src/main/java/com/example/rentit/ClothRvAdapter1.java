package com.example.rentit;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class ClothRvAdapter1 extends FirebaseRecyclerAdapter<ClothRvModel,ClothRvAdapter1.myViewHolder> {

    EditText ClothName1, ClothAddress1, ClothBrand1, ClothAdvance1,ClothRent1,ClothArea1, ClothColor1,ClothType1, ClothDesc1;
    Spinner Size, Meterial,Fit;
    String SIZE1,METERIAL1,FIT1,OwnerNo;
    Button Next;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;
    ImageView Img2, Img3, Img4,profileImg;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ClothRvAdapter1(@NonNull FirebaseRecyclerOptions<ClothRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull ClothRvModel model) {
        holder.Address.setText(model.getClothBrand());
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

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getClothUrl1());
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
                ClothName1 = view.findViewById(R.id.ClothName);
                ClothAddress1 = view.findViewById(R.id.ClothAddress);
                ClothBrand1 = view.findViewById(R.id.ClothBrand);
                ClothAdvance1 = view.findViewById(R.id.ClothAdvance);
                ClothRent1 = view.findViewById(R.id.ClothRent);
                ClothArea1 = view.findViewById(R.id.ClothArea);
                ClothColor1 =view.findViewById(R.id.ClothColor);
                ClothType1 = view.findViewById(R.id.ClothType);
                ClothDesc1 = view.findViewById(R.id.ClothDesc);

                profileImg = view.findViewById(R.id.objImg1);
                Img2 = (ImageView) view.findViewById(R.id.objImg2);
                Img3 = (ImageView)view.findViewById(R.id.objImg3);
                Img4 = (ImageView) view.findViewById(R.id.objImg4);

                Size = view.findViewById(R.id.ColorSizeSpinner);
                Meterial = view.findViewById(R.id.ColorMeteralSpinner);
                Fit = view.findViewById(R.id.ClothFitSpinner);

                Next = view.findViewById(R.id.NextButton);
                ClothName1.setText(model.getClothOwnerName());
                ClothAddress1.setText(model.getClothAddress());
                ClothAdvance1.setText(model.getClothAdvance());
                ClothBrand1.setText(model.getClothBrand());
                ClothRent1.setText(model.getClothRent());
                ClothArea1.setText(model.getClothArea());
                ClothColor1.setText(model.getClothColor());
                ClothType1.setText(model.getClothType());
                ClothDesc1.setText(model.getClothDesc());

                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Size.getContext(), R.array.ClothSize, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                int size;
                size = adapter2.getPosition(model.getClothFitSize());
                Size.setSelection(size);
                Size.setAdapter(adapter2);
                Size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        SIZE1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(Meterial.getContext(), R.array.ClothMeterial, android.R.layout.simple_spinner_item);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                int meteral;
                meteral = adapter5.getPosition(model.getClothMetal());
                Meterial.setSelection(meteral);
                Meterial.setAdapter(adapter5);
                Meterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        METERIAL1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(Fit.getContext(), R.array.ClothFit, android.R.layout.simple_spinner_item);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                int fit;
                fit = adapter7.getPosition(model.getClothFit());
                Fit.setSelection(fit);
                Fit.setAdapter(adapter7);
                Fit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FIT1 = adapterView.getItemAtPosition(i).toString();
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
                                                    Toast.makeText(Fit.getContext(), "please wait", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Fit.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(Fit.getContext(), "Data is Verifying", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Fit.getContext(), "Data is Processing", Toast.LENGTH_SHORT).show();


                        Map<String,Object> map = new HashMap<>();
                        map.put("clothOwnerName",ClothName1.getText().toString().trim());
                        map.put("clothAddress",ClothAddress1.getText().toString().trim());
                        map.put("clothAdvance",ClothAdvance1.getText().toString().trim());
                        map.put("clothArea",ClothArea1.getText().toString().trim());
                        map.put("clothBrand",ClothBrand1.getText().toString().trim());
                        map.put("clothColor",ClothColor1.getText().toString().trim());
                        map.put("clothDesc",ClothDesc1.getText().toString().trim());
                        map.put("clothFitSize",SIZE1);
                        map.put("clothMetal",METERIAL1);
                        map.put("clothName",ClothType1.getText().toString().trim());
                        map.put("clothRent",ClothRent1.getText().toString());
                        map.put("ClothFit",FIT1);


                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("clothes")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Fit.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Fit.getContext(), "Failed updated", Toast.LENGTH_SHORT).show();
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
