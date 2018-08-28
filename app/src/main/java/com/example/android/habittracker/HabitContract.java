package com.example.android.habittracker;

import android.provider.BaseColumns;

public class HabitContract {

    // empty constructor to prevent instance creation of HabitContract class
    private HabitContract() {
    }

    /**
     * Inner class that defining constants for the habits table
     * Each entry in the table represents a habit the user implemented.
     */
    public static final class HabitEntry implements BaseColumns {

        /**
         * Name of database table for habits
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique habit ID for intern use
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * habit label as entered by user
         * <p>
         * Type: Text
         */
        public final static String COLUMN_HABIT_LABEL = "label";

        /**
         * following 7 constants are the seven days of the week, can be true of false, depending on the user action
         *
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_DONE_MON = "mon";
        public final static String COLUMN_HABIT_DONE_TUE = "tue";
        public final static String COLUMN_HABIT_DONE_WED = "wed";
        public final static String COLUMN_HABIT_DONE_THU = "thu";
        public final static String COLUMN_HABIT_DONE_FRI = "fri";
        public final static String COLUMN_HABIT_DONE_SAT = "sat";
        public final static String COLUMN_HABIT_DONE_SUN = "sun";

        /**
         * Possible values for the day columns to indicate if a habit was "done" on this day .
         * Later, in the ui, users can check or uncheck the task/habit for the day
         */
        public static final int HABIT_DONE_YES = 1;
        public static final int HABIT_DONE_NO = 0;

    }
}
