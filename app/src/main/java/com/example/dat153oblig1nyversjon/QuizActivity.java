package com.example.dat153oblig1nyversjon;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.load.DataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    private List<GalleryItem> items;
    private ImageView quizImageView;
    private Button choice1, choice2, choice3;
    private static String correctAnswer;
    private int score = 0, attempts = 0;
    private TextView feedbackTextView, scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        items = GalleryDataManager.getInstance().getGalleryItems();

        feedbackTextView = findViewById(R.id.feedbackTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        quizImageView = findViewById(R.id.quizImageView);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);

        setUpQuiz();
    }

    private void setUpQuiz() {
        if (items == null || items.isEmpty()) {
            return;
        }
        int randomIndex = new Random().nextInt(items.size());
        GalleryItem currentItem = items.get(randomIndex);

        if (currentItem.isDrawableResource()) {
            quizImageView.setImageResource(currentItem.getImageResId());
        } else {
            Uri imageUri = Uri.parse(currentItem.getImagePath());
            Glide.with(this)
                    .load(imageUri)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("QuizActivity", "Feil", e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("QuizActivity", "Riktig");
                            return false;
                        }
                    })
                    .into(quizImageView);
        }
        List<String> choices = new ArrayList<>();
        choices.add(currentItem.getName());
        correctAnswer = currentItem.getName();
        while (choices.size() < 3) {
            int wrongIndex = new Random().nextInt(items.size());
            String wrongAnswer = items.get(wrongIndex).getName();
            if (!choices.contains(wrongAnswer)) {
                choices.add(wrongAnswer);
            }
        }
        choice1.setText(choices.get(0));
        choice2.setText(choices.get(1));
        choice3.setText(choices.get(2));
        Collections.shuffle(choices);

        View.OnClickListener answerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String selectedAnswer = clickedButton.getText().toString();
                checkAnswer(selectedAnswer);
            }
        };
        choice1.setOnClickListener(answerListener);
        choice2.setOnClickListener(answerListener);
        choice3.setOnClickListener(answerListener);

    }

    private void checkAnswer(String selectedAnswer) {
        attempts++;
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            feedbackTextView.setText("Riktig");
        } else {
            feedbackTextView.setText("Feil. Riktig svar: " + correctAnswer);
        }
        scoreTextView.setText("Poeng: " + score + "/ Forsøk: " + attempts);

        feedbackTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                setUpQuiz();
            }
        }, 1500);
    }
  //  public static GalleryItem getCorrectAnswer() {
  //      return correctAnswer;
 //   }

}
