package com.example.kuiz20;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    ArrayList<Question> questions;
    ArrayList<String> answers;
    int plusPoints = 5;
    int minusPoints = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        questions = (ArrayList<Question>) getIntent().getSerializableExtra(Extras.QUESTIONS);
        answers  = (ArrayList<String>) getIntent().getSerializableExtra(Extras.ANSWERS);


        int totalScore = plusPoints * questions.size();
        int score = getScore();

        TextView scoreTextView = findViewById(R.id.score);
        scoreTextView.setText(String.format("%d / %d", score, totalScore));

        int percentage = (int) ((float) score/ (float) totalScore) * 100;
        TextView greetingTextView = findViewById(R.id.greeting);
        greetingTextView.setText(getGreeting(percentage));

        String name = getIntent().getStringExtra(Extras.NAME);

        addReplyButtonHandler();
        addShareButtonHandler(name, score, totalScore);
    }

    private void addReplyButtonHandler() {
        findViewById(R.id.replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Extras.RESULT_RETURN_MODE, "Play");
                returnIntent.putExtra(Extras.NAME, getIntent().getStringExtra(Extras.NAME));
                setResult(RESULT_OK, returnIntent);
                ResultActivity.super.finish();
            }
        });
    }

    private void addSeeAnsButtonHandler() {
        findViewById(R.id.replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Extras.RESULT_RETURN_MODE, "See Answers");
                returnIntent.putExtra(Extras.NAME, getIntent().getStringExtra(Extras.NAME));
                returnIntent.putExtra(Extras.QUESTIONS, getIntent().getSerializableExtra(Extras.QUESTIONS));
                returnIntent.putExtra(Extras.ANSWERS, getIntent().getSerializableExtra(Extras.ANSWERS));
                setResult(RESULT_OK, returnIntent);
                ResultActivity.super.finish();
            }
        });
    }

    private String getGreeting(int percentage) {
        String name = getIntent().getStringExtra(Extras.NAME);
        if (percentage > 66)
            return "Congratulations " + name + "! You made a good score:";
        else if (percentage > 33)
            return "Nice Try " + name + "! Your score is:";
        else
            return  name + " you need to improve! Your score is:";
    }

    private int getScore() {
        String TAG = "getScore()";
        int i = 0;
        int points = 0;
        for(String answer: answers) {
            if(questions.get(i).checkAnswer(answer)){
                points += plusPoints;
            } else if (Objects.equals(answer, "")) {
                points += 0;
            } else {
                points -= minusPoints;
            }

            Log.d(TAG, "Selected Answer of " + i + "th Question: " + answer);
            Log.d(TAG, "Actual Answer of " + i + "th Question: " + questions.get(i).getAnswer());
            i++;
        }
        return points;
    }

    private void addShareButtonHandler(String name, int score, int totalScore) {
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareMsg = name + " scored " + score + " / " + totalScore + " in the Kuiz App.";
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMsg);
                shareIntent.setType("text/plain");

                Intent shareIntentChooser = Intent.createChooser(shareIntent, "Share Kuiz Score");
                startActivity(shareIntentChooser);
            }
        });
    }
}