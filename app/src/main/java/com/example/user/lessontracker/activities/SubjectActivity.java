package com.example.user.lessontracker.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.fragments.LessonHistoryListFragment;
import com.example.user.lessontracker.fragments.LessonListFragment;
import com.example.user.lessontracker.fragments.SubjectListFragment;
import com.example.user.lessontracker.fragments.TakeLessonFragment;

public class SubjectActivity extends AppCompatActivity {

    LinearLayout mLessonTrackerFooter;
    Button mCompletedLessonsButton;
    Button mPendingLessonsButton;
    Button mSubjectsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new SubjectListFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_fragment, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_lessons) {
            Log.d("LessonTracker", "lessons menu item selected");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            LessonListFragment lessonListFragment = new LessonListFragment();

            transaction.replace(R.id.fragment_container, lessonListFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
