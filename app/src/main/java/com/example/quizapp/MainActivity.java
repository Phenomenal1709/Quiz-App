package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionTV,questionNumberTV;
    private Button option1Btn,option2Btn,option3Btn,option4Btn;
    private ArrayList<QuizModal> quizModalArrayList;  //For storing questions & answers
    Random random;  //For accessing data from arrayList
    int currentScore=0,questionAttempted=0,currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);

        quizModalArrayList = new ArrayList<>();
        random = new Random();

        getQuizQuestion(quizModalArrayList);
        currentPos = random.nextInt(quizModalArrayList.size());
        setDataToViews(currentPos);

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }

                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);

            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }

                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);

            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }

                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }

                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
    }

        private void showBottomSheet() {

            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.idLLScore));
            TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
            Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);

            scoreTV.setText("Your Score is \n"+currentScore+"\10");
            restartQuizBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    currentPos = random.nextInt(quizModalArrayList.size());
                    setDataToViews(currentPos);
                    questionAttempted=0;
                    currentScore=0;
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setCancelable(false);
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();

        }




    private void setDataToViews(int currentPos){

        questionNumberTV.setText("Questions Attempted :"+questionAttempted+"/10");
        if(questionAttempted==10){
            showBottomSheet();
        }else{
            questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModalArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModalArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModalArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModalArrayList.get(currentPos).getOption4());

        }
    }
    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {

        quizModalArrayList.add(new QuizModal("Divide 30 by half and add ten.","40.5","50","70","I know this is a trick question, so NONE. Ha!","70"));
        quizModalArrayList.add(new QuizModal("How many months have 28 days?","2","1","All of them","Depends if there's a leap year or not.","All of them"));
        quizModalArrayList.add(new QuizModal("There are 45 apples in your basket. You take three apples out of the basket. How many apples are left?","3","42","45","I do not eat apple!","45"));
        quizModalArrayList.add(new QuizModal("If a leaf falls to the ground in a forest and no one hears it, does it make a sound?","Yes","No","Depends on how heavy the leaf was.","Depends on where it landed.","Yes"));
        quizModalArrayList.add(new QuizModal("Alex is the father of Bo.But Bo is not the son of Alex.How's that possible?","Bo is too young to know his father's name","Alex is lying,he's not the father","Bo is a girl","Alex is Bo's mother","Bo is a girl"));

    }
}