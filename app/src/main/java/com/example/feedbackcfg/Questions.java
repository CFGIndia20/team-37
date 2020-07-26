package com.example.feedbackcfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// Import Rating classes
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Questions extends AppCompatActivity {
    DatabaseReference mRootRef;
    FirebaseDatabase database;
    TextView tvQues1;
    TextView tvQues2;
    TextView tvQues3;
    TextView tvQues4;
    TextView tvQues5;
    TextView tvQues6;
    TextView tvQues7;
    TextView tvQues8;
    Button btnSubmit;
    int level1,level2,level3,level4,level5,level6,level7,level8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        final String TAG = "MyActivity";
        database= FirebaseDatabase.getInstance();
        mRootRef=database.getReference().child("feedbacks");
        tvQues1=findViewById(R.id.tvQues1);
        tvQues2=findViewById(R.id.tvQues2);
        tvQues3=findViewById(R.id.tvQues3);
        tvQues4=findViewById(R.id.tvQues4);
        tvQues5=findViewById(R.id.tvQues5);
        tvQues6=findViewById(R.id.tvQues6);
        tvQues7=findViewById(R.id.tvQues7);
        tvQues8=findViewById(R.id.tvQues8);
        btnSubmit=findViewById(R.id.btnSubmit);
        final SmileRating smileRating = findViewById(R.id.smile_rating);
        final SmileRating smileRating2 = findViewById(R.id.smile_rating);
        final SmileRating smileRating3= findViewById(R.id.smile_rating);
        final SmileRating smileRating4= findViewById(R.id.smile_rating);
        final SmileRating smileRating5= findViewById(R.id.smile_rating);
        final SmileRating smileRating6= findViewById(R.id.smile_rating);
        final SmileRating smileRating7= findViewById(R.id.smile_rating);
        final SmileRating smileRating8= findViewById(R.id.smile_rating);
        final SharedPreferences sharedPreferences = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        final SharedPreferences.Editor myEdit = sharedPreferences.edit();
        SharedPreferences sp = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);


/*
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Question q = dataSnapshot.getValue(Question.class);
                Log.i(TAG, q.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dynamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level1=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level1=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level1=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level1=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level1=5;
                        break;


                }
                myEdit.putInt("level1",level1);
                myEdit.commit();
            }
        });

        smileRating2.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dynamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level2=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level2=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level3=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level4=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level5=5;
                        break;
                }
                myEdit.putInt("level2",level2);
                myEdit.commit();
            }
        });

        smileRating3.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dynamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level3=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level3=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level3=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level3=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level3=5;
                        break;
                }
                myEdit.putInt("level3",level3);
                myEdit.commit();
            }
        });

        smileRating4.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dynamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level4=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level4=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level4=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level4=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level4=5;
                        break;
                }
                myEdit.putInt("level4",level4);
                myEdit.commit();
            }

        });

        smileRating5.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dynamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level5=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level5=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level5=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level5=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level5=5;
                        break;
                }
                myEdit.putInt("level5",level5);
                myEdit.commit();
            }
        });

        smileRating6.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dynamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level6=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level6=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level6=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level6=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level6=5;
                        break;
                }
                myEdit.putInt("level6",level6);
                myEdit.commit();
            }
        });

        smileRating7.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dynamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level7=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level7=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level7=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level7=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level7=5;
                        break;
                }
                myEdit.putInt("level7",level7);
                myEdit.commit();
            }
        });

        smileRating8.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                // Retrieve the value of the bar dinamically
                // level is from 1 to 5
                // Will return 0 if NONE selected
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        level8=1;
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        level8=2;
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        level8=3;
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        level8=4;
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        level8=5;
                        break;
                }

                myEdit.putInt("level8",level8);
                myEdit.commit();

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=20;


                Question q=new Question(1234,123,sharedPreferences.getInt("level1",1),sharedPreferences.getInt("level2",1),sharedPreferences.getInt("level3",1),sharedPreferences.getInt("level4",1),sharedPreferences.getInt("level5",1),sharedPreferences.getInt("level6",1),sharedPreferences.getInt("level7",1),sharedPreferences.getInt("level8",1));
                mRootRef.push().setValue(q);
                Intent i=new Intent(Questions.this,Thanks.class);
                startActivity(i);
            }
        });

    }
}