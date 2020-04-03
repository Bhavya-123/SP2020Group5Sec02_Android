package com.example.sp2020group5.ui.student_myjobs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.R;

public class MyJobsAdapter extends RecyclerView.Adapter {
    Context context;

    public MyJobsAdapter(Context context) {
        super();
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout LL=(LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.student_myjobs,parent,false);
        studentjobsViewHolder svh=new studentjobsViewHolder(LL);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class studentjobsViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ViewReference;
        public studentjobsViewHolder(LinearLayout  ll) {
            super(ll);
            ViewReference = ll;
        }
    }
}
