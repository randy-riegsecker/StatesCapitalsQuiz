package com.randyriegsecker.statescapitalsquiz;

// Author: Randy Riegsecker
// Date: 2022/05/31
// States & Capitals Quiz main class activity

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    int totalNumberOfQuestions;
    int questionIndex = 0;
    int correctAnswerTotal = 0;

    // Screen elements
    Button answerA, answerB, answerC, answerD, answerE;
    Button submitButton;
    Button currentClickedAnswer = null;
    Button lastClickedAnswer = null;

    TextView questionText;
    TextView questionNumberText;

    // Initialize Test Questions
    Questions quizQuestions = new Questions();

    // Generate a random order for questions using the reusable class Randomizer
    Randomizer randomQuestionOrder = new Randomizer(quizQuestions.getNumQuestions());

    // Let the fun begin!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get IDs of the elements on the user interface
        questionNumberText = findViewById(R.id.question_num);
        questionText = findViewById(R.id.question);
        answerA = findViewById(R.id.answer_a);
        answerB = findViewById(R.id.answer_b);
        answerC = findViewById(R.id.answer_c);
        answerD = findViewById(R.id.answer_d);
        answerE = findViewById(R.id.answer_e);
        submitButton = findViewById(R.id.submit);

        totalNumberOfQuestions = quizQuestions.getNumQuestions();

        // Set up Listeners for the buttons on the UI
        answerA.setOnClickListener(myListener);
        answerB.setOnClickListener(myListener);
        answerC.setOnClickListener(myListener);
        answerD.setOnClickListener(myListener);
        answerE.setOnClickListener(myListener);
        submitButton.setOnClickListener(submitListener);

        // Load the first question (questionIndex)
        loadQuestion();
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // find currently selected answer
            currentClickedAnswer = (Button) v;

            // change last selected answer back to blue, if there is one
            if (lastClickedAnswer != null && currentClickedAnswer != lastClickedAnswer)
                lastClickedAnswer.setBackgroundResource(R.color.blue_button);

            // Change the selected answer to gray
            currentClickedAnswer.setBackgroundColor(Color.GRAY);

            // save current button as the last answer selected for the next iteration
            lastClickedAnswer = (Button) v;
        }
    };

    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String correctAnswer;
            String correctAnswerToast;

            if (currentClickedAnswer != null ) {
                correctAnswer = quizQuestions.getCorrectAnswer(randomQuestionOrder.getInt(questionIndex));

                if (correctAnswer == currentClickedAnswer.getText()) {
                    correctAnswerTotal++;
                    // Comment out the Toast if you don't want to tell the test taker the answer is correct
                    Toast.makeText(getApplicationContext(), R.string.correct_answer, Toast.LENGTH_SHORT).show();
                } else {
                    // Comment out the two lines below if you don't want to let the test taker know the answer was wrong
                    correctAnswerToast = "Incorrect.  The correct answer is " + correctAnswer;
                    // Toast.makeText(getApplicationContext(), R.string.incorrect_answer, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), correctAnswerToast, Toast.LENGTH_SHORT).show();
                }
                // Adjust and reset counters and on to the next question
                currentClickedAnswer = null;
                lastClickedAnswer = null;
                ++questionIndex;
                loadQuestion();
            } else {
                Toast.makeText(getApplicationContext(), R.string.choose_an_answer, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void loadQuestion() {

        String questionNumberMessage;

        if (questionIndex < totalNumberOfQuestions) {
            // More questions to answer...
            // Reset clicked answer nothing selected
            currentClickedAnswer = null;

            questionText.setText(quizQuestions.getQuestion(randomQuestionOrder.getInt(questionIndex)));
            answerA.setBackgroundResource(R.color.blue_button);
            answerA.setText(quizQuestions.getAnswerA(randomQuestionOrder.getInt(questionIndex)));
            answerB.setBackgroundResource(R.color.blue_button);
            answerB.setText(quizQuestions.getAnswerB(randomQuestionOrder.getInt(questionIndex)));
            answerC.setBackgroundResource(R.color.blue_button);
            answerC.setText(quizQuestions.getAnswerC(randomQuestionOrder.getInt(questionIndex)));
            answerD.setBackgroundResource(R.color.blue_button);
            answerD.setText(quizQuestions.getAnswerD(randomQuestionOrder.getInt(questionIndex)));
            answerE.setBackgroundResource(R.color.blue_button);
            answerE.setText(quizQuestions.getAnswerE(randomQuestionOrder.getInt(questionIndex)));

            // Android Studio told me not to concatenate in the setText method, so it's concatenated below
            questionNumberMessage = "Question " + (questionIndex + 1) + "/" + totalNumberOfQuestions;
            questionNumberText.setText(questionNumberMessage);

        } else {
            // End the test and show final score
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Quiz Complete");
            alert.setCancelable(false);
            alert.setMessage("You answered " + correctAnswerTotal + " questions correctly out of "
                    + totalNumberOfQuestions + ".");
            alert.setPositiveButton("End Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    finish();
                }
            });
            alert.show();
        }
    }
}