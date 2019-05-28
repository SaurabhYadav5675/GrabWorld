package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.Register_Helper;
import com.mikhaellopez.circularimageview.CircularImageView;

public class Pro_camera extends AppCompatActivity {
    TextView userName,Email;
    FloatingActionButton Phone;
    String Number;
    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_camera);
        userName=findViewById(R.id.User_PName1);
        Email=findViewById(R.id.User_PEmail1);
        Phone=findViewById(R.id.PhoneCalls);
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
        txt=findViewById(R.id.Descri);
        userinfo();
    }
    public void userinfo()
    {
        Register_Helper register_helper=new Register_Helper(this);
        SQLiteDatabase sqLiteDatabase=register_helper.getReadableDatabase();
        Cursor result=register_helper.view_Profile(1,sqLiteDatabase);
        if(result.getCount()==0)
        {
            Toast.makeText(this, "No data Avilable", Toast.LENGTH_SHORT).show();
        }
        else if(result.moveToFirst())
        {
            do {
                userName.setText(result.getString(1));
                Email.setText(result.getString(2));
                Number=result.getString(4);
                txt.setText(String.valueOf(Number));

            }while (result.moveToNext());
        }
    }
    public void call()
    {
        String data="tel:"+Number;
        Intent i=new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse(data));
        startActivity(i);
    }

}
