package com.example.sp2020group5.ui.student_search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class searchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public searchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}