package bj.orace.voyage.dao;

import static bj.orace.voyage.models.Post.COLUMN_DESCRIPTION;
import static bj.orace.voyage.models.Post.COLUMN_IMAGE;
import static bj.orace.voyage.models.Post.COLUMN_NAME;
import static bj.orace.voyage.models.Post.COLUMN_PRICE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import bj.orace.voyage.models.BDonnees;
import bj.orace.voyage.models.Post;

public class PostDao {
    private final BDonnees bDonnees;


    public PostDao(BDonnees bDonnees) {
        this.bDonnees = bDonnees;
    }

    public Post createPost (Post post){
        SQLiteDatabase database =  bDonnees.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put(COLUMN_NAME,post.name);
        contentValues.put(Post.COLUMN_DESCRIPTION,post.description);
        contentValues.put(Post.COLUMN_IMAGE,post.image);
        contentValues.put(Post.COLUMN_PRICE,post.price.doubleValue());

        long id = database.insert(Post.TABLE,null,contentValues);
        post.id = id;

        return post;
    }

    @SuppressLint("Range")
    public List<Post> findAll(){
        SQLiteDatabase database = bDonnees.getReadableDatabase();
        Cursor cursor =  database.query(Post.TABLE,new String[]{},"",new String[]{},"","","");
        List<Post> posts = new ArrayList<>();
        while (cursor.moveToNext()){
            Post post =  new Post();
            post.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            post.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            post.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            post.price = BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));

            posts.add(post);
        }
        return posts;
    }
}
