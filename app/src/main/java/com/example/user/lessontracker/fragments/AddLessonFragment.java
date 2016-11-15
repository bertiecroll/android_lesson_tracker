package com.example.user.lessontracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class AddLessonFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    Spinner mSubjectSpinner;
    Spinner mTopicSpinner;
    EditText mCohortEditText;
    DatePicker mDatePicker;
    Button mAddButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_lesson, container, false);

        Activity activity = getActivity();
        mDbHelper = new LessonTrackerDbHelper(activity);

        mSubjectSpinner = (Spinner) view.findViewById(R.id.lesson_subject_spinner);
        List<Subject> subjects = new ArrayList<>(mDbHelper.allSubjects());
        ArrayAdapter<Subject> subjectAdapter =
                new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, subjects);
        mSubjectSpinner.setAdapter(subjectAdapter);

        mTopicSpinner = (Spinner) view.findViewById(R.id.lesson_topic_spinner);

        mSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Subject selectedSubject = (Subject) mSubjectSpinner.getItemAtPosition(position);
                long subjectId = selectedSubject.getId();

                List<Topic> topics = new ArrayList<>(mDbHelper.findTopicsBySubject(subjectId));
                ArrayAdapter<Topic> topicAdapter =
                        new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, topics);
                mTopicSpinner.setAdapter(topicAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mCohortEditText = (EditText) view.findViewById(R.id.lesson_cohort_edit);
        mDatePicker = (DatePicker) view.findViewById(R.id.lesson_date_picker);
        mAddButton = (Button) view.findViewById(R.id.lesson_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LessonTracker", "Add button selected");
            }
        });




        return view;
    }
}
