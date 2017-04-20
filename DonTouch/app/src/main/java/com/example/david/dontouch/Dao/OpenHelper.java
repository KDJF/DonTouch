package com.example.david.dontouch.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 2017/4/12.
 */

public class OpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_USER = "CREATE TABLE User ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "userpwd text)";


    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
