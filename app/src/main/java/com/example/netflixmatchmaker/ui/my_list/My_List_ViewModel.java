package com.example.netflixmatchmaker.ui.my_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class My_List_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public My_List_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the My List fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}