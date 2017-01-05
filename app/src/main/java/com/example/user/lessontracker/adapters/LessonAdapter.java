package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Cohort;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Topic;

import java.util.List;

public class LessonAdapter extends ArrayAdapter<Lesson> {

    private static class ViewHolder {
        TextView topicTitleTextView;
        TextView lessonCohortTextView;
        TextView lessonDateTextView;
    }

    LessonTrackerDbHelper mDbHelper;

    public LessonAdapter(Context context, List<Lesson> lessons) {
        super(context, R.layout.item_lesson, lessons);
        mDbHelper = new LessonTrackerDbHelper(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Lesson lesson = getItem(position);
        ViewHolder viewHolder;

        Cohort cohort = mDbHelper.findCohort(lesson.getCohortId());

        if (view == null) {
            viewHolder = new LessonAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_lesson, parent, false);
            viewHolder.topicTitleTextView = (TextView) view.findViewById(R.id.completed_lesson_list_item_topic);
            viewHolder.lessonCohortTextView = (TextView) view.findViewById(R.id.completed_lesson_list_item_cohort);
            viewHolder.lessonDateTextView = (TextView) view.findViewById(R.id.completed_lesson_list_item_date);
            view.setTag(viewHolder);
        } else {
            viewHolder = (LessonAdapter.ViewHolder) view.getTag();
        }

        Topic topic = mDbHelper.findTopic(lesson.getTopicId());
        viewHolder.topicTitleTextView.setText(topic.getTitle());

        String cohortName = "Cohort: " + cohort.getName();
        viewHolder.lessonCohortTextView.setText(cohortName);

        String date = "Date: " + lesson.printDate();
        viewHolder.lessonDateTextView.setText(date);

        return view;
    }

}
