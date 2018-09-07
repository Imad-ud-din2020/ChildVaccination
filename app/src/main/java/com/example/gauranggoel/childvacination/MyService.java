package com.example.gauranggoel.childvacination;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
        databaseChildDetails=new DatabaseChildDetails(this);
        databaseVaccinationDetails=new DatabaseVaccinationDetails(this);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart(Intent intent, int startId) {
    }

    @Override
    public void onDestroy() {
    }

}
