package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.adapters.OutcomeAdapter;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class TakeLessonFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    Lesson mLesson;
    Topic mTopic;
    List<Outcome> mOutcomes;
    TextView mDetailsTextView;
    TextView mTopicTitleTextView;
    ListView mLearningObjectiveList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_lesson, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        long lessonId = arguments.getLong("lessonId");
        mLesson = mDbHelper.findLesson(lessonId);
        mTopic = mDbHelper.findTopic(mLesson.getTopicId());

        mDetailsTextView = (TextView) view.findViewById(R.id.take_lesson_details);
        mDetailsTextView.setText(mLesson.toString());

        mTopicTitleTextView = (TextView) view.findViewById(R.id.take_lesson_topic_title);
        mTopicTitleTextView.setText(mTopic.getTitle());

        mLearningObjectiveList = (ListView) view.findViewById(R.id.take_lesson_learning_objectives);
        List<Outcome> outcomes = new ArrayList<>(mDbHelper.findOutcomesByLesson(lessonId));
        OutcomeAdapter outcomeAdapter =
                new OutcomeAdapter(getActivity(), outcomes);
        mLearningObjectiveList.setAdapter(outcomeAdapter);

        return view;
    }

}
