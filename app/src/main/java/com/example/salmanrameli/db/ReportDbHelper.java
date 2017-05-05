package com.example.salmanrameli.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReportDbHelper extends SQLiteOpenHelper {
    public ReportDbHelper(Context context)
    {
        super(context, ReportContract.DB_NAME, null, ReportContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ReportContract.ReportResultEntry.TABLE + " (" +
                ReportContract.ReportResultEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReportContract.ReportResultEntry.COL_LOCATION_LATITUDE + " TEXT NOT NULL, " +
                ReportContract.ReportResultEntry.COL_LOCATION_LONGITUDE + " TEXT NOT NULL, " +
                ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE + " TEXT NOT NULL, " +
                ReportContract.ReportResultEntry.COL_MEASUREMENT_RESULT + " TEXT NOT NULL, " +
                ReportContract.ReportResultEntry.COL_MEASUREMENT_STAFF_ID + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 2)
        {
            db.execSQL("DROP TABLE IF EXISTS " + ReportContract.ReportResultEntry.TABLE);

            String createTable = "CREATE TABLE " + ReportContract.ReportResultEntry.TABLE + " (" +
                    ReportContract.ReportResultEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ReportContract.ReportResultEntry.COL_LOCATION_LATITUDE + " TEXT NOT NULL, " +
                    ReportContract.ReportResultEntry.COL_LOCATION_LONGITUDE + " TEXT NOT NULL, " +
                    ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE + " TEXT NOT NULL, " +
                    ReportContract.ReportResultEntry.COL_MEASUREMENT_RESULT + " TEXT NOT NULL);";

            db.execSQL(createTable);
        }
        if(oldVersion < 3)
        {
            db.execSQL("DROP TABLE IF EXISTS " + ReportContract.ReportResultEntry.TABLE);

            String createTable = "CREATE TABLE " + ReportContract.ReportResultEntry.TABLE + " (" +
                    ReportContract.ReportResultEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ReportContract.ReportResultEntry.COL_LOCATION_LATITUDE + " TEXT NOT NULL, " +
                    ReportContract.ReportResultEntry.COL_LOCATION_LONGITUDE + " TEXT NOT NULL, " +
                    ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE + " TEXT NOT NULL, " +
                    ReportContract.ReportResultEntry.COL_MEASUREMENT_RESULT + " TEXT NOT NULL, " +
                    ReportContract.ReportResultEntry.COL_MEASUREMENT_STAFF_ID + " TEXT NOT NULL);";

            db.execSQL(createTable);
        }
    }
}
