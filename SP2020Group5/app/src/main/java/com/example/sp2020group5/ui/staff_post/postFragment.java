package com.example.sp2020group5.ui.staff_post;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.JobsActivity;
import com.example.sp2020group5.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class postFragment extends Fragment {

    private postViewModel postviewModel;
    ArrayList<postViewModel> jobslist=new ArrayList<>();
    DatabaseReference ref;
    int count=0;

    public ArrayList<postViewModel> getJobslist() {
        return jobslist;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postviewModel =
                ViewModelProviders.of(this).get(postViewModel.class);
       final View root = inflater.inflate(R.layout.staff_post, container, false);

        Button postBTN=root.findViewById(R.id.postBTN);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        ref= FirebaseDatabase.getInstance().getReference().child("ADDJOBS");
        postBTN.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                EditText titleET=root.findViewById(R.id.titleET);
                EditText descET=root.findViewById(R.id.descET);
                EditText MajorET=root.findViewById(R.id.MajorET);
                EditText qualificationET=root.findViewById(R.id.qualificationET);
                EditText deadlineET=root.findViewById(R.id.deadlineET);
                String title=titleET.getText().toString();
                String desc=descET.getText().toString();
                String major=MajorET.getText().toString();
                String qualification=qualificationET.getText().toString();
                String deadline=deadlineET.getText().toString();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/d/yyyy");

                if(!title.isEmpty() && !desc.isEmpty() && !major.isEmpty() && !qualification.isEmpty() && !deadline.isEmpty()) {

                    if ((title.length() < 30)) {
                        if ((desc.length() < 100)) {
                            LocalDate dateTime = LocalDate.parse(deadline, format);
                            LocalDate now = LocalDate.now();
                            if (!(dateTime.compareTo(now)<0)){
                                postviewModel = postViewModel.getSingleton();

                                postviewModel.arraylist_Add(postviewModel.loadjobs(title,desc,major,qualification,deadline));
                                ref.child("Job"+count).setValue(postviewModel.loadjobs(title,desc,major,qualification,deadline));
                                count++;
                                Toast.makeText(getActivity(), "Job has been posted Successfully ", Toast.LENGTH_LONG).show();
                                titleET.setText("");
                                descET.setText("");
                                MajorET.setText("");
                                qualificationET.setText("");
                                deadlineET.setText("");
                            }else{
                                Toast.makeText(getActivity(), "Deadline cannot be earlier than the current date", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getActivity(), "Description field should not exceed 100 characters", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Title cannot exceed 30 characters", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getActivity(),"All fields are Mandatory",Toast.LENGTH_LONG).show();
                }



            }
        });
      //  final TextView textView = root.findViewById(R.id.text_gallery);
      //  postViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
        //});
        return root;
    }


}