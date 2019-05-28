package com.example.myapplication.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contact_Fragment extends Fragment implements View.OnClickListener {
    TextView Pcall,Pface,Vcall,Vface;


    public Contact_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_contact_, container, false);
        Pcall=view.findViewById(R.id.Parmesh_call);
        Pface=view.findViewById(R.id.Parmesh_fec);
        Vcall=view.findViewById(R.id.vikas_call);
        Vface=view.findViewById(R.id.vikas_fec);
        Pcall.setOnClickListener(this);
        Pface.setOnClickListener(this);
        Vcall.setOnClickListener(this);
        Vface.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Parmesh_call:
                String data="tel:"+"7738258007";
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(data));
                startActivity(intent);
                break;

            case R.id.Parmesh_fec:
                String facdata="https://www.facebook.com/paemesh.yadav.560";
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(facdata));
                startActivity(i);
                break;
            case R.id.vikas_call:
                String data1="tel:"+"7021610928";
                Intent intent1=new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse(data1));
                startActivity(intent1);
                break;
            case R.id.vikas_fec:
                String facdata1="https://www.facebook.com/profile.php?id=100008218092533";
                Intent i1=new Intent(Intent.ACTION_VIEW,Uri.parse(facdata1));
                startActivity(i1);
                break;
        }
    }
}
