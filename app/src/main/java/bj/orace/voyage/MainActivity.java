package bj.orace.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import bj.orace.voyage.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView app_image;
    private CircularProgressIndicator loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app_image = (ImageView) findViewById(R.id.app_image);
        loader = (CircularProgressIndicator) findViewById(R.id.loader);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(runnable,5000);

    }
}