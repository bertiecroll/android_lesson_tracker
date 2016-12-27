package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Topic;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class TopicStatsFragment extends Fragment {

    LineChart mDurationLineChart;
    LessonTrackerDbHelper mDbHelper;
    List<Lesson> mLessons;

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

        mDurationLineChart = (LineChart) view.findViewById(R.id.topic_stats_linechart);

        mLessons = mDbHelper.findLessonsByTopic(topicId);
        if(!mLessons.isEmpty()) {
            List<Entry> lineChartEntries = new ArrayList<>();

            for (Lesson lesson : mLessons) {
                long xValue = lesson.getId();
                long yValue = lesson.getDuration() / 1000;
                lineChartEntries.add(new Entry(xValue, yValue));
            }

            LineDataSet lineChartDataSet = new LineDataSet(lineChartEntries, "lesson Durations");
            LineData lineData = new LineData(lineChartDataSet);
            mDurationLineChart.setData(lineData);
        }

        return view;
    }
}
