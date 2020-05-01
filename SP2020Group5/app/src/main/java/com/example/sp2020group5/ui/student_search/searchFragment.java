package com.example.sp2020group5.ui.student_search;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static androidx.constraintlayout.widget.Constraints.TAG;

public class searchFragment extends Fragment {

    private searchViewModel searchViewModel;
    SearchView searchData;
    DatabaseReference mDatabase, myjobs;
    TextView textDisplay1, textDisplay2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(searchViewModel.class);
        View root = inflater.inflate(R.layout.student_search, container, false);
        searchData = (SearchView) root.findViewById(R.id.searchData);
        mDatabase = FirebaseDatabase.getInstance().getReference("ADDJOBS");
        textDisplay1 = root.findViewById(R.id.textDisplay1);
        textDisplay2 = root.findViewById(R.id.textDisplay2);
        searchData.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return root;
    }

    public void getData() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s1 = " ", s2 = " ";
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.child("major").getValue().equals(searchData.getQuery().toString())) {
                        final String j, jd, q, m, d;
                        if (s1.equals(" ")) {
                            j = postSnapshot.child("jobtitle").getValue().toString();
                            jd = postSnapshot.child("jobdescription").getValue().toString();
                            q = postSnapshot.child("qualifications").getValue().toString();
                            m = postSnapshot.child("major").getValue().toString();
                            d = postSnapshot.child("deadline").getValue().toString();

                            s1 = "\n" + "JobTitle: " + j + "\n" +
                                    "Job Description: " + jd + "\n" +
                                    "Qualifications : " + q + "\n"
                                    + "Major: " + m + "\n" +
                                    "Deadline: " + d + "\n";
                            textDisplay1.setText(s1 + "\nClick here to apply!!");
                            textDisplay1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (textDisplay1.getText().toString().isEmpty()) {
                                        System.out.println("nothing");
                                    } else if (!textDisplay1.getText().toString().isEmpty()) {
                                        myjobs = FirebaseDatabase.getInstance().getReference().child("MYJOBS").push();
                                        myjobs.child("jobtitle").setValue(j);
                                        myjobs.child("jobdescription").setValue(jd);
                                        myjobs.child("qualifications").setValue(q);
                                        myjobs.child("major").setValue(m);
                                        myjobs.child("deadline").setValue(d);
                                        myjobs.child("name").setValue(j);
                                        Toast.makeText(getContext(), "Applied successfully!!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        if (!s1.equals(" ")) {
                            final String jtitle = postSnapshot.child("jobtitle").getValue().toString();
                            final String jdesc = postSnapshot.child("jobdescription").getValue().toString();
                            final String qual = postSnapshot.child("qualifications").getValue().toString();
                            final String major = postSnapshot.child("major").getValue().toString();
                            final String dline = postSnapshot.child("deadline").getValue().toString();

                            s2 = "\n" + "JobTitle: " + jtitle + "\n" +
                                    "Job Description: " + jdesc + "\n" +
                                    "Qualifications : " + qual + "\n"
                                    + "Major: " + major + "\n" +
                                    "Deadline: " + dline + "\n";
                            textDisplay2.setText(s2 + "\nClick here to apply!!");
                            textDisplay2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (textDisplay2.getText().toString().isEmpty()) {
                                        System.out.println("nothing");
                                    } else if (!textDisplay2.getText().toString().isEmpty()) {
                                        myjobs = FirebaseDatabase.getInstance().getReference().child("MYJOBS").push();
                                        myjobs.child("jobtitle").setValue(jtitle);
                                        myjobs.child("jobdescription").setValue(jdesc);
                                        myjobs.child("qualifications").setValue(qual);
                                        myjobs.child("major").setValue(major);
                                        myjobs.child("deadline").setValue(dline);
                                        myjobs.child("name").setValue(jtitle);
                                        Toast.makeText(getContext(), "Applied successfully!!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}