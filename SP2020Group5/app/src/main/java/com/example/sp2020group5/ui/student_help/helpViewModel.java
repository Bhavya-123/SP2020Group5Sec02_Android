package com.example.sp2020group5.ui.student_help;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class helpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public helpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}