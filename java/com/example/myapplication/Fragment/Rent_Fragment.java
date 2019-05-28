package com.example.myapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Rent_Camera;
import com.example.myapplication.Rent_Car;
import com.example.myapplication.Rent_House;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rent_Fragment extends Fragment implements View.OnClickListener {
    ImageView Car,House,Camera;


    public Rent_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_rent_, container, false);
       Car=view.findViewById(R.id.Img_house);
       Car.setOnClickListener(this);
       House=view.findViewById(R.id.Img_Carrent);
       House.setOnClickListener(this);
       Camera=view.findViewById(R.id.Img__Camerarent);
       Camera.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Img_house:
                Toast.makeText(getActivity(), "rent click", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Rent_House.class));
                //getFragmentManager().beginTransaction().replace(R.id.Home_frame,new Rent_Fragment()).commit();
                break;
            case R.id.Img_Carrent:
                startActivity(new Intent(getActivity(), Rent_Car.class));
                break;
            case R.id.Img__Camerarent:
                startActivity(new Intent(getActivity(), Rent_Camera.class));
                break;
        }

    }
}
