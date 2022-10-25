package bj.orace.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import bj.orace.voyage.databinding.ActivityProfileBinding;
import bj.orace.voyage.databinding.ActivitySettingBinding;

public class Setting extends AppCompatActivity {

    public ImageView retour;
//    public ActivitySettingBinding binding;
    protected  final int REQUEST_CODE = 1458;
//    public ActivityProfileBinding profileBinding;
    private ImageView editProfile;
    private ImageView photoDeProfile;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_setting);

        photoDeProfile = (ImageView) findViewById(R.id.profile);
        editProfile = (ImageView) findViewById(R.id.editer);
        retour = (ImageView) findViewById(R.id.retour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        photoDeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);
            }
        });


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditionProfile.class);
//                intent.putExtra("nom",profileBinding.nom.getText().toString());
//                intent.putExtra("prenom",profileBinding.prenom.getText().toString());
                startActivity(intent);
            }
        });




    }

}