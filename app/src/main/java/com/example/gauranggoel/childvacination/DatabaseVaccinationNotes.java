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
 * Created by gaurang goel on 06-09-2018.
 */

public class DatabaseVaccinationNotes extends  SQLiteOpenHelper {

        public static final int DATABASE_VERSION=1;

        public static final String DATABASE_NAME="VaccineNoteDatabase";
        public static final String DATABASE_TABLE="vaccine_note";
        public static final String ID = "id";
        public static final String NAME="name";
        public static final String FEE="fee";
        public static final String HOSPITAL="hospital";
        public static final String NOTE="notes";
        public static final String TAG="VaccineNoteDatabase";

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.d(TAG,"on Create");

            String query="create table "+DATABASE_TABLE+" ( "+ ID +" TEXT  , "+ NAME+" TEXT, "+FEE+" TEXT, "+HOSPITAL+" TEXT, "+NOTE+" TEXT "+ ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d(TAG,"on upgrade");

            db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE+";");
            onCreate(db);
        }

        public DatabaseVaccinationNotes(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.d(TAG,"constructor");
        }


        public void addRecord(VaccinationNoteObject vaccinationNoteObject){

            Log.d(TAG,"add record");

            SQLiteDatabase db=getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(ID,vaccinationNoteObject.getId());
            contentValues.put(NAME,vaccinationNoteObject.getName());
            contentValues.put(FEE,vaccinationNoteObject.getFee());
            contentValues.put(HOSPITAL,vaccinationNoteObject.getHospital());
            contentValues.put(NOTE,vaccinationNoteObject.getNotes());

            db.insert(DATABASE_TABLE,null,contentValues);
        }

        public void updateRecord(VaccinationNoteObject vaccinationNoteObject ){

            Log.d(TAG,"update record");

            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(ID,vaccinationNoteObject.getId());
            contentValues.put(NAME,vaccinationNoteObject.getName());
            contentValues.put(FEE,vaccinationNoteObject.getFee());
            contentValues.put(HOSPITAL,vaccinationNoteObject.getHospital());
            contentValues.put(NOTE,vaccinationNoteObject.getNotes());

            db.update(DATABASE_TABLE,contentValues,ID +" =?  AND "+NAME+" =? ",new String[]{vaccinationNoteObject.getId(),vaccinationNoteObject.getName()});
            db.close();
        }

        public List<VaccinationNoteObject> getAllRecords(String Id,String name){

            Log.d(TAG,"get all records");

            SQLiteDatabase db=getReadableDatabase();

            ArrayList<VaccinationNoteObject> arrayList = new ArrayList<VaccinationNoteObject>();

            String query= "select * from "+ DATABASE_TABLE + " where "+ID+" = '"+ Id + "' and "+ NAME + " = '"+name+"';";

            Cursor cursor =db.rawQuery(query,null);

            if(cursor!=null)
            {

                if(cursor.moveToFirst())
                {
                    do{

                        VaccinationNoteObject record = new VaccinationNoteObject();

                        record.setId(cursor.getString(0));
                        record.setName(cursor.getString(1));
                        record.setFee(cursor.getString(2));
                        record.setHospital(cursor.getString(3));
                        record.setNotes(cursor.getString(4));

                        arrayList.add(record);

                    }
                    while(cursor.moveToNext());
                }
            }

            return arrayList;
        }
/*
        public VaccinationNoteObject getSingleRecord(String id,String name){

            Log.d(TAG,"get single record");

            SQLiteDatabase db = getReadableDatabase();

            String query="select * from "+ DATABASE_TABLE + " where " + ID +" = '" + id + "' and "+NAME+ " = '"+name+"';" ;

            Cursor cursor =   db.query(DATABASE_TABLE,new String[]{ID,NAME,FEE,HOSPITAL,NOTE},ID + " =?  AND "+NAME+" =? ", new String[]{id,name},null,null,null);


            VaccinationNoteObject record=null;
            if(cursor!=null) {
                cursor.moveToFirst();
                record = new VaccinationNoteObject(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            }
            return record;
        }

        public void deleteRecord(String id){

            Log.d(TAG,"on delete");
            SQLiteDatabase db = getWritableDatabase();

            db.delete(DATABASE_TABLE,ID + " =?" , new String[]{id});
        }*/

}
