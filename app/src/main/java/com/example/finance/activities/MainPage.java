package com.example.finance.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.finance.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        startComponents();
    }

    private void startComponents(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home: {

            }
            case R.id.action_payment: {

            }
            case R.id.action_add: {

            }
            case R.id.action_settings: {

            }
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}