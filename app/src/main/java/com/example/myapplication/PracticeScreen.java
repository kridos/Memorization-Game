package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class PracticeScreen extends AppCompatActivity implements View.OnClickListener {



    private LinearLayout layout1, layout2;


    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()){


            case R.id.selectionMatching:
                intent = new Intent(this, Matching.class);
                startActivity(intent);
                break;

            case R.id.typingMatching:
                intent = new Intent(this, Learning.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_screen);

        layout1 = findViewById(R.id.typingMatching);
        layout1.setOnClickListener(this);

        layout2 = findViewById(R.id.selectionMatching);
        layout2.setOnClickListener(this);
    }


}