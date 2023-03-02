package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class InitialPage extends AppCompatActivity {

    TextView titulo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_page);
        getWindow().setStatusBarColor(Color.rgb(94,23,235));
        getSupportActionBar().hide();

        titulo2 = findViewById(R.id.titulo2);

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