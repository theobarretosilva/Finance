package com.example.finance.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finance.Firebase;
import com.example.finance.R;
import com.example.finance.classes.UserRegistration;

public class SignupPage extends AppCompatActivity {

    EditText fullName, phone, email, cpf, password, confirmPassword;
    CheckBox showPassword, showConfirmPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        startComponents();
        focusChangeListener();
    }

    private void startComponents(){
        fullName = findViewById(R.id.nomeCadastro);
        phone = findViewById(R.id.telCadastro);
        email = findViewById(R.id.emailCadastro);
        cpf = findViewById(R.id.cpfCadastro);
        password = findViewById(R.id.senhaCadasatro);
        confirmPassword = findViewById(R.id.confirmSenhaCadastro);
        showPassword = findViewById(R.id.showPass);
        showConfirmPassword = findViewById(R.id.showConfirmPass);
        progressBar = findViewById(R.id.progressBar);
    }

    public void checkFilling(View a){
        if(fullName.getText().length() < 6) {
            fullName.setError("Preencha seu nome completo corretamente!");
        } else if(phone.getText().length() != 15) {
            phone.setError("Preencha o seu nome corretamente!");
        } else if(email.getText().length() < 5) {
            email.setError("Preencha o seu email corretamente!");
        } else if(!email.getText().toString().contains("@")) {
            email.setError("Voc?? deve informar um email v??lido!");
        } else if(cpf.getText().length() != 14) {
            cpf.setError("Informe um CPF v??lido!");
        } else if(password.getText().length() < 8) {
            password.setError("A senha deve ter no m??nimo 8 caracteres!");
        } else if(confirmPassword.getText().length() < 8) {
            confirmPassword.setError("A confirma????o da senha deve ter no m??nimo 8 caracteres!");
        } else if(!confirmPassword.getText().toString().equals(password.getText().toString())) {
            password.setError("As senhas n??o est??o iguais!");
            confirmPassword.setError("As senhas n??o est??o iguais!");
        } else {
            registerUser();
        }
    }

    private void registerUser(){
        String registerEmail = email.getText().toString();
        String registerPassword = password.getText().toString();

        Firebase.getFirebaseAuth()
                .createUserWithEmailAndPassword(registerEmail, registerPassword)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        sendDatabaseUser();
                        startActivity(new Intent(SignupPage.this, SecondSignupPage.class));
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "N??o foi poss??vel conclu??r o seu cadastro, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                });
    }

    private void sendDatabaseUser(){
        UserRegistration userRegistration = new UserRegistration();

        userRegistration.setFullName(fullName.getText().toString());
        userRegistration.setPhone(phone.getText().toString());
        userRegistration.setEmail(email.getText().toString());
        userRegistration.setCpf(cpf.getText().toString());
    }

    private void focusChangeListener(){
        fullName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && fullName.getText().length() > 6) {
                progressBar.setProgress(10);
            } else if(hasFocus && fullName.getText().length() == 0) {
                progressBar.setProgress(0);
            }
        });

        phone.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && phone.getText().length() == 15) {
                progressBar.setProgress(20);
            } else if(hasFocus && phone.getText().length() == 0) {
                progressBar.setProgress(10);
            }
        });

        email.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && email.getText().length() > 5 && email.getText().toString().contains("@")) {
                progressBar.setProgress(30);
            } else if(hasFocus && email.getText().length() == 0) {
                progressBar.setProgress(20);
            }
        });

        cpf.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && cpf.getText().length() == 14) {
                progressBar.setProgress(40);
            } else if(hasFocus && cpf.getText().length() == 0) {
                progressBar.setProgress(30);
            }
        });

        password.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && password.getText().length() > 8) {
                progressBar.setProgress(50);
            } else if(hasFocus && password.getText().length() == 0) {
                progressBar.setProgress(40);
            }
        });

        confirmPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && confirmPassword.getText().toString().equals(password.getText().toString())) {
                progressBar.setProgress(60);
            } else if(hasFocus && confirmPassword.getText().length() == 0) {
                progressBar.setProgress(50);
            }
        });
    }

    public void showPassword(View m) {
        if (showPassword.isChecked()){
            password.setInputType(InputType.TYPE_CLASS_TEXT);
            showPassword.setButtonDrawable(R.drawable.ic_outline_lock_open_24);
        }else{
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPassword.setButtonDrawable(R.drawable.ic_outline_lock_24);
        }
    }

    public void showConfirmPassword(View r) {
        if (showConfirmPassword.isChecked()){
            confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            showConfirmPassword.setButtonDrawable(R.drawable.ic_outline_lock_open_24);
        }else{
            confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showConfirmPassword.setButtonDrawable(R.drawable.ic_outline_lock_24);
        }
    }

    public void goBackScreen(View l){
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
        ActivityCompat.startActivity(SignupPage.this, new Intent(this, InitialPage.class), activityOptionsCompat.toBundle());
    }
}