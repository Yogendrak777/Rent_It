package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;

public class welcomePage extends AppCompatActivity {
//    VideoView appNameLogo;
//    LottieAnimationView lottieAnimationViewStartUp;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //appNameLogo = findViewById(R.id.videoView);
        //lottieAnimationViewStartUp = findViewById(R.id.lottie);
        //lottieAnimationViewStartUp.animate().translationX(450).setDuration(3000).setStartDelay(0);
        //appNameLogo.animate().translationY(-1500).setDuration(2000).setStartDelay(0);

        videoView = findViewById(R.id.videoView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        videoView.setLayoutParams(params);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.logo);
        videoView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(welcomePage.this,loginPage.class));
                finish();
            }
        },1500);
    }
}