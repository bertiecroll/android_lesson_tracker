package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Tag;
import com.example.user.lessontracker.models.Topic;

import java.util.HashMap;
import java.util.List;

public class CompletedLessonAdapter extends ArrayAdapter<Lesson> {

    private static class ViewHolder {
        TextView lessonDetailsTextView;
        TextView topicTitleTextView;
    }

    LessonTrackerDbHelper mDbHelper;

    public CompletedLessonAdapter(Context context, List<Lesson> lessons) {
        super(context, R.layout.item_lesson, lessons);
        mDbHelper = new LessonTrackerDbHelper(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Lesson completedLesson = getItem(position);
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new CompletedLessonAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_completed_lesson, parent, false);
            viewHolder.topicTitleTextView = (TextView) view.findViewById(R.id.completed_lesson_list_item_topic);
            viewHolder.lessonDetailsTextView = (TextView) view.findViewById(R.id.completed_lesson_list_item_lesson);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CompletedLessonAdapter.ViewHolder) view.getTag();
        }

        Topic topic = mDbHelper.findTopic(completedLesson.getTopicId());
        viewHolder.topicTitleTextView.setText(topic.getTitle());

        viewHolder.lessonDetailsTextView.setText(completedLesson.toString());

        return view;
    }

}
