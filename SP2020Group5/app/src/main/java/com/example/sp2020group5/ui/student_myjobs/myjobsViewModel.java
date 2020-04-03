package com.example.sp2020group5.ui.student_myjobs;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class myjobsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    ArrayList<Jobs> myjobs=new ArrayList<>();



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

//    public Jobs loadjobs(String title,String desc,String major,String qual,String deadline ){
//        return new Jobs(title,desc,major,qual,deadline);
//    }

    public myjobsViewModel() {
        myjobs = new ArrayList<Jobs>();
        arraylist_Add();
    }
    private static myjobsViewModel theModel= null;
    //
    public static myjobsViewModel getSingleton() {
        if (theModel == null)
            theModel = new myjobsViewModel();
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
    public void arraylist_Add(){
        myjobs.add(new Jobs("java developer","need java developer",
                "Computer Science","Masters","04/12/2020"));
        myjobs.add(new Jobs("System Engineer","2 years experience is preferred",
                "Information Systems","Masters","04/10/2020"));
        myjobs.add(new Jobs("","need java developer",
                "Computer Science","Masters","04/12/2020"));

        Log.d("Arraylist","inside arraylist method"+myjobs.size());
    }


    public ArrayList<Jobs> getJobslist() {
        return myjobs;
    }
}