package com.droidsoftbd.hhbregistration.Classes;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BDDL-102 on 6/1/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    private static final String DB_NAME = "info_db";
    private static final int DB_VERSION = 1;

    public static final String ID_COLUMN  = "id";
    public static final String NAME_COLUMN = "name";
    public static final String GENDER_COLUMN = "gender";
    public static final String REGULARITY_COLUMN = "regularity";
    public static final String BLOOD_GROUP_COLUMN = "blood_group";
    public static final String AGE_COLUMN = "age";
    public static final String MOBILE_COLUMN = "mobile";
    public static final String LBDD_COLUMN = "last_blood_donate_date";
    public static final String DIVISION_COLUMN = "division";
    public static final String OCCUPATION_COLUMN = "occupation";
    public static final String FATHER_NAME_COLUMN = "father_name";
    public static final String PRESENT_ADD_COLUMN = "present_address";
    public static final String PERMANENT_ADD_COLUMN = "permanent_address";
    public static final String EMAIL_FB_COLUMN = "email_fb_id";
    public static final String STATUS_COLUMN = "status";
    public static final String TABLE_INFO = "info_tbl";

    private static final String CREATE_INFO_TABLE = "CREATE TABLE "+TABLE_INFO+" ("
            +ID_COLUMN+" INTEGER PRIMARY KEY,"
            +NAME_COLUMN+" TEXT,"
            +GENDER_COLUMN+" TEXT,"
            +REGULARITY_COLUMN+" TEXT,"
            +BLOOD_GROUP_COLUMN+" TEXT,"
            +AGE_COLUMN+" TEXT, "
            +MOBILE_COLUMN+" TEXT,"
            +LBDD_COLUMN+" TEXT,"
            +DIVISION_COLUMN+" TEXT,"
            +OCCUPATION_COLUMN+" TEXT,"
            +FATHER_NAME_COLUMN+" TEXT,"
            +PRESENT_ADD_COLUMN+" TEXT,"
            +PERMANENT_ADD_COLUMN+" TEXT,"
            +EMAIL_FB_COLUMN+" TEXT,"
            +STATUS_COLUMN+" TEXT)";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
