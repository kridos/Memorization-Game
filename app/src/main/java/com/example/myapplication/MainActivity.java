package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    //private Button practiceMatch, practiceInput, add, display, save;
    private CardView practice, createNew, add, display;
    private InputStream inputStream;
    private BufferedReader reader;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if(Data.getWords().size() > 3) {
                    intent = new Intent(this, Matching.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Not enough items in your set", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.practice:
                //Change this to a new activity with both learning methods
                intent = new Intent(this, PracticeScreen.class);
                startActivity(intent);
                break;

            case R.id.add:
                //Change this to a new activity with both adding types
                intent = new Intent(this, AddAllSet.class);
                startActivity(intent);
                //Data.clearWords();
                break;

            case R.id.display:
                intent = new Intent(this, DisplaySet.class);
                startActivity(intent);
                break;

            case R.id.create:
                intent = new Intent(this, Save.class);
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


        /*practiceMatch = findViewById(R.id.button);
        practiceMatch.setOnClickListener(this);

        practiceInput = findViewById(R.id.button1);
        practiceInput.setOnClickListener(this);

        add = findViewById(R.id.button2);
        add.setOnClickListener(this);

        display = findViewById(R.id.button3);
        display.setOnClickListener(this);

        save = findViewById(R.id.save);
        save.setOnClickListener(this);*/

        //Go to separate page to select type of practice
        practice = findViewById(R.id.practice);
        practice.setOnClickListener(this);

        createNew = findViewById(R.id.create);
        createNew.setOnClickListener(this);

        //Go to separate page to select type of adding
        add = findViewById(R.id.add);
        add.setOnClickListener(this);

        display = findViewById(R.id.display);
        display.setOnClickListener(this);

        Data.setWords(new ArrayList<>());
        Data.setSwitchChecked(true);


        try {
            inputStream = getAssets().open("example.txt");


            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    Data.getWords().add(line);
                }
            }



            inputStream.close();
            reader.close();

            //See if there is a better way to do this
            File file = new File(getFilesDir() + "/NameSet.txt");
            if(!file.exists()) {
                openFileOutput("NameSet.txt", MODE_PRIVATE).close();

            }
            String temp = Data.read("NameSet", this);

            if(!temp.contains("example")) {
                Data.save("NameSet", "Current: example\n", this);
                Data.save("example", Data.writingString(), this);
            }

            System.out.println(Data.read("NameSet", this));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}