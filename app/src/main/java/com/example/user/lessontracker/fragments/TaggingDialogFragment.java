package com.example.user.lessontracker.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Tag;
import com.example.user.lessontracker.models.Tagging;

import java.util.ArrayList;
import java.util.List;

public class TaggingDialogFragment extends DialogFragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mTaggingTitle;
    TextView mLearningObjectiveTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_tagging, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        String learningObjectiveTitle = arguments.getString("learningObjectiveTitle");
        final long outcomeId = arguments.getLong("OutcomeId");


        List<Tag> tags = new ArrayList<>(mDbHelper.allTags());
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.tagging_dialog_fragment);
        LinearLayout buttonLayout = new LinearLayout(getActivity());
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(buttonLayout);
        for (final Tag tag : tags) {
            final ImageButton tagButton = new ImageButton(getActivity());
            int ResId = tag.getIconResourceId();
            tagButton.setImageResource(ResId);
            tagButton.setBackgroundColor(Color.TRANSPARENT);
            buttonLayout.addView(tagButton,
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            tagButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("LessonTracker", tag.getTitle() + "Tag button normal click");
                    Tagging tagging = new Tagging(tag.getId(), outcomeId);
                    mDbHelper.saveTagging(tagging);
                    tagButton.setVisibility(View.INVISIBLE);
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

        mTaggingTitle = (TextView) view.findViewById(R.id.tagging_title);
        mLearningObjectiveTitle = (TextView) view.findViewById(R.id.tagging_learning_objective_title);
        mLearningObjectiveTitle.setText(learningObjectiveTitle);



        return view;
    }


}
