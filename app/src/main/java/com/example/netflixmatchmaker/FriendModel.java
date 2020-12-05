package com.example.netflixmatchmaker;

public class FriendModel {
    private String name, email, uid;

    public FriendModel(String name, String email, String uid) {
    }

    public FriendModel(String name, String title, String year, String rating) {
        this.name = name;
        this.email = email;
        this.uid = year;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }


}
