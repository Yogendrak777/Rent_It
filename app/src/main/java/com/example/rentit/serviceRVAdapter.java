package com.example.rentit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class serviceRVAdapter extends FirebaseRecyclerAdapter<ServiceRVModel,serviceRVAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public serviceRVAdapter(@NonNull FirebaseRecyclerOptions<ServiceRVModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ServiceRVModel model) {
        holder.Name.setText(model.getName());
        holder.charge.setText(model.getCharge());
        holder.exp.setText(model.getExperience());
        holder.work.setText(model.getJobName());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_rv_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView img;
        TextView Name,charge,work,exp;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            charge = itemView.findViewById(R.id.workCharge);
            cardView = itemView.findViewById(R.id.card);
            img  = itemView.findViewById(R.id.objImgSport);
            Name = itemView.findViewById(R.id.workerName);
            work = itemView.findViewById(R.id.JobName);
            exp = itemView.findViewById(R.id.workExperince);
        }
    }
}
