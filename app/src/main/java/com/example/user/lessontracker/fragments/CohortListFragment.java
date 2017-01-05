package com.example.user.lessontracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Cohort;
import com.example.user.lessontracker.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class CohortListFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mHeaderTitle;
    ListView mCohortList;
    Button mNewCohortButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Activity activity = getActivity();

        mDbHelper = new LessonTrackerDbHelper(activity);

        mHeaderTitle = (TextView) view.findViewById(R.id.list_fragment_title);
        mHeaderTitle.setText("Cohorts");

        mNewCohortButton = (Button) view.findViewById(R.id.list_new_button);
        mNewCohortButton.setText(R.string.list_new_cohort_button);
        mNewCohortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddCohortFragment addCohortFragment = new AddCohortFragment();
                transaction.replace(R.id.fragment_container, addCohortFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mCohortList = (ListView) view.findViewById(R.id.teachable_list);
        List<Cohort> cohorts = new ArrayList<>(mDbHelper.allCohorts());
        ArrayAdapter<Cohort> adapter =
                new ArrayAdapter<>(activity, R.layout.list_item_simple, cohorts);
        mCohortList.setAdapter(adapter);

        return view;
    }

}
