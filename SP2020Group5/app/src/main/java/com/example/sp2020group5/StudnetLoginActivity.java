package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudnetLoginActivity extends AppCompatActivity {
    String loginid,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studnet_login);
    }
    public void onclickLogin_student(View v){
        EditText loginidET = findViewById(R.id.loginidET);
        EditText pwdET=findViewById(R.id.loginpwdET);
        loginid=loginidET.getText().toString();
        pwd=pwdET.getText().toString();

        if(!(loginid.isEmpty() && pwd.isEmpty())){
            if(loginid.contains("@nwmissouri.edu") || loginid.contains("@NWMISSOURI.EDU") ){

                if(!(pwd.length()<8)){
                    Intent ini = new Intent(this,StudentActivity.class);
                    startActivity(ini);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Password must be 8 or more characters long",Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(getApplicationContext(),"Please enter the proper SID(e.g.XXXXXX@nwmissouri.edu)",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Please enter LoginId and Password",Toast.LENGTH_LONG).show();
        }

    }

}
