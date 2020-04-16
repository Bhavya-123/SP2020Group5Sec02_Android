package com.example.sp2020group5.ui.staff_view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.R;
import com.example.sp2020group5.ui.staff_post.postFragment;
import com.example.sp2020group5.ui.staff_post.postViewModel;
import com.example.sp2020group5.ui.student_myjobs.myjobsViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StaffViewAdapter extends RecyclerView.Adapter<StaffViewAdapter.staffviewViewHolder> {

    postViewModel pg=postViewModel.getSingleton();
    Context cont;
    ArrayList<postViewModel.jobs> j;
    public StaffViewAdapter(Context cont, ArrayList<postViewModel.jobs> list) {

        super();
        Log.d("Constructor","Inside Adapter Constructor");
        j=list;
        this.cont=cont;

    }




        @NonNull
    @Override
    public StaffViewAdapter.staffviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Toast.makeText(parent.getContext(), "Inside staffviewHolder", Toast.LENGTH_LONG).show();
        Log.d("ONCREATE VIEW HOLDER","inside view holder");
        LinearLayout CL=(LinearLayout)LayoutInflater.from(cont)
                .inflate(R.layout.staff_view,parent,false);
        staffviewViewHolder svh=new staffviewViewHolder(CL);
        return svh;
    }



    @Override
    public void onBindViewHolder(@NonNull staffviewViewHolder holder,  int position) {
        final int count=position;
        Button removeBTN=holder.ViewReference.findViewById(R.id.removeBTN);
        removeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.getJobslist().remove(count);
                notifyDataSetChanged();
            }
        });





                TextView jobname= holder.ViewReference.findViewById(R.id.jobnameTV);
                TextView jobtitle = holder.ViewReference.findViewById(R.id.jobtitleTV);
                TextView jobdesc = holder.ViewReference.findViewById(R.id.jobdescTV);
                TextView major = holder.ViewReference.findViewById(R.id.majorTV);
                TextView qualification = holder.ViewReference.findViewById(R.id.qualiTV);
                TextView deadline = holder.ViewReference.findViewById(R.id.deadlineTV);


                jobname.setText(pg.getJobslist().get(position).getJobtitle());
                    jobtitle.setText(pg.getJobslist().get(position).getJobtitle());
                    jobdesc.setText(pg.getJobslist().get(position).getJobdescription());
                    major.setText(pg.getJobslist().get(position).getMajor());
                    qualification.setText(pg.getJobslist().get(position).getQualifications());
                    deadline.setText(pg.getJobslist().get(position).getDeadline());



    }
    @Override
    public int getItemCount() {
        Log.d("Sizeeeee","The size is "+pg.getJobslist().size());
        return pg.getJobslist().size();
    }

    public static class staffviewViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ViewReference;
        public staffviewViewHolder(LinearLayout  ll) {
            super(ll);
            ViewReference = ll;
        }
    }
}
