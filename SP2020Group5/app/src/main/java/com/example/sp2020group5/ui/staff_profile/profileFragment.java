package com.example.sp2020group5.ui.staff_profile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
import com.example.sp2020group5.StaffLoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileFragment extends Fragment {

    private profileViewModel profileViewModel;
    DatabaseReference ref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(profileViewModel.class);
        // Inflating the view with the staff profile layout xml file
        final View root = inflater.inflate(R.layout.staff_profile, container, false);

        TextView nameTV = root.findViewById(R.id.profile_name);
        TextView empidTV = root.findViewById(R.id.profile_employeeid);
        final EditText passwordET = root.findViewById(R.id.profilepassword);
        TextView changeTV = (TextView) root.findViewById(R.id.profilepassword_change);
        nameTV.setText(StaffLoginActivity.name);
        empidTV.setText(StaffLoginActivity.username);
        passwordET.setText(StaffLoginActivity.pass);
        passwordET.setEnabled(false);
        // Listening for 'change' Text view to change the password using on click listener.
        changeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on click of button password field is being set to true to enable the field for editing
                passwordET.setEnabled(true);

                Button submitBTN = root.findViewById(R.id.password_ChangeBTN);
                // Listening for On click of submit button to change the password in the database
                submitBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText pwdTV = root.findViewById(R.id.profilepassword);
                        passwordET.setEnabled(false);
                        ref = FirebaseDatabase.getInstance().getReference().child("Staff");
                        // Changing the password value with the new value in the database.
                        ref.child("staff" + StaffLoginActivity.username).child("password").setValue(pwdTV.getText().toString());
                        Toast.makeText(getActivity(), "Password has been changed successfully ", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

        final TextView textView = root.findViewById(R.id.text_tools);

        return root;
    }
//
}