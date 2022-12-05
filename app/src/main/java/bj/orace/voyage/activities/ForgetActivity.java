package bj.orace.voyage.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import bj.orace.voyage.databinding.ActivityForgetBinding;

public class ForgetActivity extends AppCompatActivity {
    private ActivityForgetBinding binding;
    private String userEmail;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private boolean isOk(){
        userEmail = binding.updateEmail.getText().toString().trim();
        TextInputEditText requestview = null;
        boolean flag = false;
        if(userEmail.isEmpty()){
            binding.updateEmail.setError("Ce champs est resquis");
            flag = true;
            requestview = binding.updateEmail;
        }

        if (flag){
            requestview.requestFocus();
            return false;
        }else {
            return true;
        }
    }
}