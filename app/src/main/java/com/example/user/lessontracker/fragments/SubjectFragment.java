package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mTitleTextView;
    TextView mDetailTextView;
    Button mNewSubjectButton;
    Button mNewTopicButton;
    ListView mTopicList;
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
        if (arguments != null && arguments.containsKey("subjectId")) {
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
        mTopicList = (ListView) view.findViewById(R.id.subject_topics_list);

        if (mSubject != null) {
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

            List<Topic> topics = new ArrayList<>(mDbHelper.findTopicsBySubject(mSubject.getId()));
            ArrayAdapter<Topic> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, topics);

            mTopicList.setAdapter(adapter);

            mTopicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Topic selectedTopic = (Topic) mTopicList.getItemAtPosition(position);
                   Log.d("mTopicList", selectedTopic + " selected");
                }
            });
        } else {
            mNewTopicButton.setVisibility(View.INVISIBLE);
            mTopicList.setVisibility(View.INVISIBLE);
        }

        return view;
    }

}
