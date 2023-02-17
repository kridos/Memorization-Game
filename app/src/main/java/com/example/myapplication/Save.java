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
import java.util.Scanner;

//TODO: figure out how saving and loading works
public class Save extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private EditText input;
    private Button saveButton;
    private ListView list;
    private TextView text;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveButton:

                String temp = Data.read("NameSet",  this);

                if(!input.getText().toString().isEmpty() && !temp.contains(input.getText().toString())) {
                    //Checking if the input is empty



                    Data.save(input.getText().toString(), Data.writingString(), this);
                    Data.save("NameSet", temp + input.getText().toString() + "\n", this);




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
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.list:

                String item = parent.getItemAtPosition(position).toString();
                System.out.println("item " + item);

                if(!text.getText().toString().contains(item)){
                    File file = new File(getFilesDir(), item + ".txt");
                    System.out.println(file.delete());

                    Data.save("NameSet", remove(Data.read("NameSet", this), item), this);

                    System.out.println("stuff: " + Data.read("NameSet", this));


                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);


                }else{
                    Toast.makeText(this, "Please select a new set before trying to delete the current set", Toast.LENGTH_SHORT).show();
                }




                break;
        }

        return true;

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
        list.setOnItemLongClickListener(this);

    }



    private String remove(String given, String remove){
        String tempString = "";
        String returnString = "";
        System.out.println("Given: " + given);

        Scanner scan = new Scanner(given);

        while(scan.hasNextLine()){
            tempString = scan.nextLine();
            if(!tempString.equals(remove)){
                returnString += tempString + "\n";
            }
        }


        return returnString;

    }



}