package com.example.netflixmatchmaker.ui.explore_movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Explore_Movies_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Explore_Movies_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Explore Movies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}