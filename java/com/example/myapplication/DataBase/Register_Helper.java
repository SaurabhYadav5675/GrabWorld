package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;

public class Register_Helper extends SQLiteOpenHelper {

    public  static  final String Database_name="Register.db";

    public  static  final String Table_name="Users";
    public  static  final String Id="Id";
    public  static  final String Name="Name";
    public  static  final String Email="Email";
    public  static  final String Password="Password";
    public  static  final String Mobile="Mobile";
    public  static  final String Gender="Gender";
    public  static  final String Address="Address";
    public  static  final String Pincode="Pincode";
    public  static  final String Profile="Profile";

   // public String name,email,password,mobile,gender,address,pincode;

    public Register_Helper(Context context) {
        super(context, Database_name, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Table_name+"("+Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Name+" TEXT,"+Email+" TEXT,"+Password+" TEXT,"+Mobile+" TEXT,"+Gender+" TEXT,"+Address+" TEXT,"+Pincode+" TEXT,"+Profile+" BLOB"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name );
        onCreate(db);

    }
    public boolean insert(String name,String email,String password,String mobile,String gender,String address,String pincode,String profile)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Name,name);
        cv.put(Email,email);
        cv.put(Password,password);
        cv.put(Mobile,mobile);
        cv.put(Gender,gender);
        cv.put(Address,address);
        cv.put(Pincode,pincode);
        cv.put(Profile,profile);
        long result=sqLiteDatabase.insertOrThrow(Table_name,null,cv);
        sqLiteDatabase.close();
        if(result==-1)
        return false;
        else
            return true;
    }

    public Cursor getdata()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String[] columns={Id,Name,Email,Password,Mobile,Gender,Address,Pincode,Profile};
//        String option=Email+" LIKE ?";
//        String[] value={email};
        Cursor cursor=sqLiteDatabase.query(Table_name,columns,null,null,null,null,null);
        return cursor;
    }

    public boolean updateProfile(int id,String name,String email,String password,String mobile,String address,byte[] profile)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Name,name);
        cv.put(Email,email);
        cv.put(Password,password);
        cv.put(Mobile,mobile);
        cv.put(Address,address);
        cv.put(Profile,profile);
        long result=sqLiteDatabase.update(Table_name,cv,Id+"="+id,null);
        if(result==-1)
        return false;
        else
            return true;
    }

    //retriving single data
    public Cursor view_Profile(int id, SQLiteDatabase sqLiteDatabase)
    {
       // SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String [] data={Id,Name,Email,Password,Mobile,Gender,Address,Pincode,Profile};
       String selection=Id+" LIKE ?";
        String[] value1={String.valueOf(id)};
        Cursor result=sqLiteDatabase.query(Table_name,data,selection,value1,null,null,null);
        Log.d("TAG", result.toString());
        return result;

    }

}
