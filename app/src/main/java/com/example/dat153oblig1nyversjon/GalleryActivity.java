package com.example.dat153oblig1nyversjon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GalleryAdapter adapter;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new GalleryAdapter(GalleryDataManager.getInstance().getGalleryItems(), this);
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        Button sortButton = findViewById(R.id.sortButton);


        addButton.setOnClickListener(view -> openImageChooser());

        sortButton.setOnClickListener(view -> {
            List<GalleryItem> items = GalleryDataManager.getInstance().getGalleryItems();
            boolean isAscending = sortButton.getText().toString().equals("Sorter A-Z");
            Collections.sort(items, (o1, o2) -> isAscending ? o1.getName().compareTo(o2.getName()) : o2.getName().compareTo(o1.getName()));
            sortButton.setText(isAscending ? "Sorter Z-A" : "Sorter A-Z");
            adapter.notifyDataSetChanged();
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("bilde");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Velg"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            askForImageName(imageUri);
        }
    }

    private void askForImageName(Uri imageUri) {

        EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Navn")
                .setView(input)
                .setPositiveButton("OK", (dialog, whichButton) -> {
                    String imageName = input.getText().toString();
                    GalleryDataManager.getInstance().getGalleryItems().add(new GalleryItem(imageName, imageUri.toString()));
                    adapter.notifyItemInserted(GalleryDataManager.getInstance().getGalleryItems().size() - 1);
                    Log.d("GalleryActivity", "Blide lagt til: " + imageUri.toString());
                })
                .setNegativeButton("Avbryt", (dialog, whichButton) -> {
                })
                .show();
    }


}
