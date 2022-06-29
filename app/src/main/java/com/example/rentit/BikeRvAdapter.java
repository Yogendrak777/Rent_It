package com.example.rentit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BikeRvAdapter extends FirebaseRecyclerAdapter<bikeRvModel,BikeRvAdapter.myViewHolder> {
   FirebaseDatabase firebaseDatabase;
   DatabaseReference databaseReference;
   FirebaseAuth firebaseAuth;
   public static String currentUser;
   public static String ran;

   /**
    * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
    * {@link FirebaseRecyclerOptions} for configuration options.
    *
    * @param options
    */
   public BikeRvAdapter(@NonNull FirebaseRecyclerOptions<bikeRvModel> options) {
      super(options);
   }

   @Override
   protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull bikeRvModel model) {
      holder.BikeModel.setText(model.getBikeModel());
      holder.BikePrise.setText("\u20B9"+" "+model.getBikeRent() +" /Day");
      holder.BikeArea.setText(model.getBikeArea());
      StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl1());
      try {
         File file = File.createTempFile("randomKey", "");
         storageReference1.getFile(file)
                 .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                       Toast.makeText(holder.imgOfBike.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                       Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                       holder.imgOfBike.setImageBitmap(bitmap);

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

      firebaseAuth = FirebaseAuth.getInstance();
      firebaseDatabase = FirebaseDatabase.getInstance();
      DatabaseReference databaseReference1;
      databaseReference1 = firebaseDatabase.getReference("RentIt").child("FavouriteBike");

      holder.cardBike.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgOfBike.getContext())
                    .setContentHolder(new ViewHolder(R.layout.bike_details_page))
                    .setExpanded(true, 2700)
                    .create();

            View view = dialogPlus.getHolderView();
            final String randomKey = UUID.randomUUID().toString();

            TextView DBikeModel = view.findViewById(R.id.DBikeModel);
            TextView DBikeAddress = view.findViewById(R.id.DBikeAddress);
            TextView DBikeAdvance = view.findViewById(R.id.DBikeAdvance);
            TextView DBikeRent = view.findViewById(R.id.DBikeRent);
            TextView DBikeMileage = view.findViewById(R.id.DBikeMileage);
            TextView DBikeage = view.findViewById(R.id.DBikeAge);
            TextView DBikeBodyType = view.findViewById(R.id.DBikeBodyType);
            TextView DBikeService = view.findViewById(R.id.DBikeService);
            TextView DBikeDesc = view.findViewById(R.id.DBikeDescription);

            ImageView imgB1 = view.findViewById(R.id.objBikeImg1);
            ImageView imgB2 = view.findViewById(R.id.objBikeImg2);
            ImageView imgB3 = view.findViewById(R.id.objBikeImg3);
            ImageView imgB4 = view.findViewById(R.id.objBikeImg4);

            DBikeModel.setText(model.getBikeModel());
            DBikeAddress.setText(model.getBikeAddress()+", "+model.getBikeArea());
            DBikeAdvance.setText(model.getBikeAdvance());
            DBikeRent.setText(model.getBikeRent());
            DBikeMileage.setText(model.getBikeMileage());
            DBikeage.setText(model.getBikeAge());
            DBikeBodyType.setText(model.getBikeType());
            DBikeService.setText(model.getBikeServiceDate());
            DBikeDesc.setText(model.getBikeDescription());

            TextView next = view.findViewById(R.id.next);
            ImageButton favOn,share,favof;

            favOn = view.findViewById(R.id.favOn);
            share = view.findViewById(R.id.share);
            favof = view.findViewById(R.id.favOf);

            StorageReference storageReference1 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl1());
            StorageReference storageReference2 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl2());
            StorageReference storageReference3 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl3());
            StorageReference storageReference4 = FirebaseStorage.getInstance().getReference("images/" + model.getBikeUrl4());
            try {
               File file1 = File.createTempFile("randomKey", "");
               File file2 = File.createTempFile("randomKey", "");
               File file3 = File.createTempFile("randomKey", "");
               File file4 = File.createTempFile("randomKey", "");
               storageReference1.getFile(file1);
               storageReference2.getFile(file2);
               storageReference3.getFile(file3);
               storageReference4.getFile(file4)
                       .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                          @Override
                          public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                             Toast.makeText(imgB1.getContext(), "please wait", Toast.LENGTH_SHORT).show();
                             Bitmap bitmap1 = BitmapFactory.decodeFile(file1.getAbsolutePath());
                             Bitmap bitmap2 = BitmapFactory.decodeFile(file2.getAbsolutePath());
                             Bitmap bitmap3 = BitmapFactory.decodeFile(file3.getAbsolutePath());
                             Bitmap bitmap4 = BitmapFactory.decodeFile(file4.getAbsolutePath());
                             imgB1.setImageBitmap(bitmap1);
                             imgB2.setImageBitmap(bitmap2);
                             imgB3.setImageBitmap(bitmap3);
                             imgB4.setImageBitmap(bitmap4);

                          }
                       }).addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {
                             Toast.makeText(imgB1.getContext(), "Image can't Retrieve", Toast.LENGTH_SHORT).show();

                          }
                       });
            } catch (IOException e) {
               e.printStackTrace();
            }


            share.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  Intent intent = new Intent(Intent.ACTION_SEND);
                  intent.setType("text/plain");
                  intent.putExtra(Intent.EXTRA_SUBJECT,"House Info \n");
                  intent.putExtra(Intent.EXTRA_TEXT,model.getBikeModel() + "\n" + model.getBikeRent() + "\n" + model.getBikeMileage());
                  imgB1.getContext().startActivity(Intent.createChooser(intent,"share Via"));

               }
            });

            next.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  Intent intent = new Intent(imgB1.getContext(), BookEnter.class);
                  intent.putExtra("UserId", model.getUserId());
                  intent.putExtra("PhoneNo",model.getPhoneNo());
                  imgB1.getContext().startActivity(intent);

               }
            });

            FirebaseUser user = firebaseAuth.getCurrentUser();
            String currentUser1 = user.getUid();
//                databaseReference3 = firebaseDatabase.getReference("RentIt").child("Favourite");
            databaseReference1.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                  for (DataSnapshot ds : snapshot.getChildren()) {
                     if (ds.child("currentUsers").getValue().equals(currentUser1)) {
                        if (ds.child("ownerUId").getValue().equals(model.getUserId())) {
                           if (ds.child("ObjUrl1").getValue().equals(model.getBikeUrl1())) {
                              ran = ds.child("Random").getValue().toString();
                              favof.setVisibility(View.VISIBLE);
                              favOn.setVisibility(View.GONE);


                           }
                        }
                     }
                  }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
                  Toast.makeText(imgB1.getContext(), "sorry " + error, Toast.LENGTH_SHORT).show();
               }
            });

            favof.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  favof.setVisibility(View.GONE);
                  favOn.setVisibility(View.VISIBLE);
                  FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteBike")
                          .child(ran).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {
                                Toast.makeText(holder.BikeArea.getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                             }
                          });

               }
            });

            favOn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                  favof.setVisibility(View.VISIBLE);
                  favOn.setVisibility(View.GONE);

                  firebaseAuth = FirebaseAuth.getInstance();
                  firebaseDatabase = FirebaseDatabase.getInstance();
                  databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
                  FirebaseUser user = firebaseAuth.getCurrentUser();
                  currentUser = user.getUid();

                  Map<String, Object> map = new HashMap<>();
                  map.put("currentUsers", currentUser);
                  map.put("Random", randomKey);
                  map.put("ownerUId", model.getUserId());
                  map.put("bikeAddress",model.getBikeAddress());
                  map.put("bikeAdvance",model.getBikeAdvance());
                  map.put("bikeAge",model.getBikeAge());
                  map.put("bikeArea",model.getBikeArea());
                  map.put("bikeType",model.getBikeType());
                  map.put("bikeDescription",model.getBikeDescription());
                  map.put("bikeFastTag",model.getBikeFastTag());
                  map.put("bikeMileage",model.getBikeMileage());
                  map.put("bikeModel",model.getBikeModel());
                  map.put("bikeRent",model.getBikeRent());
                  map.put("bikeServiceDate",model.getBikeServiceDate());
                  map.put("ObjUrl1",model.getBikeUrl1());
                  map.put("bikeUrl1",model.getBikeUrl1());
                  map.put("bikeUrl2",model.getBikeUrl2());
                  map.put("bikeUrl3",model.getBikeUrl3());
                  map.put("bikeUrl4",model.getBikeUrl4());
                  map.put("type","BIKE");
                  map.put("OwnerEmail",user.getEmail());
                  map.put("UserId",user.getUid());
                  map.put("PhoneNo",model.getPhoneNo());


                  assert currentUser != null;
                  FirebaseDatabase.getInstance().getReference().child("RentIt").child("FavouriteBike").child(randomKey)
                          .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {
                                Toast.makeText(imgB1.getContext(), "Item Added to Favourite list", Toast.LENGTH_SHORT).show();

                             }
                          });
               }
            });


            dialogPlus.show();
         }
      });
   }

   @NonNull
   @Override
   public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_rv_item,parent,false);
      return new myViewHolder(view);
   }

   public class myViewHolder extends RecyclerView.ViewHolder{
      ImageView imgOfBike;
      TextView BikeModel,BikePrise,BikeArea;
      CardView cardBike;


      public myViewHolder(@NonNull View itemView) {
         super(itemView);

         imgOfBike = (ImageView)itemView.findViewById(R.id.objImgBike);
         BikeModel = (TextView) itemView.findViewById(R.id.ObjBikeModel);
         BikePrise = (TextView) itemView.findViewById(R.id.ObjBikePrise);
         BikeArea = (TextView) itemView.findViewById(R.id.ObjBikeArea);

         cardBike = (CardView) itemView.findViewById(R.id.CardBike);


      }
   }
}
