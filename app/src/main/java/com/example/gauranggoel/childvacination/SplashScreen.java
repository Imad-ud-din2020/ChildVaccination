package com.example.gauranggoel.childvacination;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new Authentication().authStateListener();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
               // FirebaseUser user = firebaseAuth.getCurrentUser();
                //Toast.makeText(SplashScreen.this, ""+user, Toast.LENGTH_SHORT).show();
              //  intent= new Intent(SplashScreen.this,Authentication.class);

                /*if(user!=null) {
                    intent= new Intent(SplashScreen.this,Authentication.class);

                    Toast.makeText(SplashScreen.this, ""+user.getDisplayName()+user.getEmail(), Toast.LENGTH_SHORT).show();
                }else{
                   intent= new Intent(SplashScreen.this,MainActivity.class);
                }
                */
                intent= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}
