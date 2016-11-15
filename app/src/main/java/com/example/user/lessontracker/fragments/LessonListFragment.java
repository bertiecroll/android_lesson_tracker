package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonListFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    Button mNewLessonButton;
    ListView mPendingLessonList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        mNewLessonButton = (Button) view.findViewById(R.id.list_new_button);
        mNewLessonButton.setText(R.string.list_new_lesson_button);

        mNewLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LessonTracker", "new lesson button clicked");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddLessonFragment addLessonFragment = new AddLessonFragment();
                transaction.replace(R.id.fragment_container, addLessonFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mPendingLessonList = (ListView) view.findViewById(R.id.teachable_list);
        List<Lesson> lessons = new ArrayList<>(mDbHelper.allLessons());



        return view;
    }
}
