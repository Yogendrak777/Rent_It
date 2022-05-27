package com.example.rentit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RvAdapter extends FirebaseRecyclerAdapter<houseRvModel,RvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RvAdapter(@NonNull FirebaseRecyclerOptions<houseRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull houseRvModel model) {
        holder.Address.setText(model.getHouseAddress());
        holder.Prise.setText(model.getHousePrise());
        holder.Bhk.setText(model.getHouseBHK());
        holder.Area.setText(model.getHouseArea());

        Glide.with(holder.img.getContext())
                .load(model.getHouseUrl1())
                .placeholder(R.drawable.com_logo)
               // .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.img);

        holder.Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.details_page))
                        .setExpanded(true,2700)
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


                Daddress.setText(model.getHouseAddress());
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

                 //img1.setImageURI(model.getHouseUrl1());

                Picasso.get().load(model.getHouseUrl1()).into(img1);
                Picasso.get().load(model.getHouseUrl2()).into(img2);
                Picasso.get().load(model.getHouseUrl3()).into(img3);
                Picasso.get().load(model.getHouseUrl4()).into(img4);


                dialogPlus.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_rv_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        //CircleImageView img;
        ImageView img;
        TextView Address,Prise,Bhk,Area;
        Button Details;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

           // img = (CircleImageView)itemView.findViewById(R.id.objImg);
            img = (ImageView)itemView.findViewById(R.id.objImg);
            Address = (TextView) itemView.findViewById(R.id.ObjName);
            Prise = (TextView) itemView.findViewById(R.id.ObjPrise);
            Bhk = (TextView) itemView.findViewById(R.id.ObjBhk);
            Area = (TextView)itemView.findViewById(R.id.ObjArea);

            Details = (Button)itemView.findViewById(R.id.ShowDetails);



        }
    }
}
