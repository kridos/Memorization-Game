package com.example.myapplication;

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
}
