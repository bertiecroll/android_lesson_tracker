package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.adapters.OutcomeAdapter;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TakeLessonFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    Lesson mLesson;
    Topic mTopic;
    TextView mDetailsTextView;
    TextView mTopicTitleTextView;
    ListView mOutcomeList;
    EditText mNotesEditText;
    Button mCompleteButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_lesson, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle arguments = getArguments();
        final long lessonId = arguments.getLong(LessonListFragment.LESSON_ID);
        final long lessonStartTime = arguments.getLong(LessonListFragment.LESSON_START_TIME);
        Log.d("LessonTracker", "Start time first = " + Long.toString(lessonStartTime));
        mLesson = mDbHelper.findLesson(lessonId);
        mTopic = mDbHelper.findTopic(mLesson.getTopicId());

        mDetailsTextView = (TextView) view.findViewById(R.id.take_lesson_details);
        mDetailsTextView.setText(mLesson.toString());

        mTopicTitleTextView = (TextView) view.findViewById(R.id.take_lesson_topic_title);
        mTopicTitleTextView.setText(mTopic.getTitle());

        mOutcomeList = (ListView) view.findViewById(R.id.take_lesson_learning_objectives);
        List<Outcome> outcomes = new ArrayList<>(mDbHelper.findOutcomesByLesson(lessonId));
        OutcomeAdapter outcomeAdapter =
                new OutcomeAdapter(getActivity(), outcomes);
        mOutcomeList.setAdapter(outcomeAdapter);

        mOutcomeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TaggingDialogFragment taggingDialog = new TaggingDialogFragment();

                Outcome selectedOutcome = (Outcome) mOutcomeList.getItemAtPosition(position);
                long learningObjectiveId = selectedOutcome.getLearningObjectiveId();
                LearningObjective learningObjective = mDbHelper.findLearningObjective(learningObjectiveId);
                String learningObjectiveTitle = learningObjective.getTitle();
                Bundle args = new Bundle();
                args.putString("learningObjectiveTitle", learningObjectiveTitle);
                args.putLong(LessonListFragment.OUTCOME_ID, selectedOutcome.getId());
                args.putLong(LessonListFragment.LESSON_ID, lessonId);
                args.putLong(LessonListFragment.LESSON_START_TIME, lessonStartTime);

                taggingDialog.setArguments(args);
                transaction.add(taggingDialog, null);
                transaction.commit();
            }
        });

        mNotesEditText = (EditText) view.findViewById(R.id.take_lesson_notes_edit);

        mCompleteButton = (Button) view.findViewById(R.id.take_lesson_complete_button);
        mCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long lessonEndTime = new Date().getTime();
                Log.d("LessonTracker", "Start time second = " + Long.toString(lessonStartTime));
                Log.d("LessonTracker", "End time = " + Long.toString(lessonEndTime));
                long duration = (lessonEndTime - lessonStartTime);
                Log.d("LessonTracker", "lesson duration = " + Long.toString(duration));
                mLesson.teach(duration);
                mLesson.setNotes(mNotesEditText.getText().toString());
                mDbHelper.updateLesson(mLesson);
                getFragmentManager().popBackStack();
                Toast.makeText(getActivity(), R.string.take_lesson_toast_taught_success, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
