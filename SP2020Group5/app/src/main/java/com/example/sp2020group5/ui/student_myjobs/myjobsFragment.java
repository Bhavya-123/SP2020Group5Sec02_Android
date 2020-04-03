package com.example.sp2020group5.ui.student_myjobs;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.R;
import com.example.sp2020group5.ui.staff_post.postFragment;
import com.example.sp2020group5.ui.staff_post.postViewModel;



public class myjobsFragment extends Fragment {

    private postFragment pf;
    private MyJobsAdapter myJobsAdapter;
    private RecyclerView studentjobsRV;
    private GestureDetectorCompat detector = null;
    private postViewModel pvm;
    private myjobsViewModel myjobsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myjobsViewModel =
                ViewModelProviders.of(this).get(myjobsViewModel.class);
        View root = inflater.inflate(R.layout.studentjobsmain, container, false);
        studentjobsRV = (RecyclerView) root.findViewById(R.id.studentjobsRV);
        RecyclerView.LayoutManager manager123 = new LinearLayoutManager(getActivity());
        studentjobsRV.setLayoutManager(manager123);
        myJobsAdapter = new MyJobsAdapter(getActivity());
        studentjobsRV.setAdapter(myJobsAdapter);

        detector = new GestureDetectorCompat(getActivity(), new myjobsFragment.RecyclerViewOnGestureListener());

        studentjobsRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
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
                    Log.d("gesture", "Gestrure listener insideeeeeeeeee");

                    return true;
                }
            }
            return false;
        }
    }
}