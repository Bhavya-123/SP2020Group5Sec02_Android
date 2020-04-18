package com.example.sp2020group5.ui.student_profile;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.R;
import com.example.sp2020group5.StudentLoginActivity;
import com.example.sp2020group5.StudentSignUp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileFragment extends Fragment {

    private profileViewModel profileViewModel;
    DatabaseReference reference;
    TextView change;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(profileViewModel.class);
        final View root = inflater.inflate(R.layout.student_profile, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
//        profileViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        TextView nameTV=root.findViewById(R.id.nameTV);
        TextView emailTV=root.findViewById(R.id.emailTV);
        final EditText passwordET=root.findViewById(R.id.pwdET);
        TextView changeTV=(TextView)root.findViewById(R.id.changeTV);
        nameTV.setText(StudentLoginActivity.name);
        emailTV.setText(StudentLoginActivity.uname);
        passwordET.setText(StudentLoginActivity.pwd);
        passwordET.setEnabled(false);
        changeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordET.setEnabled(true);
                Button submitBTN=root.findViewById(R.id.submitBTN);
                submitBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText pwdTV=root.findViewById(R.id.pwdET);
                        passwordET.setEnabled(false);
                        reference= FirebaseDatabase.getInstance().getReference().child("Students");
                        reference.child("Student"+StudentLoginActivity.uname.substring(0,StudentLoginActivity.uname.indexOf("@"))).child("password").setValue(pwdTV.getText().toString());
                        Toast.makeText(getActivity(), "Password has been changed successfully ", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });




        return root;
    }
}