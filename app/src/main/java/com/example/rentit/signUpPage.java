package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signUpPage extends AppCompatActivity {

    TextInputEditText Uname,Uemail,Uphone,Upassword,Uconfirmpassword;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String UniqId;

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
        databaseReference = firebaseDatabase.getReference("RentIt").child("9902915607").child("car1");

    }

    public void register(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String UserName = Uname.getText().toString().trim();
        String UserEmail = Uemail.getText().toString().trim();
        String UserPhone = Uphone.getText().toString().trim();
        String UserPassword = Upassword.getText().toString().trim();
        String UserConPassword = Uconfirmpassword.getText().toString().trim();
        UniqId = UserPhone;

        if(!UserPassword.equals(UserConPassword)){
            Toast.makeText(signUpPage.this,"please check both passwords",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(UserEmail)||TextUtils.isEmpty(UserPassword)||TextUtils.isEmpty(UserName)||TextUtils.isEmpty(UserPhone)){
            Toast.makeText(signUpPage.this,"please add all credentials..",Toast.LENGTH_LONG).show();
        }else{
            personInfoRvModel personInfoRvModel = new personInfoRvModel(UserName,UserEmail,UserPhone,UniqId);
            firebaseAuth.createUserWithEmailAndPassword(UserEmail,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReference.child(UniqId).setValue(personInfoRvModel);
                                Toast.makeText(signUpPage.this," Added Successfully ",Toast.LENGTH_LONG).show();

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