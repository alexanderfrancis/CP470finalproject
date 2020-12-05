package com.example.netflixmatchmaker;

public class ItemModel {
    private String title, year, rating, poster;


    public ItemModel(String poster, String title, String year, String rating) {
        this.poster = poster;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }
}
