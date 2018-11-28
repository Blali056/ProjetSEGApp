package com.theoddseagulls.segapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;



public class DB_handler extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 22;
    private static final String DATABASE_NAME = "accountRegistereds.db";
    public static final String TABLE_ACCOUNTS = "Accounts";
    public static final String TABLE_SERVICE = "Services";
    public static final String TABLE_PROVIDERSERVICE = "ProviderService";
    public static final String TABLE_PROVIDER_AVAILABILITIES = "ProviderAvailabilities";
    public static final String TABLE_PROVIDER_RATING ="ProviderRating ";

    // Table Accounts
    public static final String COLUMN_ACCOUNT_ID = "_id";
    public static final String COLUMN_EMAIL= "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_LICENCE = "licence";

    // Table Services
    public static final String COLUMN_SERVICE_ID = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_SERVICE = "Service";
    public static final String COLUMN_TAUXHORAIRE = "Taux_Horaire";

    // Table ProviderService
    public static final String COLUMN_PROVIDERSERVICE_ID = "_id";
    public static final String COLUMN_PROVIDEUSERRNAME = "ProviderUserName";
    public static final String COLUMN_SERVICENAME = "ServiceName";

    // Table ProviderAvailabilities
    public static final String COLUMN_PROVIDER_AVAILABILITIES_ID = "_id";
    public static final String COLUMN_PROVIDER_USERNAME = "Username";
    public static final String COLUMN_SAMEDI = "Samedi";
    public static final String COLUMN_DIMANCHE = "Dimanche";
    public static final String COLUMN_LUNDI = "Lundi";
    public static final String COLUMN_MARDI = "Mardi";
    public static final String COLUMN_MERCREDI = "Mercredi";
    public static final String COLUMN_JEUDI = "Jeudi";
    public static final String COLUMN_VENDREDI = "Vendredi";


    // Table Provider Rating

    // faire une table  Provide--- Rate
    // add rate remove rate modify rate
    public static final String COLUMN_PROVIDER_RATE_ID= "_id";
    public static final String COLUMN_PROVIDER = "ProviderEmail";
    public static final String COLUMN_RATE = "rate";





    public static final String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " +
            TABLE_ACCOUNTS + "("
            + COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_PASSWORD + " TEXT," +
            COLUMN_USERNAME + " TEXT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_LASTNAME + " TEXT," +
            COLUMN_TYPE + " TEXT," +
            COLUMN_ADDRESS + " TEXT," +
            COLUMN_PHONE + " TEXT," +
            COLUMN_COMPANY + " TEXT," +
            COLUMN_LICENCE + " TEXT" + ")";

    public static final String CREATE_USERS_TABLE = "CREATE TABLE " +
            TABLE_SERVICE + " ("
            + COLUMN_SERVICE_ID + " INTEGER PRIMARY KEY," +
            COLUMN_SERVICE + " TEXT," +
            COLUMN_TAUXHORAIRE + " TEXT" + ")";

    public static final String CREATE_PROVIDERSERVICE_TABLE = "CREATE TABLE " +
            TABLE_PROVIDERSERVICE + " (" + COLUMN_PROVIDERSERVICE_ID + " INTEGER PRIMARY KEY," +
            COLUMN_PROVIDEUSERRNAME  + " TEXT," +
            COLUMN_SERVICENAME + " TEXT" + ")";

    public static final String CREATE_PROVIDER_AVAILABILITIES_TABLE = "CREATE TABLE " +
            TABLE_PROVIDER_AVAILABILITIES + "("
            + COLUMN_PROVIDER_AVAILABILITIES_ID + " INTEGER PRIMARY KEY," +
            COLUMN_PROVIDER_USERNAME + " TEXT," +
            COLUMN_SAMEDI + " TEXT," +
            COLUMN_DIMANCHE + " TEXT," +
            COLUMN_LUNDI + " TEXT," +
            COLUMN_MARDI + " TEXT," +
            COLUMN_MERCREDI + " TEXT," +
            COLUMN_JEUDI + " TEXT," +
            COLUMN_VENDREDI + " TEXT" + ")";
    public static final String CREATE_PROVIDER_RATING_TABLE="CREATE TABLE "+
            TABLE_PROVIDER_RATING + "("
            + COLUMN_PROVIDER_RATE_ID+ " INTEGER PRIMARY KEY,"+ COLUMN_PROVIDER +"TEXT,"+
            COLUMN_RATE+"TEXT"+")";



    public DB_handler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PROVIDERSERVICE_TABLE);
        db.execSQL(CREATE_PROVIDER_AVAILABILITIES_TABLE);
        db.execSQL(CREATE_PROVIDER_RATING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SERVICE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROVIDERSERVICE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROVIDER_AVAILABILITIES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROVIDER_RATING);

        onCreate(db);
    }

    public void addProvider_rate(String provider,String  rate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROVIDER, provider);
        values.put(COLUMN_RATE, rate);
        db.insert(TABLE_PROVIDER_RATING,null ,values);
        db.close();

    }
    public void addService(Service service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICE, service.getService());
        values.put(COLUMN_TAUXHORAIRE, service.getTauxHoraire());
        db.insert(TABLE_SERVICE, null, values);
        db.close();
    }

    public void addProviderService(ProviderService ps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROVIDEUSERRNAME, ps.getProviderName());
        values.put(COLUMN_SERVICENAME , ps.getService());
        db.insert(TABLE_PROVIDERSERVICE, null, values);
        db.close();
    }

    public void addUser(UserAccount user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_LASTNAME, user.getLastName());
        values.put(COLUMN_TYPE, user.getType());
        values.put(COLUMN_ADDRESS, user.getAddress());
        values.put(COLUMN_PHONE, user.getPhone());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }


    public void addProvider(ProviderAccount provider){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, provider.getEmail());
        values.put(COLUMN_PASSWORD, provider.getPassword());
        values.put(COLUMN_USERNAME, provider.getUsername());
        values.put(COLUMN_NAME, provider.getName());
        values.put(COLUMN_LASTNAME, provider.getLastName());
        values.put(COLUMN_TYPE, provider.getType());
        values.put(COLUMN_ADDRESS, provider.getAddress());
        values.put(COLUMN_PHONE, provider.getPhone());
        values.put(COLUMN_COMPANY, provider.getCompany());
        values.put(COLUMN_LICENCE, provider.getLicence());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }


    public void addAdmin(AdminAccount admin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, admin.getEmail());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        values.put(COLUMN_USERNAME, admin.getUsername());
        values.put(COLUMN_NAME, admin.getName());
        values.put(COLUMN_LASTNAME, admin.getLastName());
        values.put(COLUMN_TYPE, admin.getType());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }


    public Service findService(String service){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM "
                + TABLE_SERVICE
                + " WHERE "
                + COLUMN_SERVICE
                + " = \""
                + service
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        Service services = new Service();
        if(cursor.moveToFirst()){
            services.setId(Integer.parseInt(cursor.getString(0)));
            services.setService(cursor.getString(1));
            services.setTauxHoraire(Double.parseDouble(cursor.getString(2)));

        }
        else {
            services = null;
        }
        db.close();
        return services;
    }

    public ProviderService findProviderService(String service){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM "
                + TABLE_PROVIDERSERVICE
                + " WHERE "
                + COLUMN_SERVICENAME
                + " = \""
                + service
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        ProviderService services = new ProviderService();
        if(cursor.moveToFirst()){
            services.setId(Integer.parseInt(cursor.getString(0)));
            services.setProviderName(cursor.getString(1));
            services.setService(cursor.getString(2));

        }
        else {
            services = null;
        }
        db.close();
        return services;
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
            account.setName(cursor.getString(4));
            account.setLastName(cursor.getString(5));
            account.setType(cursor.getString(6));
            account.setAddress(cursor.getString(7));
            account.setPhone(cursor.getString(8));

            cursor.close();
        } else {
            account = null;
        }
        db.close();
        return account;
    }
    public ProviderAccount findProviderAccount(String email){
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
        ProviderAccount account = new ProviderAccount();

        if(cursor.moveToFirst()){
            account.setId(Integer.parseInt(cursor.getString(0)));
            account.setEmail(cursor.getString(1));
            account.setPassword(cursor.getString(2));
            account.setUsername(cursor.getString(3));
            account.setName(cursor.getString(4));
            account.setLastName(cursor.getString(5));
            account.setType(cursor.getString(6));
            account.setAddress(cursor.getString(7));
            account.setPhone(cursor.getString(8));
            account.setCompany(cursor.getString(9));
            account.setLicence(cursor.getString(10));

            cursor.close();
        } else {
            account = null;
        }
        db.close();
        return account;
    }
    public Account findUsernameAccount(String username){
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
        Account account = new Account();

        if(cursor.moveToFirst()){
            account.setId(Integer.parseInt(cursor.getString(0)));
            account.setEmail(cursor.getString(1));
            account.setPassword(cursor.getString(2));
            account.setUsername(cursor.getString(3));
            account.setName(cursor.getString(4));
            account.setLastName(cursor.getString(5));
            account.setType(cursor.getString(6));
            account.setAddress(cursor.getString(7));
            account.setPhone(cursor.getString(8));
            account.setCompany(cursor.getString(9));
            account.setLicence(cursor.getString(10));



            cursor.close();
        } else {
            account = null;
        }
        db.close();
        return account;
    }


    public ProviderAccount findUsernameProviderAccount(String username){
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
        ProviderAccount account = new ProviderAccount();

        if(cursor.moveToFirst()){
            account.setId(Integer.parseInt(cursor.getString(0)));
            account.setEmail(cursor.getString(1));
            account.setPassword(cursor.getString(2));
            account.setUsername(cursor.getString(3));
            account.setName(cursor.getString(4));
            account.setLastName(cursor.getString(5));
            account.setType(cursor.getString(6));
            account.setAddress(cursor.getString(7));
            account.setPhone(cursor.getString(8));
            account.setCompany(cursor.getString(9));
            account.setLicence(cursor.getString(10));

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

    // calculate the rate of a provider
    
    public String find_provider_rate(String provider){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select  * FROM"
                +TABLE_PROVIDER_RATING
                +"WHERE"
                +COLUMN_PROVIDER
                + " = \""
                +provider
                +"\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        Double rate=0.0;
        int count=0;
        boolean flag= true;
        if(cursor.moveToFirst()){
            while(flag){
                rate=rate+Double.parseDouble(cursor.getString(2));
                count++;
                if(!cursor.moveToNext()){
                    flag=false;

                }
            }
        }
        else{
            rate =0.0;
        }

        return rate.toString();
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

    public boolean deleteService(String service){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "Select * FROM "
                + TABLE_SERVICE
                + " WHERE "
                + COLUMN_SERVICE
                + " = \""
                + service
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICE, COLUMN_SERVICE_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean deleteProviderService(String service){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "Select * FROM "
                + TABLE_PROVIDERSERVICE
                + " WHERE "
                + COLUMN_SERVICENAME
                + " = \""
                + service
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_PROVIDERSERVICE, COLUMN_PROVIDERSERVICE_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
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
            db.delete(TABLE_ACCOUNTS, COLUMN_ACCOUNT_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public boolean modifyService(String service,double rate){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_SERVICE
                + " WHERE "
                + COLUMN_SERVICE
                + " = \""
                + service
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_TAUXHORAIRE,rate);
                db.update(TABLE_SERVICE,contentValues,COLUMN_ACCOUNT_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    // Gets content from the db de la table des services d'admin
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_SERVICE, null);
        return cursor;
    }

    public Cursor getProviderListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_PROVIDERSERVICE, null);
        return cursor;
    }

    public boolean updateAddress(String email, String newAddress){

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
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_ADDRESS,newAddress);
            db.update(TABLE_ACCOUNTS,contentValues,COLUMN_ACCOUNT_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updatePhone(String email, String newPhone){

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
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_PHONE,newPhone);
            db.update(TABLE_ACCOUNTS,contentValues,COLUMN_ACCOUNT_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateCompany(String email, String newCompany){

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
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_COMPANY,newCompany);
            db.update(TABLE_ACCOUNTS,contentValues,COLUMN_ACCOUNT_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateLicence(String email, String newLicence){

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
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_LICENCE,newLicence);
            db.update(TABLE_ACCOUNTS,contentValues,COLUMN_ACCOUNT_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public void addProviderAvailabilities(ProviderAccount provider){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROVIDER_USERNAME, provider.getUsername());
        values.put(COLUMN_SAMEDI, provider.getSamedi());
        values.put(COLUMN_DIMANCHE, provider.getDimanche());
        values.put(COLUMN_LUNDI, provider.getLundi());
        values.put(COLUMN_MARDI, provider.getMardi());
        values.put(COLUMN_MERCREDI, provider.getMercredi());
        values.put(COLUMN_JEUDI, provider.getJeudi());
        values.put(COLUMN_VENDREDI, provider.getVendredi());

        db.insert(TABLE_PROVIDER_AVAILABILITIES, null, values);
        db.close();
    }


    public boolean updateSamedi(String username, String samedi){

        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_SAMEDI,samedi);
            db.update(TABLE_PROVIDER_AVAILABILITIES,contentValues,COLUMN_PROVIDER_AVAILABILITIES_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateDimanche(String username, String dimanche){

        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_DIMANCHE,dimanche);
            db.update(TABLE_PROVIDER_AVAILABILITIES,contentValues,COLUMN_PROVIDER_AVAILABILITIES_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateLundi(String username, String lundi){

        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_LUNDI,lundi);
            db.update(TABLE_PROVIDER_AVAILABILITIES,contentValues,COLUMN_PROVIDER_AVAILABILITIES_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateMardi(String username, String mardi){

        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_MARDI,mardi);
            db.update(TABLE_PROVIDER_AVAILABILITIES,contentValues,COLUMN_PROVIDER_AVAILABILITIES_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateMercredi(String username, String mercredi){

        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_MERCREDI,mercredi);
            db.update(TABLE_PROVIDER_AVAILABILITIES,contentValues,COLUMN_PROVIDER_AVAILABILITIES_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateJeudi(String username, String jeudi){

        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_JEUDI,jeudi);
            db.update(TABLE_PROVIDER_AVAILABILITIES,contentValues,COLUMN_PROVIDER_AVAILABILITIES_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateVendredi(String username, String vendredi){

        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + username
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            ContentValues contentValues= new ContentValues();
            contentValues.put(COLUMN_VENDREDI,vendredi);
            db.update(TABLE_PROVIDER_AVAILABILITIES,contentValues,COLUMN_PROVIDER_AVAILABILITIES_ID + " = " + idStr,null);

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public String findSamedi(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_PROVIDER_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String samedi = new String();

        if(cursor.moveToFirst()){
            samedi = cursor.getString(2);

            cursor.close();
        } else {
            samedi = null;
        }
        db.close();
        return samedi;
    }

    public String findDimanche(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_PROVIDER_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String dimanche = new String();

        if(cursor.moveToFirst()){
            dimanche = cursor.getString(3);

            cursor.close();
        } else {
            dimanche = null;
        }
        db.close();
        return dimanche;
    }

    public String findLundi(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_PROVIDER_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String lundi = new String();

        if(cursor.moveToFirst()){
            lundi = cursor.getString(4);

            cursor.close();
        } else {
            lundi = null;
        }
        db.close();
        return lundi;
    }

    public String findMardi(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_PROVIDER_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String mardi = new String();

        if(cursor.moveToFirst()){
            mardi = cursor.getString(5);

            cursor.close();
        } else {
            mardi = null;
        }
        db.close();
        return mardi;
    }


    public String findMercredi(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_PROVIDER_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String mercredi = new String();

        if(cursor.moveToFirst()){
            mercredi = cursor.getString(6);

            cursor.close();
        } else {
            mercredi = null;
        }
        db.close();
        return mercredi;
    }


    public String findJeudi(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_PROVIDER_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String jeudi = new String();

        if(cursor.moveToFirst()){
            jeudi = cursor.getString(7);

            cursor.close();
        } else {
            jeudi = null;
        }
        db.close();
        return jeudi;
    }

    public String findVendredi(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_PROVIDER_AVAILABILITIES
                + " WHERE "
                + COLUMN_PROVIDER_USERNAME
                + " = \""
                + username
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        String vendredi = new String();

        if(cursor.moveToFirst()){
            vendredi = cursor.getString(8);

            cursor.close();
        } else {
            vendredi = null;
        }
        db.close();
        return vendredi;
    }



}