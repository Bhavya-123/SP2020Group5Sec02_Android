package com.example.sp2020group5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sp2020group5.ui.staff_profile.profileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StaffLoginActivity extends AppCompatActivity {
    String empid, pwd;
    Button staffloginBTN;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase root;
    DatabaseReference reference;
    public static String username;
    public static String pass;
    public static String name;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        final EditText empidET = findViewById(R.id.staffloginidET);
        final EditText pwdET = findViewById(R.id.staffloginpwdET);
        staffloginBTN = (Button) findViewById(R.id.staffloginBTN);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();


        staffloginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empid = empidET.getText().toString().trim();
                final String password = pwdET.getText().toString().trim();
                username = empid;
                pass = password;

                if (TextUtils.isEmpty(empid)) {
                    Toast.makeText(StaffLoginActivity.this, "Please enter employee-id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(StaffLoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 8) {
                    Toast.makeText(StaffLoginActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                empid = empid + "@nwmissouri.edu";

                reference = FirebaseDatabase.getInstance().getReference().child("Staff");
                Query query = reference.orderByChild("empid").equalTo(empid);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                StaffDetails staff = user.getValue(StaffDetails.class);
                                if (staff.password.equals(password)) {
                                    name = staff.getFname() + " " + staff.getLname();
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(StaffLoginActivity.this, StaffActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(StaffLoginActivity.this, "Logged in Successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });

    }

}


