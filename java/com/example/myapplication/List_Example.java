package com.example.myapplication;

import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.DataBase.Register_Helper;

import java.util.ArrayList;
import java.util.Base64;

public class List_Example extends AppCompatActivity {
    GridView listView;
    Register_Helper register_helper;
    ArrayList<String> arrayList;
    ArrayList<String> emailList;
   ArrayList<byte[]> pictureList;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__example);
        listView = findViewById(R.id.List_Example);
        arrayList = new ArrayList<>();
        emailList = new ArrayList<>();
       pictureList = new ArrayList<>();
        register_helper = new Register_Helper(this);
        Cursor result = register_helper.getdata();
        if (result.getCount() == 0) {
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext())
            {

                arrayList.add(result.getString(1));
               emailList.add(result.getString(2));
              pictureList.add(result.getBlob(8));
            }
        }
        ListAdapter listAdapter = new ListAdapter(this, arrayList, emailList,pictureList);
        listView.setAdapter(listAdapter);
    }
}
