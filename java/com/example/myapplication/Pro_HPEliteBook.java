package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikhaellopez.circularimageview.CircularImageView;

public class Pro_HPEliteBook extends AppCompatActivity {
    CircularImageView call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro__hpelite_book);
        call=findViewById(R.id.HPElitebookCalls);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number="9594764107";
                String data="tel:"+number;
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse(data));
                startActivity(i);
            }
        });
    }
}
