package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginPage extends AppCompatActivity {

    TextInputEditText Uemail,Upassward;
    TextView forgotPassword,button;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Uemail = findViewById(R.id.email);
        Upassward = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.ForgetPassword);
        progressBar = findViewById(R.id.progress);
        firebaseAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.button);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!= null){
            startActivity(new Intent(loginPage.this,carRvContainer.class));
            this.finish();
        }
    }

    public void signup(View view) {
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(loginPage.this,signUpPage.class));
    }

    public void ForgotPassword(View view) {
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(loginPage.this,ForgotPasswordPage.class));
    }

    public void signIn(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String UserEmail = Uemail.getText().toString().trim();
        String UserPassword = Upassward.getText().toString().trim();


        if(TextUtils.isEmpty(UserEmail)||TextUtils.isEmpty(UserPassword)){
            Toast.makeText(loginPage.this,"please add all credentials..",Toast.LENGTH_LONG).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(UserEmail,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(loginPage.this,"Successfully",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(loginPage.this,MainActivity.class));
                        finish();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(loginPage.this,"SignIn Failed..",Toast.LENGTH_LONG).show();

                    }
                }
            });

        }
    }
}