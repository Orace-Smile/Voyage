package bj.orace.voyage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import bj.orace.voyage.databinding.ActivityLoginBinding;
import bj.orace.voyage.utility.LoadingDialog;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String email, password;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog = new LoadingDialog(this);

        binding.btnSignUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
        });

        binding.textForgetPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,ForgetActivity.class));

        });

        binding.btnLogin.setOnClickListener(view -> {
            if (areFieldReady()){
                login();
            }
        });
    }

    private void login() {
        loadingDialog.startLoading();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                loadingDialog.stopLoading();
                                Intent intent = new Intent(LoginActivity.this, Home.class);
                                startActivity(intent);
                                finish();
                            }else {
                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> email) {
                                        if (email.isSuccessful()){
                                            loadingDialog.stopLoading();
                                            Toast.makeText(LoginActivity.this, "Verifier votre email", Toast.LENGTH_SHORT).show();
                                        }else {
                                            loadingDialog.stopLoading();
                                            Toast.makeText(LoginActivity.this, "Erreur :"+email.getException(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }else {
                            loadingDialog.stopLoading();
                            Toast.makeText(LoginActivity.this, "Erreur :"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private boolean areFieldReady(){
        email = binding.edtEmail.getText().toString().trim();
        password = binding.edtPassword.getText().toString().trim();

        boolean flag = false;
        View requestView = null;

         if (email.isEmpty()){
            binding.edtEmail.setError("Ce champs est requis");
            flag = true;
            requestView = binding.edtEmail;
        }else if(password.isEmpty()){
            binding.edtPassword.setError("Ce champs est requis");
            flag = true;
            requestView =  binding.edtPassword;
        }else if (password.length() < 5){
            binding.edtPassword.setError("Minimum 5 caracteres");
            flag = true;
            requestView = binding.edtPassword;
        }

        if (flag){
            requestView.requestFocus();
            return false;
        }else {
            return true;
        }
    }

}