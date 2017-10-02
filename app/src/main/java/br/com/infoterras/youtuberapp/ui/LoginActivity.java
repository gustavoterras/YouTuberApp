package br.com.infoterras.youtuberapp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

    }

    public void open(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}