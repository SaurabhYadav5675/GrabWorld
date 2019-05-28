package com.example.myapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener {
    EditText Email,otp1,otp2,otp3,otp4;
    Button Submit,Resend,Dailog_Submit;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        Email = findViewById(R.id.Forgot_Email);
        Submit = findViewById(R.id.Forgot_submit);
        //  Submit.setOnClickListener(this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertFunc();
            }
        });
    }

    public void alertFunc(){
        builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view1 = layoutInflater.inflate(R.layout.dailog_otp, null);
        builder.setView(view1);
        alertDialog = builder.create();
        alertDialog.show();
        //Binding
        otp1=view1.findViewById(R.id.Otp1);
        otp2=view1.findViewById(R.id.Otp2);
        otp3=view1.findViewById(R.id.Otp3);
        otp4=view1.findViewById(R.id.Otp4);
        Resend=view1.findViewById(R.id.Otp_Resend);
        Resend.setOnClickListener(this);
        Dailog_Submit=view1.findViewById(R.id.Otp_Submit);
        Dailog_Submit.setOnClickListener(this);
            ChangeTextFocus();
//       //        otp1.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if(otp1.getText().length()==1)
//                {
//                    otp2.requestFocus();
//                }
//                return true;
//            }
//        });
  }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.Otp_Resend:
            //    Toast.makeText(this, "Request to Resend", Toast.LENGTH_SHORT).show();
                otp1.setText("");
                otp2.setText("");
                otp3.setText("");
                otp4.setText("");
                break;
        }
    }

                //code Book For Changing The Focus Of Edit Text After Click
    public  void ChangeTextFocus()
    {
        otp1.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(otp1.getText().length()==1)
                    otp2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Second Edit Text
        otp2.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(otp2.getText().length()==1)
                    otp3.requestFocus();
                else if(otp2.getText().length()==0)
                    otp1.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Third Edit Text
        otp3.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(otp3.getText().length()==1)
                    otp4.requestFocus();
                else if(otp3.getText().length()==0)
                    otp2.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(otp4.getText().length()==1)
                    otp4.requestFocus();
                else if(otp4.getText().length()==0)
                    otp3.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
