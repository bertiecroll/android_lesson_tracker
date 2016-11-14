package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerCursorWrapper;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;

public class AddLearningObjectiveFragment extends Fragment {

    TextView mTopicText;
    EditText mTitleEditText;
    EditText mDetailEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_learning_objective, container, false);

        String topicTitle = getArguments().getString("topicTitle");
        mTopicText = (TextView) view.findViewById(R.id.learning_objective_topic_text);
        mTopicText.setText(topicTitle);

        mTitleEditText = (EditText) view.findViewById(R.id.learning_objective_title_edit);
        mDetailEditText = (EditText) view.findViewById(R.id.learning_objective_detail_edit);

        return view;
    }
}
