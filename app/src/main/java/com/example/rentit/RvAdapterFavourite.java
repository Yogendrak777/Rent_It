package com.example.rentit;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

public class RvAdapterFavourite extends FirebaseRecyclerAdapter<houseRvModel,RvAdapterFavourite.myViewHolder> {

    CardView chart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    public static String currentUser;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RvAdapterFavourite(@NonNull FirebaseRecyclerOptions<houseRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RvAdapterFavourite.myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull houseRvModel model) {
        holder.Address.setText(model.getHouseBHK() + ", " + model.getHouseAddress());
        holder.Prise.setText("\u20B9" + " " + model.getHousePrise() + " /month");
        holder.Area.setText(model.getHouseArea());

        Glide.with(holder.img.getContext())
                .load(model.getHouseUrl1())
                .placeholder(R.drawable.com_logo)
                // .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.img);

        holder.Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("RentIt").child("Favourite")
                        .child(getRef(position).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.Address.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.favourite_adapter_item))
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

                ImageButton Del;

                Del = view.findViewById(R.id.delete);


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


                Picasso.get().load(model.getHouseUrl1()).into(img1);
                Picasso.get().load(model.getHouseUrl2()).into(img2);
                Picasso.get().load(model.getHouseUrl3()).into(img3);
                Picasso.get().load(model.getHouseUrl4()).into(img4);


                chart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(img1.getContext(), ChartActivity.class);
                        intent.putExtra("UserId", model.getOwnerUId());
                        img1.getContext().startActivity(intent);
                    }
                });

                dialogPlus.show();
            }
        });


    }



    @NonNull
    @Override
    public RvAdapterFavourite.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        //CircleImageView img;
        ImageView img;
        TextView Address,Prise,Area;
        CardView cardView;
        ImageButton Del;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            // img = (CircleImageView)itemView.findViewById(R.id.objImg);
            img = (ImageView)itemView.findViewById(R.id.objImg);
            Address = (TextView) itemView.findViewById(R.id.ObjName);
            Prise = (TextView) itemView.findViewById(R.id.ObjPrise);
            Area = (TextView)itemView.findViewById(R.id.ObjArea);

            Del = itemView.findViewById(R.id.delete);


            cardView = (CardView)itemView.findViewById(R.id.card1);




        }
    }
}
