package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SecondSignupPage extends AppCompatActivity {

    EditText monthlyExpense, monthlyPayment, monthlyExpensesGoal;
    Spinner mainExpense;

    String[] mainExpenses = new String []{
            "Selecione",
            "Compras",
            "Alimentação",
            "Contas",
            "Compras parceladas",
            "Transporte",
            "Outros"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_signup_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        StartComponents();
    }

    private void StartComponents() {
        monthlyExpense = findViewById(R.id.monthlyExpense);
        monthlyPayment = findViewById(R.id.monthlyPayment);
        monthlyExpensesGoal = findViewById(R.id.monthlyExpensesGoal);
        mainExpense = findViewById(R.id.mainExpense);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spi_elemento, mainExpenses);
        adapter.setDropDownViewResource(R.layout.spi_dropdown_elemento);
        mainExpense.setAdapter(adapter);
        mainExpense.setSelection(0, true);
    }

    public void CheckFilling(View g){
        if(monthlyExpense.getText().length() != ) {

        } else if() {

        } else if() {

        } else if() {

        }
    }

    private void SendFinanceDBUSer(){

    }

    public void GoBackScreen(View l){
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
        ActivityCompat.startActivity(SecondSignupPage.this, new Intent(this, SignupPage.class), activityOptionsCompat.toBundle());
    }
}