package bj.orace.voyage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import bj.orace.voyage.R;

public class Home extends AppCompatActivity {

    ImageSlider imageSlider;

    BottomNavigationView bottomNavigationView;
    private String item;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSlider =  findViewById(R.id.image_slider);

//        listView = findViewById(R.id.post_list);


//        ArrayList<Post> arrayList = new ArrayList<>();
//
//        ArrayAdapter arrayAdapter =  new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);

//        sendComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int i = 0;
////                while (i< arrayList.size()) {
//                    arrayList.add(commentInput.getText().toString());
//                    commentInput.setText("");
//                    item = String.valueOf(arrayAdapter.getItemId(1));
//
//                Toast.makeText(Home.this, commentInput.getText().toString(), Toast.LENGTH_SHORT).show();
////                }
//                affichageComment.setAdapter(arrayAdapter);
//            }
//        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.page_0);


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
                        startActivity(new Intent(getApplicationContext(), Places.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.page_2:
                        startActivity(new Intent(getApplicationContext(), Restaurants.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.page_3:
                        startActivity(new Intent(getApplicationContext(),Hotels.class));
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

        // TODO: 25/10/2022 Creation du carousel pour l'acceuil 
        ArrayList<SlideModel> imagesList = new ArrayList<>();

        imagesList.add(new SlideModel(R.drawable.image2,"Profitez des bon moments",null));
        imagesList.add(new SlideModel(R.drawable.image3,"En vacance d'ete ou vous voullez",null));
        imagesList.add(new SlideModel(R.drawable.image4,"Voyagez partout dans le monde",null));
        imagesList.add(new SlideModel(R.drawable.image5,"C'est vous le maitre",null));
        // TODO: 25/10/2022 Importtion des images depuis internet 
        imagesList.add(new SlideModel("https://imgs.search.brave.com/7WNXFtlr4-Z2NwQBE4mVYeNjOU1OLFP2AjOnnHcXibI/rs:fit:711:225:1/g:ce/aHR0cHM6Ly90c2Uy/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC4t/QjZlU0lXcUdMSUx3/c0Q3Rk84SFBBSGFF/OCZwaWQ9QXBp","Profitez des belles vues",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/EsFLlpN6R7GUihi9cDALWwMJ-OlA2rly1tMiCjAQCnw/rs:fit:711:225:1/g:ce/aHR0cHM6Ly90c2Uz/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5l/N0RBNld1Nk96cC1M/OVhCVlNCN1NRSGFF/OCZwaWQ9QXBp","Hotels,Restaurants ou un endroit",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/lfrREwVEvHMZ3ir-Fxbp-ulxYOX90Zphz018d5qhmz4/rs:fit:759:225:1/g:ce/aHR0cHM6Ly90c2Uz/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5K/eXVyOTNvbkttR2dp/c0s4cVBzMzF3SGFF/byZwaWQ9QXBp","Il n'y a que vous",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/av9xqMOHTErsUItZ670Pru0iOwWtP4O3-GytBZxs7C8/rs:fit:844:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC55/MVpHMkJGS3NtenNR/ZHVrT2d1NDBRSGFF/SyZwaWQ9QXBp","Soleil,plages,cocotiers",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/f-jQj7lp8k7rfzMIZKK_zuF7AAxiF4-ntmxv38VCSKE/rs:fit:713:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC56/NlVSUkgzYTJJX3hE/YUJwaUZ6SVdRSGFF/NyZwaWQ9QXBp","Laisse toi vivre",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/124s5XpPSX3gg5CDVJH_ph7cjVvibcW9t0wKsgDOFQI/rs:fit:844:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5Q/WWlwSl9oU25jdWdN/MlN3blppdHZnSGFF/SyZwaWQ9QXBp","C'est vous le prince",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/5HQBrsaMCKrNxqnMNDA16Xxg7QkzT5pZq0nyHXo2I2I/rs:fit:759:225:1/g:ce/aHR0cHM6Ly90c2Uy/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5h/dnhodFFxcjVGb3Vi/R2pxNHNJWHVnSGFF/byZwaWQ9QXBp","C'est vous le Roi",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/-Wmaf3A34mcP9o1ucEIaiex4IHpEAc_fVTb1ews65wY/rs:fit:780:225:1/g:ce/aHR0cHM6Ly90c2Uz/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5a/bVI5YzR0Si00RVJj/bE1qLU5pM1ZRSGFF/ZyZwaWQ9QXBp","En famille aussi",null));
        imagesList.add(new SlideModel("https://imgs.search.brave.com/QBULfTm15_suunP_IqgpzrZZ2sHgX_3Ea2ocdM1RpPQ/rs:fit:759:225:1/g:ce/aHR0cHM6Ly90c2U0/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC4w/a3UxQXZVb19fLW9o/eEg0RXFUWE9BSGFF/byZwaWQ9QXBp","Vacances guidjooo",null));
        imageSlider.setImageList(imagesList, ScaleTypes.CENTER_CROP);



    }
}