package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class OutcomeAdapter extends ArrayAdapter<Outcome> {

    public static class ViewHolder {
        TextView mObjectiveMetIndicator;
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

            viewHolder.mObjectiveMetIndicator = (TextView) view.findViewById(R.id.outcome_objective_met_indicator);
            if (mOutcome.hasObjectiveBeenMet()) {
                viewHolder.mObjectiveMetIndicator.setBackgroundColor(Color.GREEN);
            } else {
                viewHolder.mObjectiveMetIndicator.setBackgroundColor(Color.RED);
            }
            viewHolder.mObjectiveMetIndicator.setAlpha(0.75f);

            viewHolder.mLearningObjectiveTitle = (TextView) view.findViewById(R.id.outcome_list_item_outcome);
            viewHolder.mTagLayout = (LinearLayout) view.findViewById(R.id.outcome_tag_layout);
            view.setTag(viewHolder);
            List<Tag> outcomeTags = new ArrayList<>(mDbhelper.findOutcomeTags(mOutcome.getId()));
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            for (final Tag tag : outcomeTags) {
                final ImageView tagView = new ImageView(getContext());
                int ResId = tag.getIconResourceId();
                tagView.setImageResource(ResId);
                tagView.setBackgroundColor(Color.TRANSPARENT);
                viewHolder.mTagLayout.addView(tagView, params);

                tagView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Toast.makeText(getContext(), tag.getTitle(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        } else {
            viewHolder = (OutcomeAdapter.ViewHolder) view.getTag();
        }

        viewHolder.mLearningObjectiveTitle.setText(mLearningObjective.getTitle());

        return view;
    }

}
