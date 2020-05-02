package com.example.sp2020group5.ui.student_major;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.JobsActivity;
import com.example.sp2020group5.R;
import com.example.sp2020group5.ui.staff_post.postViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentmajor extends Fragment {

    Button compsci, is, bms, agri, business;
    public static ProgressBar progressBarPB;
    private StudentmajorViewModel mViewModel;
    private postViewModel pvm = new postViewModel();


    public static studentmajor newInstance() {
        return new studentmajor();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.studentmajor_fragment, container, false);
        progressBarPB = v.findViewById(R.id.progressBarPB);
        progressBarPB.setVisibility(View.INVISIBLE);
        compsci = (Button) v.findViewById(R.id.csBTN);
        is = (Button) v.findViewById(R.id.infosysBTN);
        bms = (Button) v.findViewById(R.id.bmsBTN);
        agri = (Button) v.findViewById(R.id.agriBTN);
        business = (Button) v.findViewById(R.id.businessBTN);


        //       jobname.setText(pvm.getJobslist().get(0).getJobtitle());
//        jobtitle.setText(pvm.getJobslist().get(1).getJobtitle());
//        jobdesc.setText(pvm.getJobslist().get(2).getJobdescription());
//        major.setText(pvm.getJobslist().get(3).getMajor());
//        qualification.setText(pvm.getJobslist().get(4).getQualifications());
//        deadline.setText(pvm.getJobslist().get(5).getDeadline());
        compsci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarPB.setVisibility(View.VISIBLE);
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                intent1.putExtra("major", "computer science");
                startActivity(intent1);
            }
        });
        is.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarPB.setVisibility(View.VISIBLE);
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                intent1.putExtra("major", "information systems");
                startActivity(intent1);

            }
        });
        bms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarPB.setVisibility(View.VISIBLE);
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                intent1.putExtra("major", "bio medical science");
                startActivity(intent1);
            }
        });
        agri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarPB.setVisibility(View.VISIBLE);
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                intent1.putExtra("major", "agricultural science");
                startActivity(intent1);
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarPB.setVisibility(View.VISIBLE);
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                intent1.putExtra("major", "business");
                startActivity(intent1);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StudentmajorViewModel.class);
        // TODO: Use the ViewModel


    }

}