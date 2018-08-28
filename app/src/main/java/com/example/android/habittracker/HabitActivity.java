package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittracker.HabitContract.HabitEntry;

public class HabitActivity extends AppCompatActivity {
    HabitDbHelper mDbHelper;

    private String mLabelString;
    private String mDisplayHabits;
    private TextView mDisplayDataTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        mDisplayDataTextView = findViewById(R.id.database_display);

        mDbHelper = new HabitDbHelper(this);

        //insert 10 dummy habits named "test habit 0", "test habit 1" ..., "test habit 9"
        for (int i = 0; i < 10; i++) {
            mLabelString = "test habit " + i;
            insertHabit(mLabelString);
        }

        //display habits table content on screen
        displayDatabaseInfo();

    }

    /**
     * read method to display information in the onscreen TextView about the state of
     * the habits database.
     */
    private void displayDatabaseInfo() {


        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_LABEL,
                HabitEntry.COLUMN_HABIT_DONE_MON,
                HabitEntry.COLUMN_HABIT_DONE_TUE,
                HabitEntry.COLUMN_HABIT_DONE_WED,
                HabitEntry.COLUMN_HABIT_DONE_THU,
                HabitEntry.COLUMN_HABIT_DONE_FRI,
                HabitEntry.COLUMN_HABIT_DONE_SAT,
                HabitEntry.COLUMN_HABIT_DONE_SUN
        };


        // Perform this raw SQL query "SELECT * FROM habits"
        // to get a Cursor that contains all rows from the habits table.
        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);


        try {
            // Create a header in the Text View that looks like this:
            //
            // There are  <number of rows in Cursor> habits in the table..
            // _id - label - mo - tue - wed - thu - fri - sat
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            mDisplayHabits = "There are " + cursor.getCount() + " habits in the table.\n\n";


            mDisplayHabits = mDisplayHabits.concat(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_LABEL + " - " +
                    HabitEntry.COLUMN_HABIT_DONE_MON + " - " +
                    HabitEntry.COLUMN_HABIT_DONE_TUE + " - " +
                    HabitEntry.COLUMN_HABIT_DONE_TUE + " - " +
                    HabitEntry.COLUMN_HABIT_DONE_THU + " - " +
                    HabitEntry.COLUMN_HABIT_DONE_FRI + " - " +
                    HabitEntry.COLUMN_HABIT_DONE_SAT + " - " +
                    HabitEntry.COLUMN_HABIT_DONE_SUN + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int labelColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_LABEL);
            int monColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE_MON);
            int tueColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE_TUE);
            int wedColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE_WED);
            int thuColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE_THU);
            int friColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE_FRI);
            int satColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE_SAT);
            int sunColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE_SUN);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentLabel = cursor.getString(labelColumnIndex);
                int currentMon = cursor.getInt(monColumnIndex);
                int currentTue = cursor.getInt(tueColumnIndex);
                int currentWed = cursor.getInt(wedColumnIndex);
                int currentThu = cursor.getInt(thuColumnIndex);
                int currentFri = cursor.getInt(friColumnIndex);
                int currentSat = cursor.getInt(satColumnIndex);
                int currentSun = cursor.getInt(sunColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                mDisplayHabits = mDisplayHabits.concat(("\n" +
                        currentID + " - " +
                        currentLabel + " - " +
                        currentMon + " - " +
                        currentTue + " - " +
                        currentWed + " - " +
                        currentThu + " - " +
                        currentFri + " - " +
                        currentSat + " - " +
                        currentSun));
            }

            mDisplayDataTextView.setText(mDisplayHabits);


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertHabit(String labelString) {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_LABEL, labelString);
        values.put(HabitEntry.COLUMN_HABIT_DONE_MON, 0);
        values.put(HabitEntry.COLUMN_HABIT_DONE_TUE, 0);
        values.put(HabitEntry.COLUMN_HABIT_DONE_WED, 0);
        values.put(HabitEntry.COLUMN_HABIT_DONE_THU, 0);
        values.put(HabitEntry.COLUMN_HABIT_DONE_FRI, 0);
        values.put(HabitEntry.COLUMN_HABIT_DONE_SAT, 0);
        values.put(HabitEntry.COLUMN_HABIT_DONE_SUN, 0);

        db.insert(HabitEntry.TABLE_NAME, null, values);
    }


}
