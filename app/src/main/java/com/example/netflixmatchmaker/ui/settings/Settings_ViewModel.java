package com.example.netflixmatchmaker.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Settings_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Settings_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Settings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}