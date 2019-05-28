package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikhaellopez.circularimageview.CircularImageView;

public class Pro_Mobile extends AppCompatActivity {
    CircularImageView Mphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro__mobile);
        Mphone=findViewById(R.id.MPhoneCalls);
        Mphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number="7021610928";
                String data="tel:"+number;
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse(data));
                startActivity(i);
            }
        });
    }
}
