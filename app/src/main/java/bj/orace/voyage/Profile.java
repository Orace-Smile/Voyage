package bj.orace.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import bj.orace.voyage.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {
    public ActivityProfileBinding binding;
    protected final int REQUEST_CODE_PROFILE = 1236;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}