package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.lessontracker.R;

public class AddLessonFragment extends Fragment {

    Spinner mTopicSpinner;
    EditText mCohortEditText;
    Button mDatePickerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_lesson, container, false);

        mTopicSpinner = (Spinner) view.findViewById(R.id.lesson_topic_spinner);
        mCohortEditText = (EditText) view.findViewById(R.id.lesson_cohort_edit);
        mDatePickerButton = (Button) view.findViewById(R.id.lesson_date_picker_button);

        mDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                transaction.add(datePickerFragment, null);
                transaction.commit();
            }
        });

        return view;
    }
}
