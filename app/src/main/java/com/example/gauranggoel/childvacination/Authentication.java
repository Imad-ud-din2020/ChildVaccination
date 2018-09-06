
package com.example.gauranggoel.childvacination;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gauranggoel.childvacination.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Authentication extends AppCompatActivity {

    SignInButton signInButton;
    GoogleApiClient googleApiClient;
    FirebaseAuth firebaseAuth=null;
    FirebaseAuth.AuthStateListener authStateListener;
    Intent intent1;
    public static final String TAG = "auth";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        signInButton = (SignInButton) findViewById(R.id.signIn);

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        intent1 = new Intent(Authentication.this,MainActivity.class);

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this ,new GoogleApiClient.OnConnectionFailedListener(){
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Log.d(TAG, "onConnectionFailed: 4");
                Toast.makeText(Authentication.this, "connection failed please try again", Toast.LENGTH_SHORT).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
        //        pd=new ProgressDialog(Authentication.this);
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,101);
            }
        });


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Toast.makeText(Authentication.this, ""+2 , Toast.LENGTH_SHORT).show();
/*
                    Intent intent = new Intent(Authentication.this,MainActivity.class);
                    startActivity(intent);
                   finish();
*/

                }
            }
        };



        firebaseAuth.addAuthStateListener(authStateListener);


        if(authStateListener !=null)
        {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user!=null) {
                Toast.makeText(Authentication.this, ""+3 , Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: ");
                Intent intent = new Intent(Authentication.this, MainActivity.class);
      //          pd.dismiss();
                startActivity(intent);
                finish();
            }
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101)
        {Log.d(TAG, "onActivityResult: ");

            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(googleSignInResult.isSuccess())
            {
                GoogleSignInAccount account = googleSignInResult.getSignInAccount();
                firebaseAuthWithGoogle(account);
             }
             else{
                Toast.makeText(Authentication.this, "Login Failed: Please Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        }
    }
    //private int firebaseAuthWithGoogle(GoogleSignInAccount account){
    private void firebaseAuthWithGoogle(GoogleSignInAccount account){

        AuthCredential credentials = GoogleAuthProvider.getCredential(account.getIdToken(),null );

        firebaseAuth.signInWithCredential(credentials).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful())
                {
                    Log.d(TAG, "onComplete: ");
    //                pd.dismiss();
                    Toast.makeText(Authentication.this, "Login Failed"+task, Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d(TAG, "onComplete: else");
//                    Toast.makeText(Authentication.this, ""+task, Toast.LENGTH_SHORT).show();
  //                  pd.dismiss();
                    startActivity(intent1);
                    finish();
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

//        firebaseAuth.addAuthStateListener(authStateListener);

    }

    public GoogleApiClient getGoogleApiClient(){

        return googleApiClient;
    }
    public FirebaseAuth.AuthStateListener authStateListener(){

        return authStateListener;
    }
}

/*
package com.example.gauranggoel.childvacination;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Authentication extends AppCompatActivity {

    SignInButton signInButton;
    GoogleApiClient googleApiClient;
    FirebaseAuth firebaseAuth=null;
    FirebaseAuth.AuthStateListener authStateListener;
    Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        signInButton = (SignInButton) findViewById(R.id.signIn);

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        intent1 = new Intent(Authentication.this,MainActivity.class);

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this ,new GoogleApiClient.OnConnectionFailedListener(){
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(Authentication.this, "connection failed please try again", Toast.LENGTH_SHORT).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,101);
            }
        });


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        */
/*        FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Toast.makeText(MainActivity.this, ""+2 , Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    //startActivity(intent);
                   // finish();

                }
        *//*
    }
        };



        firebaseAuth.addAuthStateListener(authStateListener);


        if(authStateListener !=null)
        {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user!=null) {
                Toast.makeText(Authentication.this, ""+3 , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Authentication.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101)
        {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(googleSignInResult.isSuccess())
            {
                GoogleSignInAccount account = googleSignInResult.getSignInAccount();
                int p=firebaseAuthWithGoogle(account);
                if(p==0)
                {
                    Toast.makeText(this, ""+1, Toast.LENGTH_SHORT).show();
                    startActivity(intent1);
                    finish();
                }
            }
        }
    }
    private int firebaseAuthWithGoogle(GoogleSignInAccount account){

        final int[] k = new int[1];
        AuthCredential credentials = GoogleAuthProvider.getCredential(account.getIdToken(),null );

        firebaseAuth.signInWithCredential(credentials).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful())
                {
                    Toast.makeText(Authentication.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    k[0] = 1;
                }
            }
        });
        return k[0];
    }
    @Override
    protected void onStart() {
        super.onStart();

//        firebaseAuth.addAuthStateListener(authStateListener);

    }

    public GoogleApiClient getGoogleApiClient(){

        return googleApiClient;
    }
    public FirebaseAuth.AuthStateListener authStateListener(){

        return authStateListener;
    }
}
*/
