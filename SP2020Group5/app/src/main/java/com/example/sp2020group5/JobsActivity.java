package com.example.sp2020group5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.ui.staff_post.postViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class JobsModel {

    private static JobAdapter jobAdapter;

    public static class JobsModelInfo {
        public String name;
        public String jobtitle;
        public String jobdescription;
        public String major;
        public String qualifications;
        public String deadline;


        public JobsModelInfo(String name, String jobtitle, String jobdescription, String major, String qualifications, String deadline) {
            this.name = name;
            this.jobtitle = jobtitle;
            this.jobdescription = jobdescription;
            this.major = major;
            this.qualifications = qualifications;
            this.deadline = deadline;
        }
    }

    private static JobsModel singleton = null;
    private static DatabaseReference jobref;

    static int i = 1;

    public static JobsModel getSingleton() {
        if (i == 1) {
            jobAdapter = new JobAdapter();
            singleton = new JobsModel();
            i++;
        } else {
            i = 1;
        }
        return singleton;
    }


    public ArrayList<JobsModelInfo> jobsArray;

    private JobsModel() {
        jobsArray = new ArrayList<>();
        jobref = JobsActivity.jobref;
        loadModel();
    }

    private void loadModel() {
        jobref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    postViewModel.jobs joblist = data.getValue(postViewModel.jobs.class);
                    if (joblist.getMajor().equalsIgnoreCase(JobsActivity.courseMajor)) {
                        String name = joblist.getJobtitle();
                        String jobtitle = joblist.getJobtitle();
                        String jobdescription = joblist.getJobdescription();
                        String major = joblist.getMajor();
                        String qualifications = joblist.getQualifications();
                        String deadline = joblist.getDeadline();

                        jobsArray.add(new JobsModelInfo(name, jobtitle, jobdescription, major, qualifications, deadline));
                    }
                }
                jobAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("database error");
            }
        });
    }
}


class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    public static String jobName, jobTitle, jobDesc, studentMajor, qualification, deadline;


    public JobAdapter() {
        super();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout reference;
        MyOnClick myOnClick;
        private Button applyBTN;

        public JobViewHolder(@NonNull LinearLayout linearLayout, MyOnClick myOnClick) {
            super(linearLayout);
            applyBTN = linearLayout.findViewById(R.id.applyBTN);
            this.reference = linearLayout;
            this.myOnClick = myOnClick;
            applyBTN.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


        }
    }

    private MyOnClick myOnClick;


    JobsModel jobsModel;

    public JobAdapter(MyOnClick myOnClick) {
        super();
        jobsModel = JobsModel.getSingleton();
        this.myOnClick = myOnClick;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_jobs, parent, false);
        JobViewHolder jobViewHolder = new JobViewHolder(v, myOnClick);
        return jobViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        final TextView jobNameTV = holder.reference.findViewById(R.id.jobnameTV);
        jobName = jobsModel.jobsArray.get(position).name;
        jobNameTV.setText(jobsModel.jobsArray.get(position).name);

        final TextView jobTitleTV = holder.reference.findViewById(R.id.jobtitleTV);
        jobTitle = jobsModel.jobsArray.get(position).jobtitle;
        jobTitleTV.setText(jobsModel.jobsArray.get(position).jobtitle);

        final TextView jobDescTV = holder.reference.findViewById(R.id.jobdescTV);
        jobDesc = jobsModel.jobsArray.get(position).jobdescription;
        jobDescTV.setText(jobsModel.jobsArray.get(position).jobdescription);

        final TextView studentMajorTV = holder.reference.findViewById(R.id.majorTV);
        studentMajor = jobsModel.jobsArray.get(position).major;
        studentMajorTV.setText(jobsModel.jobsArray.get(position).major);

        final TextView qualificationTV = holder.reference.findViewById(R.id.qualiTV);
        qualification = jobsModel.jobsArray.get(position).qualifications;
        qualificationTV.setText(jobsModel.jobsArray.get(position).qualifications);

        final TextView deadlineTV = holder.reference.findViewById(R.id.deadlineTV);
        deadline = jobsModel.jobsArray.get(position).deadline;
        deadlineTV.setText(jobsModel.jobsArray.get(position).deadline);

        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("MYJOBS");

        Button applyBTN = holder.reference.findViewById(R.id.applyBTN);
        applyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobName = jobNameTV.getText().toString();
                jobTitle = jobTitleTV.getText().toString();
                jobDesc = jobDescTV.getText().toString();
                studentMajor = studentMajorTV.getText().toString();
                qualification = qualificationTV.getText().toString();
                deadline = deadlineTV.getText().toString();
                JobsModel.JobsModelInfo job = new JobsModel.JobsModelInfo(jobName, jobTitle, jobDesc, studentMajor, qualification, deadline);
                reference.push().setValue(job);

                Toast.makeText(v.getContext(), "Your Application was successfully submitted", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return jobsModel.jobsArray.size();
    }

    public interface MyOnClick {
        public void itemClick(int position);
    }


}

public class JobsActivity extends AppCompatActivity implements JobAdapter.MyOnClick {

    String name, title, description, major, qualifications, deadline;
    public static DatabaseReference reference, jobref;
//    postViewModel pvm = new postViewModel();


    private JobAdapter jobAdapter = null;
    private RecyclerView recyclerView = null;
    private GestureDetectorCompat detectorCompat = null;

    public static String courseMajor = "";

    @Override
    public void itemClick(int position) {

    }


    private class RecyclerViewOnGuesterListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);

                // handling single tap.
                if (holder instanceof JobAdapter.JobViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "clicked is " + position);
                    JobsModel jobsModel = JobsModel.getSingleton();
                    return true;
                }
            }
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_majorjobslist);


        reference = FirebaseDatabase.getInstance().getReference().child("MYJOBS");
        jobref = FirebaseDatabase.getInstance().getReference().child("ADDJOBS");

        Intent ini = getIntent();
        courseMajor = ini.getStringExtra("major");

        System.out.println("-----------------" + courseMajor);

        jobAdapter = new JobAdapter(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(jobAdapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        detectorCompat = new GestureDetectorCompat(this, new RecyclerViewOnGuesterListener());

        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return detectorCompat.onTouchEvent(e);
            }
        });

        Button back = (Button) findViewById(R.id.backBTN);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(getApplicationContext(), StudentActivity.class);
                startActivity(ini);
            }
        });

    }
}