package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class TopicStatsFragment extends Fragment {

    private class ObjectivesXAxisFormatter implements IAxisValueFormatter {
        private List<String> mValues;

        public ObjectivesXAxisFormatter(List<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues.get((int) value);
        }
    }

    LineChart mDurationLineChart;
    BarChart mObjectivesBarChart;
    Button mDurationChartButton;
    Button mObjectivesChartButton;
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
        mObjectivesBarChart = (BarChart) view.findViewById(R.id.topic_stats_barchart);

        mLessons = mDbHelper.findLessonsByTopic(topicId, true);
        if(!mLessons.isEmpty()) {
            List<Entry> lineChartEntries = new ArrayList<>();

            for (Lesson lesson : mLessons) {
                long xValueDuration = lesson.getId();
                long yValueDuration = lesson.getDuration() / 1000;
                lineChartEntries.add(new Entry(xValueDuration, yValueDuration));
            }

            LineDataSet lineChartDataSet = new LineDataSet(lineChartEntries, "lesson Duration in Seconds");
            lineChartDataSet.setColor(-16776961);
            lineChartDataSet.setLineWidth(2);
            LineData lineData = new LineData(lineChartDataSet);
            mDurationLineChart.setData(lineData);
            mDurationLineChart.setDescription(null);

            XAxis xaxis = mDurationLineChart.getXAxis();
            xaxis.setLabelCount(mLessons.size(), true);

            List<BarEntry> barChartEntries = new ArrayList<>();
            List<LearningObjective> learningObjectives = mDbHelper.findLearningObjectivesByTopic(topicId);
            List<String> objectiveTitles = new ArrayList<>();

            long xValueCounter = 0;
            for(LearningObjective objective : learningObjectives) {
                String title = objective.getTitle();
                objectiveTitles.add(title);
                long xValueObjective = xValueCounter;
                xValueCounter++;
                int metObjectiveCount = mDbHelper.countMetOutcomesByLearningObjective(objective.getId());
                Log.d("LessonTracker", "Percentage " + mLessons.size());
                long metPercentage = metObjectiveCount * 100 / mLessons.size();
                long yValueObjective = metPercentage;
                barChartEntries.add(new BarEntry(xValueObjective, yValueObjective));
            }

            BarDataSet barChartDataSet = new BarDataSet(barChartEntries, "% of Objectives Met");
            barChartDataSet.setColor(-16776961);
            BarData barData = new BarData(barChartDataSet);
            mObjectivesBarChart.setData(barData);
            mObjectivesBarChart.setDescription(null);

            XAxis objectiveXAxis = mObjectivesBarChart.getXAxis();
            objectiveXAxis.setValueFormatter(new ObjectivesXAxisFormatter(objectiveTitles));
            objectiveXAxis.setGranularity(1f);

            YAxis objectiveLeftAxis = mObjectivesBarChart.getAxisLeft();
            objectiveLeftAxis.setAxisMaximum(100);
            objectiveLeftAxis.setAxisMinimum(0);
            mObjectivesBarChart.getAxisRight().setEnabled(false);


        }

        mDurationChartButton = (Button) view.findViewById(R.id.topic_stats_button_linechart);
        mDurationChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mObjectivesBarChart.setVisibility(View.GONE);
                mDurationLineChart.setVisibility(View.VISIBLE);
            }
        });

        mObjectivesChartButton = (Button) view.findViewById(R.id.topic_stats_button_barchart);
        mObjectivesChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDurationLineChart.setVisibility(View.GONE);
                mObjectivesBarChart.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}
