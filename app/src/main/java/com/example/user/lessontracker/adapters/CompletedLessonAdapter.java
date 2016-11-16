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
        LinearLayout outcomeReviewLayout;
        TextView outcomePositiveText;
        TextView outcomeNegativeText;
        TextView outcomeImprovementText;
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
            viewHolder.outcomeReviewLayout = (LinearLayout) view.findViewById(R.id.completed_lesson_outcome_review_layout);
            viewHolder.outcomePositiveText = (TextView) view.findViewById(R.id.completed_lesson_outcome_positive);
            viewHolder.outcomeNegativeText = (TextView) view.findViewById(R.id.completed_lesson_outcome_negative);
            viewHolder.outcomeImprovementText = (TextView) view.findViewById(R.id.completed_lesson_outcome_improvement);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CompletedLessonAdapter.ViewHolder) view.getTag();
        }

        Topic topic = mDbHelper.findTopic(completedLesson.getTopicId());
        viewHolder.topicTitleTextView.setText(topic.getTitle());

        viewHolder.lessonDetailsTextView.setText(completedLesson.toString());

        List<Outcome> lessonOutcomes = mDbHelper.findOutcomesByLesson(completedLesson.getId());
        HashMap<String, Integer> tagTypeCount = new HashMap<>();
        tagTypeCount.put("positive", 0);
        tagTypeCount.put("negative", 0);
        tagTypeCount.put("improvement", 0);

        for (Outcome outcome : lessonOutcomes) {
            List<Tag> outcomeTags = mDbHelper.findOutcomeTags(outcome.getId());
            for (Tag tag : outcomeTags) {
                String tagType = tag.getType();
                tagTypeCount.put(tagType, tagTypeCount.get(tagType) + 1);
            }
        }

        String positives = "Objectives Met: " + tagTypeCount.get("positive");
        viewHolder.outcomePositiveText.setText(positives);
        String negatives = "Objectives Not Met: " + tagTypeCount.get("negative");
        viewHolder.outcomeNegativeText.setText(negatives);
        String improvements = "Improvements: " + tagTypeCount.get("improvement");
        viewHolder.outcomeImprovementText.setText(improvements);

        return view;
    }

}
