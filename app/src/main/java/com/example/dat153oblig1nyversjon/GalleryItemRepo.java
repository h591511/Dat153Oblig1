package com.example.dat153oblig1nyversjon;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GalleryItemRepo {
    private static PhotoDAO photoDAO;
    private LiveData<List<GalleryItem>> allGalleryItems;

    public GalleryItemRepo(Application application) {
        AppDatabase ad = AppDatabase.getDatabase(application);
        photoDAO = ad.photoDAO();
        allGalleryItems = photoDAO.getAllGalleryItems();
    }
    LiveData<List<GalleryItem>> getAllGalleryItems() {
        return allGalleryItems;
    }
    private static  class insertAsyncTask extends android.os.AsyncTask<GalleryItem, Void, Void> {
        private final PhotoDAO mAsyncTaskDao;
        insertAsyncTask(PhotoDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(GalleryItem... galleryItems) {
            mAsyncTaskDao.insertGalleryItem(galleryItems[0]);
            return null;
        }
    }
    static void insert(GalleryItem galleryItem) {
        insertAsyncTask task = new insertAsyncTask(photoDAO);
        task.execute(galleryItem);
    }
    public void delete(GalleryItem galleryItem) {
        new Thread(() -> photoDAO.delete(galleryItem)).start();
    }
    public void deleteAll() {
        new Thread(()-> photoDAO.deleteAll()).start();
    }

}
