package com.imad.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.imad.quiz.data.AnswerlistAsyncResponse;
import com.imad.quiz.data.QuestionBank;
import com.imad.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionText;
    private Button trueBtn , falseBtn ;
    private ImageButton preBtn , nextBtn;
    private TextView counterText , scoreText;
    private final static int TRUE_ANSWER_SCORE = 100;



    private int counter = 0;

    private  List<Question> questionList;

    ArrayList<Question> questionArrayList1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        questionText = findViewById(R.id.questionText);
        // get all variables

        trueBtn = findViewById(R.id.trueBtn);
        falseBtn = findViewById(R.id.falseBtn);
        preBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);
        counterText = findViewById(R.id.counterText);
        scoreText = findViewById(R.id.score_text);



       questionList = new QuestionBank().getQuestion(new AnswerlistAsyncResponse() {
            @Override
            public void processFinish(ArrayList<Question> questionArrayList) {
                questionArrayList1 = questionArrayList;
                questionText.setText(questionArrayList.get(0).getQuestion());

                // add listner to btns

                trueBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(questionArrayList.get(counter).getAnswerTrue())
                        {
                            //Toast.makeText(getApplicationContext(),"you are right", Toast.LENGTH_SHORT).show();

                            questionText.setBackgroundColor(getResources().getColor(R.color.trueColor));
                            scoreUpdate(true);
                        }
                        else
                        {
                            questionText.setBackgroundColor(getResources().getColor(R.color.falseColor));
                            scoreUpdate(false);
                        }



                           // Toast.makeText(getApplicationContext(),"you are wrong", Toast.LENGTH_SHORT).show();


                    }
                });

                falseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!questionArrayList.get(counter).getAnswerTrue())
                        {
                            questionText.setBackgroundColor(getResources().getColor(R.color.trueColor));
                            scoreUpdate(true);
                            //Toast.makeText(getApplicationContext(),"you are right", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            questionText.setBackgroundColor(getResources().getColor(R.color.falseColor));
                            scoreUpdate(false);
                        }
                            //Toast.makeText(getApplicationContext(),"you are wrong", Toast.LENGTH_SHORT).show();

                    }
                });

                preBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(counter != 0)
                        {
                            counter--;
                            questionText.setText(questionArrayList.get(counter).getQuestion());
                            questionText.setBackgroundColor(getResources().getColor(R.color.white));
                            counterText.setText(counter + " OF " + questionArrayList.size());
                        }
                        saveData();

                    }
                });

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(counter < questionArrayList.size())
                        {
                            counter++;
                            questionText.setText(questionArrayList.get(counter).getQuestion());
                            questionText.setBackgroundColor(getResources().getColor(R.color.white));
                            counterText.setText(counter + " OF " + questionArrayList.size());
                        }
                        saveData();

                    }
                });


            }
        });

        loadData();

    }


        private void saveData()
        {

            SharedPreferences sharedPreferences = getSharedPreferences("score", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("scoreCounter", counter);

            editor.apply();


        }


   private void loadData()
   {

       SharedPreferences getData = getSharedPreferences("score",MODE_PRIVATE);
       int count =  getData.getInt("scoreCounter",0);
       counter = count;

       Toast.makeText(getApplicationContext(),"data restored",Toast.LENGTH_SHORT).show();

   }


private void update(ArrayList<Question> questionArrayList)
{
    questionText.setText(questionArrayList.get(counter).getQuestion());
    counterText.setText(counter + " of " + questionArrayList.size());

}


private void scoreUpdate(Boolean answer)
{
    int score = Integer.parseInt(scoreText.getText().toString());



    if(answer)
    {
        score = score + TRUE_ANSWER_SCORE;
        Toast.makeText(getApplicationContext(),score,Toast.LENGTH_SHORT);
    }
    else{

        Toast.makeText(getApplicationContext(),score,Toast.LENGTH_SHORT);

    }


}






}