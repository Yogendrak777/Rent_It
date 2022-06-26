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

public class BikeRvAdapter1 extends FirebaseRecyclerAdapter<bikeRvModel,BikeRvAdapter1.myViewHolder> {

    EditText Name1,Address1,Advance1,Rent1,Area1,Desc1,model1,Service1;
    String MILLAGE1,CARAGE1,FASTTAG1,BODYTYPE1,OwnerNo;
    ImageView Img2,Img3,Img4,profileImg;

    Button upload;

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
    public BikeRvAdapter1(@NonNull FirebaseRecyclerOptions<bikeRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull bikeRvModel model) {
        holder.Address.setText(model.getBikeModel());
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

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getBikeUrl1());
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
                        .setContentHolder(new ViewHolder(R.layout.activity_bike_info4))
                        .setExpanded(true, 2200)
                        .create();
                View view = dialogPlus.getHolderView();
                Name1 = (EditText) view.findViewById(R.id.Name);
                Address1 = (EditText) view.findViewById(R.id.Address);
                Advance1 = (EditText) view.findViewById(R.id.Advance);
                Rent1 = (EditText) view.findViewById(R.id.Rent);
                Area1 = (EditText) view.findViewById(R.id.Area);
                Desc1 = (EditText) view.findViewById(R.id.CarDesc);
                model1 = (EditText) view.findViewById(R.id.modelName);
                Service1 = view.findViewById(R.id.service);

                upload = (Button) view.findViewById(R.id.Upload);

                Spinner Millage = (Spinner) view.findViewById(R.id.MillageSpinner);
                Spinner CarAge = (Spinner) view.findViewById(R.id.AgeSpinner);
                Spinner FastTag = (Spinner) view.findViewById(R.id.FastTagSpinner);
                Spinner BodyType = (Spinner) view.findViewById(R.id.BodyTypeSpinner);

                profileImg = view.findViewById(R.id.objImg1);
                Img2 = (ImageView) view.findViewById(R.id.objImg2);
                Img3 = (ImageView) view.findViewById(R.id.objImg3);
                Img4 = (ImageView) view.findViewById(R.id.objImg4);

                Name1.setText(model.getBikeOwnerName());
                Address1.setText(model.getBikeAddress());
                Advance1.setText(model.getBikeAdvance());
                Rent1.setText(model.getBikeRent());
                Area1.setText(model.getBikeArea());
                Desc1.setText(model.getBikeDescription());
                model1.setText(model.getBikeModel());
                Service1.setText(model.getBikeServiceDate());

                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Millage.getContext(), R.array.Millage, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Millage.setAdapter(adapter2);
                int mil;
                mil = adapter2.getPosition(model.getBikeMileage());
                Millage.setSelection(mil);
                Millage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        MILLAGE1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(CarAge.getContext(), R.array.CarAge, android.R.layout.simple_spinner_item);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                CarAge.setAdapter(adapter5);
                int car;
                car = adapter5.getPosition(model.getBikeAge());
                CarAge.setSelection(car);
                CarAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        CARAGE1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(FastTag.getContext(), R.array.FastTag, android.R.layout.simple_spinner_item);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                FastTag.setAdapter(adapter7);
                int fast;
                fast = adapter7.getPosition(model.getBikeFastTag());
                FastTag.setSelection(fast);
                FastTag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FASTTAG1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(BodyType.getContext(), R.array.BikeType, android.R.layout.simple_spinner_item);
                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                BodyType.setAdapter(adapter8);
                int body;
                body = adapter8.getPosition(model.getBikeType());
                BodyType.setSelection(body);

                BodyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        BODYTYPE1 = adapterView.getItemAtPosition(i).toString();
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

                                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl1());
                                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl2());
                                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl3());
                                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl4());
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
                                                    Toast.makeText(Name1.getContext(), "please wait", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Name1.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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

                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Name1.getContext(), "Data is Verifying", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Name1.getContext(), "Data is Processing", Toast.LENGTH_SHORT).show();


                        Map<String, Object> map = new HashMap<>();
                        map.put("bikeOwnerName", Name1.getText().toString().trim());
                        map.put("bikeAddress", Address1.getText().toString().trim());
                        map.put("bikeAdvance", Advance1.getText().toString().trim());
                        map.put("bikeAge", CARAGE1);
                        map.put("bikeArea", Area1.getText().toString().trim());
                        map.put("bikeType", BODYTYPE1);
                        map.put("bikeDescription", Desc1.getText().toString());
                        map.put("bikeFastTag", FASTTAG1);
                        map.put("bikeMileage", MILLAGE1);
                        map.put("bikeModel", model1.getText().toString());
                        map.put("bikeRent", Rent1.getText().toString());
                        map.put("bikeServiceDate", Service1.getText().toString());
                        map.put("type", "BIKE");
                        map.put("OwnerEmail", user.getEmail());
                        map.put("UserId", user.getUid());
                        map.put("PhoneNo", OwnerNo);

                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("Bike")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Name1.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Name1.getContext(), "Failed updated", Toast.LENGTH_SHORT).show();
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
