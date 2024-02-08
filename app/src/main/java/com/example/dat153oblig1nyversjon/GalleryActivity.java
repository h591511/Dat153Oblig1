package com.example.dat153oblig1nyversjon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GalleryAdapter adapter;
    private List<GalleryItem> galleryItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initializeGalleryItems();

        adapter = new GalleryAdapter(galleryItems, this);
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        Button sortButton = findViewById(R.id.sortButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        sortButton.setOnClickListener(new View.OnClickListener() {
            boolean isAscending = true;

            @Override
            public void onClick(View view) {
                if (isAscending) {
                    Collections.sort(galleryItems, (o1, o2) -> o1.getName().compareTo(o2.getName()));
                    sortButton.setText("Sorter Z-A");
                } else {
                    Collections.sort(galleryItems, (o1, o2) -> o2.getName().compareTo(o1.getName()));
                    sortButton.setText("Sorter A-Z");
                }
                isAscending = !isAscending;
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initializeGalleryItems() {
        galleryItems.add(new GalleryItem("Katt", R.drawable.katt));
        galleryItems.add(new GalleryItem("Hund", R.drawable.hund));
        galleryItems.add(new GalleryItem("Hest", R.drawable.hest));
    }


}
