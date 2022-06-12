package com.example.rentit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final Context mcontext;
    private final List<personInfoRvModel> mpersonInfoRvModels;
    boolean ischat;

    String theLastMessage;

    public UserAdapter(Context mcontext, List<personInfoRvModel> mpersonInfoRvModels,boolean ischat){
        this.mcontext = mcontext;
        this.mpersonInfoRvModels = mpersonInfoRvModels;
        this.ischat = ischat;
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

        if (ischat){
            lastMessage(personInfoRvModel1.getUserId(), holder.last_msg);
        } else {
            holder.last_msg.setVisibility(View.GONE);
        }

        if (ischat){
            if (personInfoRvModel1.getStatus().equals("online")){
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
                holder.offline.setText(personInfoRvModel1.getStatus());

            } else {
                holder.img_on.setVisibility(View.GONE);
                holder.img_off.setVisibility(View.VISIBLE);
                holder.offline.setText(personInfoRvModel1.getStatus());

            }
        } else {
            holder.offline.setText(personInfoRvModel1.getStatus());
        }

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
        public TextView username1,offline,last_msg;
        private ImageView img_on;
        private ImageView img_off;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username1 = itemView.findViewById(R.id.username);
            offline = itemView.findViewById(R.id.offline);

            img_on = itemView.findViewById(R.id.img_on);
            img_off = itemView.findViewById(R.id.img_off);
            last_msg = itemView.findViewById(R.id.last_msg);
        }
    }
    private void lastMessage(final String userid, final TextView last_msg) {
        theLastMessage = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChartRvModel chat = snapshot.getValue(ChartRvModel.class);
                    if (firebaseUser != null && chat != null) {
                        if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) ||
                                chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())) {
                            theLastMessage = chat.getMessage();
                        }
                    }
                }

                switch (theLastMessage) {
                    case "default":
                        last_msg.setText("No Message");
                        break;

                    default:
                        last_msg.setText(theLastMessage);
                        break;
                }

                theLastMessage = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
