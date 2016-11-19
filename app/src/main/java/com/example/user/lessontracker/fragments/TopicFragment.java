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
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicFragment extends Fragment {

    public static final String TOPIC_ID = "topicId";
    public static final String TOPIC_TITLE = "topicTitle";

    LessonTrackerDbHelper mDbHelper;
    Topic mTopic;
    TextView mSubjectTitleTextView;
    TextView mTitleTextView;
    TextView mDetailTextView;
    Button mNewLearningObjectiveButton;
    ListView mLearningObjectiveList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachable, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        long topicId = arguments.getLong(TOPIC_ID);
        String subjectTitle = arguments.getString(SubjectFragment.SUBJECT_TITLE);
        mTopic = mDbHelper.findTopic(topicId);

        mSubjectTitleTextView = (TextView) view.findViewById(R.id.teachable_parent_title);
        mSubjectTitleTextView.setText(subjectTitle);

        mTitleTextView = (TextView) view.findViewById(R.id.teachable_title);
        mTitleTextView.setText(mTopic.getTitle());

        mDetailTextView = (TextView) view.findViewById(R.id.teachable_detail);
        mDetailTextView.setText(mTopic.getDetail());

        mNewLearningObjectiveButton = (Button) view.findViewById(R.id.teachable_child_new_button);
        mNewLearningObjectiveButton.setText(R.string.topic_new_learning_objective_button);
        mNewLearningObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddLearningObjectiveFragment addLearningObjectiveFragment =
                        new AddLearningObjectiveFragment();

                Bundle args = new Bundle();
                args.putLong(TOPIC_ID, mTopic.getId());
                args.putString(TOPIC_TITLE, mTopic.getTitle());
                addLearningObjectiveFragment.setArguments(args);

                transaction.replace(R.id.fragment_container, addLearningObjectiveFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mLearningObjectiveList = (ListView) view.findViewById(R.id.teachable_child_list);

        List<LearningObjective> learningObjectives =
                new ArrayList<>(mDbHelper.findLearningObjectivesByTopic(topicId));

        ArrayAdapter<LearningObjective> adapter =
        new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, learningObjectives);

        mLearningObjectiveList.setAdapter(adapter);
        mLearningObjectiveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LearningObjective selectedLearningObjective =
                        (LearningObjective) mLearningObjectiveList.getItemAtPosition(position);
                Log.d("LessonTracker", selectedLearningObjective + " selected");
                String toastText = selectedLearningObjective.getDetail();
                Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
