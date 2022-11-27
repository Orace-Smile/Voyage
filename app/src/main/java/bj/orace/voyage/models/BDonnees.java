package bj.orace.voyage.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDonnees extends SQLiteOpenHelper {
    private  static final String DB_NAME = "voyage_plus.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = "BDonnees";

    private static BDonnees bDonnees;

    private BDonnees(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    public static BDonnees getInstance(Context context){
        if (bDonnees == null){
            bDonnees = new BDonnees(context);
        }
        return bDonnees;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Post.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
