package com.randyriegsecker.statescapitalsquiz;

// Author: Randy Riegsecker
// Date: 2022/12/01
// States & Capitals Quiz main activity
// This is the title screen for the first activity
// https://github.com/randy-riegsecker/StatesCapitalsQuiz

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupStartQuizButton();
    }

    private void setupStartQuizButton() {
        Button startBtn = (Button) findViewById(R.id.start_button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch the quiz activity
                Intent quizIntent;
                quizIntent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(quizIntent);
            }
        });
    }
}