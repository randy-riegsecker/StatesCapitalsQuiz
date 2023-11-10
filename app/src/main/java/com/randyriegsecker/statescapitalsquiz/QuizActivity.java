package com.randyriegsecker.statescapitalsquiz;

// Author: Randy Riegsecker
// Date: 2022/05/31
// States & Capitals Quiz quiz activity
// https://github.com/randy-riegsecker/StatesCapitalsQuiz

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

    // Use for stopping many toasts queueing
    Toast questionToast;

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

            // deselect answer if it's selected and clicked again
            if (currentClickedAnswer.isSelected()) {
                currentClickedAnswer.setBackgroundResource(R.color.blue_button);
                // Future use
                currentClickedAnswer.setSelected(false);
                // reset clicked answer to nothing selected
                currentClickedAnswer = null;

            } else {
                // change last selected answer back to blue, if there is one
                if ((lastClickedAnswer != null) && (currentClickedAnswer != lastClickedAnswer)) {
                    lastClickedAnswer.setBackgroundResource(R.color.blue_button);
                    // Future use
                    lastClickedAnswer.setSelected(false);
                }

                // Change the selected answer to gray
                // currentClickedAnswer.setBackgroundColor(Color.GRAY);
                currentClickedAnswer.setBackgroundResource(R.color.gray_button);
                // Future use
                currentClickedAnswer.setSelected(true);

                // save current button as the last answer selected for the next iteration
                lastClickedAnswer = (Button) v;
            }
        }
    };

    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String correctAnswer;
            String correctAnswerToast;

            // Something was selected, so we see if it's correct
            if (currentClickedAnswer != null) {
                correctAnswer = quizQuestions.getCorrectAnswer(randomQuestionOrder.getInt(questionIndex));

                if (correctAnswer == currentClickedAnswer.getText()) {
                    correctAnswerTotal++;
                    // Comment out the Toast if you don't want to tell the test taker the answer is correct
                    // Toast.makeText(getApplicationContext(), R.string.correct_answer, Toast.LENGTH_SHORT).show();
                    toastShow("Correct!");
                } else {
                    // Comment out the two lines below if you don't want to let the test taker know the answer was wrong
                    correctAnswerToast = "Incorrect.  The correct answer is " + correctAnswer;
                    // This toast just reads "Incorrect answer, but does not tell you the correct one.
                    // Toast.makeText(getApplicationContext(), R.string.incorrect_answer, Toast.LENGTH_SHORT).show();
                    // This toast shows you the correct answer.
                    // Toast.makeText(getApplicationContext(), correctAnswerToast, Toast.LENGTH_SHORT).show();
                    toastShow(correctAnswerToast);
                }

                // On to the next question
                ++questionIndex;
                loadQuestion();
            } else {
                //Toast.makeText(getApplicationContext(), R.string.choose_an_answer, Toast.LENGTH_SHORT).show();
                toastShow("Choose An Answer!");
            }
        }
    };

    public void loadQuestion() {

        String questionNumberMessage;

        if (questionIndex < totalNumberOfQuestions) {
            // More questions to answer...
            // Initialize the buttons and reset clicked answer nothing selected
            currentClickedAnswer = null;
            lastClickedAnswer = null;

            questionText.setText(quizQuestions.getQuestion(randomQuestionOrder.getInt(questionIndex)));
            answerA.setBackgroundResource(R.color.blue_button);
            answerA.setSelected(false);
            answerA.setText(quizQuestions.getAnswerA(randomQuestionOrder.getInt(questionIndex)));
            answerB.setBackgroundResource(R.color.blue_button);
            answerB.setText(quizQuestions.getAnswerB(randomQuestionOrder.getInt(questionIndex)));
            answerB.setSelected(false);
            answerC.setBackgroundResource(R.color.blue_button);
            answerC.setText(quizQuestions.getAnswerC(randomQuestionOrder.getInt(questionIndex)));
            answerC.setSelected(false);
            answerD.setBackgroundResource(R.color.blue_button);
            answerD.setText(quizQuestions.getAnswerD(randomQuestionOrder.getInt(questionIndex)));
            answerD.setSelected(false);
            answerE.setBackgroundResource(R.color.blue_button);
            answerE.setText(quizQuestions.getAnswerE(randomQuestionOrder.getInt(questionIndex)));
            answerE.setSelected(false);

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

    public void toastShow(String message) {

        if (questionToast != null ) {
            questionToast.cancel();
        }

        questionToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        questionToast.show();
    }
}