package com.example.sp2020group5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sp2020group5.ui.login.LoginActivity;

public class StaffSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_sign_up);
    }
	public void onClickRegister_asstaff(View v){
        Toast.makeText(getApplicationContext(),"Your Registration is successful.Please Login",Toast.LENGTH_LONG).show();
        Intent ini=new Intent(this, LoginActivity.class);
        setResult(RESULT_OK,ini);
        finish();

    }
    
}