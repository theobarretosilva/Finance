package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignupPage extends AppCompatActivity {

    EditText fullName, phone, email, cpf, password, confirmPassword;
    CheckBox showPassword, showConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        StartComponents();
    }

    private void StartComponents(){
        fullName = findViewById(R.id.nomeCadastro);
        phone = findViewById(R.id.telCadastro);
        email = findViewById(R.id.emailCadastro);
        cpf = findViewById(R.id.cpfCadastro);
        password = findViewById(R.id.senhaCadasatro);
        confirmPassword = findViewById(R.id.confirmSenhaCadastro);
        showPassword = findViewById(R.id.showPass);
        showConfirmPassword = findViewById(R.id.showConfirmPass);
    }

    public void CheckFilling(View a){
        if(fullName.getText().length() < 6) {
            fullName.setError("Preencha seu nome completo corretamente!");
        } else if(phone.getText().length() != 15) {
            phone.setError("Preencha o seu nome corretamente");
        } else if(email.getText().length() < 5) {
            email.setError("");
        } else if(!email.getText().toString().contains("@")) {
            email.setError("");
        } else if(cpf.getText().length() != 14) {
            cpf.setError("");
        } else if(password.getText().length() < 8) {
            password.setError("");
        } else if(confirmPassword.getText().length() < 8) {
            confirmPassword.setError("");
        } else if(!confirmPassword.getText().toString().equals(password.getText().toString())) {
            password.setError("");
            confirmPassword.setError("");
        } else {
            RegisterUser();
        }
    }

    private void RegisterUser(){

    }

    private void SendDatabaseUser(){

    }

    public void ShowPassword(View m) {
        if (showPassword.isChecked()){
            password.setInputType(InputType.TYPE_CLASS_TEXT);
            showPassword.setButtonDrawable(R.drawable.ic_outline_lock_open_24);
        }else{
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPassword.setButtonDrawable(R.drawable.ic_outline_lock_24);
        }
    }

    public void ShowConfirmPassword(View r) {
        if (showConfirmPassword.isChecked()){
            confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            showConfirmPassword.setButtonDrawable(R.drawable.ic_outline_lock_open_24);
        }else{
            confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showConfirmPassword.setButtonDrawable(R.drawable.ic_outline_lock_24);
        }
    }

    public void GoBackScreen(View l){
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
        ActivityCompat.startActivity(SignupPage.this, new Intent(this, InitialPage.class), activityOptionsCompat.toBundle());
    }
}