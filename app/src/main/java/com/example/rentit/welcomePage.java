package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class welcomePage extends AppCompatActivity {
    TextView appNamelogo;
    LottieAnimationView lottieAnimationViewStartUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        appNamelogo = findViewById(R.id.welcome);
        lottieAnimationViewStartUp = findViewById(R.id.lottie);

        lottieAnimationViewStartUp.animate().translationX(450).setDuration(3000).setStartDelay(0);
        appNamelogo.animate().translationY(-1500).setDuration(2000).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(welcomePage.this,loginPage.class));
                finish();
            }
        },5000);
    }
}