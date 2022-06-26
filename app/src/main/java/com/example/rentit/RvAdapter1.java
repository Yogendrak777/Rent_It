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

public class RvAdapter1 extends FirebaseRecyclerAdapter<houseRvModel,RvAdapter1.myViewHolder> {

    EditText Name1,Address1,Advance1,Rent1,SqFt1,Area1,MallName1,HospitalName1,SchoolName1,Desc1;
    ImageView img1,img2,img3,img4;
    String parking1,BHK1,WATER1,FLOOR1,FACING1,BATHROOMS1,FAMILY1,FOOD1,PET1;
    Spinner MallDis1,SchoolDis1,HospitalDis1,FuelDis1,BusDis1;
    String MallDistance,SchoolDistance,HospitalDistance,FuelDistance,BusDistance;
    Button Upload;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RvAdapter1(@NonNull FirebaseRecyclerOptions<houseRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull houseRvModel model) {
        holder.Address.setText(model.getHouseBHK()+", "+model.getHouseAddress());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.Address.getContext());
                builder.setTitle("Are You Sure!!!");
                builder.setMessage("item will Delete Permanently");


                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("house")
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
                        .setContentHolder(new ViewHolder(R.layout.activity_house_info5))
                        .setExpanded(true, 2200)
                        .create();
                View view = dialogPlus.getHolderView();
                FirebaseDatabase firebaseDatabase;
                DatabaseReference databaseReference1;
                FirebaseAuth firebaseAuth;


                Name1 = (EditText)view.findViewById(R.id.Name);
                Address1 = (EditText)view.findViewById(R.id.Address);
                Advance1 = (EditText)view.findViewById(R.id.Advance);
                Rent1 = (EditText)view.findViewById(R.id.Rent);
                SqFt1 = (EditText)view.findViewById(R.id.SqFt);
                Area1 = (EditText)view.findViewById(R.id.Area);
                MallName1 = (EditText)view.findViewById(R.id.MallName);
                SchoolName1 = (EditText)view.findViewById(R.id.SchoolName);
                HospitalName1 = (EditText)view.findViewById(R.id.HospitalName);
                Desc1 = (EditText)view.findViewById(R.id.houseDesc);

                Spinner park = (Spinner)view.findViewById(R.id.ParkingSpinner);
                Spinner bhk = (Spinner)view.findViewById(R.id.BhkSpinner);
                Spinner water = (Spinner)view.findViewById(R.id.waterSpinner);
                Spinner floor = (Spinner)view.findViewById(R.id.FloorSpinner);
                Spinner Facing = (Spinner)view.findViewById(R.id.FacingSpinner);
                Spinner Bathrooms = (Spinner)view.findViewById(R.id.BathroomsSpinner);
                Spinner Family = (Spinner)view.findViewById(R.id.FamilySpinner);
                Spinner Food = (Spinner)view.findViewById(R.id.FoodTypeSpinner);
                Spinner Pet = (Spinner)view.findViewById(R.id.PetsSpinner);

                MallDis1 = (Spinner)view.findViewById(R.id.MAllDisSpinner);
                SchoolDis1 = (Spinner)view.findViewById(R.id.SchoolDisSpinner);
                HospitalDis1 = (Spinner)view.findViewById(R.id.HospitalDisSpinner);
                FuelDis1 = (Spinner)view.findViewById(R.id.FuelDisSpinner);
                BusDis1 = (Spinner)view.findViewById(R.id.BusDisSpinner);

                img1 = (ImageView)view.findViewById(R.id.objImg1);
                img2 = (ImageView)view.findViewById(R.id.objImg2);
                img3 = (ImageView)view.findViewById(R.id.objImg3);
                img4 = (ImageView)view.findViewById(R.id.objImg4);

                Upload = (Button)view.findViewById(R.id.Upload) ;

                Name1.setText(model.getHouseOwnerName());
                Address1.setText(model.getHouseAddress());
                Advance1.setText(model.getHouseAdvance());
                Rent1.setText(model.getHousePrise());
                SqFt1.setText(model.getHouseSqFt());
                Area1.setText(model.getHouseArea());
                Desc1.setText(model.getHouseDescription());

                SchoolName1.setText(model.getNearSchoolName());
                MallName1.setText(model.getNearMallsName());
                HospitalName1.setText(model.NearHospitalName);


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



                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(park.getContext(), R.array.parking, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                int parkingPos;
                parkingPos = adapter.getPosition(model.getHouseParking());
                park.setAdapter(adapter);
                park.setSelection(parkingPos);
                park.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        parking1 = adapterView.getItemAtPosition(i).toString();

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(bhk.getContext(),R.array.bhk, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bhk.setAdapter(adapter1);
                int BhkPos;
                BhkPos = adapter1.getPosition(model.getHouseBHK());
                bhk.setSelection(BhkPos);
                bhk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        BHK1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(water.getContext(), R.array.water, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                water.setAdapter(adapter2);
                int waterPos;
                waterPos = adapter2.getPosition(model.getHouseWaterSupply());
                water.setSelection(waterPos);
                water.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        WATER1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(floor.getContext(), R.array.Floor, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                floor.setAdapter(adapter3);
                int floorPos;
                floorPos = adapter3.getPosition(model.getHouseFloor());
                floor.setSelection(floorPos);
                floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FLOOR1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(Facing.getContext(), R.array.Facing, android.R.layout.simple_spinner_item);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Facing.setAdapter(adapter4);
                int facingPos;
                facingPos = adapter4.getPosition(model.getHouseFacing());
                Facing.setSelection(facingPos);
                Facing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FACING1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(Bathrooms.getContext(), R.array.bathrooms, android.R.layout.simple_spinner_item);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Bathrooms.setAdapter(adapter5);
                int bathroomPos;
                bathroomPos = adapter5.getPosition(model.getHouseBathroom());
                Bathrooms.setSelection(bathroomPos);
                Bathrooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        BATHROOMS1 = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(Family.getContext(), R.array.Family, android.R.layout.simple_spinner_item);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Family.setAdapter(adapter6);
                int familyPos;
                familyPos = adapter6.getPosition(model.getHousePrefer());
                Family.setSelection(familyPos);
                Family.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FAMILY1 = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(Food.getContext(), R.array.Food, android.R.layout.simple_spinner_item);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Food.setAdapter(adapter7);
                int FoodPos;
                FoodPos = adapter7.getPosition(model.getHouseFoodPrefer());
                Food.setSelection(FoodPos);
                Food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FOOD1 = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(Pet.getContext(), R.array.Pet, android.R.layout.simple_spinner_item);
                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Pet.setAdapter(adapter8);
                int petPos;
                petPos = adapter8.getPosition(model.getHousePetPrefer());
                Pet.setSelection(petPos);
                Pet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        PET1 = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(MallDis1.getContext(), R.array.Distance, android.R.layout.simple_spinner_item);
                adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                MallDis1.setAdapter(adapter9);
                int DistPos;
                DistPos = adapter9.getPosition(model.getMallDistance());
                MallDis1.setSelection(DistPos);
                MallDis1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        MallDistance = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(SchoolDis1.getContext(), R.array.Distance, android.R.layout.simple_spinner_item);
                adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SchoolDis1.setAdapter(adapter10);
                int DisPos1;
                DisPos1 = adapter10.getPosition(model.getSchoolDistance());
                SchoolDis1.setSelection(DisPos1);
                SchoolDis1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        SchoolDistance = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(HospitalDis1.getContext(), R.array.Distance, android.R.layout.simple_spinner_item);
                adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                HospitalDis1.setAdapter(adapter11);
                int DisPos2;
                DisPos2 = adapter11.getPosition(model.getHospitalDistance());
                HospitalDis1.setSelection(DisPos2);
                HospitalDis1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        HospitalDistance = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(FuelDis1.getContext(), R.array.Distance, android.R.layout.simple_spinner_item);
                adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                FuelDis1.setAdapter(adapter12);
                int DistancePos3;
                DistancePos3 = adapter12.getPosition(model.getNearPetrolBunkDistance());
                FuelDis1.setSelection(DistancePos3);
                FuelDis1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FuelDistance = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(BusDis1.getContext(), R.array.Distance, android.R.layout.simple_spinner_item);
                adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                BusDis1.setAdapter(adapter13);
                int DisPos4;
                DisPos4 = adapter13.getPosition(model.getNearbusStopDistance());
                BusDis1.setSelection(DisPos4);
                BusDis1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        BusDistance = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                Upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map = new HashMap<>();
                        map.put("houseOwnerName",Name1.getText().toString().trim());
                        map.put("HouseDescription",Desc1.getText().toString().trim());
                        map.put("NearHospitalName",Name1.getText().toString().trim());
                        map.put("NearMallsName",MallName1.getText().toString().trim());
                        map.put("NearSchoolName",SchoolName1.getText().toString().trim());
                        map.put("hospitalDistance",HospitalDistance);
                        map.put("houseArea",Area1.getText().toString().trim());
                        map.put("houseSqFt",SqFt1.getText().toString().trim());
                        map.put("houseAdvance",Advance1.getText().toString().trim());
                        map.put("houseBathroom",BATHROOMS1);
                        map.put("houseFacing",FACING1);
                        map.put("houseFloor",FLOOR1);
                        map.put("houseFoodPrefer",FOOD1);
                        map.put("houseParking",parking1);
                        map.put("housePetPrefer",PET1);
                        map.put("housePrefer",FAMILY1);
//                        map.put("houseUrl2",ImgUrl2);
//                        map.put("houseUrl3",ImgUrl3);
//                        map.put("houseUrl4",ImgUrl4);
                        map.put("houseWaterSupply",WATER1);
                        map.put("mallDistance",MallDistance);
                        map.put("nearPetrolBunkDistance",FuelDistance);
                        map.put("nearbusStopDistance",BusDistance);
                        map.put("schoolDistance",SchoolDistance);
                        map.put("houseAddress",Address1.getText().toString().trim());
                        map.put("houseBHK",BHK1);
                        map.put("housePrise",Rent1.getText().toString().trim());
//                        map.put("houseUrl1",ImgUrl1);

                        FirebaseDatabase.getInstance().getReference().child("RentIt").child("house")
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

    class myViewHolder extends RecyclerView.ViewHolder {

        //CircleImageView img;
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
