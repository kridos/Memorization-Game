package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

//TODO: remove duplicates
public class Matching extends AppCompatActivity implements View.OnClickListener {

    private TextView cardText;
    private Switch questionLanguage;
    private Button option1, option2, option3, option4, next, quit;
    private int correctAnswer = 0, whichOne, counter;
    private int[] randoms;

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.option1 || v.getId() == R.id.option2 || v.getId() == R.id.option3 || v.getId() == R.id.option4){
            if(correctAnswer == 0){
                option1.setBackgroundColor(getResources().getColor(R.color.green));
                option2.setBackgroundColor(getResources().getColor(R.color.red));
                option3.setBackgroundColor(getResources().getColor(R.color.red));
                option4.setBackgroundColor(getResources().getColor(R.color.red));

            }else if(correctAnswer == 1){
                option2.setBackgroundColor(getResources().getColor(R.color.green));
                option1.setBackgroundColor(getResources().getColor(R.color.red));
                option3.setBackgroundColor(getResources().getColor(R.color.red));
                option4.setBackgroundColor(getResources().getColor(R.color.red));

            }else if(correctAnswer == 2){
                option3.setBackgroundColor(getResources().getColor(R.color.green));
                option2.setBackgroundColor(getResources().getColor(R.color.red));
                option1.setBackgroundColor(getResources().getColor(R.color.red));
                option4.setBackgroundColor(getResources().getColor(R.color.red));

            }else{
                option4.setBackgroundColor(getResources().getColor(R.color.green));
                option2.setBackgroundColor(getResources().getColor(R.color.red));
                option3.setBackgroundColor(getResources().getColor(R.color.red));
                option1.setBackgroundColor(getResources().getColor(R.color.red));

            }

            next.setVisibility(View.VISIBLE);
            quit.setVisibility(View.VISIBLE);

        }else if(v.getId() == R.id.next){
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }else if(v.getId() == R.id.quit){
            finish();
        }else if(v.getId() == R.id.switch1){
            Data.setSwitchChecked(questionLanguage.isChecked());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        //Views



        cardText = findViewById(R.id.asking);

        questionLanguage = findViewById(R.id.switch1);
        questionLanguage.setChecked(Data.getSwitchChecked());
        questionLanguage.setOnClickListener(this);


        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        next = findViewById(R.id.next);
        quit = findViewById(R.id.quit);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);


        next.setVisibility(View.INVISIBLE);
        quit.setVisibility(View.INVISIBLE);

        next.setOnClickListener(this);
        quit.setOnClickListener(this);

        randoms = new int[4];

        for(int i = 0; i < 4; i++){
            randoms[i] = (int)((Math.random() * Data.getWords().size()));


        }

        while(randoms[0] == randoms[1] || randoms[0] == randoms[2] || randoms[0] == randoms[3]){
            randoms[0] = (int)((Math.random() * Data.getWords().size()));
        }

        while(randoms[1] == randoms[2] || randoms[1] == randoms[3] || randoms[1] == randoms[0]){
            randoms[1] = (int)((Math.random() * Data.getWords().size()));
        }

        while(randoms[2] == randoms[3] || randoms[2] == randoms[1] || randoms[2] == randoms[0]){
            randoms[2] = (int)((Math.random() * Data.getWords().size()));
        }

        correctAnswer = (int)(Math.random() * 4);
        counter = 0;

        populationLogic();




    }


    private void populationLogic(){


        if(questionLanguage.isChecked()) {
            cardText.setText(Data.getWords().get(randoms[0]).substring(Data.getWords().get(randoms[0]).indexOf("-") + 2));

            switch(correctAnswer){
                case 0:
                    option1.setText(Data.getWords().get(randoms[0]).substring(0, Data.getWords().get(randoms[0]).indexOf("-") - 1));
                    break;
                case 1:
                    option2.setText(Data.getWords().get(randoms[0]).substring(0, Data.getWords().get(randoms[0]).indexOf("-") - 1));
                    break;
                case 2:
                    option3.setText(Data.getWords().get(randoms[0]).substring(0, Data.getWords().get(randoms[0]).indexOf("-") - 1));
                    break;
                default:
                    option4.setText(Data.getWords().get(randoms[0]).substring(0, Data.getWords().get(randoms[0]).indexOf("-") - 1));
                    break;
            }

            randoms[0] = -1;

            for(int i = 0; i < 4; i++){
                if(i != correctAnswer){
                    if(counter == 0){
                        //Have to figure out which one was picked
                        whichOne = (int)((Math.random() * 3) + 1);



                    }else if(counter == 1){
                        boolean greaterOne = ((int)((Math.random() * 100) + 1)) > 50;
                        if(greaterOne){
                            for(int j = 3; j >= 1; j--){
                                if(randoms[j] != -1){
                                    whichOne = j;
                                    break;

                                }
                            }

                        }else{
                            for(int j = 1; j < 4; j++){
                                if(randoms[j] != -1){
                                    whichOne = j;
                                    break;
                                }
                            }
                        }





                    }else{
                        for(int j = 1; j < 4; j++){
                            if(randoms[j] != -1){
                                whichOne = j;
                                break;
                            }

                        }


                    }


                    if(i == 0){
                        option1.setText(Data.getWords().get(randoms[whichOne]).substring(0, Data.getWords().get(randoms[whichOne]).indexOf("-") - 1));

                    }else if(i == 1){
                        option2.setText(Data.getWords().get(randoms[whichOne]).substring(0, Data.getWords().get(randoms[whichOne]).indexOf("-") - 1));

                    }else if(i == 2){
                        option3.setText(Data.getWords().get(randoms[whichOne]).substring(0, Data.getWords().get(randoms[whichOne]).indexOf("-") - 1));

                    }else{
                        option4.setText(Data.getWords().get(randoms[whichOne]).substring(0, Data.getWords().get(randoms[whichOne]).indexOf("-") - 1));

                    }

                    randoms[whichOne] = -1;

                    counter++;

                }

            }

        }else{
            cardText.setText(Data.getWords().get(randoms[0]).substring(0, Data.getWords().get(randoms[0]).indexOf("-") - 1));
            switch(correctAnswer){
                case 0:
                    option1.setText(Data.getWords().get(randoms[0]).substring(Data.getWords().get(randoms[0]).indexOf("-") + 2));
                    break;
                case 1:
                    option2.setText(Data.getWords().get(randoms[0]).substring(Data.getWords().get(randoms[0]).indexOf("-") + 2));
                    break;
                case 2:
                    option3.setText(Data.getWords().get(randoms[0]).substring(Data.getWords().get(randoms[0]).indexOf("-") + 2));
                    break;
                default:
                    option4.setText(Data.getWords().get(randoms[0]).substring(Data.getWords().get(randoms[0]).indexOf("-") + 2));
                    break;
            }

            randoms[0] = -1;

            for(int i = 0; i < 4; i++){
                if(i != correctAnswer){
                    if(counter == 0){
                        //Have to figure out which one was picked
                        whichOne = (int)((Math.random() * 3) + 1);




                    }else if(counter == 1){
                        boolean greaterOne = ((int)((Math.random() * 100) + 1)) > 50;
                        if(greaterOne){
                            for(int j = 3; j >= 1; j--){
                                if(randoms[j] != -1){
                                    whichOne = j;
                                    break;

                                }
                            }

                        }else{
                            for(int j = 1; j < 4; j++){
                                if(randoms[j] != -1){
                                    whichOne = j;
                                    break;

                                }
                            }
                        }



                    }else{
                        for(int j = 1; j < 4; j++){
                            if(randoms[j] != -1){
                                whichOne = j;
                                break;

                            }
                        }


                    }


                    if(i == 0){
                        option1.setText(Data.getWords().get(randoms[whichOne]).substring(Data.getWords().get(randoms[whichOne]).indexOf("-") + 2));

                    }else if(i == 1){
                        option2.setText(Data.getWords().get(randoms[whichOne]).substring(Data.getWords().get(randoms[whichOne]).indexOf("-") + 2));

                    }else if(i == 2){
                        option3.setText(Data.getWords().get(randoms[whichOne]).substring(Data.getWords().get(randoms[whichOne]).indexOf("-") + 2));

                    }else{
                        option4.setText(Data.getWords().get(randoms[whichOne]).substring(Data.getWords().get(randoms[whichOne]).indexOf("-") + 2));

                    }

                    randoms[whichOne] = -1;
                    counter++;

                }

            }
        }
    }
}