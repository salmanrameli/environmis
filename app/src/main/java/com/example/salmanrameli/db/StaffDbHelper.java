package com.example.salmanrameli.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StaffDbHelper extends SQLiteOpenHelper {
    public StaffDbHelper(Context context) {
        super(context, StaffContract.DB_NAME, null, StaffContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + StaffContract.StaffEntry.TABLE + " (" +
                StaffContract.StaffEntry._ID + " INTEGER PRIMARY KEY, " +
                StaffContract.StaffEntry.COL_STAFF_NAME + " TEXT NOT NULL, " +
                StaffContract.StaffEntry.COL_STAFF_PASS + " TEXT NOT NULL, " +
                StaffContract.StaffEntry.COL_STAFF_ROLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StaffContract.StaffEntry.TABLE);

    }
}
