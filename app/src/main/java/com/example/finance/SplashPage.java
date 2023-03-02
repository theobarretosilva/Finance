package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

public class SplashPage extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(this, 1400);
    }

    @Override
    public void run() {
        startActivity(new Intent(SplashPage.this, InitialPage.class));
    }
}