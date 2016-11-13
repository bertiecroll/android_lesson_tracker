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
        View view = inflater.inflate(R.layout.fragment_add_topic, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        mSubjectText = (TextView) view.findViewById(R.id.topic_subject_text);
        String subjectTitle = getArguments().getString("subjectTitle");
        mSubjectText.setText(subjectTitle);

        mTitleEditText = (EditText) view.findViewById(R.id.topic_title_edit);
        mDetailEditText = (EditText) view.findViewById(R.id.topic_detail_edit);
        mAddButton = (Button) view.findViewById(R.id.topic_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long subjectId = getArguments().getLong("subjectId");
                String title = mTitleEditText.getText().toString();
                String detail = mDetailEditText.getText().toString();
                Topic topic = new Topic(subjectId, title, detail);
                mDbHelper.saveTopic(topic);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SubjectFragment fragment = new SubjectFragment();
                Bundle args = new Bundle();
                args.putLong("subjectId", subjectId);
                fragment.setArguments(args);

                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });


        return view;
    }
}
