package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.Register_Helper;
import com.example.myapplication.Fragment.Contact_Fragment;
import com.example.myapplication.Fragment.Home_Fragment;
import com.example.myapplication.Fragment.Rent_Fragment;
import com.mikhaellopez.circularimageview.CircularImageView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    NavigationView navigationView;
    CircularImageView Profile1;
    TextView Name;
    Register_Helper register_helper;
    public Bitmap bitmap;
    int Uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.Home_tool);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.Home_Navigation);

//        LayoutInflater layoutInflater = this.getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.navigation_header, null);
        View view = navigationView.getHeaderView(0);
        Profile1 = view.findViewById(R.id.Header_profile);
        Profile1.setOnClickListener(this);
        Name = view.findViewById(R.id.Header_Name);
        Name.setOnClickListener(this);

        drawerLayout = findViewById(R.id.Drawer_Layout);
        frameLayout = findViewById(R.id.Home_frame);
        navigationView = findViewById(R.id.Home_Navigation);
        navigationView.setNavigationItemSelectedListener(this);

        Intent i=getIntent();
        Uid=i.getExtras().getInt("Key");


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Home_frame, new Home_Fragment()).commit();
        }
        User_Profile();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Home_frame, new Home_Fragment()).commit();
                break;
            case R.id.rent:
                getSupportFragmentManager().beginTransaction().replace(R.id.Home_frame,new Rent_Fragment()).commit();
                break;
            case R.id.contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.Home_frame,new Contact_Fragment()).commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else

            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting_menu1:
               startActivity(new Intent(Home.this,Login.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Header_profile:
                startActivity(new Intent(Home.this, User_Profile.class).putExtra("User_id",Uid));
                break;
            case R.id.Header_Name:
                //startActivity(new Intent(Home.this, User_Profile.class));
                break;
        }

    }

    public void User_Profile()
    {
        register_helper = new Register_Helper(this);
        SQLiteDatabase sqLiteDatabase=register_helper.getReadableDatabase();
        Cursor reslt = register_helper.view_Profile(Uid,sqLiteDatabase);
        if (reslt.getCount()==0)
        {
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }
        else
        {
            reslt.moveToFirst();
            boolean status=false;
            do {

                byte[] image=reslt.getBlob(8);
               bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
                Profile1.setImageBitmap(bitmap);
                Name.setText(reslt.getString(1));
                status=true;
            }while (reslt.moveToNext());
        }
    }
}
