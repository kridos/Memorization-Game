package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Data {
    public static Data instance;



    public static Data getInstance(String name){
        if(null == instance){
            return new Data();
        }

        return instance;
    }


    private static ArrayList<String> words;

    /**
     * Sets the static ArrayList words to the ArrayList passed into method
     * @param newWords
     */
    public static void setWords(ArrayList<String> newWords){
        words = newWords;
    }

    /**
     * Returns the static ArrayList words
     * @return
     */
    public static ArrayList<String> getWords(){
        return words;
    }

    /**
     * Clears the static ArrayList words
     */
    public static void clearWords(){
        words.clear();
    }

    /**
     * Adds to static ArrayList words using given String
     * @param adding
     */
    public static void addToWords(String adding){
        words.add(adding);
    }

    private static boolean switchChecked;

    public static boolean getSwitchChecked() {
        return switchChecked;
    }

    public static void setSwitchChecked(boolean switchChecked) {
        Data.switchChecked = switchChecked;
    }

    /**
     * Converts ArrayList into long string for putting in file
     *
     */
    public static String writingString(){
        String returnString = "";

        for(int i = 0; i < words.size(); i++){

            returnString += words.get(i);

            if(i != words.size() - 1){
                returnString += "\n";
            }
        }

        return returnString;
    }


    public  static void save(String filename, String toSave, Context context)  {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename + ".txt", MODE_PRIVATE);
            fileOutputStream.write(toSave.getBytes());

            Toast.makeText(context, "Saved " + filename + ".txt", Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(String fileName, Context context){

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");
            }


            return stringBuffer.toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> readForList(String fileName, Context context){
        try {
            ArrayList<String> returnList = new ArrayList<>();
            FileInputStream fileInputStream = context.openFileInput(fileName + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while((lines = bufferedReader.readLine()) != null){
                returnList.add(lines);
            }


            return returnList;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
