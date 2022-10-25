package bj.orace.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import bj.orace.voyage.databinding.ActivityEditionProfileBinding;
import bj.orace.voyage.databinding.ActivityProfileBinding;

public class EditionProfile extends AppCompatActivity {
    private ActivityEditionProfileBinding editionProfileBinding;
    private ActivityProfileBinding profileBinding;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition_profile);
        back =findViewById(R.id.image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}