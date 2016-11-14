package com.example.user.lessontracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectListFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    ListView mSubjectList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Activity activity = getActivity();

        mDbHelper = new LessonTrackerDbHelper(activity);

        mSubjectList = (ListView) view.findViewById(R.id.teachable_list);
        List<Subject> subjects = new ArrayList<>(mDbHelper.allSubjects());
        ArrayAdapter<Subject> adapter =
                new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, subjects);
        mSubjectList.setAdapter(adapter);

        return view;
    }
}
