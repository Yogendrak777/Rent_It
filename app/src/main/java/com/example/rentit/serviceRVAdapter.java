package com.example.rentit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.File;
import java.io.IOException;

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

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getImg1());
        try {
            File file = File.createTempFile("randomKey","");
            storageReference1.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(holder.img.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            holder.img.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(holder.img.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.workers_details_page))
                        .setExpanded(true, 2700)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView Area = view.findViewById(R.id.wArea);
                TextView Rent = view.findViewById(R.id.wRent);
                TextView Exp = view.findViewById(R.id.wExp);
                TextView job = view.findViewById(R.id.wjobName);
                TextView lang = view.findViewById(R.id.wLangauge);
                TextView workTime = view.findViewById(R.id.wWorkTime);
                TextView desc = view.findViewById(R.id.wDescription);
                TextView name = view.findViewById(R.id.wName);

                ImageView img1 = view.findViewById(R.id.objSportImg1);
                ImageView img2 = view.findViewById(R.id.objSportImg2);
                ImageView img3 = view.findViewById(R.id.objSportImg3);

                name.setText(model.getName());
                Area.setText(model.getArea());
                Rent.setText(model.getCharge());
                Exp.setText(model.getExperience());
                job.setText(model.getJobName());
                lang.setText(model.getLangaugeKnow());
                workTime.setText(model.getWorkingTime());
                desc.setText(model.getDesc());

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getImg1());
                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/"+model.getImg2());
                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/"+model.getImg3());
                try {
                    File file1 = File.createTempFile("randomKey","");
                    File file2 = File.createTempFile("randomKey","");
                    File file3 = File.createTempFile("randomKey","");
                    File file4 = File.createTempFile("randomKey","");
                    storageReference1.getFile(file1);
                    storageReference2.getFile(file2);
                    storageReference3.getFile(file3)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(img1.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                                    Bitmap bitmap1 = BitmapFactory.decodeFile(file1.getAbsolutePath());
                                    Bitmap bitmap2 = BitmapFactory.decodeFile(file2.getAbsolutePath());
                                    Bitmap bitmap3 = BitmapFactory.decodeFile(file3.getAbsolutePath());
                                    img1.setImageBitmap(bitmap1);
                                    img2.setImageBitmap(bitmap2);
                                    img3.setImageBitmap(bitmap3);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(img1.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }




                dialogPlus.show();

            }
        });


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
