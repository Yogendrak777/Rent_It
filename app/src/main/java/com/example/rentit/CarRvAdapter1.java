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

public class CarRvAdapter1 extends FirebaseRecyclerAdapter<carRvModel,CarRvAdapter1.myViewHolder> {

    EditText Name1,Address1,Advance1,Rent1,Area1,Desc1,model1,Service1;
    String TRASNMISSION1,FUEL1,MILLAGE1,AIRBAG1,SEATS1,CARAGE1,GEARBOX1,FASTTAG1,BODYTYPE1,OwnerNo;
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
    public CarRvAdapter1(@NonNull FirebaseRecyclerOptions<carRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull carRvModel model) {
        holder.Address.setText(model.getCarModel());
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

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getCarUrl1());
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
                        .setContentHolder(new ViewHolder(R.layout.activity_car_info4))
                        .setExpanded(true, 2200)
                        .create();
                View view = dialogPlus.getHolderView();
                Name1 = (EditText)view.findViewById(R.id.Name);
                Address1 = (EditText)view.findViewById(R.id.Address);
                Advance1 = (EditText)view.findViewById(R.id.Advance);
                Rent1 = (EditText)view.findViewById(R.id.Rent);
                Area1 = (EditText)view.findViewById(R.id.Area);
                Desc1 = (EditText)view.findViewById(R.id.CarDesc);
                model1 = (EditText)view.findViewById(R.id.modelName);
                Service1 = view.findViewById(R.id.service);

                upload = (Button)view.findViewById(R.id.Upload);

                Spinner Trans = (Spinner)view.findViewById(R.id.GearSpinner);
                Spinner Fuel = (Spinner)view.findViewById(R.id.FuelSpinner);
                Spinner Millage = (Spinner)view.findViewById(R.id.MillageSpinner);
                Spinner AirBag = (Spinner)view.findViewById(R.id.AirBagSpinner);
                Spinner Seats = (Spinner)view.findViewById(R.id.SeatsSpinner);
                Spinner CarAge = (Spinner)view.findViewById(R.id.AgeSpinner);
                Spinner GearBox = (Spinner)view.findViewById(R.id.BoxSpeedSpinner);
                Spinner FastTag = (Spinner)view.findViewById(R.id.FastTagSpinner);
                Spinner BodyType = (Spinner)view.findViewById(R.id.BodyTypeSpinner);

                profileImg =view.findViewById(R.id.objImg1);
                Img2 = (ImageView)view.findViewById(R.id.objImg2);
                Img3 = (ImageView)view.findViewById(R.id.objImg3);
                Img4 = (ImageView)view.findViewById(R.id.objImg4);

                Name1.setText(model.getCarOwnerName());
                Address1.setText(model.getCarAddress());
                Advance1.setText(model.getCarAdvance());
                Rent1.setText(model.getCarPrice());
                Area1.setText(model.getCarArea());
                Desc1.setText(model.getCarDescription());
                model1.setText(model.getCarModel());
                Service1.setText(model.getCarServiceDate());

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Trans.getContext(),R.array.Transmission, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Trans.setAdapter(adapter);
                int Tra;
                Tra = adapter.getPosition(model.getCarTransmission());
                Trans.setSelection(Tra);
                Trans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        TRASNMISSION1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(Fuel.getContext(),R.array.Fuel, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Fuel.setAdapter(adapter1);
                int Ful;
                Ful = adapter1.getPosition(model.getCarFuel());
                Fuel.setSelection(Ful);
                Fuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FUEL1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Millage.getContext(), R.array.Millage, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Millage.setAdapter(adapter2);
                int mil;
                mil = adapter2.getPosition(model.getCarMilage());
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

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(AirBag.getContext(), R.array.AirBags, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                AirBag.setAdapter(adapter3);
                int air;
                air = adapter3.getPosition(model.getCarAirbag());
                AirBag.setSelection(air);
                AirBag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        AIRBAG1 = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(Seats.getContext(), R.array.Seats, android.R.layout.simple_spinner_item);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Seats.setAdapter(adapter4);
                int set;
                set = adapter4.getPosition(model.getCarSeats());
                Seats.setSelection(set);
                Seats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        SEATS1 = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(CarAge.getContext(),R.array.CarAge, android.R.layout.simple_spinner_item);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                CarAge.setAdapter(adapter5);
                int car;
                car = adapter5.getPosition(model.getCarAge());
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

                ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(GearBox.getContext(), R.array.GearBox, android.R.layout.simple_spinner_item);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                GearBox.setAdapter(adapter6);
                int gear;
                gear = adapter6.getPosition(model.getCarGearBox());
                GearBox.setSelection(gear);
                GearBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        GEARBOX1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(FastTag.getContext(), R.array.FastTag, android.R.layout.simple_spinner_item);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                FastTag.setAdapter(adapter7);
                int fast;
                fast =  adapter7.getPosition(model.getCarFastTag());
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

                ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(BodyType.getContext(), R.array.BodyType, android.R.layout.simple_spinner_item);
                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                BodyType.setAdapter(adapter8);
                int body;
                body = adapter8.getPosition(model.getCarBodyType());
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

                                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getCarUrl());
                                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + model.getCarUrl1());
                                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + model.getCarUrl2());
                                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + model.getCarUrl3());
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
                        map.put("CarOwnerName", Name1.getText().toString().trim());
                        map.put("carAddress", Address1.getText().toString().trim());
                        map.put("carAdvance", Advance1.getText().toString().trim());
                        map.put("carAge", CARAGE1);
                        map.put("carAirbag", AIRBAG1);
                        map.put("carArea", Area1.getText().toString().trim());
                        map.put("carBodyType", BODYTYPE1);
                        map.put("carDescription", Desc1.getText().toString());
                        map.put("carFastTag", FASTTAG1);
                        map.put("carFuel", FUEL1);
                        map.put("carGearBox", GEARBOX1);
                        map.put("carMilage", MILLAGE1);
                        map.put("carModel", model1.getText().toString());
                        map.put("carPrice", Rent1.getText().toString());
                        map.put("carSeats", SEATS1);
                        map.put("carServiceDate", Service1.getText().toString());
                        map.put("carTransmission", TRASNMISSION1);

                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("car")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.Address.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.Address.getContext(), "Failed updated", Toast.LENGTH_SHORT).show();
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
