package com.example.user.lessontracker.fragments;

import android.graphics.Color;
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
import android.widget.RelativeLayout;
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

    RelativeLayout mRelativeLayout;
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
        mTopic = mDbHelper.findTopic(topicId);

        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.list_fragment_header_layout);
        mRelativeLayout.setVisibility(View.GONE);

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
