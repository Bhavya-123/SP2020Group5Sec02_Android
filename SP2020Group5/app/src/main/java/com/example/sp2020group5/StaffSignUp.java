package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class StaffSignUp extends AppCompatActivity {
    String staffFname, staffLname, empid, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_sign_up);
    }

    public void onClickRegister_asstaff(View v) {
        EditText staffFnameET = findViewById(R.id.stafffirstnameET);
        staffFname = staffFnameET.getText().toString();
        EditText staffLnameET = findViewById(R.id.stafflastnameET);
        staffLname = staffLnameET.getText().toString();
        EditText empidET = findViewById(R.id.staffempIdET);
        empid = empidET.getText().toString();
        EditText pwdET = findViewById(R.id.staffpasswordET);
        pwd = pwdET.getText().toString();
        if (!(staffFname.isEmpty() && staffLname.isEmpty() && empid.isEmpty() && pwd.isEmpty())) {
            if (!(staffFnameET.length() > 50)) {
                if (!(staffLnameET.length() > 50)) {
                    if ((empid.length() == 6)) {
                        if (!(pwd.length() < 8)) {
                            Toast.makeText(getApplicationContext(), "Your Registration is successful.Please Login", Toast.LENGTH_LONG).show();
                            Intent ini = new Intent(this, StaffLoginActivity.class);
                            startActivity(ini);
                        } else {
                            Toast.makeText(getApplicationContext(), "password cannot be less than 8 characters", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "employee id should contain 6 characters", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Last Name field cannot be more than 50 characters long", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "First Name field cannot be more than 50 characters long", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "All fields are Mandatory.", Toast.LENGTH_LONG).show();

        }


    }


}
    

