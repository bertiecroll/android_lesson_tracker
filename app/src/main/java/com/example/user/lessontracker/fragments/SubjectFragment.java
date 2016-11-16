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
import com.example.user.lessontracker.adapters.TopicAdapter;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mTitleTextView;
    TextView mDetailTextView;
    Button mNewTopicButton;
    ListView mTopicList;
    Subject mSubject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachable, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        mTitleTextView = (TextView) view.findViewById(R.id.teachable_title);

        mDetailTextView = (TextView) view.findViewById(R.id.teachable_detail);

        mNewTopicButton = (Button) view.findViewById(R.id.teachable_child_new_button);
        mNewTopicButton.setText(R.string.subject_new_topic_button);

        mTopicList = (ListView) view.findViewById(R.id.teachable_child_list);

        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("subjectId")) {
            long subjectId = arguments.getLong("subjectId");
            mSubject = mDbHelper.findSubject(subjectId);
        }

        if (mSubject != null) {
            mTitleTextView.setText(mSubject.getTitle());
            mDetailTextView.setText(mSubject.getDetail());

            mNewTopicButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    AddTopicFragment addTopicFragment = new AddTopicFragment();

                    Bundle args = new Bundle();
                    args.putLong("subjectId", mSubject.getId());
                    args.putString("subjectTitle", mSubject.getTitle());
                    addTopicFragment.setArguments(args);
                    transaction.replace(R.id.fragment_container, addTopicFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            List<Topic> topics = new ArrayList<>(mDbHelper.findTopicsBySubject(mSubject.getId()));
            TopicAdapter adapter = new TopicAdapter(getActivity(), topics);

            mTopicList.setAdapter(adapter);

            mTopicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Topic selectedTopic = (Topic) mTopicList.getItemAtPosition(position);
                    Log.d("mTopicList", selectedTopic + " selected");

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    TopicFragment topicFrag = new TopicFragment();

                    Bundle args = new Bundle();
                    args.putLong("topicId", selectedTopic.getId());
                    args.putString("subjectTitle", mSubject.getTitle());
                    topicFrag.setArguments(args);
                    transaction.replace(R.id.fragment_container, topicFrag);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        } else {
            mNewTopicButton.setVisibility(View.INVISIBLE);
            mTopicList.setVisibility(View.INVISIBLE);
        }

        return view;
    }

}
