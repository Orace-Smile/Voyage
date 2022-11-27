package bj.orace.voyage.metiers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bj.orace.voyage.models.Post;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostManagerImplementOnline implements PostManager{

    private  OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private Gson gson = new Gson();
    private String POST_URL = "https://realty-mole-property-api.p.rapidapi.com/properties?address=5500%20Grand%20Lake%20Dr%2C%20San%20Antonio%2C%20TX%2C%2078244";


    String toto (){
        Request request = new Request.Builder()
                .url("https://realty-mole-property-api.p.rapidapi.com/properties?address=5500%20Grand%20Lake%20Dr%2C%20San%20Antonio%2C%20TX%2C%2078244")
                .get()
                .addHeader("X-RapidAPI-Key", "27aab07a1amsh5c6a69d0849136ep12628bjsn1b86633c2c8b")
                .addHeader("X-RapidAPI-Host", "realty-mole-property-api.p.rapidapi.com")
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d("TAG","LA reponse : "+response);
            return response.body().string();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        return null;
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public List<Post> findAll(){
        try {
            String res = get(POST_URL);
            List<Post> posts = gson.fromJson(res, new TypeToken<List<Post>>() {
            }.getType());
            return posts;
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    String get(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "27aab07a1amsh5c6a69d0849136ep12628bjsn1b86633c2c8b")
                .addHeader("X-RapidAPI-Host", "realty-mole-property-api.p.rapidapi.com")
                .build();
        try(Response response =  client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
