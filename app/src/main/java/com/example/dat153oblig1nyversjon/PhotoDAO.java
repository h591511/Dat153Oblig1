package com.example.dat153oblig1nyversjon;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhotoDAO {




    @Insert
    void insertGalleryItem(GalleryItem galleryItem);
    @Delete
    void delete(GalleryItem galleryItem);
    @Query("SELECT * FROM GalleryItem")
    LiveData<List<GalleryItem>> getAllGalleryItems();
    @Query("DELETE FROM GalleryItem")
    void deleteAll();


}
