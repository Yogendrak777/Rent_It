package com.example.rentit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class BikeRvAdapter extends FirebaseRecyclerAdapter<bikeRvModel,BikeRvAdapter.myViewHolder> {

   /**
    * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
    * {@link FirebaseRecyclerOptions} for configuration options.
    *
    * @param options
    */
   public BikeRvAdapter(@NonNull FirebaseRecyclerOptions<bikeRvModel> options) {
      super(options);
   }

   @Override
   protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull bikeRvModel model) {
      holder.BikeModel.setText(model.getBikeModel());
      holder.BikePrise.setText("\u20B9"+" "+model.getBikeRent() +" /Day");
      holder.BikeArea.setText(model.getBikeArea());

      Glide.with(holder.imgOfBike.getContext())
              .load(model.getBikeUrl1())
              .placeholder(R.drawable.com_logo)
              // .circleCrop()
              .error(R.drawable.ic_baseline_account_circle_24)
              .into(holder.imgOfBike);

      holder.cardBike.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfBike.getContext())
                    .setContentHolder(new ViewHolder(R.layout.bike_details_page))
                    .setExpanded(true, 2700)
                    .create();

            View view = dialogPlus.getHolderView();
            TextView DBikeModel = view.findViewById(R.id.DBikeModel);
            TextView DBikeAddress = view.findViewById(R.id.DBikeAddress);
            TextView DBikeAdvance = view.findViewById(R.id.DBikeAdvance);
            TextView DBikeRent = view.findViewById(R.id.DBikeRent);
            TextView DBikeMileage = view.findViewById(R.id.DBikeMileage);
            TextView DBikeage = view.findViewById(R.id.DBikeAge);
            TextView DBikeBodyType = view.findViewById(R.id.DBikeBodyType);
            TextView DBikeService = view.findViewById(R.id.DBikeService);
            TextView DBikeDesc = view.findViewById(R.id.DBikeDescription);

            ImageView imgB1 = view.findViewById(R.id.objBikeImg1);
            ImageView imgB2 = view.findViewById(R.id.objBikeImg2);
            ImageView imgB3 = view.findViewById(R.id.objBikeImg3);
            ImageView imgB4 = view.findViewById(R.id.objBikeImg4);

            DBikeModel.setText(model.getBikeModel());
            DBikeAddress.setText(model.getBikeAddress()+", "+model.getBikeArea());
            DBikeAdvance.setText(model.getBikeAdvance());
            DBikeRent.setText(model.getBikeRent());
            DBikeMileage.setText(model.getBikeMileage());
            DBikeage.setText(model.getBikeAge());
            DBikeBodyType.setText(model.getBikeType());
            DBikeService.setText(model.getBikeServiceDate());
            DBikeDesc.setText(model.getBikeDescription());

            Picasso.get().load(model.getBikeUrl1()).into(imgB1);
            Picasso.get().load(model.getBikeUrl2()).into(imgB2);
            Picasso.get().load(model.getBikeUrl3()).into(imgB3);
            Picasso.get().load(model.getBikeUrl4()).into(imgB4);

            dialogPlus.show();
         }
      });
   }

   @NonNull
   @Override
   public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_rv_item,parent,false);
      return new myViewHolder(view);
   }

   public class myViewHolder extends RecyclerView.ViewHolder{
      ImageView imgOfBike;
      TextView BikeModel,BikePrise,BikeArea;
      CardView cardBike;


      public myViewHolder(@NonNull View itemView) {
         super(itemView);

         imgOfBike = (ImageView)itemView.findViewById(R.id.objImgBike);
         BikeModel = (TextView) itemView.findViewById(R.id.ObjBikeModel);
         BikePrise = (TextView) itemView.findViewById(R.id.ObjBikePrise);
         BikeArea = (TextView) itemView.findViewById(R.id.ObjBikeArea);

         cardBike = (CardView) itemView.findViewById(R.id.CardBike);


      }
   }
}
