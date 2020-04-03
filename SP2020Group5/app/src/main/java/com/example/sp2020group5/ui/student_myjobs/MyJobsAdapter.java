package com.example.sp2020group5.ui.student_myjobs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.R;

public class MyJobsAdapter extends RecyclerView.Adapter<MyJobsAdapter.studentjobsViewHolder> {
    myjobsViewModel mvm = myjobsViewModel.getSingleton();
    Context context;

    public MyJobsAdapter(Context context) {
        super();
        this.context=context;
    }

    @NonNull
    @Override
    public studentjobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout LL=(LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.student_myjobs,parent,false);
        studentjobsViewHolder svh=new studentjobsViewHolder(LL);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull studentjobsViewHolder holder, int position) {
        TextView jobname= holder.ViewReference.findViewById(R.id.jobnameTV);
        TextView jobtitle = holder.ViewReference.findViewById(R.id.jobtitleTV);
        TextView jobdesc = holder.ViewReference.findViewById(R.id.jobdescTV);
        TextView major = holder.ViewReference.findViewById(R.id.majorTV);
        TextView qualification = holder.ViewReference.findViewById(R.id.qualiTV);
        TextView deadline = holder.ViewReference.findViewById(R.id.deadlineTV);
        jobname.setText(mvm.getJobslist().get(position).getJobtitle());
        jobtitle.setText(mvm.getJobslist().get(position).getJobtitle());
        jobdesc.setText(mvm.getJobslist().get(position).getJobdescription());
        major.setText(mvm.getJobslist().get(position).getMajor());
        qualification.setText(mvm.getJobslist().get(position).getQualifications());
        deadline.setText(mvm.getJobslist().get(position).getDeadline());

    }


    @Override
    public int getItemCount() {
        return mvm.getJobslist().size();
    }

    public static class studentjobsViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ViewReference;
        public studentjobsViewHolder(LinearLayout  ll) {
            super(ll);
            ViewReference = ll;
        }
    }
}
