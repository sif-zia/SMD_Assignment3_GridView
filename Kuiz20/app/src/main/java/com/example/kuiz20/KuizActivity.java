package com.example.kuiz20;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class KuizActivity extends AppCompatActivity {

    long timeLimit = 5 * 60 * 1000;
    private long timeLeftInMillis = timeLimit;
    private CountDownTimer countDownTimer;
    List<Question> selectedQuestions;
    int crrQuestionNo = 0;
    int noOfQuestions = 20;
    int RESULT_ACTIVITY_REQUEST_CODE = 1;
    ArrayList<String> selectedAnswers = new ArrayList<>(Collections.nCopies(noOfQuestions, ""));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kuiz);

        String TAG = "KuizActivity";
        String Name = getIntent().getStringExtra(Extras.NAME);

        Log.d(TAG, String.format("Hello %s!", Name));

        loadQuestions();
        loadSingleQuestion(selectedQuestions.get(crrQuestionNo));
        setOptionButtonHandler();
        setNextButtonHandler();
        setPreviousButtonHandler();
        startTimer();
    }

    private void loadQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of Pakistan?", new String[]{"Karachi", "Lahore", "Islamabad", "Quetta"}, "Islamabad"));
        questionList.add(new Question("Who is known as the Father of the Nation in Pakistan?", new String[]{"Liaquat Ali Khan", "Allama Iqbal", "Quaid-e-Azam", "Zulfikar Ali Bhutto"}, "Quaid-e-Azam"));
        questionList.add(new Question("Which river is the longest in Pakistan?", new String[]{"Indus", "Jhelum", "Ravi", "Chenab"}, "Indus"));
        questionList.add(new Question("When did Pakistan become an independent country?", new String[]{"1940", "1947", "1956", "1965"}, "1947"));
        questionList.add(new Question("What is the national language of Pakistan?", new String[]{"Urdu", "Punjabi", "Sindhi", "Pashto"}, "Urdu"));
        questionList.add(new Question("Who was the first Prime Minister of Pakistan?", new String[]{"Liaquat Ali Khan", "Zulfikar Ali Bhutto", "Benazir Bhutto", "Ayub Khan"}, "Liaquat Ali Khan"));
        questionList.add(new Question("What is the highest civilian award in Pakistan?", new String[]{"Nishan-e-Pakistan", "Hilal-e-Imtiaz", "Sitara-e-Jurat", "Tamgha-e-Shujaat"}, "Nishan-e-Pakistan"));
        questionList.add(new Question("What is the national sport of Pakistan?", new String[]{"Cricket", "Hockey", "Football", "Kabaddi"}, "Hockey"));
        questionList.add(new Question("Which city is known as the 'City of Lights'?", new String[]{"Lahore", "Karachi", "Islamabad", "Peshawar"}, "Karachi"));
        questionList.add(new Question("In which year was the Lahore Resolution passed?", new String[]{"1930", "1940", "1947", "1956"}, "1940"));
        questionList.add(new Question("Which is the largest province of Pakistan by area?", new String[]{"Punjab", "Sindh", "Khyber Pakhtunkhwa", "Balochistan"}, "Balochistan"));
        questionList.add(new Question("Who wrote Pakistan's national anthem?", new String[]{"Allama Iqbal", "Faiz Ahmed Faiz", "Hafeez Jalandhari", "Ahmed Faraz"}, "Hafeez Jalandhari"));
        questionList.add(new Question("Which mountain is the highest peak in Pakistan?", new String[]{"K2", "Nanga Parbat", "Broad Peak", "Gasherbrum I"}, "K2"));
        questionList.add(new Question("Which sea lies to the south of Pakistan?", new String[]{"Mediterranean Sea", "Arabian Sea", "Caspian Sea", "Red Sea"}, "Arabian Sea"));
        questionList.add(new Question("Which is the most populous city in Pakistan?", new String[]{"Karachi", "Lahore", "Faisalabad", "Rawalpindi"}, "Karachi"));
        questionList.add(new Question("Who is known as the Poet of the East?", new String[]{"Faiz Ahmed Faiz", "Mirza Ghalib", "Allama Iqbal", "Ahmed Faraz"}, "Allama Iqbal"));
        questionList.add(new Question("What is the official currency of Pakistan?", new String[]{"Dollar", "Rupee", "Taka", "Dinar"}, "Rupee"));
        questionList.add(new Question("Which is the largest desert in Pakistan?", new String[]{"Thar", "Cholistan", "Kharan", "Thal"}, "Thar"));
        questionList.add(new Question("Which Pakistani scientist won the Nobel Prize in Physics?", new String[]{"Abdul Sattar Edhi", "Malala Yousafzai", "Abdus Salam", "Pervez Hoodbhoy"}, "Abdus Salam"));
        questionList.add(new Question("In which year did Pakistan conduct its first nuclear tests?", new String[]{"1974", "1998", "2001", "2005"}, "1998"));
        questionList.add(new Question("Which city is known as the 'Heart of Pakistan'?", new String[]{"Karachi", "Islamabad", "Lahore", "Peshawar"}, "Lahore"));
        questionList.add(new Question("Who was the first female Prime Minister of Pakistan?", new String[]{"Benazir Bhutto", "Fatima Jinnah", "Asma Jahangir", "Malala Yousafzai"}, "Benazir Bhutto"));
        questionList.add(new Question("What is the national animal of Pakistan?", new String[]{"Lion", "Tiger", "Markhor", "Elephant"}, "Markhor"));
        questionList.add(new Question("Which ocean borders Pakistan?", new String[]{"Indian Ocean", "Pacific Ocean", "Atlantic Ocean", "Arabian Sea"}, "Arabian Sea"));
        questionList.add(new Question("Who is the current Prime Minister of Pakistan (2024)?", new String[]{"Imran Khan", "Anwar ul Haq Kakar", "Nawaz Sharif", "Shehbaz Sharif"}, "Shehbaz Sharif"));
        questionList.add(new Question("Which is the largest dam in Pakistan?", new String[]{"Tarbela Dam", "Mangla Dam", "Warsak Dam", "Diamer-Bhasha Dam"}, "Tarbela Dam"));
        questionList.add(new Question("What is the national flower of Pakistan?", new String[]{"Rose", "Sunflower", "Tulip", "Jasmine"}, "Jasmine"));
        questionList.add(new Question("When was the Constitution of Pakistan first enacted?", new String[]{"1956", "1962", "1973", "1985"}, "1973"));
        questionList.add(new Question("Which is the largest mosque in Pakistan?", new String[]{"Badshahi Mosque", "Faisal Mosque", "Shah Jahan Mosque", "Grand Jamia Mosque"}, "Faisal Mosque"));
        questionList.add(new Question("Which Pakistani city is known for the Mohenjo-Daro archaeological site?", new String[]{"Larkana", "Quetta", "Multan", "Sukkur"}, "Larkana"));

        Collections.shuffle(questionList);
        selectedQuestions = questionList.subList(0, noOfQuestions);
    }

    @SuppressLint("DefaultLocale")
    private void loadSingleQuestion(@NonNull Question crrQuestion) {
        TextView questionTextView = findViewById(R.id.question);
        questionTextView.setText(crrQuestion.getStatement());

        Button option1Button = findViewById(R.id.option1Button);
        Button option2Button = findViewById(R.id.option2Button);
        Button option3Button = findViewById(R.id.option3Button);
        Button option4Button = findViewById(R.id.option4Button);

        option1Button.setText(crrQuestion.getOptions()[0]);
        option2Button.setText(crrQuestion.getOptions()[1]);
        option3Button.setText(crrQuestion.getOptions()[2]);
        option4Button.setText(crrQuestion.getOptions()[3]);

        if (match(selectedAnswers.get(crrQuestionNo), crrQuestion.getOptions()[0]))
            option1Button.setBackgroundColor(getColor(R.color.cyan));
        if (match(selectedAnswers.get(crrQuestionNo), crrQuestion.getOptions()[1]))
            option2Button.setBackgroundColor(getColor(R.color.cyan));
        if (match(selectedAnswers.get(crrQuestionNo), crrQuestion.getOptions()[2]))
            option3Button.setBackgroundColor(getColor(R.color.cyan));
        if (match(selectedAnswers.get(crrQuestionNo), crrQuestion.getOptions()[3]))
            option4Button.setBackgroundColor(getColor(R.color.cyan));

        TextView questionNoTextView = findViewById(R.id.index);
        questionNoTextView.setText(String.format("%d / %d", crrQuestionNo + 1, noOfQuestions));
    }

    public void setOptionButtonHandler() {
        Button option1Button = findViewById(R.id.option1Button);
        Button option2Button = findViewById(R.id.option2Button);
        Button option3Button = findViewById(R.id.option3Button);
        Button option4Button = findViewById(R.id.option4Button);

        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswers.set(crrQuestionNo, option1Button.getText().toString());
                resetOptionButtons();
                option1Button.setBackgroundColor(getColor(R.color.cyan));
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswers.set(crrQuestionNo, option2Button.getText().toString());
                resetOptionButtons();
                option2Button.setBackgroundColor(getColor(R.color.cyan));
            }
        });

        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswers.set(crrQuestionNo, option3Button.getText().toString());
                resetOptionButtons();
                option3Button.setBackgroundColor(getColor(R.color.cyan));
            }
        });

        option4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswers.set(crrQuestionNo, option4Button.getText().toString());
                resetOptionButtons();
                option4Button.setBackgroundColor(getColor(R.color.cyan));
            }
        });
    }

    public void setNextButtonHandler() {
        Button nextButton = findViewById(R.id.next);
        Button previousButton = findViewById(R.id.previous);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (crrQuestionNo + 1 == noOfQuestions) {
                    showDialog();
                    return;
                }
                if (crrQuestionNo == 0)
                    previousButton.setEnabled(true);
                crrQuestionNo++;
                resetOptionButtons();
                loadSingleQuestion(selectedQuestions.get(crrQuestionNo));
                if (crrQuestionNo + 1 == noOfQuestions)
                    nextButton.setText("Submit");
            }
        });
    }

    public void setPreviousButtonHandler() {
        Button previousButton = findViewById(R.id.previous);
        Button nextButton = findViewById(R.id.next);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (crrQuestionNo == 0)
                    return;
                if (crrQuestionNo == noOfQuestions - 1)
                    nextButton.setText("Next");
                crrQuestionNo--;
                resetOptionButtons();
                loadSingleQuestion(selectedQuestions.get(crrQuestionNo));
                if (crrQuestionNo == 0)
                    previousButton.setEnabled(false);
            }
        });
    }

    private boolean match(String a, String b) {
        return Objects.equals(a, b);
    }

    private void resetOptionButtons() {
        Button option1Button = findViewById(R.id.option1Button);
        Button option2Button = findViewById(R.id.option2Button);
        Button option3Button = findViewById(R.id.option3Button);
        Button option4Button = findViewById(R.id.option4Button);

        option1Button.setBackgroundColor(getColor(R.color.purple_500));
        option2Button.setBackgroundColor(getColor(R.color.purple_500));
        option3Button.setBackgroundColor(getColor(R.color.purple_500));
        option4Button.setBackgroundColor(getColor(R.color.purple_500));
    }

    private void startTimer() {
        timeLeftInMillis = timeLimit;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                submit();
            }
        }.start();
    }

    private void updateTimer() {
        TextView timerTextView = findViewById(R.id.timer);
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        @SuppressLint("DefaultLocale") String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);

        LinearLayout loading = (LinearLayout) findViewById(R.id.loading);
        int loadingWidth = findViewById(R.id.loadingParent).getWidth();
        float percentage = ((float) timeLeftInMillis) / ((float) timeLimit);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) loading.getLayoutParams();
        params.width = (int) (loadingWidth * percentage);
        loading.setLayoutParams(params);
    }

    private void submit() {
        countDownTimer.cancel();
        timeLeftInMillis = timeLimit;
        crrQuestionNo = 0;

        ((Button) findViewById(R.id.next)).setText("Next");
        findViewById(R.id.previous).setEnabled(false);

        Intent intent = new Intent(KuizActivity.this, ResultActivity.class);
        intent.putExtra(Extras.NAME, getIntent().getStringExtra(Extras.NAME));
        intent.putExtra(Extras.QUESTIONS, new ArrayList<Question> (selectedQuestions));
        intent.putExtra(Extras.ANSWERS, selectedAnswers);

        loadQuestions();
        loadSingleQuestion(selectedQuestions.get(crrQuestionNo));
        
        KuizActivity.this.startActivityForResult(intent, RESULT_ACTIVITY_REQUEST_CODE);
    }

    private void showDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Submission")
                .setMessage("Are you sure you want to submit the Quiz?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle "Yes" button click
                        submit();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle "No" button click
                    }
                });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            if (Objects.equals(data.getStringExtra(Extras.RESULT_RETURN_MODE), "Play")) startTimer();
            if (Objects.equals(data.getStringExtra(Extras.RESULT_RETURN_MODE), "See Answers"))
            {

            };

        }
    }
}