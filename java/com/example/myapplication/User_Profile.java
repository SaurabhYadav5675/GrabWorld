package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.Register_Helper;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class User_Profile extends AppCompatActivity  {

    CircularImageView Profile;
    FloatingActionButton Editbtn;
    Button Update;
    Switch Edit_swtch;
    EditText User_Name,User_Email,User_Mobile,User_Address,User_Password;
    String Name,Email,Mobile,Address,Password;
    Bitmap bitmap;
    private final int Camera_Request=0;
    private final int Gallery_Request=1;
    Register_Helper register_helper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        Profile=findViewById(R.id.User_Profile);
        Editbtn=findViewById(R.id.User_Edit);
        User_Name=findViewById(R.id.User_name);
        User_Email=findViewById(R.id.User_Email);
        User_Mobile=findViewById(R.id.User_Mobile);
        User_Address=findViewById(R.id.User_Address);
        User_Password=findViewById(R.id.User_Password);
        Edit_swtch=findViewById(R.id.Edit_switch);
        Edit_swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    User_Name.setEnabled(true);
                    User_Email.setEnabled(true);
                    User_Mobile.setEnabled(true);
                    User_Address.setEnabled(true);
                    User_Password.setEnabled(true);
                }
                else
                {
                    User_Name.setEnabled(false);
                    User_Email.setEnabled(false);
                    User_Mobile.setEnabled(false);
                    User_Address.setEnabled(false);
                    User_Password.setEnabled(false);
                }
            }
        });
        Editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Option_List();
            }
        });
        Update=findViewById(R.id.Update_btn);
        Update.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                update_profile();
            }
        });
        View_Profiledata();


    }


    public void Option_List()
    {
        final  CharSequence[] Options={"Click Photo","Select From Gallery","Cancle"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setItems(Options, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int selection) {
                if(Options[selection].equals("Click Photo"))
                {
                    if(ContextCompat.checkSelfPermission(User_Profile.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                        CapturePhoto();
                    }
                    else{
                        if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                            Toast.makeText(getApplicationContext(), "Permission Needed.", Toast.LENGTH_LONG).show();
                        }
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Camera_Request);
                    }

                }
                else if(Options[selection].equals("Select From Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, Gallery_Request);

                }
                else if(Options[selection].equals("Cancle"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    public void  CapturePhoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, Camera_Request);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==Camera_Request)
            {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                Profile.setImageBitmap(bitmap);

            }
            else if(requestCode==Gallery_Request)
            {
                Uri selectedImage = data.getData();

                String[] filePath = { MediaStore.Images.Media.DATA };

                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                Log.w("image path ", picturePath+"");

                Profile.setImageBitmap(thumbnail);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == Camera_Request){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
             CapturePhoto();
            }
            else{
                Toast.makeText(getApplicationContext(), "Permission Needed.", Toast.LENGTH_LONG).show();
            }
        }
        else {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void update_profile()
    {
        Name=User_Name.getText().toString();
        Email=User_Email.getText().toString();
        Password=User_Password.getText().toString();
        Mobile=User_Mobile.getText().toString();
        Address=User_Address.getText().toString();
        Intent intent = getIntent();
       int id=intent.getExtras().getInt("User_id");


        Profile.setDrawingCacheEnabled(true);
        Profile.buildDrawingCache();
        Bitmap bitmap=Profile.getDrawingCache();
        byte[] data={};
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            data = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
        register_helper=new Register_Helper(this);
       boolean  result= register_helper.updateProfile(id,Name,Email,Password,Mobile,Address,data);
       if(result==true)
       {
           Toast.makeText(this, "Profile Updated Successfull", Toast.LENGTH_SHORT).show();
       }
       else
           Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
    }
//
    public void  View_Profiledata()
    {
        Intent intent = getIntent();
       int Uid=intent.getExtras().getInt("User_id");
        register_helper=new Register_Helper(getApplicationContext());
        sqLiteDatabase=register_helper.getReadableDatabase();
        Cursor result=register_helper.view_Profile(Uid,sqLiteDatabase);
        if(result.getCount()==0)
        {
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }
        else if(result.moveToFirst())
        {
            do{
                User_Name.setText(result.getString(1));
                User_Email.setText(result.getString(2));
                User_Password.setText(result.getString(3));
                User_Mobile.setText(result.getString(4));
                User_Address.setText(result.getString(6));
                byte[] image=result.getBlob(8);
                bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
                Profile.setImageBitmap(bitmap);
            }while (result.moveToNext());
        }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(User_Profile.this,Home.class);
        startActivity(i);
        super.onBackPressed();
    }
}
