package com.example.user.lessontracker.activities;

import android.content.Intent;
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
    Button mCohortsButton;

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
                Intent intent = new Intent(LessonTrackerActivity.this, LessonHistoryActivity.class);
                startActivity(intent);
            }
        });
        mPendingLessonsButton = (Button) findViewById(R.id.lesson_tracker_footer_take_lesson_button);
        mPendingLessonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonTrackerActivity.this, TakeLessonActivity.class);
                startActivity(intent);
            }
        });

        mSubjectsButton = (Button) findViewById(R.id.lesson_tracker_footer_subjects_button);
        mSubjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonTrackerActivity.this, TeachableActivity.class);
                startActivity(intent);
            }
        });

        mCohortsButton = (Button) findViewById(R.id.lesson_tracker_footer_cohorts_button);
        mCohortsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonTrackerActivity.this, CohortActivity.class);
                startActivity(intent);
            }
        });
    }
}
