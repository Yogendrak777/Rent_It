package com.example.rentit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

public class carRvAdapter extends FirebaseRecyclerAdapter<carRvModel,carRvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public carRvAdapter(@NonNull FirebaseRecyclerOptions<carRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull carRvModel model) {
        holder.CarModel.setText((model.getCarModel())+", "+model.getCarBodyType());
        holder.CarPrise.setText("\u20B9"+" "+model.getCarPrice() +" /Day");
        holder.CarFuel.setText(model.getCarFuel());
        holder.CarArea.setText(model.getCarArea());

        Glide.with(holder.imgOfCar.getContext())
                .load(model.getCarUrl1())
                .placeholder(R.drawable.com_logo)
                // .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.imgOfCar);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfCar.getContext())
                        .setContentHolder(new ViewHolder(R.layout.car_details_page))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView DcarModel = view.findViewById(R.id.DCarModel);
                TextView DcarAddress = view.findViewById(R.id.DCarAddress);
                TextView DcarAdvance = view.findViewById(R.id.DcarAdvance);
                TextView DcarRent = view.findViewById(R.id.DcarRent);
                TextView DcarAge = view.findViewById(R.id.DcarAge);
                TextView DgearTrans = view.findViewById(R.id.DcarTransmission);
                TextView DBodyType = view.findViewById(R.id.DcarBodyType);
                TextView DAirBag = view.findViewById(R.id.DcarAirBag);
                TextView DgrerBox = view.findViewById(R.id.DcarGearBox);
                TextView DFastTag = view.findViewById(R.id.DcarFastTag);
                TextView Dseating = view.findViewById(R.id.DcarSeating);
                TextView DcarMileage = view.findViewById(R.id.DcarMileage);
                TextView DlastService = view.findViewById(R.id.DService);
                TextView DcarDes = view.findViewById(R.id.DCarDescription);

                ImageView imgC1 = view.findViewById(R.id.objCarImg1);
                ImageView imgC2 = view.findViewById(R.id.objCarImg2);
                ImageView imgC3 = view.findViewById(R.id.objCarImg3);
                ImageView imgC4 = view.findViewById(R.id.objCarImg4);

                DcarModel.setText(model.getCarModel()+", "+model.getCarFuel());
                DcarAddress.setText(model.getCarAddress()+", "+model.getCarArea());
                DcarAdvance.setText(model.getCarAdvance());
                DcarRent.setText(model.getCarPrice());
                DcarAge.setText(model.getCarAge());
                DgearTrans.setText(model.getCarTransmission());
                DBodyType.setText(model.getCarBodyType());
                DAirBag.setText(model.getCarAirbag());
                DgrerBox.setText((model.getCarGearBox()));
                DFastTag.setText(model.getCarFastTag());
                Dseating.setText(model.getCarSeats());
                DcarMileage.setText(model.getCarMilage());
                DlastService.setText(model.getCarServiceDate());
                DcarDes.setText(model.getCarDescription());

                Picasso.get().load(model.getCarUrl()).into(imgC1);
                Picasso.get().load(model.getCarUrl1()).into(imgC2);
                Picasso.get().load(model.getCarUrl2()).into(imgC3);
                Picasso.get().load(model.getCarUrl3()).into(imgC4);

                dialogPlus.show();

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_rv_item,parent,false);
        return new myViewHolder(view);

    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfCar;
        TextView CarModel,CarPrise,CarArea,CarFuel;
        Button DetailsOfCar;
        CardView cardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfCar = (ImageView)itemView.findViewById(R.id.objImgCar);
            CarModel = (TextView) itemView.findViewById(R.id.ObjCarModel);
            CarPrise = (TextView) itemView.findViewById(R.id.ObjCarPrise);
            CarArea = (TextView)itemView.findViewById(R.id.ObjCarArea);
            CarFuel = (TextView)itemView.findViewById(R.id.ObjCarFuel);
            cardView = (CardView)itemView.findViewById(R.id.card);


            DetailsOfCar = (Button)itemView.findViewById(R.id.ShowCarDetails);

        }
    }
}
