package com.example.feedbackcfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView tvFeedback;
    Button btnFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvFeedback=findViewById(R.id.tvFeedback);
        btnFeedback=findViewById(R.id.btnFeedback);

//        On click on the button "submit feedback", the user will be taken to the next page which displays the questions
        btnFeedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, Questions.class);
                startActivity(i);
                finish();
            }
        });

    }
}