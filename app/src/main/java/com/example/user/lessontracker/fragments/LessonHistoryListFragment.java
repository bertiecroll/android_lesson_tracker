package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonHistoryListFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    ListView mCompletedLessonList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        mCompletedLessonList = (ListView) view.findViewById(R.id.teachable_list);
        List<Lesson> lessons = new ArrayList<>(mDbHelper);

        return view;
    }
}
