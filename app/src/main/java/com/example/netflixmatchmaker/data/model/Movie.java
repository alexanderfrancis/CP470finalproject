package com.example.netflixmatchmaker.data.model;

public class Movie {

    String title = "";
    int year = 0;
    String id = "";
    String poster = "";

    public Movie (String title, int year, String id, String poster) {
        this.title = title;
        this.year = year;
        this.id = id;
        this.poster = poster;
    }

    public void printMovie() {
        System.out.println("Title: " + this.getTitle());
        System.out.println("Year: " + this.getYear());
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getID() {
        return this.id;
    }

    public String getPoster() {
        return this.poster;
    }
}
