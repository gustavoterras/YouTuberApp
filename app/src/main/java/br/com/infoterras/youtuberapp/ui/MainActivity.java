package br.com.infoterras.youtuberapp.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.databinding.ActivityMainBinding;
import br.com.infoterras.youtuberapp.viewModel.ContentViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new ContentViewModel(this));
    }
}
