package com.example.user.lessontracker.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.fragments.LessonHistoryListFragment;
import com.example.user.lessontracker.fragments.LessonListFragment;
import com.example.user.lessontracker.fragments.SubjectListFragment;

public abstract class LessonTrackerActivity extends AppCompatActivity {

    LinearLayout mLessonTrackerFooter;
    Button mCompletedLessonsButton;
    Button mPendingLessonsButton;
    Button mSubjectsButton;
    Button mTagsButton;

    protected abstract void getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        getFragment();

        mLessonTrackerFooter = (LinearLayout) findViewById(R.id.lesson_tracker_footer_menu);
        mCompletedLessonsButton = (Button) findViewById(R.id.lesson_tracker_footer_completed_lessons_button);
        mCompletedLessonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                LessonHistoryListFragment lessonHistoryListFragment =
                        new LessonHistoryListFragment();
                transaction.replace(R.id.fragment_container, lessonHistoryListFragment, null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mPendingLessonsButton = (Button) findViewById(R.id.lesson_tracker_footer_take_lesson_button);
        mPendingLessonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                LessonListFragment lessonListFragment =
                        new LessonListFragment();
                transaction.replace(R.id.fragment_container, lessonListFragment, null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mSubjectsButton = (Button) findViewById(R.id.lesson_tracker_footer_subjects_button);
        mSubjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                SubjectListFragment subjectListFragment =
                        new SubjectListFragment();
                transaction.replace(R.id.fragment_container, subjectListFragment, null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mTagsButton = (Button) findViewById(R.id.lesson_tracker_footer_tags_button);
        mTagsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Custom Tags Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}