package bj.orace.voyage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import bj.orace.voyage.databinding.ActivityForgetBinding;

public class ForgetActivity extends AppCompatActivity {
    private ActivityForgetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}