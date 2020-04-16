package com.example.sp2020group5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class StudentSignUp extends AppCompatActivity implements View.OnClickListener{
    String fname,lname,email,password;
    private EditText emailET,passwordET;
    private Button stusignupBTN;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase root;
    DatabaseReference reference;
    private ProgressDialog progressDialog;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        stusignupBTN = (Button) findViewById(R.id.stusignupBTN);
        stusignupBTN.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        reference = root.getReference().child("Students");


    }

    private void registerUser(){
        email = emailET.getText().toString().trim();
        password = passwordET.getText().toString().trim();
        EditText fnameET = findViewById(R.id.firstnameET);
        fname=fnameET.getText().toString().trim();
        EditText lnameET = findViewById(R.id.lastnameET);
        lname=lnameET.getText().toString().trim();




        StudentDetails stu = new StudentDetails(fname,lname,email,password);
        reference.child("Student"+count).setValue(stu);
        count++;



        if(!(fname.isEmpty() && lname.isEmpty() && email.isEmpty() && password.isEmpty())){
            if(!(fname.length()>50)){
                if(!(lname.length()>50)) {
                    if ((email.contains("@nwmissouri.edu") || email.contains("@NWMISSOURI.EDU"))) {
                        if (!(password.length() < 8)) {


                            Toast.makeText(getApplicationContext(),"Your Registration as student is successful.Please Login",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(StudentSignUp.this,StudentLoginActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "password cannot be less than 8 characters", Toast.LENGTH_LONG).show();
                            return;
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter SID in correct format(e.g. XXXXXX@nwmissouri.edu)", Toast.LENGTH_LONG).show();
                        return;

                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Last Name field cannot be more than 50 characters long",Toast.LENGTH_LONG).show();
                    return;
                }

            }else{
                Toast.makeText(getApplicationContext(),"First Name field cannot be more than 50 characters long",Toast.LENGTH_LONG).show();
                return;
            }

                }else{
            Toast.makeText(getApplicationContext(),"All fields are Mandatory.",Toast.LENGTH_LONG).show();
            return;

        }
        progressDialog.setMessage("Registering user...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.dismiss();


//        firebaseAuth.createUserWithEmailAndPassword(email,password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(StudentSignUp.this,"Registered Successfully",Toast.LENGTH_LONG).show();
//                            progressDialog.dismiss();
//                            Intent intent = new Intent(StudentSignUp.this,StudentLoginActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//                            Toast.makeText(StudentSignUp.this,"could not register",Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
//                        }
//                    }
//                });
    }

    @Override
    public void onClick(View v) {
        if (v == stusignupBTN){
            registerUser();
        }
    }

}
