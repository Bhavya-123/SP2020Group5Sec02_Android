package com.example.sp2020group5.ui.student_search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.JobsActivity;
import com.example.sp2020group5.R;

public class searchFragment extends Fragment {

    private searchViewModel searchViewModel;

    Button search;
    RadioButton r1,r2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(searchViewModel.class);
        View root = inflater.inflate(R.layout.student_search, container, false);
        search = (Button)root.findViewById(R.id.searchBTN);
        r1 = (RadioButton)root.findViewById(R.id.radioButton);
        r2 = (RadioButton)root.findViewById(R.id.radioButton2);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!((r1.isChecked())||r2.isChecked())) {
                    Toast.makeText(getContext(),"please check any one radio button option",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent1 = new Intent(getActivity(), JobsActivity.class);
                    startActivity(intent1);
                }
            }});
        final TextView textView = root.findViewById(R.id.text_slideshow);
        searchViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}