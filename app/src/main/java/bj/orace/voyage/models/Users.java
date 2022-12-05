package bj.orace.voyage.models;

import java.io.Serializable;

public class Users implements Serializable {

    public static final String TABLE = "users";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXIST users " +
            "(id Integer PRIMARY KEY AUTOINCREMENT,"+
            "userName TEXT NOT NULL," +
            "email TEXT NOT NULL," +
            "password TEXT," +
            "imageProfile TEXT )";

    public static  final String COLUMN_ID = "id";
    public static  final String COLUMN_NAME = "username";
    public static  final String COLUMN_EMAIL = "email";
    public static  final String COLUMN_PASSWORD = "password";
    public static  final String COLUMN_IMAGE = "imageProfile";





}
