package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondSignupPage extends AppCompatActivity {

    EditText monthlyExpense, monthlyPayment, monthlyExpensesGoal;
    Spinner mainExpense;
    CheckBox checkData;

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

        startComponents();
        focusChangeListener();
    }

    private void startComponents() {
        monthlyExpense = findViewById(R.id.monthlyExpense);
        monthlyPayment = findViewById(R.id.monthlyPayment);
        monthlyExpensesGoal = findViewById(R.id.monthlyExpensesGoal);
        mainExpense = findViewById(R.id.mainExpense);
        checkData = findViewById(R.id.checkData);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spi_elemento, mainExpenses);
        adapter.setDropDownViewResource(R.layout.spi_dropdown_elemento);
        mainExpense.setAdapter(adapter);
        mainExpense.setSelection(0, true);
    }

    public void checkFilling(View g){
        if(monthlyExpense.getText().length() < 9) {
            monthlyExpense.setError("Deve estar no formato: R$ XXX,XX ou R$ X.XXX,XX");
        } else if(monthlyPayment.getText().length() < 9) {
            monthlyPayment.setError("Deve estar no formato: R$ XXX,XX ou R$ X.XXX,XX");
        } else if(monthlyExpensesGoal.getText().length() < 9) {
            monthlyExpensesGoal.setError("Deve estar no formato: R$ XXX,XX ou R$ X.XXX,XX");
        } else if(mainExpense.getSelectedItem().toString() == "Selecione") {
            TextView errorText = (TextView)mainExpense.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Selecione uma opção válida!");
        } else if (!checkData.isChecked()) {
            checkData.setError("Você precisa aceitar os nossos termos!");
        } else {
            sendFinanceDBUSer();
        }
    }

    private void sendFinanceDBUSer(){
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setMonthlyExpenses(monthlyExpense.getText().toString());
        userRegistration.setMonthlyPayment(monthlyPayment.getText().toString());
        userRegistration.setMonthlyExpensesGoal(monthlyExpensesGoal.getText().toString());
        userRegistration.setMainExpense(mainExpense.getSelectedItem().toString());
        userRegistration.registerUser();
    }

    private void focusChangeListener(){

    }

    public void goBackScreen(View l){
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
        ActivityCompat.startActivity(SecondSignupPage.this, new Intent(this, SignupPage.class), activityOptionsCompat.toBundle());
    }
}