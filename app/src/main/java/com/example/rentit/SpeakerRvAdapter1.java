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

public class SpeakerRvAdapter1  extends FirebaseRecyclerAdapter<SpeakerRvModel,SpeakerRvAdapter1.myViewHolder> {

    EditText SpeAddress1, SpeModel1, SpeRent1, SpeAdvance1, SpeArea1, SpeDec1;
    String BLUETOOTH1, DISPLAY1, MEMORY1, POWER1, SPETYPE1;
    Spinner Bluetooth,Display,Memory,Power,SpeType;
    Button uplaod;
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
    public SpeakerRvAdapter1(@NonNull FirebaseRecyclerOptions<SpeakerRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull SpeakerRvModel model) {
        holder.Address.setText(model.getSpeModel());
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

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getSpeUrl1());
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
                SpeAddress1 =view.findViewById(R.id.SpeAddress);
                SpeModel1 =view.findViewById(R.id.SpemodelName);
                SpeRent1 = view.findViewById(R.id.SpeRent);
                SpeAdvance1 = view.findViewById(R.id.SpeAdvance);
                SpeArea1 = view.findViewById(R.id.SpeArea);
                SpeDec1 = view.findViewById(R.id.SpeDesc);

                profileImg = view.findViewById(R.id.objImg1);
                Img2 = (ImageView)view.findViewById(R.id.objImg2);
                Img3 = (ImageView)view.findViewById(R.id.objImg3);
                Img4 = (ImageView)view.findViewById(R.id.objImg4);

                Bluetooth = view.findViewById(R.id.SpeBluetoothSpinner);
                Display = view.findViewById(R.id.SpeDisplaySpinner);
                Memory = view.findViewById(R.id.SpeMemorySpinner);
                Power = view.findViewById(R.id.SpePowerSpinner);
                SpeType = view.findViewById(R.id.SpeTypeSpinner);
                uplaod = view.findViewById(R.id.upload);

                SpeAddress1.setText(model.getSpeAddress());
                SpeModel1.setText(model.getSpeModel());
                SpeRent1.setText(model.getSpeRent());
                SpeAdvance1.setText(model.getSpeAdvance());
                SpeArea1.setText(model.getSpeArea());
                SpeDec1.setText(model.getSpeDescription());

                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Bluetooth.getContext(), R.array.SpeakerBluetooth, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Bluetooth.setAdapter(adapter2);
                int blue;
                blue = adapter2.getPosition(model.getSpeBluetooth());
                Bluetooth.setSelection(blue);
                Bluetooth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        BLUETOOTH1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(Display.getContext(), R.array.SpeakerDisplay, android.R.layout.simple_spinner_item);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Display.setAdapter(adapter5);
                int dis;
                dis = adapter5.getPosition(model.getSpeDisplayType());
                Display.setSelection(dis);
                Display.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        DISPLAY1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(Power.getContext(), R.array.SpeakerPower, android.R.layout.simple_spinner_item);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Power.setAdapter(adapter7);
                int pow;
                pow = adapter7.getPosition(model.getSpePowerSource());
                Power.setSelection(pow);
                Power.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        POWER1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(Memory.getContext(), R.array.SpeakerMemory, android.R.layout.simple_spinner_item);
                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Memory.setAdapter(adapter8);
                int mem;
                mem = adapter8.getPosition(model.getSpeModel());
                Memory.setSelection(mem);
                Memory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        MEMORY1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(SpeType.getContext(), R.array.SpeakerType, android.R.layout.simple_spinner_item);
                adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpeType.setAdapter(adapter9);
                int type;
                type = adapter9.getPosition(model.getSpeType());
                SpeType.setSelection(type);
                SpeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        SPETYPE1 = adapterView.getItemAtPosition(i).toString();
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
                                                    Toast.makeText(Memory.getContext(), "please wait", Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(Memory.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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

                uplaod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("speAddress", SpeAddress1.getText().toString().trim());
                        map.put("speAdvance", SpeAdvance1.getText().toString().trim());
                        map.put("speArea", SpeArea1.getText().toString().trim());
                        map.put("speBluetooth", BLUETOOTH1);
                        map.put("speDescription", SpeDec1.getText().toString().trim());
                        map.put("speDisplayType", DISPLAY1);
                        map.put("speMemoryCardSlot", MEMORY1);
                        map.put("speModel", SpeModel1.getText().toString().trim());
                        map.put("spePowerSource", POWER1);
                        map.put("speRent", SpeRent1.getText().toString().trim());
                        map.put("speType", SPETYPE1);
                        map.put("type", "SPEAKER");
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("speakers")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Memory.getContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Memory.getContext(), "Failed updated", Toast.LENGTH_SHORT).show();
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
