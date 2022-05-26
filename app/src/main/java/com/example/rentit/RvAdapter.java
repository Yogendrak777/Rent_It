package com.example.rentit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

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

        Glide.with(holder.img.getContext())
                .load(model.getHouseUrl1())
                .placeholder(R.drawable.com_logo)
                .circleCrop()
                .error(R.drawable.ic_baseline_account_circle_24)
                .into(holder.img);

        holder.Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.details_page))
                        .setExpanded(true,2000)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView Daddress = view.findViewById(R.id.Daddress);
                TextView Dbhk = view.findViewById(R.id.DBhk);
                TextView Dprice = view.findViewById(R.id.Dprise);
                TextView DUrl = view.findViewById(R.id.DUrl);
                TextView Dtype = view.findViewById(R.id.Dtype);

                Daddress.setText(model.getHouseAddress());
                Dbhk.setText(model.getHouseBHK());
                Dprice.setText(model.getHousePrise());
                DUrl.setText(model.getHouseUrl1());
                Dtype.setText(model.getType());

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

        CircleImageView img;
        TextView Address,Prise,Bhk;
        Button Details;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.objImg);
            Address = (TextView) itemView.findViewById(R.id.ObjName);
            Prise = (TextView) itemView.findViewById(R.id.ObjPrise);
            Bhk = (TextView) itemView.findViewById(R.id.ObjBhk);

            Details = (Button)itemView.findViewById(R.id.ShowDetails);

        }
    }
}
