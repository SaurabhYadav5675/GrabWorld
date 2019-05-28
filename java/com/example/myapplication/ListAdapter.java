package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Base64;

public class ListAdapter extends ArrayAdapter<String> {

    private  ArrayList<String> name;
    private ArrayList<String> email;
   private ArrayList<byte[]> picture;
   byte[] data;
    Context context;
    public ListAdapter(Context context, ArrayList<String> name,ArrayList<String> email,ArrayList<byte[]> picture) {
        super(context, R.layout.product_format,name);
        this.name = name;
       this.email=email;
      this.picture=picture;
        this.context=context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder=null;
        if(view==null)
        {
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            view=layoutInflater.inflate(R.layout.product_format,null,true);
            viewHolder=new ViewHolder(view);
           view.setTag(viewHolder);
        }
        else {viewHolder=(ViewHolder)view.getTag(); }

        viewHolder.Pname.setText(name.get(position));
        viewHolder.Email.setText(email.get(position));
        data=picture.get(position);
      Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
        viewHolder.Profile.setImageBitmap(bitmap);
        return view;
    }
    public class ViewHolder
    {
        TextView Pname,Email;
     CircularImageView Profile;
        public ViewHolder(View view)
        {
            Pname=view.findViewById(R.id.Product_name);
            Email=view.findViewById(R.id.Product_dis);
            Profile=view.findViewById(R.id.Product_Pic);
        }

    }
}
