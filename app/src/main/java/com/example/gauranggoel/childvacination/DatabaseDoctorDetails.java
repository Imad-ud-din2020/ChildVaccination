package com.example.gauranggoel.childvacination;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurang goel on 03-09-2018.
 */

public class DatabaseDoctorDetails  extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="DoctorDatabase";
    public static final String DATABASE_TABLE="doctor_record";
    public static final String ID = "id";
    public static final String NAME="name";
    public static final String PHONE="phone";
    public static final String HOSPITAL="hospital";

    public static final String TAG="doctorDatabase";




    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"on Create");
        String query="create table "+DATABASE_TABLE+" ( "+ ID +" TEXT  PRIMARY KEY , "+ NAME+" TEXT, "+PHONE+" TEXT, "+HOSPITAL+" TEXT "+ ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG,"onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
        onCreate(db);
    }
    public DatabaseDoctorDetails(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d(TAG,"constructor");
    }

    public void addRecord(DoctorDetails doctorDetails){

        Log.d(TAG,"add record");
        SQLiteDatabase db=getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,doctorDetails.getHospital()+"/"+doctorDetails.getName()+"/"+doctorDetails.getPhone());
        contentValues.put(NAME,doctorDetails.getName());
        contentValues.put(PHONE,doctorDetails.getPhone());
        contentValues.put(HOSPITAL,doctorDetails.getHospital());

        db.insert(DATABASE_TABLE,null,contentValues);
    }

    public void updateRecord(DoctorDetails doctorDetails ){

        Log.d(TAG,"update record");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,doctorDetails.getHospital()+"/"+doctorDetails.getName()+"/"+doctorDetails.getPhone());
        contentValues.put(NAME,doctorDetails.getName());
        contentValues.put(PHONE,doctorDetails.getPhone());
        contentValues.put(HOSPITAL,doctorDetails.getHospital());

        db.update(DATABASE_TABLE,contentValues,ID +" =? ",new String[]{doctorDetails.getId()});
        db.close();

    }



    public List<DoctorDetails> getAllRecords(){

        Log.d(TAG,"getAllRecords");
        SQLiteDatabase db=getReadableDatabase();

        ArrayList<DoctorDetails> arrayList = new ArrayList<DoctorDetails>();

        String query= "select * from "+ DATABASE_TABLE;

        Cursor cursor =db.rawQuery(query,null);

        if(cursor!=null)
        {

            if(cursor.moveToFirst())
            {
                do{

                    DoctorDetails record = new DoctorDetails();

                    record.setId(cursor.getString(0));
                    record.setName(cursor.getString(1));
                    record.setPhone(cursor.getString(2));
                    record.setHospital(cursor.getString(3));

                    arrayList.add(record);

                }
                while(cursor.moveToNext());
            }
        }

        return arrayList;
    }

    public DoctorDetails getSingleRecord(String id){

        Log.d(TAG,"getSingleRecord");
        SQLiteDatabase db = getReadableDatabase();

        String query="select * from "+ DATABASE_TABLE + " where " + ID +" = " + id + ";" ;

        Cursor cursor =   db.query(DATABASE_TABLE,new String[]{ID,NAME,PHONE,HOSPITAL},ID + " =? ", new String[]{id},null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();

        DoctorDetails record = new DoctorDetails(cursor.getString(0),cursor.getString(1),cursor.getString(1),cursor.getString(1));

        return record;
    }



    public void deleteRecord(String id){

        Log.d(TAG,"deteteRecord");
        SQLiteDatabase db = getWritableDatabase();

        db.delete(DATABASE_TABLE,ID + " =?" , new String[]{id});
    }
}
