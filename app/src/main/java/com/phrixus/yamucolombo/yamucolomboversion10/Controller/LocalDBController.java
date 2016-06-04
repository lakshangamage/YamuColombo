package com.phrixus.yamucolombo.yamucolomboversion10.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Lakshan on 2016-05-03.
 */
public class LocalDBController extends SQLiteOpenHelper {

    private final String CLASS = LocalDBController.class.getSimpleName();

    public LocalDBController(Context context) {
        super(context, "yamucol_db", null, 1);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE user ( username VARCHAR(75) PRIMARY KEY, name VARCHAR(150) )";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(CLASS, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS user");

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String username, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name); // Name
        values.put("username", username); // Email

        // Inserting Row
        long id = db.insert("user", null, values);
        db.close(); // Closing database connection

        Log.d(CLASS, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM user";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("username", cursor.getString(1));
            user.put("name", cursor.getString(2));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(CLASS, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("user", null, null);
        db.close();

        Log.d(CLASS, "Deleted all user info from sqlite");
    }

}
