package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Tag;

import java.util.HashMap;
import java.util.List;

public class OutcomeDialogFragment extends DialogFragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mReviewPositiveText;
    TextView mReviewNegativeText;
    TextView mReviewImprovementText;
    Button mDoneButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_outcome, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        final long lessonId = arguments.getLong("lessonId");

//        mReviewLayout = (LinearLayout) view.findViewById(R.id.completed_lesson_outcome_review_layout);
        mReviewPositiveText = (TextView) view.findViewById(R.id.completed_lesson_outcome_positive);
        mReviewNegativeText = (TextView) view.findViewById(R.id.completed_lesson_outcome_negative);
        mReviewImprovementText = (TextView) view.findViewById(R.id.completed_lesson_outcome_improvement);
        mDoneButton = (Button) view.findViewById(R.id.completed_lesson_done_button);

        List<Outcome> lessonOutcomes = mDbHelper.findOutcomesByLesson(lessonId);
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
        mReviewPositiveText.setText(positives);
        String negatives = "Objectives Not Met: " + tagTypeCount.get("negative");
        mReviewNegativeText.setText(negatives);
        String improvements = "Improvements: " + tagTypeCount.get("improvement");
        mReviewImprovementText.setText(improvements);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
