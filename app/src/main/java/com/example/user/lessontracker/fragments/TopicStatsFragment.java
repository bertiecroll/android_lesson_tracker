package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Topic;

public class TopicStatsFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;

    TextView mAverageDuration;
    TextView mPercentObjectivesMet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_stats, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        long topicId = arguments.getLong(TopicFragment.TOPIC_ID);
        double averageDuration = mDbHelper.averageLessonDurationByTopic(topicId);

        mAverageDuration = (TextView) view.findViewById(R.id.topic_stats_average_duration);
        mAverageDuration.setText(Double.toString(averageDuration));

        mPercentObjectivesMet = (TextView) view.findViewById(R.id.topic_stats_percent_objectives_met);
        mPercentObjectivesMet.setText(R.string.topic_stats_percent_objectives_met);

        return view;
    }
}
