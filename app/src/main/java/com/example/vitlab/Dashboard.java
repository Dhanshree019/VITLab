package com.example.vitlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView txt,textView3,textView4,textView2;
    ImageView imageView2,imageView11,imageView12;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseUser user=mAuth.getCurrentUser();

    private RecyclerView recyclerView,recyclerView2;
    private StaticRvAdapter staticRvAdapter;
    private StaticRv2Adapter staticRv2Adapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Date dt;
    Calendar calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        txt=(TextView)findViewById(R.id.txtmsg);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        imageView2=(ImageView) findViewById(R.id.imageView2);
        imageView11=(ImageView) findViewById(R.id.imageView11);
        imageView12=(ImageView) findViewById(R.id.imageView12);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,Rules.class);
                startActivity(i);
            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,Precausation.class);
                startActivity(i);
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,About.class);
                startActivity(i);
            }
        });
//        Intent intent=getIntent();
//        String username=intent.getStringExtra("User");
//        textView2.setText("Welcome,"+"   "+username);

//        textView2.setText(getIntent().getExtras().getString("User"));

        Calendar calender = Calendar.getInstance();
        int timeOfDay = calender.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 0 && timeOfDay < 12){
            txt.setText("Good Morning,");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            txt.setText("Good Afternoon,");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            txt.setText("Good Evening,");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            txt.setText("Good Night,");
        }
        textView3.setPaintFlags(textView3.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        textView4.setPaintFlags(textView3.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        ArrayList<StaticRvModel> item=new ArrayList<>();
        item.add(new StaticRvModel (R.drawable.softwarelab_1,  "Software Lab 301 ","Timing:10am-12pm","Incharge:Prof.abc","Lunch Time:12pm-1pm"));
        item.add(new StaticRvModel(R.drawable.softwarelab_1,  "Software Lab 302 ","Timing:10am-12pm","Incharge:Prof.xyz","Lunch Time:12pm-1pm"));
        item.add(new StaticRvModel(R.drawable.softwarelab_1,  "Software Lab 303 ","Timing:10am-12pm","Incharge:Prof.xyz","Lunch Time:12pm-1pm"));
        item.add(new StaticRvModel(R.drawable.softwarelab_1,  "Software Lab 304 ","Timing:10am-12pm","Incharge:Prof.xyz","Lunch Time:12pm-1pm"));
        item.add(new StaticRvModel(R.drawable.softwarelab_1,  "Software Lab 305 ","Timing:10am-12pm","Incharge:Prof.xyz","Lunch Time:12pm-1pm"));


        recyclerView=findViewById(R.id.recyclerView);
        staticRvAdapter=new StaticRvAdapter(item,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);
        ArrayList<StaticRv2Model> item1=new ArrayList<>();
        item1.add(new StaticRv2Model (R.drawable.hardware,  "Hardware Lab 201 ","Timing:10am-12:pm","Incharge:Prof.abc","Lunchtime:12pm-1pm"));
        item1.add(new StaticRv2Model(R.drawable.hardware,  "Hardware  Lab 202 ","Timing:10am-12:pm","Incharge:Prof.xyz","Lunchtime:12pm-1pm"));
        item1.add(new StaticRv2Model(R.drawable.hardware,  "Hardware  Lab 203 ","Timing:10am-12:pm","Incharge:Prof.xyz","Lunchtime:12pm-1pm"));
        item1.add(new StaticRv2Model(R.drawable.hardware,  "Hardware  Lab 204 ","Timing:10am-12:pm","Incharge:Prof.xyz","Lunchtime:12pm-1pm"));
        item1.add(new StaticRv2Model(R.drawable.hardware,  "Hardware  Lab 205 ","Timing:10am-12:pm","Incharge:Prof.xyz","Lunchtime:12pm-1pm"));

        recyclerView2=findViewById(R.id.recyclerView2);
        staticRv2Adapter=new StaticRv2Adapter(item1,this);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setAdapter(staticRv2Adapter);


        drawerLayout=findViewById (R.id.drawer_layout);
        navigationView=findViewById (R.id.nav_view);
        toolbar=findViewById (R.id.toolbar);

        setSupportActionBar (toolbar);





        navigationView.bringToFront ();
        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle (this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener (toogle);
        toogle.syncState ();
        navigationView.setNavigationItemSelectedListener (this);
        navigationView.setCheckedItem (R.id.nav_home);


    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen (GravityCompat.START)){
            drawerLayout.closeDrawer (GravityCompat.START);
        }

        else {
            super.onBackPressed ();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem menuItem) {
        switch (menuItem.getItemId() ){
            case R.id.nav_home:
                break;
            case R.id.nav_history:
                Intent intent=new Intent(Dashboard.this,History.class );
                startActivity (intent);
                break;
            case R.id.nav_logout:
                    mAuth.signOut();
                    startActivity(new Intent(Dashboard.this, MainActivity.class));
                    finish();

        }

        drawerLayout.closeDrawer (GravityCompat.START);

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}