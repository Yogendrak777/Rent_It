package com.example.rentit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static  final int MSG_TYPE_LEFT = 0;
    public static  final int MSG_TYPE_RIGHT = 1;
    private Context mcontext;
    private List<ChartRvModel> mchart;

    FirebaseUser firebaseUser;

    public MessageAdapter(Context mcontext, List<ChartRvModel> mchart){
        this.mchart = mchart;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.chart_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.chart_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChartRvModel chartRvModel = mchart.get(position);
        holder.show_message.setText(chartRvModel.getMessage());

    }

    @Override
    public int getItemCount() {
        return mchart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView show_message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
        }
    }
    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mchart.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

}
