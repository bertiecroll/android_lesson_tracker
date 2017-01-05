package com.example.user.lessontracker.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Tag;
import com.example.user.lessontracker.models.Tagging;

import java.util.ArrayList;
import java.util.List;

public class TaggingDialogFragment extends DialogFragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mTaggingTitle;
    TextView mLearningObjectiveTitle;
    ToggleButton mObjectiveMetToggle;
    LinearLayout mTagContainer;
    Button mCompleteButton;
    Outcome mCurrentOutcome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_tagging, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        String learningObjectiveTitle = arguments.getString("learningObjectiveTitle");
        final long outcomeId = arguments.getLong(LessonListFragment.OUTCOME_ID);
        final long lessonId = arguments.getLong(LessonListFragment.LESSON_ID);
        final long lessonStartTime = arguments.getLong(LessonListFragment.LESSON_START_TIME);

        mCurrentOutcome = mDbHelper.findOutcome(outcomeId);

        mObjectiveMetToggle = (ToggleButton) view.findViewById(R.id.tagging_objective_met_toggle);
        if (mCurrentOutcome.hasObjectiveBeenMet()) {
            mObjectiveMetToggle.setChecked(true);
        } else {
            mObjectiveMetToggle.setChecked(false);
        }

        List<Tag> allTags = new ArrayList<>(mDbHelper.allTags());
        List<Long> usedTagIds = new ArrayList<>(mDbHelper.findOutcomeTagIds(outcomeId));

        mTagContainer = (LinearLayout) view.findViewById(R.id.tagging_tag_container);
        for (final Tag tag : allTags) {
            if (!usedTagIds.contains(tag.getId())) {
                final ImageButton tagButton = new ImageButton(getActivity());
                int ResId = tag.getIconResourceId();
                tagButton.setImageResource(ResId);
                tagButton.setBackgroundColor(Color.TRANSPARENT);
                mTagContainer.addView(tagButton,
                        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                tagButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("LessonTracker", tag.getTitle() + "Tag button normal click");
                        Tagging tagging = new Tagging(tag.getId(), outcomeId);
                        mDbHelper.saveTagging(tagging);
                        tagButton.setVisibility(View.GONE);
                    }
                });

                tagButton.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Log.d("LessonTracker", tag.getTitle() + "Tag button long click");
                        Toast.makeText(getActivity(), tag.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }

        }

        mTaggingTitle = (TextView) view.findViewById(R.id.tagging_title);
        mLearningObjectiveTitle = (TextView) view.findViewById(R.id.tagging_learning_objective_title);
        mLearningObjectiveTitle.setText(learningObjectiveTitle);

        mCompleteButton = (Button) view.findViewById(R.id.tagging_complete_button);
        mCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LessonTracker", "Complete button click");
                if(mObjectiveMetToggle.isChecked()) {
                    mCurrentOutcome.setObjectiveMet(true);
                } else {
                    mCurrentOutcome.setObjectiveMet(false);
                }
                mDbHelper.updateOutcome(mCurrentOutcome);
                getFragmentManager().popBackStack();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TakeLessonFragment takeLessonFragment = new TakeLessonFragment();
                Bundle args = new Bundle();
                args.putLong(LessonListFragment.LESSON_ID, lessonId);
                args.putLong(LessonListFragment.LESSON_START_TIME, lessonStartTime);
                takeLessonFragment.setArguments(args);
                transaction.replace(R.id.fragment_container, takeLessonFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                getDialog().dismiss();
            }
        });

        return view;
    }
}
