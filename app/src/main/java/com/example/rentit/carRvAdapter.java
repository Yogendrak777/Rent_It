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

public class carRvAdapter extends FirebaseRecyclerAdapter<carRvModel,carRvAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public carRvAdapter(@NonNull FirebaseRecyclerOptions<carRvModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull carRvModel model) {
        holder.CarModel.setText((model.getCarModel())+", "+model.getCarBodyType());
        holder.CarPrise.setText("\u20B9"+" "+model.getCarPrice() +" /Day");
        holder.CarFuel.setText(model.getCarFuel());
        holder.CarArea.setText(model.getCarArea());

        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getCarUrl());
        try {
            File file = File.createTempFile("randomKey","");
            storageReference1.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(holder.imgOfCar.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            holder.imgOfCar.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(holder.imgOfCar.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfCar.getContext())
                        .setContentHolder(new ViewHolder(R.layout.car_details_page))
                        .setExpanded(true, 2100)
                        .create();

                View view = dialogPlus.getHolderView();
                TextView DcarModel = view.findViewById(R.id.DCarModel);
                TextView DcarAddress = view.findViewById(R.id.DCarAddress);
                TextView DcarAdvance = view.findViewById(R.id.DcarAdvance);
                TextView DcarRent = view.findViewById(R.id.DcarRent);
                TextView DcarAge = view.findViewById(R.id.DcarAge);
                TextView DgearTrans = view.findViewById(R.id.DcarTransmission);
                TextView DBodyType = view.findViewById(R.id.DcarBodyType);
                TextView DAirBag = view.findViewById(R.id.DcarAirBag);
                TextView DgrerBox = view.findViewById(R.id.DcarGearBox);
                TextView DFastTag = view.findViewById(R.id.DcarFastTag);
                TextView Dseating = view.findViewById(R.id.DcarSeating);
                TextView DcarMileage = view.findViewById(R.id.DcarMileage);
                TextView DlastService = view.findViewById(R.id.DService);
                TextView DcarDes = view.findViewById(R.id.DCarDescription);

                ImageView imgC1 = view.findViewById(R.id.objCarImg1);
                ImageView imgC2 = view.findViewById(R.id.objCarImg2);
                ImageView imgC3 = view.findViewById(R.id.objCarImg3);
                ImageView imgC4 = view.findViewById(R.id.objCarImg4);

                DcarModel.setText(model.getCarModel()+", "+model.getCarFuel());
                DcarAddress.setText(model.getCarAddress()+", "+model.getCarArea());
                DcarAdvance.setText(model.getCarAdvance());
                DcarRent.setText(model.getCarPrice());
                DcarAge.setText(model.getCarAge());
                DgearTrans.setText(model.getCarTransmission());
                DBodyType.setText(model.getCarBodyType());
                DAirBag.setText(model.getCarAirbag());
                DgrerBox.setText((model.getCarGearBox()));
                DFastTag.setText(model.getCarFastTag());
                Dseating.setText(model.getCarSeats());
                DcarMileage.setText(model.getCarMilage());
                DlastService.setText(model.getCarServiceDate());
                DcarDes.setText(model.getCarDescription());

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/"+model.getCarUrl());
                StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/"+model.getCarUrl1());
                StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/"+model.getCarUrl2());
                StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/"+model.getCarUrl3());
                try {
                    File file1 = File.createTempFile("randomKey","");
                    File file2 = File.createTempFile("randomKey","");
                    File file3 = File.createTempFile("randomKey","");
                    File file4 = File.createTempFile("randomKey","");
                    storageReference1.getFile(file1);
                    storageReference2.getFile(file2);
                    storageReference3.getFile(file3);
                    storageReference4.getFile(file4)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(DAirBag.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                                    Bitmap bitmap1 = BitmapFactory.decodeFile(file1.getAbsolutePath());
                                    Bitmap bitmap2 = BitmapFactory.decodeFile(file2.getAbsolutePath());
                                    Bitmap bitmap3 = BitmapFactory.decodeFile(file3.getAbsolutePath());
                                    Bitmap bitmap4 = BitmapFactory.decodeFile(file4.getAbsolutePath());
                                    imgC1.setImageBitmap(bitmap1);
                                    imgC2.setImageBitmap(bitmap2);
                                    imgC3.setImageBitmap(bitmap3);
                                    imgC4.setImageBitmap(bitmap4);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(DAirBag.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_rv_item,parent,false);
        return new myViewHolder(view);

    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imgOfCar;
        TextView CarModel,CarPrise,CarArea,CarFuel;
        CardView cardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOfCar = (ImageView)itemView.findViewById(R.id.objImgCar);
            CarModel = (TextView) itemView.findViewById(R.id.ObjCarModel);
            CarPrise = (TextView) itemView.findViewById(R.id.ObjCarPrise);
            CarArea = (TextView)itemView.findViewById(R.id.ObjCarArea);
            CarFuel = (TextView)itemView.findViewById(R.id.ObjCarFuel);
            cardView = (CardView)itemView.findViewById(R.id.card);



        }
    }
}
