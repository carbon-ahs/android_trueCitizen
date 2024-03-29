package com.axiagroups.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.axiagroups.truecitizen.databinding.ActivityMainBinding;
import com.axiagroups.truecitizen.models.Question;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQusIndex = 0;
    private Question[] questionBank = new Question[] {
        //create/instantiate question objects
        new Question(R.string.question_amendments, false), //correct: 27
        new Question(R.string.question_constitution, true),
        new Question(R.string.question_declaration, true),
        new Question(R.string.question_independence_rights, true),
        new Question(R.string.question_religion, true),
        new Question(R.string.question_government, false),
        new Question(R.string.question_government_feds, false),
        new Question(R.string.question_government_senators, true),
        //and add more!
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        //binding.questionTV.setText(questionBank[currentQusIndex].getAnsResId());
        updateQuestion();
        binding.nextBtn.setOnClickListener(view -> {
            //Log.d("Main", "onCreate:" + currentQusIndex++);
            currentQusIndex = (currentQusIndex + 1) % questionBank.length;
            updateQuestion();
        });

        binding.prevBtn.setOnClickListener(view -> {
            if (currentQusIndex > 0) {
                currentQusIndex = (currentQusIndex - 1) % questionBank.length;
                updateQuestion();
            }
        });
        binding.trueBtn.setOnClickListener(view -> checkAnswer(true));
        binding.falseBtn.setOnClickListener(view -> checkAnswer(false));
    }

    private void updateQuestion() {
        binding.questionTV.setText(questionBank[currentQusIndex].getAnsResId());
    }

    private void checkAnswer(boolean userChoice){
        int messageId;
        if (userChoice == questionBank[currentQusIndex].isAnsTrue()){
            messageId = R.string.correct_answer;
        }
        else {
            messageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }
}