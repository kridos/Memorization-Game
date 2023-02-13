package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSet extends AppCompatActivity implements View.OnClickListener {
    Button back, continueAdding;
    EditText first, second;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continueAdding:
                if(!first.getText().toString().isEmpty() && !second.getText().toString().isEmpty()){
                    Data.addToWords(first.getText().toString() + " - " + second.getText().toString());

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }

                break;

            case R.id.goBack:
                if(!first.getText().toString().isEmpty() && !second.getText().toString().isEmpty()) {
                    Data.addToWords(first.getText().toString() + " - " + second.getText().toString());
                }

                finish();
                break;

            default:
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);


        back = findViewById(R.id.goBack);
        back.setOnClickListener(this);

        continueAdding = findViewById(R.id.continueAdding);
        continueAdding.setOnClickListener(this);

        first = findViewById(R.id.firstAttributeInput);

        second = findViewById(R.id.secondAttributeInput);
    }


}