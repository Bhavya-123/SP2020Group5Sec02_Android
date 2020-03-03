package com.example.sp2020group5.ui.student_myjobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class myjobsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public myjobsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}