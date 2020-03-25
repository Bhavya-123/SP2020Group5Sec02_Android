package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class StudentSignUp extends AppCompatActivity {
    String fname,lname,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
    }
	public void onClickRegister_asstudent(View v){
        EditText fnameET = findViewById(R.id.firstnameET);
        fname=fnameET.getText().toString();
        EditText lnameET = findViewById(R.id.lastnameET);
        lname=lnameET.getText().toString();
        EditText emailET = findViewById(R.id.emailET);
        email=emailET.getText().toString();
        EditText passwordET = findViewById(R.id.passwordET);
        password=passwordET.getText().toString();
        if(!(fname.isEmpty() && lname.isEmpty() && email.isEmpty() && password.isEmpty())){
            if(!(fname.length()>50)){
                if(!(lname.length()>50)) {
                    if ((email.contains("@nwmissouri.edu") || email.contains("@NWMISSOURI.EDU"))) {
                        if (!(password.length() < 8)) {

                            Toast.makeText(getApplicationContext(),"Your Registration as student is successful.Please Login",Toast.LENGTH_LONG).show();
                            Intent ini=new Intent(this, StudentLoginActivity.class);
                            startActivity(ini);

                        } else {
                            Toast.makeText(getApplicationContext(), "password cannot be less than 8 characters", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter SID in correct format(e.g. XXXXXX@nwmissouri.edu)", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Last Name field cannot be more than 50 characters long",Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(getApplicationContext(),"First Name field cannot be more than 50 characters long",Toast.LENGTH_LONG).show();
            }

                }else{
            Toast.makeText(getApplicationContext(),"All fields are Mandatory.",Toast.LENGTH_LONG).show();

        }



}
}
