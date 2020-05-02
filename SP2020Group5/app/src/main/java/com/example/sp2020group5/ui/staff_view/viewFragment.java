package com.example.sp2020group5.ui.staff_view;

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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

public class viewFragment extends Fragment {

    private viewViewModel viewViewModel;
    private postFragment pf;
    private StaffViewAdapter staffvAdapter;
    private RecyclerView staffRV;
    private GestureDetectorCompat detector = null;
    private postViewModel pvm;
    DatabaseReference ref;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewViewModel =
                ViewModelProviders.of(this).get(viewViewModel.class);
        // Inflating the view with the layout xml which has a Recycler view in it
        View root = inflater.inflate(R.layout.staff_viewmain, container, false);
        staffRV=(RecyclerView)root.findViewById(R.id.jobslistRV);
        // Getting access to the Addjobs node in the database.
        ref= FirebaseDatabase.getInstance().getReference().child("ADDJOBS");

        RecyclerView.LayoutManager manager123=new LinearLayoutManager(getActivity());
        // Setting layout manager to the Recycler view
        staffRV.setLayoutManager(manager123);
        // Divider item decoration class is used to divide the items in the recycler view with a horizontal line
        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        // This is to set the color to the divider
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{R.color.colorBlack, R.color.colorBlack});
        // To set the height and width of the divider
        drawable.setSize(1,3);
        itemDecor.setDrawable(drawable);
        // adding the decoration to the recycler view
        staffRV.addItemDecoration(itemDecor);
        postViewModel.getSingleton().getJobslist().clear();
        // This addValue event listener listens for a data change and retrieves the complete data
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                     postViewModel.jobs j=snapshot.getValue(postViewModel.jobs.class);

                     postViewModel.getSingleton().arraylist_Add(j);
                }
                staffvAdapter= new StaffViewAdapter(getActivity(),postViewModel.getSingleton().getJobslist());
                // Setting the adapter class to the recycler view to display data in the holder.
                staffRV.setAdapter(staffvAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        detector = new GestureDetectorCompat(getActivity(), new viewFragment.RecyclerViewOnGestureListener());

        staffRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView staffRV, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });

//
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