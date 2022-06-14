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

public class SpeakerRvAdapter extends FirebaseRecyclerAdapter<SpeakerRvModel,SpeakerRvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SpeakerRvAdapter(@NonNull FirebaseRecyclerOptions<SpeakerRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull SpeakerRvModel model) {
        holder.SpeModel.setText(model.getSpeBrand()+", "+model.getSpeModel());
        holder.SpePrise.setText(model.getSpeRent());
        holder.SpeArea.setText(model.getSpeArea());

        Glide.with(holder.imgOfSpe.getContext())
                .load(model.getSpeUrl1())
                .placeholder(R.drawable.com_logo)
                // .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.imgOfSpe);

        holder.cardSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfSpe.getContext())
                        .setContentHolder(new ViewHolder(R.layout.speaker_rv_details_page))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView DSpeModel = view.findViewById(R.id.DSpeModel);
                TextView DSpeAddress = view.findViewById(R.id.DSpeAddress);
                TextView DSpeAdv = view.findViewById(R.id.DSpeAdvance);
                TextView DSpeRent = view.findViewById(R.id.DSpeRent);
                TextView DSpeBlue = view.findViewById(R.id.DSpeBlue);
                TextView DSprDis = view.findViewById(R.id.DSpeDis);
                TextView DSpePow = view.findViewById(R.id.DSpePow);
                TextView DSpeType = view.findViewById(R.id.DSpeType);
                TextView DSpeMeo = view.findViewById(R.id.DSpeMemoSlot);
                TextView DSpeDisc = view.findViewById(R.id.DSpeDesc);

                ImageView imgC1 = view.findViewById(R.id.objSpeImg1);
                ImageView imgC2 = view.findViewById(R.id.objSpeImg2);
                ImageView imgC3 = view.findViewById(R.id.objSpeImg3);
                ImageView imgC4 = view.findViewById(R.id.objSpeImg4);

                DSpeModel.setText(model.getSpeBrand()+", "+model.getSpeModel());
                DSpeAddress.setText(model.getSpeAddress()+", "+model.getSpeArea());
                DSpeAdv.setText(model.getSpeAdvance());
                DSpeRent.setText(model.getSpeRent());
                DSpeBlue.setText(model.getSpeBluetooth());
                DSprDis.setText(model.getSpeDisplayType());
                DSpePow.setText(model.getSpePowerSource());
                DSpeType.setText(model.getSpeType());
                DSpeMeo.setText(model.getSpeMemoryCardSlot());
                DSpeDisc.setText(model.getSpeDescription());

                Picasso.get().load(model.getSpeUrl1()).into(imgC1);
                Picasso.get().load(model.getSpeUrl2()).into(imgC2);
                Picasso.get().load(model.getSpeUrl3()).into(imgC3);
                Picasso.get().load(model.getSpeUrl4()).into(imgC4);

                dialogPlus.show();

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speaker_rv_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfSpe;
        TextView SpeModel,SpePrise,SpeArea;
        CardView cardSpeaker;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfSpe = (ImageView)itemView.findViewById(R.id.objSpeImg);
            SpeModel = (TextView)itemView.findViewById(R.id.ObjSpeName);
            SpePrise = (TextView)itemView.findViewById(R.id.ObjSpePrise);
            SpeArea = (TextView)itemView.findViewById(R.id.ObjSpeArea);
            cardSpeaker = (CardView)itemView.findViewById(R.id.CardSpeak);

        }
    }
}
