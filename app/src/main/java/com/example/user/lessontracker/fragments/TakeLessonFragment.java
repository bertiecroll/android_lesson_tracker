package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;

public class TakeLessonFragment extends Fragment {

    LessonTrackerDbHelper mDbhelper;
    TextView mDetailsTextView;
    ListView mLearningObjectiveList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_lesson, container, false);

        mDbhelper = new LessonTrackerDbHelper(getActivity());

        mDetailsTextView = (TextView) view.findViewById(R.id.take_lesson_details);
        mLearningObjectiveList = (ListView) view.findViewById(R.id.take_lesson_learning_objectives);

        return view;
    }

}
