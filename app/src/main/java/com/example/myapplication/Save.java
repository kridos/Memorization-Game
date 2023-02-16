package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;

//TODO: figure out how saving and loading works
public class Save extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText input;
    private Button saveButton;
    private ListView list;
    private TextView text;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveButton:

                if(!input.getText().toString().isEmpty()) {
                    //Checking if the input is empty

                    String temp = Data.read("NameSet",  this);

                    Data.save(input.getText().toString(), Data.writingString(), this);
                    Data.save("NameSet", temp + input.getText().toString() + "\n", this);
                    System.out.println(input.getText().toString() + " is Here");




                    input.setText("");


                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
                break;


            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.list:
                String item = parent.getItemAtPosition(position).toString();
                Data.setWords(Data.readForList(item, this));
                text.setText("Current Set: " + item);



                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        input = findViewById(R.id.input);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        text = findViewById(R.id.currentSet);

        list = findViewById(R.id.list);

        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.readForList("NameSet", this)));

        list.setOnItemClickListener(this);

    }



}