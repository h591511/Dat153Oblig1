package com.example.dat153oblig1nyversjon;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (GalleryDataManager.getInstance().getGalleryItems().isEmpty()) {
            initializeGalleryItems();
        }

        Button galleryButton = findViewById(R.id.galleryButton);
        Button quizButton = findViewById(R.id.quizButton);

        galleryButton.setOnClickListener(v -> startGalleryActivity());
        quizButton.setOnClickListener(v -> startQuizActivity());
    }

    private void startGalleryActivity() {
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    private void startQuizActivity() {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(intent);
    }


    private void initializeGalleryItems() {
        ArrayList<GalleryItem> galleryItems = new ArrayList<>();
        galleryItems.add(new GalleryItem("Katt", R.drawable.katt));
        galleryItems.add(new GalleryItem("Hund", R.drawable.hund));
        galleryItems.add(new GalleryItem("Hest", R.drawable.hest));

        GalleryDataManager.getInstance().setGalleryItems(galleryItems);
    }
}