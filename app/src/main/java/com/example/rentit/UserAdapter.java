package com.example.rentit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final Context mcontext;
    private final List<personInfoRvModel> mpersonInfoRvModels;

    public UserAdapter(Context mcontext, List<personInfoRvModel> mpersonInfoRvModels){
        this.mcontext = mcontext;
        this.mpersonInfoRvModels = mpersonInfoRvModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.user_chart_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        personInfoRvModel personInfoRvModel1 = mpersonInfoRvModels.get(position);
        holder.username1.setText(personInfoRvModel1.getUserNameDb());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mcontext,ChartActivity.class);
                intent.putExtra("UserId", personInfoRvModel1.getUserId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mpersonInfoRvModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username1 = itemView.findViewById(R.id.username);
        }
    }


}
