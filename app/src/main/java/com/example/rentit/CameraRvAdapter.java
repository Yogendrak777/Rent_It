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

public class CameraRvAdapter extends FirebaseRecyclerAdapter<CameraRvModel,CameraRvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CameraRvAdapter(@NonNull FirebaseRecyclerOptions<CameraRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CameraRvModel model) {
        holder.CamModel.setText(model.getCameraBrand()+", "+model.getCameraModel());
        holder.CamPrise.setText(model.getCameraRent());
        holder.CamArea.setText(model.getCameraArea());

        Glide.with(holder.imgOfCamera.getContext())
                .load(model.getCamUrl1())
                .placeholder(R.drawable.com_logo)
                // .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.imgOfCamera);

        holder.cardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfCamera.getContext())
                        .setContentHolder(new ViewHolder(R.layout.camera_rv_details))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView DcamModel = view.findViewById(R.id.DCamModel);
                TextView DcamAddress = view.findViewById(R.id.DCamAddress);
                TextView DcamAdvance = view.findViewById(R.id.DcamAdvance);
                TextView DcamRent = view.findViewById(R.id.DcamRent);
                TextView DcamFlash = view.findViewById(R.id.DcamFlash);
                TextView DcamLed = view.findViewById(R.id.DcamLed);
                TextView DcamManual = view.findViewById(R.id.DcamFocus);
                TextView DcamMovie = view.findViewById(R.id.DcamFormat);
                TextView Dcamzoom = view.findViewById(R.id.DcamOptionzoom);
                TextView DcamType = view.findViewById(R.id.DcamType);
                TextView DcamDesc = view.findViewById(R.id.DCamDescription);

                ImageView imgC1 = view.findViewById(R.id.objCamImg1);
                ImageView imgC2 = view.findViewById(R.id.objCamImg2);
                ImageView imgC3 = view.findViewById(R.id.objCamImg3);
                ImageView imgC4 = view.findViewById(R.id.objCamImg4);

                DcamModel.setText(model.getCameraBrand()+", "+model.getCameraModel());
                DcamAddress.setText(model.getCameraAddress()+", "+model.getCameraArea());
                DcamAdvance.setText(model.getCameraAdvance());
                DcamRent.setText(model.getCameraRent());
                DcamFlash.setText(model.getCameraFlash());
                DcamLed.setText(model.getCameraLedMonitor());
                DcamManual.setText(model.getCameraManualFous());
                DcamMovie.setText(model.getCameraMovieFormat());
                Dcamzoom.setText(model.getCameraOptialZoom());
                DcamType.setText(model.getType());
                DcamDesc.setText(model.getCameraDescription());

                Picasso.get().load(model.getCamUrl1()).into(imgC1);
                Picasso.get().load(model.getCamUrl2()).into(imgC2);
                Picasso.get().load(model.getCamUrl3()).into(imgC3);
                Picasso.get().load(model.getCamUrl4()).into(imgC4);

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
                        intent.putExtra(Intent.EXTRA_TEXT,model.getCameraModel() + "," + model.getCameraRent() + "," + model.getCameraBrand());
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_rv_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfCamera;
        TextView CamModel,CamPrise,CamArea;
        CardView cardCamera;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfCamera = (ImageView)itemView.findViewById(R.id.objImgCamera);
            CamModel = (TextView) itemView.findViewById(R.id.ObjCamModel);
            CamPrise = (TextView)itemView.findViewById(R.id.ObjCamPrise);
            CamArea = (TextView) itemView.findViewById(R.id.ObjCamArea);
            cardCamera = (CardView)itemView.findViewById(R.id.CardCamer);


        }
    }

}
