package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {


    //Objects

    InputStream inputStream;
    BufferedReader reader;
    double startTime = System.currentTimeMillis();


    public void onClick(View v) {
        Intent intent = new Intent(this, Learning.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data.setWords(new ArrayList<>());
        Data.setSwitchChecked(true);


        //Other stuff
        //file = new File("words.txt");
        try {
            inputStream = getAssets().open("words.txt");


            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    Data.getWords().add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}