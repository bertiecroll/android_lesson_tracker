package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lessontracker.R;
import com.github.mikephil.charting.charts.LineChart;

public class TopicStatsFragment extends Fragment {

    LineChart mDurationLineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_stats, container, false);

        mDurationLineChart = (LineChart) view.findViewById(R.id.topic_stats_linechart);

        return view;
    }
}
