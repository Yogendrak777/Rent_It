package com.example.rentit;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BookRvAdapter extends FirebaseRecyclerAdapter<BookRvModel,BookRvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookRvAdapter(@NonNull FirebaseRecyclerOptions<BookRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull BookRvModel model) {
        holder.name.setText(model.getBuyerName());
        holder.active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<>();
                map.put("Status","Accept");

                FirebaseDatabase.getInstance().getReference().child("RentIt").child("BookList")
                        .child(getRef(position).getKey()).updateChildren(map);
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        CardView cardView;
        Button active;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.objImg);
            name = (TextView) itemView.findViewById(R.id.ObjName);

            cardView = (CardView)itemView.findViewById(R.id.card1);

            active  = itemView.findViewById(R.id.button3);
        }
    }

}
