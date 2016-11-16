package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class OutcomeAdapter extends ArrayAdapter<Outcome> {

    public static class ViewHolder {
        TextView mLearningObjectiveTitle;
        LinearLayout mTagLayout;
    }

    LessonTrackerDbHelper mDbhelper;
    Outcome mOutcome;
    LearningObjective mLearningObjective;

    public OutcomeAdapter(Context context, List<Outcome> outcomes) {
        super(context, R.layout.item_outcome, outcomes);
        mDbhelper = new LessonTrackerDbHelper(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        mOutcome = getItem(position);
        mLearningObjective = mDbhelper.findLearningObjective(mOutcome.getLearningObjectiveId());

        if (view == null) {
            viewHolder = new OutcomeAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_outcome, parent, false);
            viewHolder.mLearningObjectiveTitle = (TextView) view.findViewById(R.id.outcome_list_item_outcome);
            viewHolder.mTagLayout = (LinearLayout) view.findViewById(R.id.outcome_tag_layout);
            view.setTag(viewHolder);
        } else {
            viewHolder = (OutcomeAdapter.ViewHolder) view.getTag();
        }

        viewHolder.mLearningObjectiveTitle.setText(mLearningObjective.getTitle());

//        List<Tag> outcomeTags = new ArrayList<>(mDbhelper.findT);


        return view;
    }

}
