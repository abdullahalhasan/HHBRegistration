package com.droidsoftbd.hhbregistration.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.app.DownloadManager.COLUMN_STATUS;

/**
 * Created by BDDL-102 on 6/1/2018.
 */

public class DBManager {
    private UserInfo userInfo;
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;


    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    private void openDB() {
        database = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        dbHelper.close();
    }

    public boolean addUserInfo(UserInfo userInfo) {
        this.openDB();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME_COLUMN,userInfo.getFullName());
        contentValues.put(DBHelper.GENDER_COLUMN,userInfo.getGender());
        contentValues.put(DBHelper.BLOOD_GROUP_COLUMN,userInfo.getBloodGroup());
        contentValues.put(DBHelper.AGE_COLUMN,userInfo.getAge());
        contentValues.put(DBHelper.MOBILE_COLUMN,userInfo.getMobileNumber());
        contentValues.put(DBHelper.LBDD_COLUMN,userInfo.getLastBloodDonateDate());
        contentValues.put(DBHelper.DIVISION_COLUMN,userInfo.getDivision());
        contentValues.put(DBHelper.OCCUPATION_COLUMN,userInfo.getOccupation());
        contentValues.put(DBHelper.FATHER_NAME_COLUMN,userInfo.getFatherName());
        contentValues.put(DBHelper.PRESENT_ADD_COLUMN,userInfo.getPresentAddress());
        contentValues.put(DBHelper.PERMANENT_ADD_COLUMN,userInfo.getPermanentAddress());
        contentValues.put(DBHelper.EMAIL_FB_COLUMN,userInfo.getEmailFbID());
        contentValues.put(DBHelper.REGULARITY_COLUMN,userInfo.getRegularity());
        contentValues.put(DBHelper.STATUS_COLUMN,userInfo.getStatus());

        long inserted = database.insert(DBHelper.TABLE_INFO,null,contentValues);

        this.closeDB();

        if (inserted>0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<UserInfo> getAllUserInfos(){

        this.openDB();
        ArrayList<UserInfo> userInfoList = new ArrayList();
        Cursor cursor = database.query(DBHelper.TABLE_INFO,null,null,null,null,null,DBHelper.BLOOD_GROUP_COLUMN);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COLUMN));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COLUMN));
                String gender = cursor.getString(cursor.getColumnIndex(DBHelper.GENDER_COLUMN));
                String bloodGroup = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_GROUP_COLUMN));
                String age = cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COLUMN));
                String mobile = cursor.getString(cursor.getColumnIndex(DBHelper.MOBILE_COLUMN));
                String lastBloodDonateDate = cursor.getString(cursor.getColumnIndex(DBHelper.LBDD_COLUMN));
                String division = cursor.getString(cursor.getColumnIndex(DBHelper.DIVISION_COLUMN));
                String occupation = cursor.getString(cursor.getColumnIndex(DBHelper.OCCUPATION_COLUMN));
                String fatherName = cursor.getString(cursor.getColumnIndex(DBHelper.FATHER_NAME_COLUMN));
                String presentAddress = cursor.getString(cursor.getColumnIndex(DBHelper.PRESENT_ADD_COLUMN));
                String permanentAddress = cursor.getString(cursor.getColumnIndex(DBHelper.PERMANENT_ADD_COLUMN));
                String emailFbID = cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_FB_COLUMN));
                String regularity = cursor.getString(cursor.getColumnIndex(DBHelper.REGULARITY_COLUMN));
                String status = cursor.getString(cursor.getColumnIndex(DBHelper.STATUS_COLUMN));

                UserInfo userInfo = new UserInfo(id,name,gender,bloodGroup,age,mobile,lastBloodDonateDate,division,occupation,
                        fatherName,presentAddress,permanentAddress,emailFbID,regularity,status);
                userInfoList.add(userInfo);
                cursor.moveToNext();
            }
            this.closeDB();
        }

        return userInfoList;
    }

    public UserInfo getUserInfo(int id) {

        this.openDB();
        Cursor cursor = database.query(DBHelper.TABLE_INFO, new String[] {DBHelper.ID_COLUMN,DBHelper.NAME_COLUMN,DBHelper.GENDER_COLUMN ,DBHelper.BLOOD_GROUP_COLUMN,
                        DBHelper.GENDER_COLUMN,DBHelper.MOBILE_COLUMN,DBHelper.DIVISION_COLUMN,DBHelper.LBDD_COLUMN,DBHelper.PRESENT_ADD_COLUMN},
                        DBHelper.ID_COLUMN+"="+id,null,null,null,null);

        cursor.moveToFirst();

        int useId = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COLUMN));
        String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COLUMN));
        String gender = cursor.getString(cursor.getColumnIndex(DBHelper.GENDER_COLUMN));
        String bloodGroup = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_GROUP_COLUMN));
        String mobile = cursor.getString(cursor.getColumnIndex(DBHelper.MOBILE_COLUMN));
        String lastBloodDonateDate = cursor.getString(cursor.getColumnIndex(DBHelper.LBDD_COLUMN));
        String division = cursor.getString(cursor.getColumnIndex(DBHelper.DIVISION_COLUMN));
        String presentAddress = cursor.getString(cursor.getColumnIndex(DBHelper.PRESENT_ADD_COLUMN));

        UserInfo userInfo = new UserInfo(id,name,gender,bloodGroup,mobile,lastBloodDonateDate,division,presentAddress);

        this.closeDB();
        return userInfo;

    }

    public Cursor getUnSyncList() {
        this.openDB();

        String sql = "SELECT * FROM " + DBHelper.TABLE_INFO + " WHERE " + DBHelper.STATUS_COLUMN + " = 0;";
        Cursor c = database.rawQuery(sql, null);
        this.closeDB();
        return c;
    }

    public boolean updateNameStatus(int id, int status) {
        this.openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.STATUS_COLUMN, status);
        long updated = database.update(DBHelper.TABLE_INFO, contentValues, DBHelper.ID_COLUMN + "=" + id, null);
        this.closeDB();
        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }
}
