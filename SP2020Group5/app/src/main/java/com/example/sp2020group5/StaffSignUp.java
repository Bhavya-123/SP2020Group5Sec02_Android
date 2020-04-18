package com.example.sp2020group5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StaffSignUp extends AppCompatActivity implements View.OnClickListener{
    String staffFname, staffLname, empid, password;
    private EditText empidET,passwordET;
    private Button staffsignupBTN;
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    DatabaseReference reference;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_sign_up);
        empidET = (EditText) findViewById(R.id.staffempIdET);
        passwordET = (EditText) findViewById(R.id.staffpasswordET);
        staffsignupBTN = (Button) findViewById(R.id.staffsignupBTN);
        staffsignupBTN.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        reference = root.getReference().child("Staff");
    }

    private void registerUser(){
        EditText staffFnameET = findViewById(R.id.stafffirstnameET);
        staffFname = staffFnameET.getText().toString();
        EditText staffLnameET = findViewById(R.id.stafflastnameET);
        staffLname = staffLnameET.getText().toString();
        final EditText empidET = findViewById(R.id.staffempIdET);
        empid = empidET.getText().toString();
        EditText pwdET = findViewById(R.id.staffpasswordET);
        password = pwdET.getText().toString();


//        StaffDetails staff = new StaffDetails(staffFname,staffLname,empid,password);
//
//        reference.child("Staff"+empid).setValue(staff);

        if ((!staffFname.isEmpty() && !staffLname.isEmpty() && !empid.isEmpty() && !password.isEmpty())) {
            if (!(staffFnameET.length() > 50)) {
                if (!(staffLnameET.length() > 50)) {
                    if ((empid.length() == 6)) {
                        if (!(password.length() < 8)) {
//                            Toast.makeText(getApplicationContext(), "Your Registration is successful.Please Login", Toast.LENGTH_LONG).show();
//                            Intent ini = new Intent(this, StaffLoginActivity.class);
//                            startActivity(ini);
                        } else {
                            Toast.makeText(getApplicationContext(), "password cannot be less than 8 characters", Toast.LENGTH_LONG).show();
                            return;
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "employee id should contain 6 characters", Toast.LENGTH_LONG).show();
                        return;
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Last Name field cannot be more than 50 characters long", Toast.LENGTH_LONG).show();
                    return;
                }

            } else {
                Toast.makeText(getApplicationContext(), "First Name field cannot be more than 50 characters long", Toast.LENGTH_LONG).show();
                return;
            }
        } else {
            Toast.makeText(getApplicationContext(), "All fields are Mandatory.", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering user");
        progressDialog.show();
        empid = empid+"@nwmissouri.edu";

        mAuth.fetchSignInMethodsForEmail(empid).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean verify = task.getResult().getSignInMethods().isEmpty();
                if (verify) {
                    mAuth.createUserWithEmailAndPassword(empid, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        StaffDetails staff = new StaffDetails(staffFname, staffLname, empid, password);
                                        String[] splitEmail = empid.split("@");
                                        reference.child("staff" + splitEmail[0]).setValue(staff);

                                        Toast.makeText(StaffSignUp.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                                        progressDialog.dismiss();
                                        Intent intent = new Intent(StaffSignUp.this, StaffLoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(StaffSignUp.this, "could not register", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            });
                } else {
                    empidET.setError("email already exist");
                    Toast.makeText(getApplicationContext(), "User already exist.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

//        xyz=empid;
//        empid = empid+"@nwmissouri.edu";
//        mAuth.createUserWithEmailAndPassword(empid,password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
//                            progressDialog.dismiss();
//                            Intent intent = new Intent(StaffSignUp.this,StaffLoginActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//                            Toast.makeText(StaffSignUp.this,"could not register",Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
//                        }
//                    }
//                });


    }


    @Override
    public void onClick(View v) {
        if (v == staffsignupBTN){
            registerUser();
        }
    }
}

//
//    public void onClickRegister_asstaff(View v) {
//        EditText staffFnameET = findViewById(R.id.stafffirstnameET);
//        staffFname = staffFnameET.getText().toString();
//        EditText staffLnameET = findViewById(R.id.stafflastnameET);
//        staffLname = staffLnameET.getText().toString();
//        EditText empidET = findViewById(R.id.staffempIdET);
//        empid = empidET.getText().toString();
//        EditText pwdET = findViewById(R.id.staffpasswordET);
//        pwd = pwdET.getText().toString();
//        if ((!staffFname.isEmpty() && !staffLname.isEmpty() && !empid.isEmpty() && !pwd.isEmpty())) {
//            if (!(staffFnameET.length() > 50)) {
//                if (!(staffLnameET.length() > 50)) {
//                    if ((empid.length() == 6)) {
//                        if (!(pwd.length() < 8)) {
//                            Toast.makeText(getApplicationContext(), "Your Registration is successful.Please Login", Toast.LENGTH_LONG).show();
//                            Intent ini = new Intent(this, StaffLoginActivity.class);
//                            startActivity(ini);
//                        } else {
//                            Toast.makeText(getApplicationContext(), "password cannot be less than 8 characters", Toast.LENGTH_LONG).show();
//                        }
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), "employee id should contain 6 characters", Toast.LENGTH_LONG).show();
//                    }
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Last Name field cannot be more than 50 characters long", Toast.LENGTH_LONG).show();
//                }
//
//            } else {
//                Toast.makeText(getApplicationContext(), "First Name field cannot be more than 50 characters long", Toast.LENGTH_LONG).show();
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "All fields are Mandatory.", Toast.LENGTH_LONG).show();
//
//        }
//
//
//    }




    

