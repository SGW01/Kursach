package sgw.kursach.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DataBaseModule {

    private final static String TAG = DataBaseModule.class.getSimpleName();

    public int count;

    public DataBaseModule() {

    }


    public boolean saveToDB(Context context, String name, String surname, int age, String email,
                            int track, int foreign, int education,
                            int command, int leadership, int driver) {
        SQLiteDatabase database = new DBSQLiteHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TablesPresenterClass.Candidate.COLUMN_NAME, name);
        values.put(TablesPresenterClass.Candidate.COLUMN_SURNAME, surname);
        values.put(TablesPresenterClass.Candidate.COLUMN_AGE, age);
        values.put(TablesPresenterClass.Candidate.COLUMN_EMAIL, email);

        values.put(TablesPresenterClass.Candidate.COLUMN_TRACK, track);
        values.put(TablesPresenterClass.Candidate.COLUMN_FOREIGN, foreign);
        values.put(TablesPresenterClass.Candidate.COLUMN_EDUCATION, education);

        values.put(TablesPresenterClass.Candidate.COLUMN_COMMAND, command);
        values.put(TablesPresenterClass.Candidate.COLUMN_LEADERSHIP, leadership);
        values.put(TablesPresenterClass.Candidate.COLUMN_DRIVER, driver);

        long newRowId = database.insert(TablesPresenterClass.Candidate.TABLE_NAME, null, values);
        Log.d(TAG, "The new row Id is " + newRowId);

        return true;
    }

    public int getDBSize(Context context) {
        SQLiteDatabase sqLiteDatabase = new DBSQLiteHelper(context).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " +
                TablesPresenterClass.Candidate.COLUMN_SURNAME + " from " + TablesPresenterClass.Candidate.TABLE_NAME, null);
        return cursor.getCount();
    }

    public int[] readFromDB(Context context, String surname) {
        int[] data = new int[7];

        SQLiteDatabase database = new DBSQLiteHelper(context).getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("select " +
                TablesPresenterClass.Candidate.COLUMN_AGE + ", " +
                TablesPresenterClass.Candidate.COLUMN_TRACK + ", " +
                TablesPresenterClass.Candidate.COLUMN_FOREIGN + ", " +
                TablesPresenterClass.Candidate.COLUMN_EDUCATION + ", " +
                TablesPresenterClass.Candidate.COLUMN_COMMAND + ", " +
                TablesPresenterClass.Candidate.COLUMN_LEADERSHIP + ", " +
                TablesPresenterClass.Candidate.COLUMN_DRIVER + " " +
                "from " + TablesPresenterClass.Candidate.TABLE_NAME + " WHERE " +
                TablesPresenterClass.Candidate.COLUMN_SURNAME + " = ? ", new String[]{surname});
        cursor.moveToFirst();
        Log.d(TAG, String.valueOf(cursor.getCount()));
        data[0] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_AGE));
        data[1] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_TRACK));
        data[2] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_FOREIGN));
        data[3] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_EDUCATION));
        data[4] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_COMMAND));
        data[5] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_LEADERSHIP));
        data[6] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_DRIVER));
        cursor.close();
        database.close();
        return data;

    }

    public String[] getSurnames(Context context) {
        String names[];
        SQLiteDatabase database = new DBSQLiteHelper(context).getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("select " +
                TablesPresenterClass.Candidate.COLUMN_SURNAME + " from " + TablesPresenterClass.Candidate.TABLE_NAME, null);
        cursor.moveToFirst();
        names = new String[cursor.getCount()];
        count = cursor.getCount();
        Log.d(TAG, String.valueOf(cursor.getCount()));
        for (int i = 0; i < count; i++) {
            names[i] = cursor.getString(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_SURNAME));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return names;
    }

    public double[][] readAllFromDB(Context context) {
        double[][] data;

        SQLiteDatabase database = new DBSQLiteHelper(context).getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("select " +
                TablesPresenterClass.Candidate.COLUMN_AGE + ", " +
                TablesPresenterClass.Candidate.COLUMN_TRACK + ", " +
                TablesPresenterClass.Candidate.COLUMN_FOREIGN + ", " +
                TablesPresenterClass.Candidate.COLUMN_EDUCATION + ", " +
                TablesPresenterClass.Candidate.COLUMN_COMMAND + ", " +
                TablesPresenterClass.Candidate.COLUMN_LEADERSHIP + ", " +
                TablesPresenterClass.Candidate.COLUMN_DRIVER + " " +
                "from " + TablesPresenterClass.Candidate.TABLE_NAME, null);
        cursor.moveToFirst();
        count = cursor.getCount();
        data = new double[cursor.getCount()][7];
        Log.d(TAG, String.valueOf(cursor.getCount()));
        for (int i = 0; i < count; i++) {
            data[i][0] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_AGE));
            data[i][1] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_TRACK));
            data[i][2] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_FOREIGN));
            data[i][3] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_EDUCATION));
            data[i][4] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_COMMAND));
            data[i][5] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_LEADERSHIP));
            data[i][6] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Candidate.COLUMN_DRIVER));

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return data;
    }

    public boolean deleteFromDB(Context context, String surname) {
        SQLiteDatabase database = new DBSQLiteHelper(context).getWritableDatabase();
        long rowId = database.delete(TablesPresenterClass.Candidate.TABLE_NAME,
                TablesPresenterClass.Candidate.COLUMN_SURNAME + " = ? ", new String[]{surname});
        Log.d(TAG, "The deleted row count is " + rowId);
        database.close();
        return true;

    }

    //--------------------------------------------------------------HRInterview

    public boolean updateDB(Context context, String surname, int expectation, int initiative, int motivation, int flexibility, int responsibility, int frustration, int efficiency) {
        SQLiteDatabase database = new DBSQLiteHelper(context).getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TablesPresenterClass.Candidate.COLUMN_EXPECTATION, expectation);
        contentValues.put(TablesPresenterClass.Candidate.COLUMN_INITIATIVE, initiative);
        contentValues.put(TablesPresenterClass.Candidate.COLUMN_MOTIVATION, motivation);
        contentValues.put(TablesPresenterClass.Candidate.COLUMN_FLEXIBILITY, flexibility);
        contentValues.put(TablesPresenterClass.Candidate.COLUMN_RESPONSIBILITY, responsibility);
        contentValues.put(TablesPresenterClass.Candidate.COLUMN_FRUSTRATION, frustration);
        contentValues.put(TablesPresenterClass.Candidate.COLUMN_EFFICIENCY, efficiency);

        long rowId = database.update(TablesPresenterClass.Candidate.TABLE_NAME,
                contentValues, TablesPresenterClass.Candidate.COLUMN_SURNAME + " = ? ", new String[]{surname});
        Log.d(TAG, "updated column is " + rowId);
        database.close();
        return true;
    }

    public boolean updateRangCV(Context context, double[] fFinal) {
        SQLiteDatabase database = new DBSQLiteHelper(context).getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        for (double aFFinal : fFinal)
            contentValues.put(TablesPresenterClass.Candidate.COLUMN_RANGING_AFTER_CV, aFFinal);

        database.update(TablesPresenterClass.Candidate.TABLE_NAME, contentValues, null, null);

        database.close();

        return true;
    }
}