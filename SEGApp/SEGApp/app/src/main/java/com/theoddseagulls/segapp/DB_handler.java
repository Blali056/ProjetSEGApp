package com.theoddseagulls.segapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;


public class DB_handler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_INFO= "info";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL= "email";
    public static final String COLUMN_password = "password";
    public static final String COLUMN_TYPE = "type";




    public DB_handler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
            TABLE_INFO + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_EMAIL +
            " TEXT," + COLUMN_password + " TEXT" +")";
    SQLiteDatabase db;
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
        onCreate(db);
        this.db=db;
    }
    public User findUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_INFO
                + " WHERE "
                + COLUMN_EMAIL
                + " = \""
                + user.getEmail()
                + "\""
                +"AND"
                +COLUMN_password
                +" = \""
                +user.getPassword();

        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            user.setEmail(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_password, user.getPassword());
        values.put(COLUMN_TYPE, user.getType());


        db.insert(TABLE_INFO, null, values);
        db.close();

    }




}

