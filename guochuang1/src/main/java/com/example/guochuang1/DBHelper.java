package com.example.guochuang1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "kezi.db";
    public static final String TB_NAME = "shoppingCar";
    public static final String TB_NAME2 = "setOrder";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TB_NAME + "(PIC INTEGER PRIMARY KEY,NAME TEXT,PRICE INTEGER,NUM INTEGER)");
        db.execSQL("CREATE TABLE " + TB_NAME2 + "(ORDER_TIME TEXT PRIMARY KEY,START_TIME TEXT,DURING_TIME INTEGER,CHECK_TIME TEXT,MONEY INTEGER ,STATE INTEGER,TYPE TEXT,CONTENT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
