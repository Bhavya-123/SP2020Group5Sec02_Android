package com.example.sp2020group5;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class StudentLoginActivity extends AppCompatActivity {
    String emailid,studentpwd;
    EditText emailidET;
    EditText studentpwdET;
    Button studentloginBTN;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        emailidET = (EditText) findViewById(R.id.stuloginidET);
        studentpwdET=(EditText) findViewById(R.id.stuloginpwdET);
        studentloginBTN = findViewById(R.id.studentloginBTN);
        emailid=emailidET.getText().toString();
        studentpwd=studentpwdET.getText().toString();
        firebaseAuth = FirebaseAuth.getInstance();

        studentloginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailidET.getText().toString().trim();
                String password = studentpwdET.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(StudentLoginActivity.this,"Please enter email-id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(StudentLoginActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<8){
                    Toast.makeText(StudentLoginActivity.this,"Password is too short",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(StudentLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                  startActivity(new Intent(getApplicationContext(),StudentActivity.class));
                                } else {
                                    Toast.makeText(StudentLoginActivity.this,"Login failed or User is not availabe",Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });




//        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
//                if (mFirebaseUser!=null){
//                    Toast.makeText(StudentLoginActivity.this,"you are logged in",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(StudentLoginActivity.this,StudentActivity.class);
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(StudentLoginActivity.this,"Please login",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };

//        studentloginBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!(emailid.isEmpty() && studentpwd.isEmpty())) {
//                    firebaseAuth.createUserWithEmailAndPassword(emailid, studentpwd)
//                            .addOnCompleteListener(StudentLoginActivity.this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(StudentLoginActivity.this, "Login error", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        Intent intent = new Intent(StudentLoginActivity.this, StudentActivity.class);
//                                        startActivity(intent);
//                                    }
//                                }
//                            });
//                }
//            }
//        });


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(mAuthStateListner);
//    }

//    public void onclickLogin_student(View v){
//        EditText emailidET = findViewById(R.id.stuloginidET);
//        EditText studentpwdET=findViewById(R.id.stuloginpwdET);
//        emailid=emailidET.getText().toString();
//        studentpwd=studentpwdET.getText().toString();
//
//        if(!(emailid.isEmpty() && studentpwd.isEmpty())){
//            if(emailid.contains("@nwmissouri.edu") || emailid.contains("@NWMISSOURI.EDU") ){
//
//                if(!(studentpwd.length()<8)){
//                    Intent ini = new Intent(this,StudentActivity.class);
//                    startActivity(ini);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Password must be 8 or more characters long",Toast.LENGTH_LONG).show();
//                }
//
//            }else{
//                Toast.makeText(getApplicationContext(),"Please enter the proper SID(e.g.XXXXXX@nwmissouri.edu)",Toast.LENGTH_LONG).show();
//            }
//        }else{
//            Toast.makeText(getApplicationContext(),"Please enter LoginId and Password",Toast.LENGTH_LONG).show();
//        }
//
//    }

}
