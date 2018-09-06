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

public class DatabaseVaccinationDetails extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="VaccineDatabase";
    public static final String DATABASE_TABLE="vaccine_record";
    public static final String ID = "id";
    public static final String NAME="name";
    public static final String SCHEDULE="schedule_time";
    public static final String GIVEN="given_on";
    public static final String STATUS="status";
    public static final String TAG="VaccineDatabase";

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG,"on Create");

        String query="create table "+DATABASE_TABLE+" ( "+ ID +" TEXT  , "+ NAME+" TEXT, "+SCHEDULE+" TEXT, "+GIVEN+" TEXT, "+STATUS+" TEXT "+ ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG,"on upgrade");

        db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE+";");
        onCreate(db);
    }

    public DatabaseVaccinationDetails(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG,"constructor");
    }


    public void addRecord(VaccinationObject vaccinationObject){

        Log.d(TAG,"add record");

        SQLiteDatabase db=getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,vaccinationObject.getId());
        contentValues.put(NAME,vaccinationObject.getName());
        contentValues.put(SCHEDULE,vaccinationObject.getScheduleTime());
        contentValues.put(GIVEN,vaccinationObject.getGivenTime());
        contentValues.put(STATUS,vaccinationObject.getStatus());

        db.insert(DATABASE_TABLE,null,contentValues);
    }

    public void updateRecord(VaccinationObject vaccinationObject ){

        Log.d(TAG,"update record");

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,vaccinationObject.getId());
        contentValues.put(NAME,vaccinationObject.getName());
        contentValues.put(SCHEDULE,vaccinationObject.getScheduleTime());
        contentValues.put(GIVEN,vaccinationObject.getGivenTime());
        contentValues.put(STATUS,vaccinationObject.getStatus());

        db.update(DATABASE_TABLE,contentValues,ID +" =?  AND "+NAME+" =? ",new String[]{vaccinationObject.getId(),vaccinationObject.getName()});
        db.close();
    }

    public List<VaccinationObject> getAllRecords(String Id){


        Log.d(TAG,"get all records");

        SQLiteDatabase db=getReadableDatabase();

        ArrayList<VaccinationObject> arrayList = new ArrayList<VaccinationObject>();

        String query= "select * from "+ DATABASE_TABLE + " where "+ID+" = '"+Id+"';";

        Cursor cursor =db.rawQuery(query,null);

        if(cursor!=null)
        {

            if(cursor.moveToFirst())
            {
                do{

                    VaccinationObject record = new VaccinationObject();

                    record.setId(cursor.getString(0));
                    record.setName(cursor.getString(1));
                    record.setScheduleTime(cursor.getString(2));
                    record.setGivenTime(cursor.getString(3));
                    record.setStatus(cursor.getString(4));

                    arrayList.add(record);

                }
                while(cursor.moveToNext());
            }
        }

        return arrayList;
    }

    public VaccinationObject getSingleRecord(String id,String name){

        Log.d(TAG,"get single record");

        SQLiteDatabase db = getReadableDatabase();

        String query="select * from "+ DATABASE_TABLE + " where " + ID +" = '" + id + "' and "+NAME+ " = '"+name+"';" ;

        Cursor cursor =   db.query(DATABASE_TABLE,new String[]{ID,NAME,SCHEDULE,GIVEN,STATUS},ID + " =?  AND "+NAME+" =? ", new String[]{id,name},null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();

        VaccinationObject record = new VaccinationObject(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return record;
    }

    public void deleteRecord(String id){

        Log.d(TAG,"on delete");

        SQLiteDatabase db = getWritableDatabase();

        db.delete(DATABASE_TABLE,ID + " =?" , new String[]{id});
    }

}
