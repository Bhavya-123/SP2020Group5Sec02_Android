package com.example.sp2020group5.ui.staff_post;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.sp2020group5.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class postFragment extends Fragment {
    // Declaring a variable for postviewModel class
    private postViewModel postviewModel;
    ArrayList<postViewModel> jobslist=new ArrayList<>();
    // Creating a reference for database
    DatabaseReference ref;
    int count=0;

    public ArrayList<postViewModel> getJobslist() {
        return jobslist;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postviewModel =
                ViewModelProviders.of(this).get(postViewModel.class);
        // Inflating the staff post layout xml file
       final View root = inflater.inflate(R.layout.staff_post, container, false);

        Button postBTN=root.findViewById(R.id.postBTN);
        // creating a node in the firebase database with root as ADDJOBS
        ref= FirebaseDatabase.getInstance().getReference().child("ADDJOBS");
        // Listening for Onclick using on Click listeners
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
                DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy");
                Boolean bool;
                // Checking validations of all the fields
                if(!title.isEmpty() && !desc.isEmpty() && !major.isEmpty() && !qualification.isEmpty() && !deadline.isEmpty()) {

                    if ((title.length() < 30)) {
                        if ((desc.length() < 100)) {
                            // Converting the date taken from the user i.e. String to date datatype
                            try {
                                LocalDate dateTime = LocalDate.parse(deadline, format);


                                LocalDate now = LocalDate.now();

                                if (!(dateTime.compareTo(now) < 0)) {
                                    postviewModel = postViewModel.getSingleton();
                                    // If there are no validation issues then the data is stored into database-ADDJOBS node
                                    ref.push().setValue(postviewModel.loadjobs(title, desc, major, qualification, deadline));

                                    count++;
                                    Toast.makeText(getActivity(), "Job has been posted Successfully ", Toast.LENGTH_LONG).show();
                                    titleET.setText("");
                                    descET.setText("");
                                    MajorET.setText("");
                                    qualificationET.setText("");
                                    deadlineET.setText("");
                                } else {
                                    Toast.makeText(getActivity(), "Deadline cannot be earlier than the current date", Toast.LENGTH_LONG).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(getActivity(), "Please enter Deadline in the correct format", Toast.LENGTH_LONG).show();
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

        return root;
    }


}