package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Button practice, add, display;
    private InputStream inputStream;
    private BufferedReader reader;

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                intent = new Intent(this, Matching.class);
                startActivity(intent);
                break;

            case R.id.button2:
                intent = new Intent(this, AddSet.class);
                startActivity(intent);
                Data.clearWords();
                break;

            case R.id.button3:
                intent = new Intent(this, DisplaySet.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        practice = findViewById(R.id.button);
        practice.setOnClickListener(this);

        add = findViewById(R.id.button2);
        add.setOnClickListener(this);

        display = findViewById(R.id.button3);
        display.setOnClickListener(this);

        Data.setWords(new ArrayList<>());
        Data.setSwitchChecked(true);



        try {
            inputStream = getAssets().open("words.txt");


            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    Data.getWords().add(line);
                }
            }

            inputStream.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}