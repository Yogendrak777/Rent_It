package com.example.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class signUpPage extends AppCompatActivity {

    TextInputEditText Uname,Uemail,Uphone,Upassword,Uconfirmpassword;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        Uname = findViewById(R.id.UserName);
        Uemail = findViewById(R.id.email);
        Uphone = findViewById(R.id.UserNumber);
        Upassword = findViewById(R.id.password);
        Uconfirmpassword = findViewById(R.id.conformPassword);
        progressBar = findViewById(R.id.progress);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");

    }

    public void register(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String UserName = Uname.getText().toString().trim();
        String UserEmail = Uemail.getText().toString().trim();
        String UserPhone = Uphone.getText().toString().trim();
        String UserPassword = Upassword.getText().toString().trim();
        String UserConPassword = Uconfirmpassword.getText().toString().trim();



        if(!UserPassword.equals(UserConPassword)){
            Toast.makeText(signUpPage.this,"please check both passwords",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(UserEmail)||TextUtils.isEmpty(UserPassword)||TextUtils.isEmpty(UserName)||TextUtils.isEmpty(UserPhone)) {
            Toast.makeText(signUpPage.this, "please add all credentials..", Toast.LENGTH_LONG).show();
        } else if (UserPassword.length() < 6 ){
                Toast.makeText(signUpPage.this, "password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        }else{

           // personInfoRvModel personInfoRvModel = new personInfoRvModel(UserName,UserEmail,UserPhone);
            firebaseAuth.createUserWithEmailAndPassword(UserEmail,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.GONE);

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        String userId = user.getUid();

                        HashMap<String,Object> map = new HashMap<>();
                        map.put("userEmailDb",UserEmail);
                        map.put("userNameDb",UserName);
                        map.put("userPhoneDb",UserPhone);
                        map.put("UserId",userId);
                        map.put("UserPass",UserPassword);
                        map.put("Status","offline");
                        map.put("UserIMage","");

                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReference.child(userId).setValue(map);
                                Toast.makeText(signUpPage.this," Added Successfully ",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(signUpPage.this,"Error ",Toast.LENGTH_LONG).show();

                            }
                        });

                        Toast.makeText(signUpPage.this,"Registered Successfully ",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(signUpPage.this,loginPage.class));
                        finish();
                    }
                    else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(signUpPage.this,"Registered failed.. please try again later ",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}