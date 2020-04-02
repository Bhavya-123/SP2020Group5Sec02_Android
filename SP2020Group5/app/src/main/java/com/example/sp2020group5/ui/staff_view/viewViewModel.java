package com.example.sp2020group5.ui.staff_view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class viewViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public viewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Something");
    }

    public LiveData<String> getText() {
        return mText;
    }
}