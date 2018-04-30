package sgw.kursach.database;

import android.provider.BaseColumns;

public class TablesPresenterClass {

    private TablesPresenterClass() {

    }

    public static class Kandidat implements BaseColumns {
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
                + COLUMN_DRIVER + " INTEGER NOT NULL)";
    }

    public static class Ocinka implements BaseColumns {
        public static final String TABLE_NAME = "ocinka";
        public static final String COLUMN_BAL_AGE = "bal_age";
        public static final String COLUMN_BAL_STAZH = "bal_stazh";
        public static final String COLUMN_BAL_NAYAVNVO = "bal_nayavn_vo";
        public static final String COLUMN_BAL_NAYAVNVP = "bal_nayavn_vp";
        public static final String COLUMN_BAL_NAYAVNDZ = "bal_nayavn_dz";
        public static final String COLUMN_BAL_NAYAVNDK = "bal_nayavn_dk";
        public static final String COLUMN_BAL_NAYAVNDV = "bal_nayavn_dv";
        public static final String COLUMN_BAL_KILKINM = "bal_kilk_inm";


        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BAL_AGE + " INTEGER, " +
                COLUMN_BAL_STAZH + " INTEGER, " +
                COLUMN_BAL_NAYAVNVO + " INTEGER, " +
                COLUMN_BAL_NAYAVNVP + " INTEGER, " +
                COLUMN_BAL_NAYAVNDZ + " INTEGER, " +
                COLUMN_BAL_NAYAVNDK + " INTEGER, " +
                COLUMN_BAL_NAYAVNDV + " INTEGER, " +
                COLUMN_BAL_KILKINM + " INTEGER" + ")";
    }

}