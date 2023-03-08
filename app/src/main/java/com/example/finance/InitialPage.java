package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

public class InitialPage extends AppCompatActivity {

    TextView titulo2;
    Button login, cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        startActivity(new Intent(InitialPage.this, SecondSignupPage.class));

        titulo2 = findViewById(R.id.titulo2);
        login = findViewById(R.id.btnLogin);
        cadastro = findViewById(R.id.btnCadastro);

        login.setOnClickListener(v -> {
            startActivity(new Intent(InitialPage.this, LoginPage.class));
        });

        cadastro.setOnClickListener(v -> {
            startActivity(new Intent(InitialPage.this, SignupPage.class));
        });

        setarUnderline();
    }

    public void setarUnderline() {
        if (Build.VERSION.SDK_INT >= 24)
        {
            titulo2.setText(Html.fromHtml("<u>O seu aplicativo favorito de finanças!</u>",Html.FROM_HTML_MODE_LEGACY));
        }
        else
        {
            titulo2.setText(Html.fromHtml("<u>O seu aplicativo favorito de finanças!</u>"));
        }
    }
}