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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AddLessonFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    Spinner mSubjectSpinner;
    Spinner mTopicSpinner;
    EditText mCohortEditText;
    DatePicker mDatePicker;
    CheckBox mTeachNowCheckBox;
    Button mAddButton;
    Topic mSelectedTopic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_lesson, container, false);

        final Activity activity = getActivity();
        mDbHelper = new LessonTrackerDbHelper(activity);

        mSubjectSpinner = (Spinner) view.findViewById(R.id.lesson_subject_spinner);
        List<Subject> subjects = new ArrayList<>(mDbHelper.allSubjects());
        ArrayAdapter<Subject> subjectAdapter =
                new ArrayAdapter<>(activity, R.layout.spinner_item_main, subjects);
        subjectAdapter.setDropDownViewResource(R.layout.spinner_dropdown_main);
        mSubjectSpinner.setAdapter(subjectAdapter);

        mTopicSpinner = (Spinner) view.findViewById(R.id.lesson_topic_spinner);

        mSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Subject selectedSubject = (Subject) mSubjectSpinner.getItemAtPosition(position);
                long subjectId = selectedSubject.getId();

                List<Topic> topics = new ArrayList<>(mDbHelper.findTopicsBySubject(subjectId));
                ArrayAdapter<Topic> topicAdapter =
                        new ArrayAdapter<>(getActivity(), R.layout.spinner_item_main, topics);
                topicAdapter.setDropDownViewResource(R.layout.spinner_dropdown_main);
                mTopicSpinner.setAdapter(topicAdapter);
                mTopicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mSelectedTopic = (Topic) mTopicSpinner.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mCohortEditText = (EditText) view.findViewById(R.id.lesson_cohort_edit);
        mDatePicker = (DatePicker) view.findViewById(R.id.lesson_date_picker);

        mTeachNowCheckBox = (CheckBox) view.findViewById(R.id.lesson_teach_check_box);

        mAddButton = (Button) view.findViewById(R.id.lesson_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LessonTracker", "Add button selected");
                long cohortID = Long.parseLong(mCohortEditText.getText().toString());
                long topicId = mSelectedTopic.getId();
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                long dateAsLong = date.getTime();
                int taught = mTeachNowCheckBox.isChecked() ? 1 : 0;

                Lesson lesson = new Lesson(cohortID, topicId, dateAsLong, taught);
                long lessonId = mDbHelper.saveLesson(lesson);

                List<LearningObjective> learningObjectives =
                        new ArrayList<>(mDbHelper.findLearningObjectivesByTopic(topicId));
                for (LearningObjective learningObjective : learningObjectives) {
                    Outcome outcome = new Outcome(lessonId, learningObjective.getId());
                    mDbHelper.saveOutcome(outcome);
                }

                getFragmentManager().popBackStack();
                Toast.makeText(getActivity(), R.string.lesson_toast_add_success, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
