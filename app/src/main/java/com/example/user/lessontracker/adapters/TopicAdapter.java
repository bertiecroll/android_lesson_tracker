package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Tag;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends ArrayAdapter<Topic> {

    public static class ViewHolder {
        TextView mTopicTitle;
        LinearLayout mLightsLayout;
    }

    LessonTrackerDbHelper mDbhelper;
    Topic mTopic;
    LearningObjective mLearningObjective;

    public TopicAdapter(Context context, List<Topic> topics) {
        super(context, R.layout.item_outcome, topics);
        mDbhelper = new LessonTrackerDbHelper(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TopicAdapter.ViewHolder viewHolder;
        mTopic = getItem(position);

        if (view == null) {
            viewHolder = new TopicAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_outcome, parent, false);
            viewHolder.mTopicTitle = (TextView) view.findViewById(R.id.outcome_list_item_outcome);
            viewHolder.mLightsLayout = (LinearLayout) view.findViewById(R.id.outcome_tag_layout);
            view.setTag(viewHolder);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        return view;
    }
}
