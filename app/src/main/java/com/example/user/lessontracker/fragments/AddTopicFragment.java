package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Topic;

public class AddTopicFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mSubjectText;
    EditText mTitleEditText;
    EditText mDetailEditText;
    Button mAddButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_teachable, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        final Bundle receivedArgs = getArguments();
        final long subjectId = receivedArgs.getLong(SubjectFragment.SUBJECT_ID);
        final String subjectTitle = receivedArgs.getString(SubjectFragment.SUBJECT_TITLE);

        mSubjectText = (TextView) view.findViewById(R.id.add_parent_title_text);
        mSubjectText.setText(subjectTitle);

        mTitleEditText = (EditText) view.findViewById(R.id.add_title_edit);
        mTitleEditText.setHint(R.string.topic_title_hint);

        mDetailEditText = (EditText) view.findViewById(R.id.add_detail_edit);
        mDetailEditText.setHint(R.string.topic_detail_hint);

        mAddButton = (Button) view.findViewById(R.id.add_button);
        mAddButton.setText(R.string.topic_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicTitle = mTitleEditText.getText().toString();
                String topicDetail = mDetailEditText.getText().toString();
                Topic topic = new Topic(subjectId, topicTitle, topicDetail);
                long topicId = mDbHelper.saveTopic(topic);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddLearningObjectiveFragment addLearningObjectiveFragment =
                        new AddLearningObjectiveFragment();

                Bundle args = new Bundle();
                args.putLong(TopicFragment.TOPIC_ID, topicId);
                args.putString(TopicFragment.TOPIC_TITLE, topicTitle);
                args.putLong(SubjectFragment.SUBJECT_ID, subjectId);
                addLearningObjectiveFragment.setArguments(args);
                transaction.replace(R.id.fragment_container, addLearningObjectiveFragment);
                transaction.commit();
                Toast.makeText(getActivity(), R.string.topic_toast_add_success, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
