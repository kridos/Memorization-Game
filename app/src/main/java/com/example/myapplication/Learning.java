package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

//TODO: figure out how to account for spaces at the beginning or end of typed answer
public class Learning extends AppCompatActivity implements View.OnClickListener {
    private TextView answer, cardText;
    private Switch questionLanguage;
    private Button nextButton, quit;
    private View card;
    private EditText input;
    private int buttonCounter = 0;
    private String spanishWord, english;

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.answer:
                Toast.makeText(this, "Easter Egg", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button:
                if(buttonCounter == 0) {
                    answer.setVisibility(View.VISIBLE);

                    if(Data.getSwitchChecked()){
                        if(input.getText().toString().equalsIgnoreCase(spanishWord)){
                            card.setBackgroundColor(getResources().getColor(R.color.green));
                        }else{
                            card.setBackgroundColor(getResources().getColor(R.color.red));
                        }
                    }else{
                        if(input.getText().toString().equalsIgnoreCase(english)){
                            card.setBackgroundColor(getResources().getColor(R.color.green));
                        }else{
                            card.setBackgroundColor(getResources().getColor(R.color.red));
                        }
                    }
                    buttonCounter++;
                }else{
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }

                break;

            case R.id.switch1:
                Data.setSwitchChecked(questionLanguage.isChecked());
                break;

            case R.id.quitButton:
                finish();
                break;

            default:
                break;


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        //Views
        answer = findViewById(R.id.answer);
        answer.setVisibility(View.INVISIBLE);
        answer.setOnClickListener(this);

        quit = findViewById(R.id.quitButton);
        quit.setOnClickListener(this);

        nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(this);

        cardText = findViewById(R.id.asking);

        questionLanguage = findViewById(R.id.switch1);
        questionLanguage.setChecked(Data.getSwitchChecked());
        questionLanguage.setOnClickListener(this);

        card = findViewById(R.id.card);

        input = findViewById(R.id.input);

        int random = (int)((Math.random() * Data.getWords().size()));
        System.out.println(random);
        String chosen = Data.getWords().get(random);
        System.out.println("This is the word" + chosen);
        spanishWord = chosen.substring(0, chosen.indexOf("-") - 1);
        english = chosen.substring(chosen.indexOf("-") + 2);

        if(questionLanguage.isChecked()) {
            cardText.setText(english);
            answer.setText("Answer: \"" + spanishWord + "\"");
        }else{
            cardText.setText(spanishWord);
            answer.setText("Answer: \"" + english + "\"");
        }
    }
}