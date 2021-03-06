package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;

public class AddLearningObjectiveFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mTopicText;
    EditText mTitleEditText;
    EditText mDetailEditText;
    Button mAddButton;
    Button mFinishButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_teachable, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        Bundle receivedArgs = getArguments();
        final String topicTitle = receivedArgs.getString(TopicFragment.TOPIC_TITLE);
        final long subjectId = receivedArgs.getLong(SubjectFragment.SUBJECT_ID);
        final long topicId = receivedArgs.getLong(TopicFragment.TOPIC_ID);

        mTopicText = (TextView) view.findViewById(R.id.add_parent_title_text);
        mTopicText.setText(topicTitle);

        mTitleEditText = (EditText) view.findViewById(R.id.add_title_edit);
        mTitleEditText.setHint(R.string.learning_objective_title_hint);

        mDetailEditText = (EditText) view.findViewById(R.id.add_detail_edit);
        mDetailEditText.setHint(R.string.learning_objective_detail_hint);

        mAddButton = (Button) view.findViewById(R.id.add_button);
        mAddButton.setText(R.string.learning_objective_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LessonTracker", "AddLearningObjective button clicked");

                String title = mTitleEditText.getText().toString();
                String detail = mDetailEditText.getText().toString();
                LearningObjective learningObjective =
                        new LearningObjective(topicId, title, detail);
                mDbHelper.saveLearningObjective(learningObjective);

                mTitleEditText.setText(null);
                mDetailEditText.setText(null);
                Toast.makeText(getActivity(), R.string.learning_objective_toast_add_success, Toast.LENGTH_SHORT).show();
            }
        });

        mFinishButton = (Button) view.findViewById(R.id.add_finish_button);
        mFinishButton.setText(R.string.learning_objective_finish_button);
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SubjectFragment subjectFrag = new SubjectFragment();

                Bundle subjectArgs = new Bundle();
                subjectArgs.putLong(SubjectFragment.SUBJECT_ID, subjectId);
                subjectFrag.setArguments(subjectArgs);

                transaction.remove(AddLearningObjectiveFragment.this);
                transaction.commit();
                getFragmentManager().popBackStack();
                Toast.makeText(getActivity(), R.string.topic_toast_add_success, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
