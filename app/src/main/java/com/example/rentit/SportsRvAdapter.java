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

public class SportsRvAdapter extends FirebaseRecyclerAdapter<SportRvModel,SportsRvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SportsRvAdapter(@NonNull FirebaseRecyclerOptions<SportRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull SportRvModel model) {
        holder.SportName.setText(model.getSpoName()+", "+model.getSpoModel());
        holder.SportPrise.setText(model.getSpoRent());
        holder.SportBrand.setText(model.getSpoBrand());
        holder.SportArea.setText(model.getSpoArea());

        Glide.with(holder.imgOfSport.getContext())
                .load(model.getSpoUrl1())
                .placeholder(R.drawable.com_logo)
                // .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.imgOfSport);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfSport.getContext())
                        .setContentHolder(new ViewHolder(R.layout.sports_details_pages))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView DSportName = view.findViewById(R.id.DSportName);
                TextView DSportAddress = view.findViewById(R.id.DSportAddress);
                TextView DSportAdvance = view.findViewById(R.id.DSportAdvance);
                TextView DSportRent = view.findViewById(R.id.DSportRent);
                TextView DSportBrand = view.findViewById(R.id.DSportBrand);
                TextView DSportColor = view.findViewById(R.id.DSportColor);
                TextView DSportIdel = view.findViewById(R.id.DSportIdel);
                TextView DSportMeterial = view.findViewById(R.id.DSportMeterial);
                TextView DSportType = view.findViewById(R.id.DSportType);
                TextView DSportIn = view.findViewById(R.id.DSportPack);
                TextView DSportDesc = view.findViewById(R.id.DSportDescription);

                ImageView img1 = view.findViewById(R.id.objSportImg1);
                ImageView img2 = view.findViewById(R.id.objSportImg2);
                ImageView img3 = view.findViewById(R.id.objSportImg3);
                ImageView img4 = view.findViewById(R.id.objSportImg4);

                DSportName.setText(model.getSpoName()+", "+model.getSpoModel());
                DSportAddress.setText(model.getSpoAddress()+", "+model.getSpoArea());
                DSportAdvance.setText(model.getSpoAdvance());
                DSportRent.setText(model.getSpoRent());
                DSportBrand.setText(model.getSpoBrand());
                DSportColor.setText(model.getSpoColor());
                DSportIdel.setText(model.getSpoIdelFor());
                DSportMeterial.setText(model.getSpoMeterial());
                DSportType.setText(model.getSpoType());
                DSportIn.setText(model.getSpoPacks());
                DSportDesc.setText(model.getSpoDesc());

                Picasso.get().load(model.getSpoUrl1()).into(img1);
                Picasso.get().load(model.getSpoUrl2()).into(img2);
                Picasso.get().load(model.getSpoUrl3()).into(img3);
                Picasso.get().load(model.getSpoUrl4()).into(img4);

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
                        intent.putExtra(Intent.EXTRA_TEXT,model.getSpoModel() + "," + model.getSpoBrand() + "," + model.getSpoRent());
                        img1.getContext().startActivity(Intent.createChooser(intent,"share Via"));

                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(img1.getContext(), BookEnter.class);
                        intent.putExtra("UserId", model.getUserId());
                        img1.getContext().startActivity(intent);

                    }
                });

                dialogPlus.show();

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_rv_items,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfSport;
        TextView SportBrand,SportPrise,SportArea,SportName;
        CardView cardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfSport = (ImageView) itemView.findViewById(R.id.objImgSport);
            SportName = (TextView) itemView.findViewById(R.id.ObjSporName);
            SportPrise = (TextView)itemView.findViewById(R.id.ObjSporPrise);
            SportArea = (TextView)itemView.findViewById(R.id.ObjSporArea);
            SportBrand = (TextView)itemView.findViewById(R.id.ObjSporBrand);

            cardView = (CardView)itemView.findViewById(R.id.card6);
        }
    }
}
