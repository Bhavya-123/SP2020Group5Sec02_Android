package com.example.sp2020group5.ui.staff_post;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class postViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    ArrayList<jobs> jobslist=new ArrayList<>();

   public static class jobs implements DatabaseReference.CompletionListener {
        private String jobtitle;
        private String jobdescription;
        private String major;
        private String qualifications;
        private String deadline;
        // No-arg constructor
        public jobs() {

        }

        public jobs(String jobtitle, String jobdescription, String major, String qualifications, String deadline) {
            this.jobtitle = jobtitle;
            this.jobdescription = jobdescription;
            this.major = major;
            this.qualifications = qualifications;
            this.deadline = deadline;
        }
        // Getters and Setters
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


       @Override
       public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

       }
   }


    public jobs loadjobs(String title,String desc,String major,String qual,String deadline ) {

        return new jobs(title, desc, major, qual, deadline);
    }



    public postViewModel() {

    }
    private static postViewModel theModel= null;
    // Singleton method to get access to a single object
    public static postViewModel getSingleton() {
        if (theModel == null)
            theModel = new postViewModel();
        return theModel;
    }
//



    public LiveData<String> getText() {
        return mText;
    }
    public void arraylist_Add(jobs m){

        jobslist.add(m);

    }

    public ArrayList<jobs> getJobslist() {
        return jobslist;
    }
}