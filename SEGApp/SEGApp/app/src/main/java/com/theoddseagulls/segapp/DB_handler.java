package com.theoddseagulls.segapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;


public class DB_handler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountRegistereds.db";
    public static final String TABLE_ACCOUNTS = "Accounts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL= "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_TYPE = "type";


    public DB_handler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " +
                TABLE_ACCOUNTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_TYPE + " TEXT" + ")";

        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        onCreate(db);
    }

    public void addUser(UserAccount user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_TYPE, user.getType());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }
    public void addProvider(ProviderAccount provider){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, provider.getEmail());
        values.put(COLUMN_PASSWORD, provider.getPassword());
        values.put(COLUMN_USERNAME, provider.getUsername());
        values.put(COLUMN_TYPE, provider.getType());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }
    public void addAdmin(AdminAccount admin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, admin.getEmail());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        values.put(COLUMN_USERNAME, admin.getUsername());
        values.put(COLUMN_TYPE, admin.getType());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    public Account findAccount(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_ACCOUNTS
                + " WHERE "
                + COLUMN_EMAIL
                + " = \""
                + email
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        Account account = new Account();

        if(cursor.moveToFirst()){
            account.setId(Integer.parseInt(cursor.getString(0)));
            account.setEmail(cursor.getString(1));
            account.setPassword(cursor.getString(2));
            account.setUsername(cursor.getString(3));
            account.setType(cursor.getString(4));

            cursor.close();
        } else {
            account = null;
        }
        db.close();
        return account;
    }


    public String findEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_ACCOUNTS
                + " WHERE "
                + COLUMN_EMAIL
                + " = \""
                + email
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String emailFound = new String();

        if(cursor.moveToFirst()){
            emailFound = cursor.getString(1);

            cursor.close();
        } else {
            emailFound = null;
        }
        db.close();
        return emailFound;
    }


    public String findUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_ACCOUNTS
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String usernameFound = new String();

        if(cursor.moveToFirst()){
            usernameFound = cursor.getString(3);

            cursor.close();
        } else {
            usernameFound = null;
        }
        db.close();
        return usernameFound;
    }



    public boolean deleteAccount(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_ACCOUNTS
                + " WHERE "
                + COLUMN_EMAIL
                + " = \""
                + email
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_ACCOUNTS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}