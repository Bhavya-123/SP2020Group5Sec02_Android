package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class StudentSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
    }
	public void onClickRegister_asstudent(View v){
        Toast.makeText(getApplicationContext(),"Your Registration as student is successful.Please Login",Toast.LENGTH_LONG).show();
        Intent ini=new Intent(this, LoginActivity.class);
        setResult(RESULT_OK,ini);
        finish();
}
}
