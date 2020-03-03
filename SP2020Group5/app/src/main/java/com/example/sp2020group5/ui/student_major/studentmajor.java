package com.example.sp2020group5.ui.student_major;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.JobsActivity;
import com.example.sp2020group5.R;

public class studentmajor extends Fragment {

    Button button,button9,button10,button11,button12;
    private StudentmajorViewModel mViewModel;

    public static studentmajor newInstance() {
        return new studentmajor();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.studentmajor_fragment, container, false);
        button = (Button) v.findViewById(R.id.button);
        button9 = (Button) v.findViewById(R.id.button9);
        button10 = (Button) v.findViewById(R.id.button10);
        button11 = (Button) v.findViewById(R.id.button11);
        button12 = (Button) v.findViewById(R.id.button12);
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
