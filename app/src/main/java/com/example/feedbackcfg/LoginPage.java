package com.example.feedbackcfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    Button signinbtn;
    ConstraintLayout loginForm;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        signinbtn=(Button)findViewById(R.id.signinbtn);
        final EditText usernametxt=(EditText)findViewById(R.id.usernametxt);
        final EditText passwordtxt=(EditText)findViewById(R.id.passwordtxt);

        SharedPreferences preferences=getSharedPreferences("MYPREFS",MODE_PRIVATE);



        String uname1 = preferences.getString("username","");
        if(uname1!=""){
            Intent nextScreen=new Intent(LoginPage.this,LanguagePreference.class);
            startActivity(nextScreen);
        }
        else{
            loginForm=findViewById(R.id.loginForm);
            loginForm.setVisibility(View.VISIBLE);
        }
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    SharedPreferences preferences=getSharedPreferences("MYPREFS",MODE_PRIVATE);
                    String user=usernametxt.getText().toString();
                    String password=passwordtxt.getText().toString();

                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("username",user);
                    editor.commit();

                    Intent nextScreen=new Intent(LoginPage.this,LanguagePreference.class);
                    startActivity(nextScreen);


            }
        });
    }
}