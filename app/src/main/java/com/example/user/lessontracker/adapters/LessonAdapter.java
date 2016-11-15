package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;

public class LessonAdapter extends ArrayAdapter<Lesson> {

    private static class ViewHolder {
        TextView lessonDetails;
        TextView topicTitle;
    }

    LessonTrackerDbHelper mDbHelper;

    public LessonAdapter(Context context, ArrayList<Lesson> lessons) {
        super(context, R.layout.item_lesson, lessons);
        mDbHelper = new LessonTrackerDbHelper(getContext());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Lesson lesson = getItem(position);
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_lesson, parent, false);
            viewHolder.lessonDetails = (TextView) view.findViewById(R.id.lesson_list_item_lesson);
            viewHolder.topicTitle = (TextView) view.findViewById(R.id.lesson_list_item_topic);
            view.setTag(viewHolder);
        } else {
           viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.lessonDetails.setText(lesson.toString());

        Topic topic = mDbHelper.findTopic(lesson.getTopicId());
        viewHolder.topicTitle.setText(topic.getTitle());

        return view;
    }
}
