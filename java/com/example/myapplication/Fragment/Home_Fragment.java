package com.example.myapplication.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.myapplication.Pro_Bag;
import com.example.myapplication.Pro_HPEliteBook;
import com.example.myapplication.Pro_Mobile;
import com.example.myapplication.Pro_camera;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment implements View.OnClickListener {
    int imageid[] = {R.drawable.grab1, R.drawable.phones_back};
    ViewFlipper viewFlipper;
    Button Sell;
    ImageView Camera, Mobile, Bag, Laptop, Car, Calculator;
    CardView Card_Camera, Card_Mobile, Card_Bag, Card_Laptop, Card_Car, Card_Calculator;


    public Home_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        viewFlipper = view.findViewById(R.id.Home_View);
        Camera = view.findViewById(R.id.Img_camera);
        Camera.setOnClickListener(this);
        Mobile = view.findViewById(R.id.Img_mobile);
        Mobile.setOnClickListener(this);
        Bag = view.findViewById(R.id.Img_bag);
        Bag.setOnClickListener(this);
        Laptop = view.findViewById(R.id.Img_laptop);
        Laptop.setOnClickListener(this);
        Car = view.findViewById(R.id.Img_car);
        Calculator = view.findViewById(R.id.Img_calculator);
        Card_Camera=view.findViewById(R.id.Card_camera);
        Card_Mobile=view.findViewById(R.id.Card_mobile);
        Card_Bag=view.findViewById(R.id.Card_bag);
        Card_Laptop=view.findViewById(R.id.Card_laptop);
        Card_Car=view.findViewById(R.id.Card_car);
        Card_Calculator=view.findViewById(R.id.Card_calculator);
        Sell = view.findViewById(R.id.Home_Sell);
        // Sell.setOnClickListener();

        // flipper(1);
        for (int i = 0; i < imageid.length; i++) {
            flipper(imageid[i]);
        }
        return view;
    }

    public void flipper(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Img_camera:
                startActivity(new Intent(getActivity(), Pro_camera.class));
                break;
            case R.id.Img_mobile:
                startActivity(new Intent(getActivity(), Pro_Mobile.class));
                break;
            case R.id.Img_bag:
                startActivity(new Intent(getActivity(), Pro_Bag.class));
                break;
            case R.id.Img_laptop:
                startActivity(new Intent(getActivity(), Pro_HPEliteBook.class));
                break;
        }

    }
}
