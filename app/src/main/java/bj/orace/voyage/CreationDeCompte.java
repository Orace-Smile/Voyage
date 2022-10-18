package bj.orace.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import bj.orace.voyage.databinding.ActivityMain2Binding;
import bj.orace.voyage.ui.login.LoginActivity;

public class CreationDeCompte extends AppCompatActivity {
    private ActivityMain2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        EditText nom = binding.nom;
        EditText prenom = binding.prenom;
        EditText pass = binding.motDePasse;
        EditText confirmPass = binding.confirmMotDePasse;
        Button creerCompte = binding.creer;

        creerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                intent.putExtra("nom",nom.getText().toString());
//                intent.putExtra("prenom",prenom.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }
}