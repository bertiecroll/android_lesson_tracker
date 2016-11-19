package com.example.user.lessontracker.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.fragments.LessonHistoryListFragment;
import com.example.user.lessontracker.fragments.LessonListFragment;
import com.example.user.lessontracker.fragments.SubjectListFragment;

public class TeachableActivity extends LessonTrackerActivity {

    LinearLayout mLessonTrackerFooter;
    Button mCompletedLessonsButton;
    Button mPendingLessonsButton;
    Button mSubjectsButton;
    Button mTagsButton;

    @Override
    protected void getFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new LessonListFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
                Toast.makeText(TeachableActivity.this, "Custom Tags Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
