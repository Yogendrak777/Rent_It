package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HouseInfo5 extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
    FirebaseAuth firebaseAuth;

    EditText Name1,Address1,Advance1,Rent1,SqFt1,Area1,MallName1,HospitalName1,SchoolName1,Desc1;
    ImageView img1,img2,img3,img4;
    String parking1,BHK1,WATER1,FLOOR1,FACING1,BATHROOMS1,FAMILY1,FOOD1,PET1,OwnerNo;
    Spinner MallDis1,SchoolDis1,HospitalDis1,FuelDis1,BusDis1;
    String MallDistance,SchoolDistance,HospitalDistance,FuelDistance,BusDistance;
    Button Upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info5);
        Name1 = (EditText)findViewById(R.id.Name);
        Address1 = (EditText)findViewById(R.id.Address);
        Advance1 = (EditText)findViewById(R.id.Advance);
        Rent1 = (EditText)findViewById(R.id.Rent);
        SqFt1 = (EditText)findViewById(R.id.SqFt);
        Area1 = (EditText)findViewById(R.id.Area);
        MallName1 = (EditText)findViewById(R.id.MallName);
        SchoolName1 = (EditText)findViewById(R.id.SchoolName);
        HospitalName1 = (EditText)findViewById(R.id.HospitalName);
        Desc1 = (EditText)findViewById(R.id.houseDesc);

        Spinner park = (Spinner) findViewById(R.id.ParkingSpinner);
        Spinner bhk = (Spinner) findViewById(R.id.BhkSpinner);
        Spinner water = (Spinner) findViewById(R.id.waterSpinner);
        Spinner floor = (Spinner) findViewById(R.id.FloorSpinner);
        Spinner Facing = (Spinner) findViewById(R.id.FacingSpinner);
        Spinner Bathrooms = (Spinner)findViewById(R.id.BathroomsSpinner);
        Spinner Family = (Spinner)findViewById(R.id.FamilySpinner);
        Spinner Food = (Spinner)findViewById(R.id.FoodTypeSpinner);
        Spinner Pet = (Spinner)findViewById(R.id.PetsSpinner);

        MallDis1 = (Spinner)findViewById(R.id.MAllDisSpinner);
        SchoolDis1 = (Spinner)findViewById(R.id.SchoolDisSpinner);
        HospitalDis1 = (Spinner)findViewById(R.id.HospitalDisSpinner);
        FuelDis1 = (Spinner)findViewById(R.id.FuelDisSpinner);
        BusDis1 = (Spinner)findViewById(R.id.BusDisSpinner);

        img1 = (ImageView)findViewById(R.id.objImg1);
        img2 = (ImageView)findViewById(R.id.objImg2);
        img3 = (ImageView)findViewById(R.id.objImg3);
        img4 = (ImageView)findViewById(R.id.objImg4);

        Upload = (Button)findViewById(R.id.Upload) ;

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Address = intent.getStringExtra("Address");
        String Advance = intent.getStringExtra("Advance");
        String Rent = intent.getStringExtra("Rent");
        String SqFt= intent.getStringExtra("SqFt");
        String Area = intent.getStringExtra("Area");
        String parking = intent.getStringExtra("parking");
        String BHK = intent.getStringExtra("BHK");
        String WATER= intent.getStringExtra("WATER");
        String FLOOR = intent.getStringExtra("FLOOR");
        String FACING = intent.getStringExtra("FACING");
        String BATHROOM = intent.getStringExtra("BATHROOM");
        String FAMILY = intent.getStringExtra("FAMILY");
        String FOOD = intent.getStringExtra("FOOD");
        String PET = intent.getStringExtra("PET");
        String Desc = intent.getStringExtra("Desc");
        String ImgUrl1 = intent.getStringExtra("ImgUrl1");
        String ImgUrl2 = intent.getStringExtra("ImgUrl2");
        String ImgUrl3 = intent.getStringExtra("ImgUrl3");
        String ImgUrl4 = intent.getStringExtra("ImgUrl4");
        String ImgUrlBill = intent.getStringExtra("ImgUrlBill");
        String MallDis = intent.getStringExtra("MallDis");
        String HospitalDis = intent.getStringExtra("HospitalDis");
        String SchoolDis = intent.getStringExtra("SchoolDis");
        String FuelDis = intent.getStringExtra("FuelDis");
        String BusDis = intent.getStringExtra("BusDis");
        String MallName = intent.getStringExtra("MallName");
        String SchoolName = intent.getStringExtra("SchoolName");
        String HospitalName = intent.getStringExtra("HospitalName");

        Name1.setText(Name);
        Address1.setText(Address);
        Advance1.setText(Advance);
        Rent1.setText(Rent);
        SqFt1.setText(SqFt);
        Area1.setText(Area.toUpperCase());
        Desc1.setText(Desc);

        SchoolName1.setText(SchoolName);
        MallName1.setText(MallName);
        HospitalName1.setText(HospitalName);
//        img1.setImageURI(Uri.parse(ImgUrl1));
//        img2.setImageURI(Uri.parse(ImgUrl2));
//        img3.setImageURI(Uri.parse(ImgUrl3));
//        img4.setImageURI(Uri.parse(ImgUrl4));

        Picasso.get().load(ImgUrl1).into(img1);
        Picasso.get().load(ImgUrl2).into(img2);
        Picasso.get().load(ImgUrl3).into(img3);
        Picasso.get().load(ImgUrl4).into(img4);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.parking, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int parkingPos;
        parkingPos = adapter.getPosition(parking);
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

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.bhk, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bhk.setAdapter(adapter1);
        int BhkPos;
        BhkPos = adapter1.getPosition(BHK);
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

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.water, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        water.setAdapter(adapter2);
        int waterPos;
        waterPos = adapter2.getPosition(WATER);
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


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.Floor, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floor.setAdapter(adapter3);
        int floorPos;
        floorPos = adapter3.getPosition(FLOOR);
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

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.Facing, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Facing.setAdapter(adapter4);
        int facingPos;
        facingPos = adapter4.getPosition(FACING);
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

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.bathrooms, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bathrooms.setAdapter(adapter5);
        int bathroomPos;
        bathroomPos = adapter5.getPosition(BATHROOM);
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

        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.Family, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Family.setAdapter(adapter6);
        int familyPos;
        familyPos = adapter6.getPosition(FAMILY);
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

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,R.array.Food, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Food.setAdapter(adapter7);
        int FoodPos;
        FoodPos = adapter7.getPosition(FOOD);
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

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,R.array.Pet, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Pet.setAdapter(adapter8);
        int petPos;
        petPos = adapter8.getPosition(PET);
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

        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MallDis1.setAdapter(adapter9);
        int DistPos;
        DistPos = adapter9.getPosition(MallDis);
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

        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolDis1.setAdapter(adapter10);
        int DisPos1;
        DisPos1 = adapter10.getPosition(SchoolDis);
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

        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HospitalDis1.setAdapter(adapter11);
        int DisPos2;
        DisPos2 = adapter11.getPosition(HospitalDis);
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

        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FuelDis1.setAdapter(adapter12);
        int DistancePos3;
        DistancePos3 = adapter12.getPosition(FuelDis);
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

        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BusDis1.setAdapter(adapter13);
        int DisPos4;
        DisPos4 = adapter13.getPosition(BusDis);
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


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("userEmailDb").getValue().equals(user.getEmail())){
                         OwnerNo = ds.child("userPhoneDb").getValue(String.class);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HouseInfo5.this, "Data is Verifying", Toast.LENGTH_SHORT).show();
                Toast.makeText(HouseInfo5.this, "Data is Processing", Toast.LENGTH_SHORT).show();

                Map<String,Object> map = new HashMap<>();
                map.put("houseOwnerName",Name1.getText().toString().trim());
                map.put("OwnerEmail",user.getEmail());
                map.put("HouseBillUrl",ImgUrlBill);
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
                map.put("housePhoneNo",OwnerNo);
                map.put("housePrefer",FAMILY1);
                map.put("houseUrl2",ImgUrl2);
                map.put("houseUrl3",ImgUrl3);
                map.put("houseUrl4",ImgUrl4);
                map.put("houseWaterSupply",WATER1);
                map.put("mallDistance",MallDistance);
                map.put("nearPetrolBunkDistance",FuelDistance);
                map.put("nearbusStopDistance",BusDistance);
                map.put("schoolDistance",SchoolDistance);
                map.put("houseAddress",Address1.getText().toString().trim());
                map.put("houseBHK",BHK1);
                map.put("housePrise",Rent1.getText().toString().trim());
                map.put("houseUrl1",ImgUrl1);
                map.put("type","HOUSE");

                FirebaseDatabase.getInstance().getReference().child("RentIt").child("house").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(HouseInfo5.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(HouseInfo5.this,CartPage.class));

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(HouseInfo5.this, "Data Upload Failed", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

    }

}

