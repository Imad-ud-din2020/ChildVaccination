package com.example.gauranggoel.childvacination;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurang goel on 02-09-2018.
 */

public class DatabaseChildDetails extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="MyDatabase";
    public static final String DATABASE_TABLE="child_record";
    public static final String ID = "id";
    public static final String NAME="name";
    public static final String PHONE="phone";
    public static final String EMAIL="email";
    public static final String DOB="dob";




    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table "+DATABASE_TABLE+" ( "+ ID +" TEXT  PRIMARY KEY , "+ NAME+" TEXT, "+PHONE+" TEXT, "+EMAIL+" TEXT, "+DOB+" TEXT "+ ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
        onCreate(db);
    }
    public DatabaseChildDetails(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addRecord(ChildDetails childDetails){

        SQLiteDatabase db=getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,childDetails.getDob()+"/"+childDetails.getName()+"/"+childDetails.getPhone());
        contentValues.put(NAME,childDetails.getName());
        contentValues.put(PHONE,childDetails.getPhone());
        contentValues.put(EMAIL,childDetails.getEmail());
        contentValues.put(DOB,childDetails.getDob());

        db.insert(DATABASE_TABLE,null,contentValues);
    }

    public void updateRecord(ChildDetails childDetails ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,childDetails.getDob()+"/"+childDetails.getName()+"/"+childDetails.getPhone()+"/"+childDetails.getEmail());
        contentValues.put(NAME,childDetails.getName());
        contentValues.put(PHONE,childDetails.getPhone());
        contentValues.put(EMAIL,childDetails.getEmail());
        contentValues.put(DOB,childDetails.getDob());

        db.update(DATABASE_TABLE,contentValues,ID +" =? ",new String[]{childDetails.getId()});
        db.close();

    }



    public List<ChildDetails> getAllRecords(){

        SQLiteDatabase db=getReadableDatabase();

        ArrayList<ChildDetails> arrayList = new ArrayList<ChildDetails>();

        String query= "select * from "+ DATABASE_TABLE;

        Cursor cursor =db.rawQuery(query,null);

        if(cursor!=null)
        {

            if(cursor.moveToFirst())
            {
                do{

                    ChildDetails record = new ChildDetails();

                    record.setId(cursor.getString(0));
                    record.setName(cursor.getString(1));
                    record.setPhone(cursor.getString(2));
                    record.setEmail(cursor.getString(3));
                    record.setDob(cursor.getString(4));

                    arrayList.add(record);

                }
                while(cursor.moveToNext());
            }
        }

        return arrayList;
    }

    public ChildDetails getSingleRecord(String id){

        SQLiteDatabase db = getReadableDatabase();

        String query="select * from "+ DATABASE_TABLE + " where " + ID +" = " + id + ";" ;

        Cursor cursor =   db.query(DATABASE_TABLE,new String[]{ID,NAME,PHONE,EMAIL,DOB},ID + " =? ", new String[]{id},null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();

        ChildDetails record = new ChildDetails(cursor.getString(0),cursor.getString(1),cursor.getString(1),cursor.getString(1),cursor.getString(1));

        return record;
    }



    public void deleteRecord(String id){

        SQLiteDatabase db = getWritableDatabase();

        db.delete(DATABASE_TABLE,ID + " =?" , new String[]{id});
    }
}
