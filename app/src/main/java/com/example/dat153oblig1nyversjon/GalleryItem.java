package com.example.dat153oblig1nyversjon;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class GalleryItem  {
    @ColumnInfo(name = "name")
    private String name;
    @PrimaryKey(autoGenerate = true)
    private int imageResId = -1;
    @ColumnInfo(name = "imagePath")
    private String imagePath = null;

    public GalleryItem() {}

    public GalleryItem(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }


    public GalleryItem(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isDrawableResource() {

        return imageResId != -1;
    }


}
