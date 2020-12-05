package com.example.netflixmatchmaker;

public class ItemModel {
    private String title, year, rating, poster, imdbId;


    public ItemModel(String poster, String title, String year, String rating,String imdbId) {
        this.poster = poster;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.imdbId=imdbId;
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
    public String getImdbId() {
        return imdbId;
    }
}
