package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.DataBase.Register_Helper;

import java.util.Calendar;

import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    DatePickerDialog.OnDateSetListener mydilog;
    EditText name,surname,email,password,confirm,mobile,dob,address,pin;
    Button Save,Clear;
    RadioButton male,female;
    String Reg_Name,Name,Surname,Email,Password,Confirm,Mobile,Dob,Gender="Male",Address,Pin;
    CheckBox show;
    Register_Helper register_helper;
    String  Profile="Null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Edit Text
        name=findViewById(R.id.Rname);
        surname=findViewById(R.id.Rsurname);
        email=findViewById(R.id.Remail);
        password=findViewById(R.id.Rpassword);
        confirm=findViewById(R.id.Rconfirm);
        mobile=findViewById(R.id.Rmobile);
        dob=findViewById(R.id.RDOB);
        address=findViewById(R.id.Raddress);
        pin=findViewById(R.id.Rpincode);

        //Radio Button
        male=findViewById(R.id.Rmale);
        female=findViewById(R.id.Rfemale);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        Save=findViewById(R.id.Save);
        Clear=findViewById(R.id.Clear);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });
        Clear.setOnClickListener(this);

        // Show Check Box
        show=findViewById(R.id.ShowP);
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                if(check)
                {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                   show.setButtonDrawable(R.drawable.hide_password);
                }
                else
                {
                    //need to import android.text.InputType
                    password.setInputType(InputType.TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    show.setButtonDrawable(R.drawable.show_password);
                }
            }
        });


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dailog=new DatePickerDialog(Registration.this,mydilog,year,month,day);
                dailog.show();
            }
        });
        mydilog =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                String yr=Integer.toString(year);
                String mn=Integer.toString(month+1);
                String dy=Integer.toString(day);
                String date=dy+"/"+mn+"/"+yr;
                dob.setText(date);

            }
        };
    }

    @Override
    public void onClick(View view)
    {
        boolean check=((RadioButton) view).isChecked();
       switch (view.getId())
       {
           case R.id.Rmale:
               if(check)
               {

                 //  Gender=male.getText().toString().trim();
                   Toast.makeText(this, "Clicked is "+Gender, Toast.LENGTH_SHORT).show();
               }
               else
               { Gender="Male";}
           case R.id.Rfemale:
               if(check)
               {
                  // Gender=female.getText().toString().trim();
                   Toast.makeText(this, "Clicked is "+Gender, Toast.LENGTH_SHORT).show();
               }
               else
               { Gender="Male";}
       }
    }

    public void Validation()
    {

        Name=name.getText().toString();
        Surname=surname.getText().toString();
        Email=email.getText().toString();
        Password=password.getText().toString();
        Confirm=confirm.getText().toString();
        Mobile=mobile.getText().toString();
        Dob=dob.getText().toString();
        Address=address.getText().toString();
        Pin=pin.getText().toString();
        String Pass="(?i).{8,10}";
        String Mob="[0-9]{10}";

        if (Name.equals(""))
        { ShowToast("Enter Name",270);}

        else if(Surname.equals(""))
        {ShowToast("Enter Surname",340); }

        else if(Email.equals(""))
        {ShowToast("Enter Email_ID",340);}

        else if(!Email.matches("(?i).*@.*.com"))
        {ShowToast("Please Follow the format",340);}

        else if(Password.equals(""))
        { ShowToast("Enter Password",340);}

        else if(!Password.matches(Pass))
        {ShowToast("Minimum 8 character",300);}

        else if(!Password.matches(Confirm))
        {ShowToast("Both Password must be same",340);}

        else if(!Mobile.matches(Mob))
        {
            ShowToast("10 Degit Number",200);
        }
        else if(Dob=="")
        {ShowToast("Enter Date Of Birth ",340);}

        else if (Address.equals(""))
        {ShowToast("Enter Address",340);}

        else if (!Pin.matches("[0-9]{6}"))
        { ShowToast("Enter Pincode",340);}

        else{ Registration_Process();}

    }

    public void ShowToast(String msg,int Position)
    {
        Toast toast=Toast.makeText(this, "Please "+msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,Position);
        toast.show();

    }

    public void   Registration_Process()
    {
        Reg_Name=Name+Surname;

        register_helper=new Register_Helper(this);
        boolean result=register_helper.insert(Reg_Name,Email,Password,Mobile, Gender,Address,Pin,Profile);
        try {

            if (result = true)
            {
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
            else {
                Toast.makeText(this, "Error Founded", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e )
        {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

    }
}
