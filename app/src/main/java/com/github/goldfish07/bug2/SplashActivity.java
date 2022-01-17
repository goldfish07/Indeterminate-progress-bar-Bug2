package com.github.goldfish07.bug2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    CircularProgressIndicator circularProgressIndicator;
    LinearProgressIndicator linearProgressIndicator;
    ProgressBar appCompactProgressBar;
    ProgressBar appCompactProgressBarHorizontal;

    Button startActivityBtn;
    RadioGroup radioGroup;
    String choice = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        circularProgressIndicator = findViewById(R.id.circularProgressIndicator);
        linearProgressIndicator = findViewById(R.id.linearProgressIndicator);
        appCompactProgressBar = findViewById(R.id.appCompactProgressbar);
        appCompactProgressBarHorizontal = findViewById(R.id.appCompactProgressbarHorizontal);

        startActivityBtn = findViewById(R.id.startActivityBtn);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);


        startActivityBtn.setOnClickListener(v -> {
            if(choice.isEmpty()){
                Toast.makeText(SplashActivity.this,"Please select your choice for activity",Toast.LENGTH_SHORT).show();
            } else {
                circularProgressIndicator.setVisibility(View.VISIBLE);
                linearProgressIndicator.setVisibility(View.VISIBLE);
                appCompactProgressBar.setVisibility(View.VISIBLE);
                appCompactProgressBarHorizontal.setVisibility(View.VISIBLE);
                if(choice.equals("handler")){
                    startDelayActivity();
                } else if (choice.equals("countDown")){
                    startActivityUsingCountdownTimer();
                }
            }

        });

    }

    RadioGroup.OnCheckedChangeListener checkedChangeListener = (group, checkedId) -> {
        if(checkedId == R.id.radioHandler){
            choice = "handler";
        } else if(checkedId == R.id.radioCountDown){
            choice = "countDown";
        }

    };
    public void startDelayActivity(){
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        },2000);
    }

    public void startActivityUsingCountdownTimer(){
        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }.start();
    }

}