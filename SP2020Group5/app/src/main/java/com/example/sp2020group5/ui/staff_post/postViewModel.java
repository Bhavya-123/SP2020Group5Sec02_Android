package com.example.sp2020group5.ui.staff_post;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class postViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private String jobtitle;
    private String jobdescription;
    private String major;
    private String qualifications;
    private String deadline;



    public postViewModel() {
       // mText = new MutableLiveData<>();
        //mText.setValue("");
    }
    public postViewModel(String jobtitle, String jobdescription, String major, String qualifications, String deadline) {

        this.jobtitle = jobtitle;
        this.jobdescription = jobdescription;
        this.major = major;
        this.qualifications = qualifications;
        this.deadline = deadline;


    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public String getMajor() {
        return major;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getDeadline() {
        return deadline;
    }

    public LiveData<String> getText() {
        return mText;
    }
}