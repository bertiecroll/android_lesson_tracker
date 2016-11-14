package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Topic;

public class TopicFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    Topic mTopic;
    TextView mSubjectTitleTextView;
    TextView mTitleTextView;
    TextView mDetailTextView;
    Button mNewLearningObjectiveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        long topicId = arguments.getLong("topicId");
        String subjectTitle = arguments.getString("subjectTitle");
        mTopic = mDbHelper.findTopic(topicId);

        mSubjectTitleTextView = (TextView) view.findViewById(R.id.topic_subject_title);
        mSubjectTitleTextView.setText(subjectTitle);

        mTitleTextView = (TextView) view.findViewById(R.id.topic_title);
        mTitleTextView.setText(mTopic.getTitle());

        mDetailTextView = (TextView) view.findViewById(R.id.topic_detail);
        mDetailTextView.setText(mTopic.getDetail());

        mNewLearningObjectiveButton = (Button) view.findViewById(R.id.topic_new_learning_objective_button);
        mNewLearningObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddLearningObjectiveFragment addLearningObjectiveFragment =
                        new AddLearningObjectiveFragment();

                Bundle args = new Bundle();
                args.putLong("topicId", mTopic.getId());
                args.putString("topicTitle", mTopic.getTitle());
                addLearningObjectiveFragment.setArguments(args);

                transaction.replace(R.id.fragment_container, addLearningObjectiveFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
