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
import com.example.user.lessontracker.models.Subject;

public class SubjectFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mTitleTextView;
    TextView mDetailTextView;
    Button mNewSubjectButton;
    Button mNewTopicButton;
    Subject mSubject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        Bundle arguments = getArguments();
        if (arguments.containsKey("subjectId")) {
            long subjectId = arguments.getLong("subjectId");
            mSubject = mDbHelper.findSubject(subjectId);
        } else {
            mSubject = mDbHelper.findSubject(1);
        }

        mTitleTextView = (TextView) view.findViewById(R.id.subject_title);
        mTitleTextView.setText(mSubject.getTitle());

        mDetailTextView = (TextView) view.findViewById(R.id.subject_detail);
        mDetailTextView.setText(mSubject.getDetail());

        mNewSubjectButton = (Button) view.findViewById(R.id.subject_new_subject_button);
        mNewSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddSubjectFragment AddSubjectFrag = new AddSubjectFragment();
                transaction.replace(R.id.fragment_container, AddSubjectFrag);
                transaction.commit();
            }
        });
        mNewTopicButton = (Button) view.findViewById(R.id.subject_new_topic_button);
        if (mSubject == null) {
            mNewTopicButton.setVisibility(View.INVISIBLE);
        }
        mNewTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddTopicFragment AddTopicFrag = new AddTopicFragment();

                Bundle args = new Bundle();
                args.putLong("subjectId", mSubject.getId());
                args.putString("subjectTitle", mSubject.getTitle());
                AddTopicFrag.setArguments(args);
                transaction.replace(R.id.fragment_container, AddTopicFrag);
                transaction.commit();
            }
        });

        return view;
    }

}
