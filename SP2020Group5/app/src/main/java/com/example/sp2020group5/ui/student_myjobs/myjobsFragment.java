package com.example.sp2020group5.ui.student_myjobs;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.R;
import com.example.sp2020group5.ui.staff_post.postFragment;
import com.example.sp2020group5.ui.staff_post.postViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class myjobsFragment extends Fragment {

    private postFragment pf;
    private MyJobsAdapter myJobsAdapter;
    private RecyclerView studentjobsRV;
    private GestureDetectorCompat detector = null;
    private myjobsViewModel myjobsViewModel;
    DatabaseReference jobsref;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myjobsViewModel =
                ViewModelProviders.of(this).get(myjobsViewModel.class);
        View root = inflater.inflate(R.layout.studentjobsmain, container, false);
        studentjobsRV = (RecyclerView) root.findViewById(R.id.studentjobsRV);
        jobsref = FirebaseDatabase.getInstance().getReference().child("MYJOBS");

        RecyclerView.LayoutManager manager123 = new LinearLayoutManager(getActivity());
        studentjobsRV.setLayoutManager(manager123);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{R.color.colorBlack, R.color.colorBlack});
        drawable.setSize(1, 3);
        itemDecor.setDrawable(drawable);
        studentjobsRV.addItemDecoration(itemDecor);
        myjobsViewModel.getSingleton().getJobslist().clear();
        //myJobsAdapter = new MyJobsAdapter(getActivity());
        //studentjobsRV.setAdapter(myJobsAdapter);

        jobsref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    myjobsViewModel.Jobs jobs = data.getValue(myjobsViewModel.Jobs.class);
                    myjobsViewModel.getSingleton().arraylist_Add(jobs);
                }
                myJobsAdapter = new MyJobsAdapter(getActivity(), myjobsViewModel.getSingleton().getJobslist());
                studentjobsRV.setAdapter(myJobsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        detector = new GestureDetectorCompat(getActivity(), new myjobsFragment.RecyclerViewOnGestureListener());

        studentjobsRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            public boolean onInterceptTouchEvent(RecyclerView studentjobsRV, MotionEvent e) {
                return detector.onTouchEvent(e);
            }

        });


        // final TextView textView = root.findViewById(R.id.text_gallery);
//        myjobsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        //ontap to list_item remove the item from the list
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = studentjobsRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = studentjobsRV.getChildViewHolder(view);
                if (holder instanceof MyJobsAdapter.studentjobsViewHolder) {
                    int position = holder.getAdapterPosition();
                    //Log.d("gesture", "Gestrure listener insideeeeeeeeee");

                    return true;
                }
            }
            return false;
        }
    }
}