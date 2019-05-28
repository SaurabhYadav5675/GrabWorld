package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.Register_Helper;

import static android.text.InputType.*;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    TextView Forgot;
    Button Login, Sign;
    CheckBox show;
    String name, password;
    ImageView reg_icon;
    int User_id;
    Register_Helper register_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.User);
        Forgot = findViewById(R.id.Forgot);
        Forgot.setOnClickListener(this);
        pass = findViewById(R.id.Password);
        Login = findViewById(R.id.Login);
        Sign = findViewById(R.id.Signup);
        Login.setOnClickListener(this);
        Sign.setOnClickListener(this);
        show = findViewById(R.id.Showpass);
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean Checked) {
                if (Checked) {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //show.setText("Hide");
                    show.setButtonDrawable(R.drawable.hide_password);
                } else {
                    //need to import android.text.InputType
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    //show.setText("Show");
                    show.setButtonDrawable(R.drawable.show_password);
                }

            }
        });
        reg_icon=findViewById(R.id.Reg_icon);
        reg_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),List_Example.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        name = user.getText().toString();
        password = pass.getText().toString();
        switch (view.getId())
        {
            case R.id.Login:
               if (name.equals(""))
               {
                   dailogfun("Enter Name");
               }

//               else if(!name.matches("(?i).*@.*.com"))
//               {
//                   dailogfun("Please Write in Proper Format");
//               }
                else if(password.equals(""))
                {
                    dailogfun("Enter Password");
                }
               else
                   {
                       validation();
                   }

                break;
            case R.id.Signup:
                Intent i = new Intent(getApplicationContext(), Registration.class);
                startActivity(i);
                break;
            case R.id.Forgot:
                Intent intent = new Intent(getApplicationContext(), Forgot_Password.class);
                startActivity(intent);
                break;
        }

    }

    public void dailogfun(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//          builder.setTitle("Information");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public  void validation()
    {
        String User_name="";
        register_helper=new Register_Helper(this);
        Cursor result=register_helper.getdata();
        if(result.getCount()==0)
        {
            Toast.makeText(this, "No data Available", Toast.LENGTH_LONG).show();
        }
        else
        {
            result.moveToFirst();
            boolean login=false;
            do {

                if(name.matches(result.getString(2))&& (password.matches(result.getString(3))))
                {
                    login=true;
                    User_id = result.getInt(0);
                    User_name=result.getString(1);
                }

                }while (result.moveToNext());
            if(login==true)
            {
                Toast.makeText(this, "Welcome to grab World Mr. " + User_name, Toast.LENGTH_LONG).show();
                Intent i=new Intent(Login.this,Home.class);
                i.putExtra("Key",User_id);
                startActivity(i);

            }
            else
            {
                Toast.makeText(this, "Invalid Details... Try Again", Toast.LENGTH_LONG).show();
            }
            }
        }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}




