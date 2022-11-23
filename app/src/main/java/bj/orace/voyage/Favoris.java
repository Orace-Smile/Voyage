package bj.orace.voyage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Favoris extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.page_4);

        // TODO: 26/10/2022 Gestion des clique sur le menu du bas
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_0:
                        Intent intent = new Intent(getApplicationContext(),Home.class);
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
                        startActivity(new Intent(getApplicationContext(),Hotels.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.page_4:
                        startActivity(new Intent(getApplicationContext(),Favoris.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}