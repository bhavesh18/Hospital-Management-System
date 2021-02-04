package com.app.HospitalManagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Hospital.db";

    public static final String DOC_TABLE_NAME = "doctor_table";
    public static final String PATIENT_TABLE_NAME = "patient_table";

    public static final String DOC_ID = "id";
    public static final String DOC_NAME = "name";
    public static final String DOC_EMAIL = "email";
    public static final String DOC_QUALIFICATION = "qualification";
    public static final String DOC_DESIGNATION = "designation";

    public static final String PAT_ID = "id";
    public static final String PAT_NAME = "name";
    public static final String PAT_EMAIL = "email";
    public static final String PAT_DOC_ID = "doctorid";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+DOC_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PATIENT_TABLE_NAME);
        db.execSQL("create table " + DOC_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,qualification TEXT,designation TEXT)");
        db.execSQL("create table " + PATIENT_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,doctorid TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DOC_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PATIENT_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDocData(Context context,String name, String email, String design , String quali) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(DOC_ID, 1);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("designation", design);
        contentValues.put("qualification", quali);
//        long result = db.insert(DOC_TABLE_NAME, null, contentValues);
        db.insert(DOC_TABLE_NAME, null, contentValues);
        Toast.makeText(context,name+ "+" + email,Toast.LENGTH_SHORT).show();
        return true;
    }

    public Cursor getAllDocData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + DOC_TABLE_NAME, null);
        return res;
    }

    public boolean updateDocData( String name, String email, String design,String quali, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, id);
        contentValues.put(DOC_NAME, name);
        contentValues.put(DOC_EMAIL, email);
        contentValues.put(DOC_DESIGNATION, design);
        contentValues.put(DOC_QUALIFICATION, quali);
        db.update(DOC_TABLE_NAME, contentValues, "id = ?", new String[]{id});
        return true;
    }

    public Integer deleteDocData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DOC_TABLE_NAME, "id = ?", new String[]{id});
    }

    public Boolean deleteAllDocData(){
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete(DOC_TABLE_NAME, null, null);
        return true;
    }


    ///Patient Tables

    public Boolean insertPatData(String name, String email, String doctor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email", email);
        contentValues.put("doctorid", doctor);
//        long result = db.insert(DOC_TABLE_NAME, null, contentValues);
        db.insert(PATIENT_TABLE_NAME, null, contentValues);

        return true;
    }

    public Cursor getAllPatData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + PATIENT_TABLE_NAME, null);
        return res;
    }

    public boolean updatePatData( String name, String email, String doctorid, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, id);
        contentValues.put(PAT_NAME, name);
        contentValues.put(PAT_EMAIL, email);
        contentValues.put(PAT_DOC_ID, doctorid);
        db.update(PATIENT_TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deletePatData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PATIENT_TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Boolean deleteAllPatData(){
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete(PATIENT_TABLE_NAME, null, null);
         return true;
    }
}