package bj.orace.voyage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Setting extends AppCompatActivity {

    public ImageView retour;
    protected  final int REQUEST_CODE = 1458;
    private ImageView editProfile,photoDeProfile,favoris,parametre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        favoris =  findViewById(R.id.favoris);
        photoDeProfile = (ImageView) findViewById(R.id.profile);
        editProfile = (ImageView) findViewById(R.id.editer);
        retour = (ImageView) findViewById(R.id.retour);
        parametre = findViewById(R.id.param);

        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Favoris.class));
                finish();
            }
        });


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

        parametre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ParametreActivity.class));
            }
        });


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditionProfile.class);
                startActivity(intent);
            }
        });
    }

}