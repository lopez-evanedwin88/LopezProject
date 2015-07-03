package com.project.lopez.myprojectapplication.main;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Administrator on 7/2/2015.
 */
public class DbHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "Database";
    static final String TABLE_NAME = "Item";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,_name TEXT NOT NULL);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_v, int new_v) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_NAME);
        onCreate(db);

    }

}