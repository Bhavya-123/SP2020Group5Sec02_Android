package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class StaffLoginActivity extends AppCompatActivity {
    String empid,pwd;
    Button staffloginBTN;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        final EditText empidET = findViewById(R.id.staffloginidET);
        final EditText pwdET=findViewById(R.id.staffloginpwdET);
        staffloginBTN = (Button)findViewById(R.id.staffloginBTN);
        empid=empidET.getText().toString();
        pwd=pwdET.getText().toString();

        firebaseAuth = FirebaseAuth.getInstance();

        staffloginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empid = empidET.getText().toString().trim();
                String password = pwdET.getText().toString().trim();

                if(TextUtils.isEmpty(empid)){
                    Toast.makeText(StaffLoginActivity.this,"Please enter employee-id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(StaffLoginActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<8){
                    Toast.makeText(StaffLoginActivity.this,"Password is too short",Toast.LENGTH_SHORT).show();
                    return;
                }

                empid=empid+"@nwmissouri.edu";
                firebaseAuth.signInWithEmailAndPassword(empid, password)
                        .addOnCompleteListener(StaffLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) { startActivity(new Intent(getApplicationContext(),StaffActivity.class));
                                } else {
                                    Toast.makeText(StaffLoginActivity.this,"Login failed or User is not availabe",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            }
                        });
            }

        });

    }
//    public void onclickLogin_staff(View v){
//        EditText empidET = findViewById(R.id.staffloginidET);
//        EditText pwdET=findViewById(R.id.staffloginpwdET);
//        empid=empidET.getText().toString();
//        pwd=pwdET.getText().toString();
//        firebaseAuth=FirebaseAuth.getInstance();
//


//        if(!(empid.isEmpty() && pwd.isEmpty())){
//            if((empid.length()==6 )){
//
//                if(!(pwd.length()<8)){
//                    Intent ini = new Intent(this,StaffActivity.class);
//                    startActivity(ini);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Password must be 8 or more characters long",Toast.LENGTH_LONG).show();
//                }
//
//            }else{
//                Toast.makeText(getApplicationContext(),"Please enter the 6 digit Employee ID",Toast.LENGTH_LONG).show();
//            }
//        }else{
//            Toast.makeText(getApplicationContext(),"Please enter EmployeeID and Password",Toast.LENGTH_LONG).show();
//        }

    }


