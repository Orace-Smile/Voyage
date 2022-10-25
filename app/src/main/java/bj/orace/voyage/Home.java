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

    // TODO: 25/10/2022 Declaration des variables 
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

        // TODO: 25/10/2022 Cliaue sur le menu qui renvoie sur le Reglage
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 25/10/2022 Passage au parametre 
                Intent intent = new Intent(getApplicationContext(),Setting.class);
                startActivity(intent);
            }
        });
        // TODO: 25/10/2022 Creation du carousel pour l'acceuil 
        ArrayList<SlideModel> imagesList = new ArrayList<>();

        imagesList.add(new SlideModel(R.drawable.image2,"Profitez de vos bon moments",null));
        imagesList.add(new SlideModel(R.drawable.image3,"En vacance d'ete ou vous voullez",null));
        imagesList.add(new SlideModel(R.drawable.image4,"Voyagez",null));
        imagesList.add(new SlideModel(R.drawable.image5,"C'est vous le maitre",null));
        // TODO: 25/10/2022 Importtion des images depuis internet 
        imagesList.add(new SlideModel("https://imgs.search.brave.com/7WNXFtlr4-Z2NwQBE4mVYeNjOU1OLFP2AjOnnHcXibI/rs:fit:711:225:1/g:ce/aHR0cHM6Ly90c2Uy/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC4t/QjZlU0lXcUdMSUx3/c0Q3Rk84SFBBSGFF/OCZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/EsFLlpN6R7GUihi9cDALWwMJ-OlA2rly1tMiCjAQCnw/rs:fit:711:225:1/g:ce/aHR0cHM6Ly90c2Uz/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5l/N0RBNld1Nk96cC1M/OVhCVlNCN1NRSGFF/OCZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/lfrREwVEvHMZ3ir-Fxbp-ulxYOX90Zphz018d5qhmz4/rs:fit:759:225:1/g:ce/aHR0cHM6Ly90c2Uz/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5K/eXVyOTNvbkttR2dp/c0s4cVBzMzF3SGFF/byZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/av9xqMOHTErsUItZ670Pru0iOwWtP4O3-GytBZxs7C8/rs:fit:844:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC55/MVpHMkJGS3NtenNR/ZHVrT2d1NDBRSGFF/SyZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/f-jQj7lp8k7rfzMIZKK_zuF7AAxiF4-ntmxv38VCSKE/rs:fit:713:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC56/NlVSUkgzYTJJX3hE/YUJwaUZ6SVdRSGFF/NyZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/124s5XpPSX3gg5CDVJH_ph7cjVvibcW9t0wKsgDOFQI/rs:fit:844:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5Q/WWlwSl9oU25jdWdN/MlN3blppdHZnSGFF/SyZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/5HQBrsaMCKrNxqnMNDA16Xxg7QkzT5pZq0nyHXo2I2I/rs:fit:759:225:1/g:ce/aHR0cHM6Ly90c2Uy/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5h/dnhodFFxcjVGb3Vi/R2pxNHNJWHVnSGFF/byZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/-Wmaf3A34mcP9o1ucEIaiex4IHpEAc_fVTb1ews65wY/rs:fit:780:225:1/g:ce/aHR0cHM6Ly90c2Uz/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5a/bVI5YzR0Si00RVJj/bE1qLU5pM1ZRSGFF/ZyZwaWQ9QXBp","C'est vous le maitre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/QBULfTm15_suunP_IqgpzrZZ2sHgX_3Ea2ocdM1RpPQ/rs:fit:759:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC4w/a3UxQXZVb19fLW9o/eEg0RXFUWE9BSGFF/byZwaWQ9QXBp",null));
        imageSlider.setImageList(imagesList, ScaleTypes.CENTER_CROP);

//        imageSlider.setTouchListener(onTouchEvent());

    }
}