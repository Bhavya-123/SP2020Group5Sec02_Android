package com.example.sp2020group5.ui.staff_help;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sp2020group5.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class helpFragment extends Fragment {

    private helpViewModel helpViewModel;
    private ArrayList<String> querylist=new ArrayList<>();
    DatabaseReference ref;
     static int count=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        helpViewModel =
                ViewModelProviders.of(this).get(helpViewModel.class);
        // Inflating the staff help layout xml file
        final View root = inflater.inflate(R.layout.staff_help, container, false);
        Button helpBTN=root.findViewById(R.id.helpBTN);
        // Listening for click of help button using on click listeners
        helpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText queryET=root.findViewById(R.id.queryET);
                String query=queryET.getText().toString();
                if(!query.isEmpty()){
                    // Creating a node with name Help_Queries to store the raised queries by the user
                    ref= FirebaseDatabase.getInstance().getReference().child("HELP_QUERIES");
                     // storing the data into the database
                     ref.child("Query"+getnumber()).setValue(query);
                     querylist.add(query);
                    queryET.setText("");
                    //count++;
                    Toast.makeText(getActivity(), "Your Query will be addressed soon", Toast.LENGTH_LONG).show();
                    Log.d("help arraylist","list size is"+querylist.size());

                }else{
                    Toast.makeText(getActivity(), "Please enter the query which needs to be addressed", Toast.LENGTH_LONG).show();
                }
            }
        });
//
        return root;
    }
    public static int getnumber(){
        return count++;
    }
}