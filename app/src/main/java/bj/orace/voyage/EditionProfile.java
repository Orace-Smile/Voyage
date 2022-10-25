package bj.orace.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import bj.orace.voyage.databinding.ActivityEditionProfileBinding;
import bj.orace.voyage.databinding.ActivityProfileBinding;

public class EditionProfile extends AppCompatActivity {
    private ActivityEditionProfileBinding editionProfileBinding;
    private ActivityProfileBinding profileBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition_profile);
    }
}