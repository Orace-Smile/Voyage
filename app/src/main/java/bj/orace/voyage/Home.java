package bj.orace.voyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ImageView menu ;
    ImageSlider imageSlider;
    SearchView rechercher;
    SearchView searchView;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu = findViewById(R.id.menu);
        imageSlider =  findViewById(R.id.image_slider);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Setting.class);
                startActivity(intent);
            }
        });

        ArrayList<SlideModel> imagesList = new ArrayList<>();

        imagesList.add(new SlideModel(R.drawable.image2,"Profitez de vos bon moments",null));
        imagesList.add(new SlideModel(R.drawable.image3,"En vacance d'ete ou vous voullez",null));
        imagesList.add(new SlideModel(R.drawable.image4,"Voyagez",null));
        imagesList.add(new SlideModel(R.drawable.image5,"C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/QBULfTm15_suunP_IqgpzrZZ2sHgX_3Ea2ocdM1RpPQ/rs:fit:759:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC4w/a3UxQXZVb19fLW9o/eEg0RXFUWE9BSGFF/byZwaWQ9QXBp",null));
        imageSlider.setImageList(imagesList, ScaleTypes.CENTER_CROP);

//        imageSlider.setTouchListener(onTouchEvent());

    }
}