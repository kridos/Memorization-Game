package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Scanner;

public class AddAllSet extends AppCompatActivity implements View.OnClickListener {

    private Button save, back;
    private EditText input;
    private String current;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                finish();
                break;
            case R.id.save:

                Data.save(current, input.getText().toString(), this);
                Data.setWords(Data.readForList(current, this));
                Toast.makeText(this, "saved to " + current, Toast.LENGTH_SHORT).show();
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
        current = findCurrent();
        input.setText(Data.read(current, this));
    }

    public String findCurrent(){

        String given = Data.read("NameSet", this);

        String tempString = "";

        Scanner scan = new Scanner(given);

        while(scan.hasNextLine()){
            tempString = scan.nextLine();
            if(tempString.contains("Current:")){
                Scanner newScan = new Scanner(tempString);
                newScan.next();

                return newScan.next();
            }
        }


        return "";
    }


}