package com.example.newsshow;

import android.graphics.Bitmap;

public class News {

    private String source, title, url, date;
    private Bitmap image;

    public News(String author, String title, String url, Bitmap imageUrl, String date) {
        this.source = author;
        this.title = title;
        this.url = url;
        this.image = imageUrl;
        this.date = date;
    }
    int imageResource = 0;

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }


    public String getDate() {
        return date;
    }

    public Bitmap getImageResource() {
        return  image;
    }
}