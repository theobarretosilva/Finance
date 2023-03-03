package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

        StartComponents();
    }

    private void StartComponents() {
        emailLogin = findViewById(R.id.editEmail);
        senhaLogin = findViewById(R.id.editSenha);
        checkLock = findViewById(R.id.checkLock);
        esqueciSenha = findViewById(R.id.esqueciSenha);
    }

    public void CheckFields(View a) {
        if (emailLogin.getText().length() < 6) {
            emailLogin.setError("Preencha seu email corretamente!");
        } else if (senhaLogin.getText().length() < 12) {
            senhaLogin.setError("Preencha sua senha corretamente");
        } else {
            UserAuth(a);
        }
    }

    public void UserAuth(View a) {
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

    public void ShowPassword(View m) {
        if (checkLock.isChecked()){
            senhaLogin.setInputType(InputType.TYPE_CLASS_TEXT);
            checkLock.setButtonDrawable(R.drawable.ic_outline_lock_open_24);
        }else{
            senhaLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            checkLock.setButtonDrawable(R.drawable.ic_outline_lock_24);
        }
    }

    public void RecoverPassword(View r){
        String email = emailLogin.getText().toString();

        if (email.isEmpty()){
            emailLogin.setError("Você precisa inserir o seu email para recuperar a sua senha");
        }else{
            SendEmail(email);
        }
    }

    private void SendEmail(String email){
        Firebase.getFirebaseAuth().sendPasswordResetEmail(email)
                .addOnSuccessListener(unused ->
                        Toast.makeText(getBaseContext(), "Enviamos uma mensagem para o seu email com um link para redefinir", Toast.LENGTH_LONG).show()
                )
                .addOnFailureListener(e ->
                        Toast.makeText(getBaseContext(), "Erro ao enviar o email", Toast.LENGTH_LONG).show()
                );
    }
}