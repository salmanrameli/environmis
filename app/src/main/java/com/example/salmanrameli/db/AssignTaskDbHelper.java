package com.example.salmanrameli.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AssignTaskDbHelper extends SQLiteOpenHelper {
    public AssignTaskDbHelper(Context context) {
        super(context, AssignTaskContract.DB_NAME, null, AssignTaskContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + AssignTaskContract.AssignTaskEntry.TABLE + " (" +
                AssignTaskContract.AssignTaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AssignTaskContract.AssignTaskEntry.COL_LOCATION_LATITUDE + " TEXT NOT NULL, " +
                AssignTaskContract.AssignTaskEntry.COL_LOCATION_LONGITUDE + " TEXT NOT NULL, " +
                AssignTaskContract.AssignTaskEntry.COL_STAFF_ID + " TEXT NOT NULL, " +
                AssignTaskContract.AssignTaskEntry.COL_IS_DONE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AssignTaskContract.AssignTaskEntry.TABLE);
    }
}
