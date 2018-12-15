package com.example.exitpoll;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "poll.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "exitpoll";
    public static final String COL_ID = "_id";
    public static final String COL_NUMBER = "number";
    public static final String COL_SCORE = "score";
    public static final String COL_IMAGE = "image";

    private static final String SQL_CREATE_TABLE
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_NUMBER + " TEXT,"
            + COL_SCORE + " TEXT,"
            + COL_IMAGE + " TEXT "
            + ")";


    public DatabaseHelper(Context context) {
        super(context, "poll.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);


        ContentValues cv ;
        cv = new ContentValues();
        cv.put(COL_NUMBER, "no");
        cv.put(COL_SCORE, "0");
        cv.put(COL_IMAGE,"vote_no.png");
        db.insert(TABLE_NAME, null, cv);

        cv.put(COL_NUMBER, "1");
        cv.put(COL_SCORE, "0");
        cv.put(COL_IMAGE,"one.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NUMBER, "2");
        cv.put(COL_SCORE, "0");
        cv.put(COL_IMAGE,"two.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NUMBER, "3");
        cv.put(COL_SCORE, "0");
        cv.put(COL_IMAGE,"three.png");
        db.insert(TABLE_NAME, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
