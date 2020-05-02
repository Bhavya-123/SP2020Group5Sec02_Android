package com.example.sp2020group5.ui.staff_view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.R;

import com.example.sp2020group5.ui.staff_post.postViewModel;

import java.util.ArrayList;

public class StaffViewAdapter extends RecyclerView.Adapter<StaffViewAdapter.staffviewViewHolder> {
    // Getting the object of postViewModel class using getsingleton method
    postViewModel pg=postViewModel.getSingleton();
    Context cont;
    ArrayList<postViewModel.jobs> j;
    // Constructor
    public StaffViewAdapter(Context cont, ArrayList<postViewModel.jobs> list) {

        super();
        j=list;
        this.cont=cont;

    }
     // Overriding the method which creates a view holder required for Recycler View
        @NonNull
    @Override
    public StaffViewAdapter.staffviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout CL=(LinearLayout)LayoutInflater.from(cont)
                .inflate(R.layout.staff_view,parent,false);
        staffviewViewHolder svh=new staffviewViewHolder(CL);
        return svh;
    }


    // Overriding a method to bind the data to the the view holder in the recycler view.
    @Override
    public void onBindViewHolder(@NonNull staffviewViewHolder holder, final int position) {
        final int count=position;
        // Listens for the Remove Button in the recycler view to remove the particular data
        Button removeBTN=holder.ViewReference.findViewById(R.id.removeBTN);
        removeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.getJobslist().remove(count);
                //FirebaseDatabase.getInstance().getReference(position).removeValue();
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
