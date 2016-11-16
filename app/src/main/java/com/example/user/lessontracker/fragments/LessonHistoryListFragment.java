package com.example.user.lessontracker.fragments;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.adapters.CompletedLessonAdapter;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class LessonHistoryListFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    LinearLayout mSpinnerLayout;
    Spinner mSubjectSpinner;
    Spinner mTopicSpinner;
    Button mLessonFilterButton;
    ListView mCompletedLessonList;
    Topic mSelectedTopic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_history, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        mSpinnerLayout = (LinearLayout) view.findViewById(R.id.lesson_history_spinner_layout);

        mSubjectSpinner = (Spinner) view.findViewById(R.id.lesson_history_subject_spinner);
        List<Subject> subjects = new ArrayList<>(mDbHelper.allSubjects());
        ArrayAdapter<Subject> subjectAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, subjects);
        mSubjectSpinner.setAdapter(subjectAdapter);

        mTopicSpinner = (Spinner) view.findViewById(R.id.lesson_history_topic_spinner);

        mSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Subject selectedSubject = (Subject) mSubjectSpinner.getItemAtPosition(position);
                long subjectId = selectedSubject.getId();

                List<Topic> topics = new ArrayList<>(mDbHelper.findTopicsBySubject(subjectId));
                ArrayAdapter<Topic> topicAdapter =
                        new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, topics);
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

        mCompletedLessonList = (ListView) view.findViewById(R.id.lesson_history_list);
        final List<Lesson> lessons = new ArrayList<>(mDbHelper.findCompletedLessons());
        CompletedLessonAdapter completedLessonAdapter =
                new CompletedLessonAdapter(getActivity(), lessons);
        mCompletedLessonList.setAdapter(completedLessonAdapter);

        mCompletedLessonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LessonTracker", "Completed lesson item selected");
                Lesson selectedLesson = (Lesson) mCompletedLessonList.getItemAtPosition(position);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                OutcomeDialogFragment outcomeDialogFragment = new OutcomeDialogFragment();

                Bundle args = new Bundle();
                args.putLong("lessonId", selectedLesson.getId());
                outcomeDialogFragment.setArguments(args);
                transaction.add(outcomeDialogFragment, null);
                transaction.commit();
            }
        });

        mLessonFilterButton = (Button) view.findViewById(R.id.lesson_history_filter_button);
        mLessonFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long topicId = mSelectedTopic.getId();
                List<Lesson> topicLessons = new ArrayList<>();
                for (Lesson lesson : lessons) {
                    if (lesson.getTopicId() == topicId) {
                        topicLessons.add(lesson);
                    }
                }
                CompletedLessonAdapter completedLessonAdapter =
                        new CompletedLessonAdapter(getActivity(), topicLessons);
                mCompletedLessonList.setAdapter(completedLessonAdapter);
            }
        });

        return view;
    }
}
