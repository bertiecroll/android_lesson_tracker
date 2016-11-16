package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView outcomeReviewTextView;
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
            viewHolder.lessonDetailsTextView = (TextView) view.findViewById(R.id.completed_lesson_list_item_lesson);
            viewHolder.topicTitleTextView = (TextView) view.findViewById(R.id.completed_lesson_list_item_topic);
            viewHolder.outcomeReviewTextView = (TextView) view.findViewById(R.id.completed_lesson_outcome_review);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CompletedLessonAdapter.ViewHolder) view.getTag();
        }

        viewHolder.lessonDetailsTextView.setText(completedLesson.toString());

        Topic topic = mDbHelper.findTopic(completedLesson.getTopicId());
        viewHolder.topicTitleTextView.setText(topic.getTitle());

        List<Outcome> lessonOutcomes = mDbHelper.findOutcomesByLesson(completedLesson.getId());
        HashMap<String, Integer> tagTypeCount = new HashMap<>();
        for (Outcome outcome : lessonOutcomes) {
            List<Tag> outcomeTags = mDbHelper.findOutcomeTags(outcome.getId());
            for (Tag tag : outcomeTags) {
                String tagType = tag.getType();
                if (tagTypeCount.get(tagType) == null) {
                    tagTypeCount.put(tagType, 0);
                } else {
                    tagTypeCount.put(tagType, tagTypeCount.get(tagType) + 1);
                }
            }
        }
        String outcomeReview = "";
        if (tagTypeCount.get("negative") == null && tagTypeCount.get("improvement") == null) {
            outcomeReview += "Perfect lesson! No improvements needed";
        } else if (tagTypeCount.get("negative") == null) {
            outcomeReview += "Good lesson, though some improvements required";
        } else {
            outcomeReview += "Lesson needs work. Check full review";
        }

        viewHolder.outcomeReviewTextView.setText(outcomeReview);

        return view;
    }

}
