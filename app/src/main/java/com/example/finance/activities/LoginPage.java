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
import android.widget.TextView;
import android.widget.Toast;

import com.example.finance.Firebase;
import com.example.finance.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginPage extends AppCompatActivity {

    EditText emailLogin, senhaLogin;
    CheckBox checkLock;
    TextView esqueciSenha;

    String erro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        startComponents();
    }

    private void startComponents() {
        emailLogin = findViewById(R.id.editEmail);
        senhaLogin = findViewById(R.id.editSenha);
        checkLock = findViewById(R.id.checkLock);
        esqueciSenha = findViewById(R.id.esqueciSenha);
    }

    public void checkFields(View a) {
        if (emailLogin.getText().length() < 6) {
            emailLogin.setError("Preencha seu email corretamente!");
        } else if (senhaLogin.getText().length() < 12) {
            senhaLogin.setError("Preencha sua senha corretamente");
        } else {
            userAuth(a);
        }
    }

    public void userAuth(View a) {
        String email = emailLogin.getText().toString();
        String senha = senhaLogin.getText().toString();

        Firebase.getFirebaseAuth()
                .signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginPage.this, MainPage.class));
                    } else {
                        try {
                            throw task.getException();
                        }catch (Exception e){
                            erro = "Erro ao logar o usuário";
                        }
                        Snackbar snackbar = Snackbar.make(a,erro,Snackbar.LENGTH_LONG);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                    }
                });
    }

    public void showPassword(View m) {
        if (checkLock.isChecked()){
            senhaLogin.setInputType(InputType.TYPE_CLASS_TEXT);
            checkLock.setButtonDrawable(R.drawable.ic_outline_lock_open_24);
        }else{
            senhaLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            checkLock.setButtonDrawable(R.drawable.ic_outline_lock_24);
        }
    }

    public void recoverPassword(View r){
        String email = emailLogin.getText().toString();

        if (email.isEmpty()){
            emailLogin.setError("Você precisa inserir o seu email para recuperar a sua senha");
        }else{
            sendEmail(email);
        }
    }

    private void sendEmail(String email){
        Firebase.getFirebaseAuth().sendPasswordResetEmail(email)
                .addOnSuccessListener(unused ->
                        Toast.makeText(getBaseContext(), "Enviamos uma mensagem para o seu email com um link para redefinir", Toast.LENGTH_LONG).show()
                )
                .addOnFailureListener(e ->
                        Toast.makeText(getBaseContext(), "Erro ao enviar o email", Toast.LENGTH_LONG).show()
                );
    }

    public void goBackScreen(View l){
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
        ActivityCompat.startActivity(LoginPage.this, new Intent(this, InitialPage.class), activityOptionsCompat.toBundle());
    }
}