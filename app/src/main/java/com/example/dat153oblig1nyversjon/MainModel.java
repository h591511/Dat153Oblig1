package com.example.dat153oblig1nyversjon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


public class MainModel extends AndroidViewModel {
    private GalleryItemRepo repo;
    private LiveData<List<GalleryItem>> databaseItem;

    public MainModel(@NonNull Application application) {
        super(application);
        repo = new GalleryItemRepo(application);
        databaseItem = repo.getAllGalleryItems();
    }

    LiveData<List<GalleryItem>> getAllGalleryItems() {
        return databaseItem;
    }
    public void insert(GalleryItem galleryItem) {
        repo.insert(galleryItem);
    }
    public void delete(GalleryItem galleryItem) {
        repo.deleteAll();
    }
    public void deleteAll() {
        repo.deleteAll();
    }

}
