package com.example.david.dontouch.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by david on 2017/4/12.
 */

public class OpenHelper extends SQLiteOpenHelper {
    private String TAG = "OpenHelper";
    public static final String CREATE_USER = "CREATE TABLE User ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "userpwd text)";

    public static final String CREATE_TIME = "CREATE TABLE Time ("
            + "id integer primary key autoincrement, "
            + "timer DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "isOpen integer)";


    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i(TAG, getDatabaseName());

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS Time");
        db.execSQL(CREATE_TIME);
        db.execSQL(CREATE_USER);
        Log.i(TAG, "CREATE TABLES");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TIME);
        db.execSQL("DELETE FROM Time");
        Log.i(TAG, "CREATE TABLES");
    }
}
