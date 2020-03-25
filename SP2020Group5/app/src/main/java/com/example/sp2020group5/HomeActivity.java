package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void HomeLogin_Student(View v){
        Intent ini = new Intent(this,StudnetLoginActivity.class);
        startActivity(ini);
    }

    public void HomeLogin_Staff(View v){
        Intent ini = new Intent(this, StaffLoginActivity.class);
        startActivity(ini);
    }

    public void HomeStaff_Signup(View v){
        Intent ini = new Intent(this, StaffSignUp.class);
        startActivity(ini);
    }
    public void HomeStudent_Signup(View v){
        Intent ini = new Intent(this, StudentSignUp.class);
        startActivity(ini);
    }
}
