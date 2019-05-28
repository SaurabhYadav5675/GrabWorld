package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikhaellopez.circularimageview.CircularImageView;

public class Rent_Car extends AppCompatActivity {
    CircularImageView Call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent__car);
        Call=findViewById(R.id.HarshadCalls);
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data="tel:"+"9594764107";
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(data));
                startActivity(intent);
            }
        });
    }
}
