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

        values.put(TablesPresenterClass.Kandidat.COLUMN_NAME, name);
        values.put(TablesPresenterClass.Kandidat.COLUMN_SURNAME, surname);
        values.put(TablesPresenterClass.Kandidat.COLUMN_AGE, age);
        values.put(TablesPresenterClass.Kandidat.COLUMN_EMAIL, email);

        values.put(TablesPresenterClass.Kandidat.COLUMN_TRACK, track);
        values.put(TablesPresenterClass.Kandidat.COLUMN_FOREIGN, foreign);
        values.put(TablesPresenterClass.Kandidat.COLUMN_EDUCATION, education);

        values.put(TablesPresenterClass.Kandidat.COLUMN_COMMAND, command);
        values.put(TablesPresenterClass.Kandidat.COLUMN_LEADERSHIP, leadership);
        values.put(TablesPresenterClass.Kandidat.COLUMN_DRIVER, driver);

        long newRowId = database.insert(TablesPresenterClass.Kandidat.TABLE_NAME, null, values);
        Log.d(TAG, "The new row Id is " + newRowId);

        return true;
    }


    public int[] readFromDB(Context context, String name) {
        int[] data = new int[7];

        SQLiteDatabase database = new DBSQLiteHelper(context).getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("select " +
                TablesPresenterClass.Kandidat.COLUMN_AGE + ", " +
                TablesPresenterClass.Kandidat.COLUMN_TRACK + ", " +
                TablesPresenterClass.Kandidat.COLUMN_FOREIGN + ", " +
                TablesPresenterClass.Kandidat.COLUMN_EDUCATION + ", " +
                TablesPresenterClass.Kandidat.COLUMN_COMMAND + ", " +
                TablesPresenterClass.Kandidat.COLUMN_LEADERSHIP + ", " +
                TablesPresenterClass.Kandidat.COLUMN_DRIVER + " " +
                "from " + TablesPresenterClass.Kandidat.TABLE_NAME + " WHERE " +
                TablesPresenterClass.Kandidat.COLUMN_NAME + " = ? ", new String[]{name});
        cursor.moveToFirst();
        Log.d(TAG, String.valueOf(cursor.getCount()));
        data[0] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_AGE));
        data[1] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_TRACK));
        data[2] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_FOREIGN));
        data[3] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_EDUCATION));
        data[4] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_COMMAND));
        data[5] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_LEADERSHIP));
        data[6] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_DRIVER));
        cursor.close();
        database.close();
        return data;

    }

    public String[] getNames(Context context) {
        String names[];
        SQLiteDatabase database = new DBSQLiteHelper(context).getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("select " +
                TablesPresenterClass.Kandidat.COLUMN_NAME + " from " + TablesPresenterClass.Kandidat.TABLE_NAME, null);
        cursor.moveToFirst();
        names = new String[cursor.getCount()];
        count = cursor.getCount();
        Log.d(TAG, String.valueOf(cursor.getCount()));
        for (int i = 0; i < count; i++) {
            names[i] = cursor.getString(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_NAME));
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
                TablesPresenterClass.Kandidat.COLUMN_AGE + ", " +
                TablesPresenterClass.Kandidat.COLUMN_TRACK + ", " +
                TablesPresenterClass.Kandidat.COLUMN_FOREIGN + ", " +
                TablesPresenterClass.Kandidat.COLUMN_EDUCATION + ", " +
                TablesPresenterClass.Kandidat.COLUMN_COMMAND + ", " +
                TablesPresenterClass.Kandidat.COLUMN_LEADERSHIP + ", " +
                TablesPresenterClass.Kandidat.COLUMN_DRIVER + " " +
                "from " + TablesPresenterClass.Kandidat.TABLE_NAME, null);
        cursor.moveToFirst();
        count = cursor.getCount();
        data = new double[cursor.getCount()][7];
        Log.d(TAG, String.valueOf(cursor.getCount()));
        for (int i = 0; i < count; i++) {
            data[i][0] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_AGE));
            data[i][1] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_TRACK));
            data[i][2] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_FOREIGN));
            data[i][3] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_EDUCATION));
            data[i][4] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_COMMAND));
            data[i][5] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_LEADERSHIP));
            data[i][6] = cursor.getInt(cursor.getColumnIndexOrThrow(TablesPresenterClass.Kandidat.COLUMN_DRIVER));

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return data;
    }

    public boolean deleteFromDB(Context context, String name) {
        SQLiteDatabase database = new DBSQLiteHelper(context).getWritableDatabase();
        long rowId = database.delete(TablesPresenterClass.Kandidat.TABLE_NAME,
                TablesPresenterClass.Kandidat.COLUMN_NAME + " = ? ", new String[]{name});
        Log.d(TAG, "The deleted row count is " + rowId);
        database.close();
        return true;

    }
}