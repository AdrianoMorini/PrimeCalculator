package it.ticandtac.primecalculator.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import it.ticandtac.primecalculator.MainActivity;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FuncResult.db";
    public static final String TABLE_NAME = "FuncResult_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "FUNCTION";
    public static final String COL3 = "RESULT";
    public static final String COL4 = "FUNCTION_NAME";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FUNCTION TEXT,RESULT,FUNCTION_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String function, String rslt, String functionName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, function);
        contentValues.put(COL3, rslt);
        contentValues.put(COL4,functionName);
        try {
            db.insert(TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
        return true;
    }




    public boolean delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        Toast.makeText(MainActivity.getMainCntxt(), "Clear all", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Cursor getAllData() {
        Cursor crs = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            crs = db.query(TABLE_NAME, null, null, null, null, null, null);
            crs.moveToFirst();
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
        return crs;
    }

}
