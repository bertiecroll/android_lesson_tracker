package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Outcome;

import java.util.List;

public class OutcomeAdapter extends ArrayAdapter<Outcome> {

    public static class ViewHolder {
        TextView mLearningObjectiveTitle;
    }

    LessonTrackerDbHelper mDbhelper;

    public OutcomeAdapter(Context context, List<Outcome> outcomes) {
        super(context, R.layout.item_outcome, outcomes);
        mDbhelper = new LessonTrackerDbHelper(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return null;
    }

}
