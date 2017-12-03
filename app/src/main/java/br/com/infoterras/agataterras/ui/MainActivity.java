package br.com.infoterras.agataterras.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.infoterras.agataterras.R;
import br.com.infoterras.agataterras.databinding.ActivityMainBinding;
import br.com.infoterras.agataterras.viewModel.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new ContentViewModel(this));
    }
}
