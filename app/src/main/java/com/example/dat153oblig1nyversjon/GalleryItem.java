package com.example.dat153oblig1nyversjon;

import java.io.Serializable;

public class GalleryItem implements Serializable {
    private String name;
    private int imageResId = -1;
    private String imagePath = null;

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

    public int getImageResId() {
        return imageResId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isDrawableResource() {
        return imageResId != -1;
    }

}
