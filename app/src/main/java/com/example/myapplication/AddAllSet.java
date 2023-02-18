package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAllSet extends AppCompatActivity implements View.OnClickListener {

    private Button save, back;
    private EditText input;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                finish();
                break;
            case R.id.save:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_all_set);

        save = findViewById(R.id.save);
        save.setOnClickListener(this);

        back = findViewById(R.id.home);
        back.setOnClickListener(this);

        input = findViewById(R.id.hugeLoader);
        input.setText(Data.writingString());
    }


}