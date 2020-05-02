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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentLoginActivity extends AppCompatActivity {
    String emailid, studentpwd;
    EditText emailidET;
    EditText studentpwdET;
    Button studentloginBTN;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase root;
    DatabaseReference reference;
    public static String uname;
    public static String pwd;
    public static String name;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        emailidET = (EditText) findViewById(R.id.stuloginidET);
        studentpwdET = (EditText) findViewById(R.id.stuloginpwdET);
        studentloginBTN = findViewById(R.id.studentloginBTN);
        emailid = emailidET.getText().toString();
        studentpwd = studentpwdET.getText().toString();
        firebaseAuth = FirebaseAuth.getInstance();

        studentloginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailidET.getText().toString().trim();
                final String password = studentpwdET.getText().toString().trim();

                uname = email;
                pwd = password;

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(StudentLoginActivity.this, "Please enter email-id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(StudentLoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 8) {
                    Toast.makeText(StudentLoginActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                    return;
                }

                reference = FirebaseDatabase.getInstance().getReference().child("Students");
                Query query = reference.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                StudentDetails stu = user.getValue(StudentDetails.class);
                                if (stu.password.equals(password)) {
                                    name = stu.getFname() + " " + stu.getLname();
                                    Intent intent = new Intent(StudentLoginActivity.this, StudentActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}



