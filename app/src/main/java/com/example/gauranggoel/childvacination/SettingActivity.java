package com.example.gauranggoel.childvacination;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class SettingActivity extends AppCompatActivity {

    ImageView img,img1;
    CheckBox ch1,ch2;
    int mail=1,notification=1;
    AlertDialog.Builder alert;
    SharedPreferences shref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();

        shref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor=shref.edit();

        if(shref.getString("notification",null)==null)
            editor.putString("notification","1");
        else
            notification=Integer.parseInt(shref.getString("notification",null));

        if(shref.getString("mail",null)==null)
            editor.putString("mail","1");
        else
            mail=Integer.parseInt(shref.getString("mail",null));


        ch1= (CheckBox) findViewById(R.id.notification_check);
        ch2= (CheckBox) findViewById(R.id.mail_check);

        if(notification==1)
            ch1.setChecked(true);
        else
            ch1.setChecked(false);

        if(mail==1)
            ch2.setChecked(true);
        else
            ch2.setChecked(false);


            img = (ImageView) findViewById(R.id.setting_icon);
        img1 = (ImageView) findViewById(R.id.reminderTime);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch1.isChecked()) {
                    notification = 1;
                }
                else
                {
                     alert = new AlertDialog.Builder(SettingActivity.this);
                    alert.setTitle("Notification");
                    alert.setMessage("Now you will no longer recieve notifications");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            notification=0;
                            dialog.dismiss();
                        }
                    });
                    alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();

                }

            }
        });

        ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch2.isChecked())
                    mail=1;
                else
                {
                    alert = new AlertDialog.Builder(SettingActivity.this);
                    alert.setTitle("Notification");
                    alert.setMessage("Now you will no longer recieve Mails");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mail=0;
                            dialog.dismiss();
                        }
                    });
                    alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();
                }
            }
        });

        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("notification",String.valueOf(notification));
                editor.putString("mail",String.valueOf(mail));
                editor.commit();
                onBackPressed();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
