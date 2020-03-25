package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StaffLoginActivity extends AppCompatActivity {
    String empid,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
    }
    public void onclickLogin_staff(View v){
        EditText empidET = findViewById(R.id.staffloginidET);
        EditText pwdET=findViewById(R.id.staffloginpwdET);
        empid=empidET.getText().toString();
        pwd=pwdET.getText().toString();

        if(!(empid.isEmpty() && pwd.isEmpty())){
            if(!(empid.length()<6 )){

                if(!(pwd.length()<8)){
                    Intent ini = new Intent(this,StaffActivity.class);
                    startActivity(ini);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Password must be 8 or more characters long",Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(getApplicationContext(),"Please enter the 6 digit Employee ID",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Please enter EmployeeID and Password",Toast.LENGTH_LONG).show();
        }

    }

}
