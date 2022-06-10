package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPage extends AppCompatActivity {

    TextInputEditText Uemail;
    TextView button;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);
        Uemail = findViewById(R.id.email);
        button = findViewById(R.id.button);

    }

    public void forgot(View view) {

        email = Uemail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
        } else {

            FirebaseAuth auth = FirebaseAuth.getInstance();
            String emailAddress = email;

            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordPage.this, "Please Check your Email", Toast.LENGTH_SHORT).show();
                                Toast.makeText(ForgotPasswordPage.this, "Password is sent to your Email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPasswordPage.this, loginPage.class));
                                finish();
                            }
                        }
                    });
        }
    }
}
