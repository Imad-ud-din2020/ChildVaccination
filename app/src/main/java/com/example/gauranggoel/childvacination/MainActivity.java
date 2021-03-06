package com.example.gauranggoel.childvacination;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
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
import android.widget.ImageView;
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
    public static  DatabaseVaccinationDetails databaseVaccinationDetails;
    public static final String TAG="MAIN";
    int longClickedPosition=-1;
    ArrayList<ChildDetails> al ;
    String[] s;
    public static int position1=-1;
    public static Activity activity ;
    //this detemine => no. of times get view will be called
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity= this;

        View v1 = getLayoutInflater().inflate(R.layout.nav_header_main,null);
        Toast.makeText(activity, ""+v1, Toast.LENGTH_SHORT).show();
        TextView username = (TextView) v1.findViewById(R.id.userName);
        TextView useremail = (TextView) v1.findViewById(R.id.emailId);
        ImageView img=v1.findViewById(R.id.imageView);
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
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });



        /*testing for alarm*/
        //Intent intent = new Intent(MainActivity.this,ReminderInCalender.class);
        //startActivity(intent);

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

        databaseVaccinationDetails=new DatabaseVaccinationDetails(this);


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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,VaccinationSchedule.class);
                position1=position;
                startActivity(intent);
            }
        });

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

    }


    //context menu options

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit Details");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Delete")
        {
            databaseChildDetails.deleteRecord(al.get(longClickedPosition).getId());
            databaseVaccinationDetails.deleteRecord(al.get(longClickedPosition).getId());
            al.remove(longClickedPosition);
            adapter.notifyDataSetChanged();

            Log.d(TAG,"delete");
        }
        else  if(item.getTitle()=="Edit Details")
        {
            Intent intent= new Intent(MainActivity.this,EditChildDetails.class);
            ChildDetails child = al.get(longClickedPosition);
            intent.putExtra("name",child.getName());
            intent.putExtra("phone",child.getPhone());
            intent.putExtra("email",child.getEmail());
            intent.putExtra("dob",child.getDob());
            intent.putExtra("position",longClickedPosition);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_share) {

        }
        else if (id == R.id.action_rate) {

        } else if (id == R.id.action_feedback) {
            postQuery();
       }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
       /* if (id == R.id.nav_home) {

        } else */
       if (id == R.id.nav_addchildDetails) {
             intent = new Intent(MainActivity.this,AddChildDetails.class);

        } else if (id == R.id.nav_doctor) {
             intent = new Intent(MainActivity.this,AddDoctorDetail.class);
        }
        else if (id == R.id.nav_CallDoctor) {
             intent = new Intent(MainActivity.this,ShowingDoctorDetails.class);

        } else if (id == R.id.nav_vaccineDetails) {
             intent = new Intent(MainActivity.this,VaccineDetails.class);

        } else if (id == R.id.nav_setting) {
            intent = new Intent(MainActivity.this,SettingActivity.class);

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
            startActivity(intent1);
            finish();
            // Handle the camera action
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void postQuery(){
        String to[] = {"gauranggoel.9@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SEND); // send is for sending data -> decide -> to send where
        intent.setData(Uri.parse("mailto:"+to)); // send to mailing app (dvm understands that -> trannsfer data to mailing app)
        intent.putExtra(Intent.EXTRA_SUBJECT,"FeedBack");
        intent.putExtra(Intent.EXTRA_TEXT,"");
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        intent.setType("text/message");
        startActivity(Intent.createChooser(intent,"Email"));

    }
}
