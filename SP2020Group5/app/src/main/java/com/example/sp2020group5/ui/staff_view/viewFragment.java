package com.example.sp2020group5.ui.staff_view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp2020group5.R;
import com.example.sp2020group5.ui.staff_post.postFragment;
import com.example.sp2020group5.ui.staff_post.postViewModel;

public class viewFragment extends Fragment {

    private viewViewModel viewViewModel;
    private postFragment pf;
    private StaffViewAdapter staffvAdapter;
    private RecyclerView staffRV;
    private GestureDetectorCompat detector = null;
    private postViewModel pvm;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

//        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
//        ViewModelProvider provider = new ViewModelProvider(mainActivity, factory);
//        mainviewmodel = provider.get(MainViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewViewModel =
                ViewModelProviders.of(this).get(viewViewModel.class);
        View root = inflater.inflate(R.layout.staff_viewmain, container, false);
        staffRV=(RecyclerView)root.findViewById(R.id.jobslistRV);


        //staffRV.setHasFixedSize(true);

        RecyclerView.LayoutManager manager123=new LinearLayoutManager(getActivity());
        staffRV.setLayoutManager(manager123);
        staffvAdapter= new StaffViewAdapter(getActivity());
        staffRV.setAdapter(staffvAdapter);
        detector = new GestureDetectorCompat(getActivity(), new viewFragment.RecyclerViewOnGestureListener());

        staffRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView staffRV, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });

//        viewViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//            }
//        });
        return root;
    }
    class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        //ontap to list_item remove the item from the list
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = staffRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = staffRV.getChildViewHolder(view);
                if (holder instanceof StaffViewAdapter.staffviewViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.d("gesture","Gestrure listener insideeeeeeeeee");

                    return true;
                }
            }
            return false;
        }
    }
}