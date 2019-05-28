package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikhaellopez.circularimageview.CircularImageView;

public class Pro_Bag extends AppCompatActivity {
    CircularImageView Call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro__bag);
        Call = findViewById(R.id.bagCall);
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "9699147320";
                String data = "tel:" + number;
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse(data));
                startActivity(i);
            }
        });

    }
}

