package com.example.sp2020group5.ui.student_myjobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.R;

public class myjobsFragment extends Fragment {

    private myjobsViewModel myjobsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myjobsViewModel =
                ViewModelProviders.of(this).get(myjobsViewModel.class);
        View root = inflater.inflate(R.layout.student_myjobs, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        myjobsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}