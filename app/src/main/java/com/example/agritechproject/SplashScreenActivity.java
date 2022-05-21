package com.example.agritechproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txtPourcentage;
    int value;
    Handler handler=new Handler();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar=findViewById(R.id.progresbar);
        txtPourcentage=findViewById(R.id.txt_pourcentage);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                progressAnimation();

            }
        });
        thread.start();
    }
    public void progressAnimation(){
        for (value=0;value<100;value=value+1){
            try {
                Thread.sleep(50);
                progressBar.setProgress(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    txtPourcentage.setText(String.valueOf(value+" %"));

                }
            });

        }
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}