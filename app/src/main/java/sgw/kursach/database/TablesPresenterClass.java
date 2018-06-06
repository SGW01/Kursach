package sgw.kursach.database;

import android.provider.BaseColumns;

public class TablesPresenterClass {

    private TablesPresenterClass() {

    }

    public static class Candidate implements BaseColumns {
        public static final String TABLE_NAME = "candidate";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_EMAIL = "email";

        public static final String COLUMN_TRACK = "track";
        public static final String COLUMN_FOREIGN = "foreignLanguage";
        public static final String COLUMN_EDUCATION = "education";

        public static final String COLUMN_COMMAND = "command";
        public static final String COLUMN_LEADERSHIP = "leadership";
        public static final String COLUMN_DRIVER = "driver";

        public static final String IS_APPROPRIATE_AFTER_CV = "appropriateaftercv";

        public static final String COLUMN_RANGING_AFTER_CV = "rangingaftercv";

        public static final String COLUMN_RANGING_AFTER_HR = "rangingafterhr";

        public static final String COLUMN_EXPECTATION = "expectation";
        public static final String COLUMN_INITIATIVE = "initiative";
        public static final String COLUMN_MOTIVATION = "motivation";
        public static final String COLUMN_FLEXIBILITY = "flexibility";
        public static final String COLUMN_RESPONSIBILITY = "responsibility";
        public static final String COLUMN_FRUSTRATION = "frustration";
        public static final String COLUMN_EFFICIENCY = "efficiency";


        public static final String CREATE_TABLE = "create table "
                + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_SURNAME + " TEXT NOT NULL, "
                + COLUMN_AGE + " INTEGER NOT NULL, "
                + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_TRACK + " INTEGER NOT NULL, "
                + COLUMN_FOREIGN + " INTEGER NOT NULL, "
                + COLUMN_EDUCATION + " INTEGER NOT NULL, "
                + COLUMN_COMMAND + " INTEGER NOT NULL, "
                + COLUMN_LEADERSHIP + " INTEGER NOT NULL, "
                + COLUMN_DRIVER + " INTEGER NOT NULL, "
                + COLUMN_EXPECTATION + " INTEGER, "
                + COLUMN_INITIATIVE + " INTEGER, "
                + COLUMN_MOTIVATION + " INTEGER, "
                + COLUMN_FLEXIBILITY + " INTEGER, "
                + COLUMN_RESPONSIBILITY + " INTEGER, "
                + COLUMN_FRUSTRATION + " INTEGER, "
                + COLUMN_EFFICIENCY + " INTEGER, "
                + COLUMN_RANGING_AFTER_CV + " TEXT, "
                + COLUMN_RANGING_AFTER_HR + " TEXT, "
                + IS_APPROPRIATE_AFTER_CV + " INTEGER)";
    }

}