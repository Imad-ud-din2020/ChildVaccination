package com.example.gauranggoel.childvacination;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by gaurang goel on 06-09-2018.
 */

public class MyService extends Service {


    DatabaseChildDetails databaseChildDetails;
    DatabaseVaccinationDetails databaseVaccinationDetails;
    @Override
    public IBinder onBind(Intent intent) {
        return null;


    }

    public MyService() {
/*
        databaseChildDetails=new DatabaseChildDetails(this);
        databaseVaccinationDetails=new DatabaseVaccinationDetails(this);
*/
    }
    int day,month,year;
    @Override
    public void onCreate() {

        Calendar ca=Calendar.getInstance();
         day = ca.get(Calendar.DAY_OF_MONTH);
         month = ca.get(Calendar.MONTH);
         year = ca.get(Calendar.YEAR);

        databaseChildDetails=new DatabaseChildDetails(this);
        databaseVaccinationDetails=new DatabaseVaccinationDetails(this);

        ArrayList<ChildDetails> child = (ArrayList<ChildDetails>) databaseChildDetails.getAllRecords();

        for(int i=0;i<child.size();i++)
        {
            String id = child.get(i).getId();

            ArrayList<VaccinationObject> vaccine = (ArrayList<VaccinationObject>) databaseVaccinationDetails.getAllRecords(id);

            String name=child.get(i).getName();
            String email=child.get(i).getEmail();
            String vaccines="";
            for(int j=0;j<vaccine.size();j++)
            {
                String schedule=vaccine.get(j).getScheduleTime();
                String status = vaccine.get(j).getStatus();
                if(status.equals("NotGiven"))
                {
                    int c=compare(schedule);
                    if(c==2)
                        break;
                    else if(c==0)
                    {
                        vaccines=vaccine.get(j).getName()+", ";
                    }
                }
                else{
                    continue;
                }

            }
            if(!vaccines.equals(""))
            {
                sendMail(name,email,vaccines);
                sendNotification(name,vaccines);
            }
        }

    }

    public int compare(String time){
        int p=0;

        String c[]=time.split("/");

        int day1=Integer.parseInt(c[0]);
        int month1=Integer.parseInt(c[1]);
        int year1=Integer.parseInt(c[2]);

        if((year-year1)==0)
        {
            if((month-month1)==0)
            {
                if((day-day1)<7)
                {
                    return 0;
                }
            }
            else if((month-month1)==1)
            {
                if((day1-day)>8)
                    return 0;
            }
            else{
                return 1;
            }
        }
        else if((year-year1)==1)
        {
            if((month1-month)==11)
            {
                if((day1-day)>8)
                    return 0;
            }
        }
        else{
            return 2;
        }
        return 10;
    }

    public void sendMail(String name,String email,String vaccines)
    {

    }
    public  void sendNotification(String name,String vaccines){

    }

    @Override
    public void onStart(Intent intent, int startId) {


    }

    @Override
    public void onDestroy() {
    }

}
