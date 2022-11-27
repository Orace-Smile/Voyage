package bj.orace.voyage.services;

import android.content.Context;
import android.util.Log;

import java.math.BigDecimal;
import java.util.List;

import bj.orace.voyage.dao.PostDao;
import bj.orace.voyage.models.BDonnees;
import bj.orace.voyage.models.Post;

public class BuildData {

    public static Post generatePost(){
        return new Post(1,"Etoile Rouge",
                "Le plis grand carrefour de Cotonou",
                "R.drawable.etoile_rouge", BigDecimal.valueOf(50000));
    }

    public static void testDB(Context context) {
        BDonnees bDonnees = BDonnees.getInstance(context);
        PostDao postDao = new PostDao(bDonnees);
        Post post = postDao.createPost(generatePost());
        List<Post> posts = postDao.findAll();
        for (Post post1 : posts){
            Log.d("TAG","testDB: " + post1);
        }
    }


}
