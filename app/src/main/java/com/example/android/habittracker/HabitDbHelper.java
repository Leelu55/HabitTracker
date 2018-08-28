package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDbHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "habits.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructor for  {@link HabitDbHelper} instances.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // String with SQL statement creating the habits table
        String SQL_CREATE_HABITS_TABLE =  "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT_LABEL + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DONE_MON + " INTEGER DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DONE_TUE + " INTEGER DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DONE_WED + " INTEGER DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DONE_THU + " INTEGER DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DONE_FRI + " INTEGER DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DONE_SAT + " INTEGER DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DONE_SUN + " INTEGER DEFAULT 0);";

        // Execute the SQL create statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * This is called when the database needs to be upgraded.
         */


    }
}
