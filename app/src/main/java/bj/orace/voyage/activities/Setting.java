package bj.orace.voyage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import bj.orace.voyage.Profile;
import bj.orace.voyage.R;

public class Setting extends AppCompatActivity {


    protected  final int REQUEST_CODE = 1458;
    private ImageView editProfile,photoDeProfile,favoris,parametre;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        favoris =  findViewById(R.id.favoris);
        photoDeProfile = (ImageView) findViewById(R.id.profile);
        editProfile = (ImageView) findViewById(R.id.editer);
        parametre = findViewById(R.id.param);

        photoDeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(), Profile.class);
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

        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Favoris.class));
                finish();
                bottomNavigationView = findViewById(R.id.bottom_navigation);
                bottomNavigationView.setSelectedItemId(R.id.page_5);

                // TODO: 26/10/2022 Gestion des clique sur le menu du bas
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.page_0:
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                                overridePendingTransition(0,0);
                                finish();
                                return true;
                            case R.id.page_1:
                                startActivity(new Intent(getApplicationContext(),Places.class));
                                overridePendingTransition(0,0);
                                finish();
                                return true;
                            case R.id.page_2:
                                startActivity(new Intent(getApplicationContext(),Restaurants.class));
                                overridePendingTransition(0,0);
                                finish();
                                return true;
                            case R.id.page_3:
                                startActivity(new Intent(getApplicationContext(), Hotels.class));
                                overridePendingTransition(0,0);
                                finish();
                                return true;
                            case R.id.page_5:
                                startActivity(new Intent(getApplicationContext(), Setting.class));
                                overridePendingTransition(0,0);
                                finish();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });


    }

}