package com.example.user.lessontracker.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
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

    private class PieChartValueFormatter implements IValueFormatter {
        private DecimalFormat mValues;

        public PieChartValueFormatter() {
            this.mValues = new DecimalFormat("###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mValues.format(value) + " %";
        }
    }

    LineChart mDurationLineChart;
    BarChart mObjectivesBarChart;
    PieChart mNotMetPieChart;
    Button mDurationChartButton;
    Button mObjectivesChartButton;
    Button mPieChartButton;
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
        mObjectivesBarChart = (BarChart) view.findViewById(R.id.topic_stats_barchart_all);
        mNotMetPieChart = (PieChart) view.findViewById(R.id.topic_stats_piechart);

        mLessons = mDbHelper.findLessonsByTopic(topicId, true);
        if(!mLessons.isEmpty()) {
            List<Entry> lineChartEntries = new ArrayList<>();

            long xLineValueCounter = 1;
            for (Lesson lesson : mLessons) {
                long yValueDurationMinutes = lesson.getDuration() / 1000 / 60;
                lineChartEntries.add(new Entry(xLineValueCounter, yValueDurationMinutes));
                xLineValueCounter++;
            }

            LineDataSet lineChartDataSet = new LineDataSet(lineChartEntries, "lesson Duration in Minutes");
            int lineColor = ContextCompat.getColor(getContext(), R.color.navyBlue);
            lineChartDataSet.setColor(lineColor);
            lineChartDataSet.setLineWidth(2);
            LineData lineData = new LineData(lineChartDataSet);
            mDurationLineChart.setData(lineData);
            mDurationLineChart.setDescription(null);

            XAxis xaxis = mDurationLineChart.getXAxis();
            xaxis.setLabelCount(mLessons.size(), true);

            List<BarEntry> allBarChartEntries = new ArrayList<>();
            List<PieEntry> pieChartEntries = new ArrayList<>();

            List<LearningObjective> learningObjectives = mDbHelper.findLearningObjectivesByTopic(topicId);
            List<String> objectiveTitles = new ArrayList<>();

            long xBarValueCounter = 0;
            for(LearningObjective objective : learningObjectives) {
                String title = objective.getTitle();
                objectiveTitles.add(title);
                int metObjectiveCount = mDbHelper.countOutcomesByLearningObjective(objective.getId(), true);
                int notMetCount = mLessons.size() - metObjectiveCount;
                long yValueMetPercentage = metObjectiveCount * 100 / mLessons.size();

                allBarChartEntries.add(new BarEntry(xBarValueCounter, yValueMetPercentage));
                pieChartEntries.add(new PieEntry(notMetCount, title));
                xBarValueCounter++;
            }

            BarDataSet allBarChartDataSet = new BarDataSet(allBarChartEntries, "% of Objectives Met");
            int barColor = ContextCompat.getColor(getContext(), R.color.gold);
            allBarChartDataSet.setColor(barColor);
            BarData barData = new BarData(allBarChartDataSet);
            mObjectivesBarChart.setData(barData);
            mObjectivesBarChart.setDescription(null);

            XAxis objectiveXAxis = mObjectivesBarChart.getXAxis();
            objectiveXAxis.setValueFormatter(new ObjectivesXAxisFormatter(objectiveTitles));
            objectiveXAxis.setGranularity(1f);
            objectiveXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            YAxis objectiveLeftAxis = mObjectivesBarChart.getAxisLeft();
            objectiveLeftAxis.setAxisMaximum(100);
            objectiveLeftAxis.setAxisMinimum(0);
            mObjectivesBarChart.getAxisRight().setEnabled(false);

            PieDataSet pieChartDataSet = new PieDataSet(pieChartEntries, "Not Met Percentage");
            pieChartDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            pieChartDataSet.setValueFormatter(new PieChartValueFormatter());
            pieChartDataSet.setSliceSpace(2);

            PieData pieData = new PieData(pieChartDataSet);
            mNotMetPieChart.setData(pieData);
            mNotMetPieChart.setUsePercentValues(true);
            mNotMetPieChart.setDescription(null);
        }

        mDurationChartButton = (Button) view.findViewById(R.id.topic_stats_button_linechart);
        mDurationChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mObjectivesBarChart.setVisibility(View.GONE);
                mNotMetPieChart.setVisibility(View.GONE);
                mDurationLineChart.setVisibility(View.VISIBLE);
                mDurationLineChart.animateXY(1000, 1000);
            }
        });

        mObjectivesChartButton = (Button) view.findViewById(R.id.topic_stats_button_barchart_all);
        mObjectivesChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDurationLineChart.setVisibility(View.GONE);
                mNotMetPieChart.setVisibility(View.GONE);
                mObjectivesBarChart.setVisibility(View.VISIBLE);
                mObjectivesBarChart.animateY(2000);
            }
        });

        mPieChartButton = (Button) view.findViewById(R.id.topic_stats_button_piechart);
        mPieChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDurationLineChart.setVisibility(View.GONE);
                mObjectivesBarChart.setVisibility(View.GONE);
                mNotMetPieChart.setVisibility(View.VISIBLE);
                mNotMetPieChart.animateY(2000);
            }
        });

        return view;
    }
}
