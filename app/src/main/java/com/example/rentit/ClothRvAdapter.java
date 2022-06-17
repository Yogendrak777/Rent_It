package com.example.rentit;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class ClothRvAdapter extends FirebaseRecyclerAdapter<ClothRvModel,ClothRvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ClothRvAdapter(@NonNull FirebaseRecyclerOptions<ClothRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ClothRvModel model) {
        holder.ClothName.setText(model.getClothName());
        holder.ClothBrand.setText(model.getClothBrand());
        holder.ClothPrise.setText(model.getClothRent());
        holder.ClothArea.setText(model.getClothArea());

        Glide.with(holder.imgOfCloth.getContext())
                .load(model.getClothUrl1())
                .placeholder(R.drawable.com_logo)
                // .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.imgOfCloth);

        holder.cardClot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfCloth.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cloth_detail_page))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView DClothName = view.findViewById(R.id.DClotheName);
                TextView DClothAddress = view.findViewById(R.id.DClothAddress);
                TextView DClothAdv = view.findViewById(R.id.DClothAdvance);
                TextView DClothRent = view.findViewById(R.id.DClothRent);
                TextView DClothBrand = view.findViewById(R.id.DClothBrand);
                TextView DClothColor = view.findViewById(R.id.DClothColor);
                TextView DClothSize = view.findViewById(R.id.DClothSize);
                TextView DClothMeterial = view.findViewById(R.id.DClothMeterial);
                TextView DClothFit = view.findViewById(R.id.DClothFitType);
                TextView DClothType = view.findViewById(R.id.DClothType);
                TextView DClothDesc = view.findViewById(R.id.DclothDescription);

                ImageView imgC1 = view.findViewById(R.id.objClothImg1);
                ImageView imgC2 = view.findViewById(R.id.objClothImg2);
                ImageView imgC3 = view.findViewById(R.id.objClothImg3);
                ImageView imgC4 = view.findViewById(R.id.objClothImg4);

                DClothName.setText(model.getClothName()+", "+model.getClothMetal());
                DClothAddress.setText(model.getClothAddress()+", "+model.getClothArea());
                DClothAdv.setText(model.getClothAdvance());
                DClothRent.setText(model.getClothRent());
                DClothBrand.setText(model.getClothBrand());
                DClothColor.setText(model.getClothColor());
                DClothSize.setText(model.getClothSizeNo());
                DClothMeterial.setText(model.getClothMetal());
                DClothFit.setText(model.getClothFitSize());
                DClothType.setText(model.getClothType());
                DClothDesc.setText(model.getClothDesc());

                Picasso.get().load(model.getClothUrl1()).into(imgC1);
                Picasso.get().load(model.getClothUrl2()).into(imgC2);
                Picasso.get().load(model.getClothUrl3()).into(imgC3);
                Picasso.get().load(model.getClothUrl4()).into(imgC4);

                TextView next = view.findViewById(R.id.next);
                ImageButton favOn,share;

                favOn = view.findViewById(R.id.favOn);
                share = view.findViewById(R.id.share);

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT,"House Info \n");
                        intent.putExtra(Intent.EXTRA_TEXT,model.getClothBrand() + "," + model.getClothColor() + "," + model.getClothRent());
                        imgC1.getContext().startActivity(Intent.createChooser(intent,"share Via"));

                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(imgC1.getContext(), BookEnter.class);
                        intent.putExtra("UserId", model.getUserId());
                        imgC1.getContext().startActivity(intent);

                    }
                });

                dialogPlus.show();

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cloth_rv_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfCloth;
        TextView ClothName,ClothPrise,ClothArea,ClothBrand;
        CardView cardClot;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfCloth = (ImageView)itemView.findViewById(R.id.objImgCloth);
            ClothName = (TextView)itemView.findViewById(R.id.ObjClothModel);
            ClothPrise = (TextView)itemView.findViewById(R.id.ObjClothPrise);
            ClothArea = (TextView)itemView.findViewById(R.id.ObjClothArea);
            ClothBrand = (TextView) itemView.findViewById(R.id.ObjClothBrand);
            cardClot = (CardView)itemView.findViewById(R.id.CardClot);

        }
    }
}
