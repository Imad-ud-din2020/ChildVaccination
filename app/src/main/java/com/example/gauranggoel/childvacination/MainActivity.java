package com.example.gauranggoel.childvacination;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    ListView listView;
    CustomAdapter adapter;
    public static  DatabaseChildDetails databaseChildDetails;
    public static final String TAG="MAIN";
    int longClickedPosition=-1;
    ArrayList<ChildDetails> al ;
    String[] s;
    //this detemine => no. of times get view will be called
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddChildDetails.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //extra

        databaseChildDetails = new DatabaseChildDetails(this);

        al= (ArrayList<ChildDetails>) databaseChildDetails.getAllRecords();

        Log.d(TAG,"got Object");
        //Toast.makeText(MainActivity.this, ""+al, Toast.LENGTH_SHORT).show();


        //ListView Section

        listView = (ListView) findViewById(R.id.listView);

        adapter = new CustomAdapter(this,al);

        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);


        //event over list view

        //on click


        //on long press

        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
             longClickedPosition=position;

                Log.d(TAG,"context Listener");
                return false;
            }
        });


        //acessing User details
        TextView username = (TextView) findViewById(R.id.userName);
        TextView useremail = (TextView) findViewById(R.id.emailId);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new Authentication().authStateListener();
       FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null) {
            String name = user.getDisplayName().toString();
            String email= user.getEmail().toString();
            Toast.makeText(MainActivity.this, name+"\n"+email, Toast.LENGTH_SHORT).show();
            username.setText(name);
            useremail.setText(email);
        }


    }


    //context menu options


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Delete")
        {
            databaseChildDetails.deleteRecord(al.get(longClickedPosition).getId());
            al.remove(longClickedPosition);
            adapter.notifyDataSetChanged();

            Log.d(TAG,"delete");
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
         //   super.onBackPressed();

            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        if (id == R.id.action_help) {

        } else if (id == R.id.action_settings) {

        } else if (id == R.id.action_share) {

        }
        else if (id == R.id.action_rate) {

        } else if (id == R.id.action_feedback) {

       }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_addchildDetails) {
             intent = new Intent(MainActivity.this,AddChildDetails.class);

        } else if (id == R.id.nav_doctor) {
             intent = new Intent(MainActivity.this,AddDoctorDetail.class);
        }
        else if (id == R.id.nav_CallDoctor) {
             intent = new Intent(MainActivity.this,ShowingDoctorDetails.class);

        } else if (id == R.id.nav_vaccineDetails) {
             intent = new Intent(MainActivity.this,VaccineDetails.class);

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if(intent!=null)
        {
            startActivity(intent);

        }

        if (id == R.id.nav_logOut) {
            FirebaseAuth.getInstance().signOut();
            firebaseAuth.removeAuthStateListener(authStateListener);

            Intent intent1 = new Intent(MainActivity.this,Authentication.class);
            startActivity(intent);
            finish();
            // Handle the camera action
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
