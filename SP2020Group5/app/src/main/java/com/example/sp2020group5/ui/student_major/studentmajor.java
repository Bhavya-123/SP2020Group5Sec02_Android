package com.example.sp2020group5.ui.student_major;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.JobsActivity;
import com.example.sp2020group5.R;
import com.example.sp2020group5.ui.staff_post.postViewModel;

public class studentmajor extends Fragment {

    Button button,button9,button10,button11,button12;
    private StudentmajorViewModel mViewModel;
    private postViewModel pvm = new postViewModel();

    public static studentmajor newInstance() {
        return new studentmajor();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.studentmajor_fragment, container, false);
        button = (Button) v.findViewById(R.id.csBTN);
        button9 = (Button) v.findViewById(R.id.infosysBTN);
        button10 = (Button) v.findViewById(R.id.bmsBTN);
        button11 = (Button) v.findViewById(R.id.agriBTN);
        button12 = (Button) v.findViewById(R.id.businessBTN);
        TextView jobname= v.findViewById(R.id.jobnameTV);
        TextView jobtitle = v.findViewById(R.id.jobtitleTV);
        TextView jobdesc = v.findViewById(R.id.jobdescTV);
        TextView major = v.findViewById(R.id.majorTV);
        TextView qualification = v.findViewById(R.id.qualiTV);
        TextView deadline = v.findViewById(R.id.deadlineTV);
        //       jobname.setText(pvm.getJobslist().get(0).getJobtitle());
//        jobtitle.setText(pvm.getJobslist().get(1).getJobtitle());
//        jobdesc.setText(pvm.getJobslist().get(2).getJobdescription());
//        major.setText(pvm.getJobslist().get(3).getMajor());
//        qualification.setText(pvm.getJobslist().get(4).getQualifications());
//        deadline.setText(pvm.getJobslist().get(5).getDeadline());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent1 = new Intent(getActivity(), JobsActivity.class);
               startActivity(intent1);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                startActivity(intent1);

            }
            });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                startActivity(intent1);
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                startActivity(intent1);
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), JobsActivity.class);
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
