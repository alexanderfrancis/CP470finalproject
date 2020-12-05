package com.example.netflixmatchmaker;

public class FriendModel {
    private String name, email, uid;


    public FriendModel(String name, String email, String uid) {
        this.name = name;
        this.email = email;
        this.uid = uid;

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
