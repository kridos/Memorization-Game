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

    public static void setWords(ArrayList<String> newWords){
        words = newWords;
    }

    public static ArrayList<String> getWords(){
        return words;
    }

    private static boolean switchChecked;

    public static boolean getSwitchChecked() {
        return switchChecked;
    }

    public static void setSwitchChecked(boolean switchChecked) {
        Data.switchChecked = switchChecked;
    }
}
