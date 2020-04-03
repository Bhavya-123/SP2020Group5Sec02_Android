package com.example.sp2020group5.ui.staff_post;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class postViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    ArrayList<Jobs> jobslist=new ArrayList<>();

   public static class Jobs

    {
        private String jobtitle;
        private String jobdescription;
        private String major;
        private String qualifications;
        private String deadline;

        public Jobs(String jobtitle, String jobdescription, String major, String qualifications, String deadline) {
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


    }

    public Jobs loadjobs(String title,String desc,String major,String qual,String deadline ){
      return new Jobs(title,desc,major,qual,deadline);
    }

    public postViewModel() {
       // mText = new MutableLiveData<>();
        //mText.setValue("");
    }
    private static postViewModel theModel= null;
    //
    public static postViewModel getSingleton() {
        if (theModel == null)
            theModel = new postViewModel();
        return theModel;
    }
//    public postViewModel(String jobtitle, String jobdescription, String major, String qualifications, String deadline) {
//
//        this.jobtitle = jobtitle;
//        this.jobdescription = jobdescription;
//        this.major = major;
//        this.qualifications = qualifications;
//        this.deadline = deadline;
//
//
//    }



    public LiveData<String> getText() {
        return mText;
    }
    public void arraylist_Add(Jobs m){

        jobslist.add(m);
        Log.d("Arraylist","inside arraylist method"+jobslist.size());
    }

    public ArrayList<Jobs> getJobslist() {
        return jobslist;
    }
}