package com.example.feedbackcfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LanguagePreference extends AppCompatActivity {
    Button btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_preference);
        btnlogout=(Button)findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences preferences=getSharedPreferences("MYPREFS",MODE_PRIVATE);

                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("username","");
                editor.commit();

                Intent nextScreen=new Intent(LanguagePreference.this,LoginPage.class);
                startActivity(nextScreen);


            }
        });
    }
}