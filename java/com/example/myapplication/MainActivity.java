package com.example.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
      private ProgressBar mprogress;
      private int count=0;
      private Handler mhandler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mprogress=findViewById(R.id.progressBar2);

        new Thread(new Runnable() {
            @Override
            public void run()
            {
               while (count<40)
               {
                   count++;
                   SystemClock.sleep(40);
                   mhandler.post(new Runnable() {
                       @Override
                       public void run()
                       {
                          mprogress.setProgress(count);
                       }
                   });

               }
               mhandler.post(new Runnable() {
                   @Override
                   public void run()
                   {
                       Toast.makeText(MainActivity.this, "Welcome To Grab World.. ", Toast.LENGTH_SHORT).show();
                       Intent i=new Intent(getApplicationContext(),Login.class);
                       startActivity(i);
                   }
               });
            }
        }).start();
    }
}
