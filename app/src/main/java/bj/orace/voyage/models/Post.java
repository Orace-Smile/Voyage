package bj.orace.voyage.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import bj.orace.voyage.R;

public class Post implements Serializable {


    public static final String TABLE = "posts";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXIST posts " +
          "(id Integer PRIMARY KEY AUTOINCREMENT,"+
          "name TEXT NOT NULL," +
          "description TEXT NOT NULL," +
            "image TEXT," +
            "price DOUBLE DEFAULT 0)";
    public static  final String COLUMN_ID = "id";
    public static  final String COLUMN_NAME = "name";
    public static  final String COLUMN_DESCRIPTION = "description";
    public static  final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";

    public static final String[] FROM =  new String[]{COLUMN_NAME,COLUMN_DESCRIPTION, COLUMN_PRICE};
    public static final  int[] TO = new int[] {R.id.post_name,R.id.post_description,R.id.post_price};

    public long id;
    public String name;
    public String description;
    public String image;
    public BigDecimal price;

    public Post(long id, String name, String description, String image, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Post() {

    }

    @Override
    public String toString() {
        return "Post{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        map.put(COLUMN_NAME,name);
        map.put(COLUMN_DESCRIPTION,description);
        map.put(COLUMN_PRICE,price.toString());

        return map;
    }
}
