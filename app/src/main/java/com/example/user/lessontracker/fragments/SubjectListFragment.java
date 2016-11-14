package com.example.user.lessontracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectListFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    ListView mSubjectList;
    Button mNewSubjectButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Activity activity = getActivity();

        mDbHelper = new LessonTrackerDbHelper(activity);

        mNewSubjectButton = (Button) view.findViewById(R.id.list_new_button);
        mNewSubjectButton.setText(R.string.list_new_subject_button);
        mNewSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddSubjectFragment AddSubjectFragment = new AddSubjectFragment();
                transaction.replace(R.id.fragment_container, AddSubjectFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mSubjectList = (ListView) view.findViewById(R.id.teachable_list);
        List<Subject> subjects = new ArrayList<>(mDbHelper.allSubjects());
        ArrayAdapter<Subject> adapter =
                new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, subjects);
        mSubjectList.setAdapter(adapter);

        mSubjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject selectedSubject = (Subject) mSubjectList.getItemAtPosition(position);
                Log.d("LessonTracker", selectedSubject + " selected");

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SubjectFragment subjectFragment = new SubjectFragment();

                Bundle args = new Bundle();
                args.putLong("subjectId", selectedSubject.getId());
                subjectFragment.setArguments(args);
                transaction.replace(R.id.fragment_container, subjectFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
