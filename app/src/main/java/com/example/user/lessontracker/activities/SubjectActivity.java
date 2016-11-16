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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.fragments.LessonListFragment;
import com.example.user.lessontracker.fragments.SubjectListFragment;

public class SubjectActivity extends AppCompatActivity {

    LinearLayout mLessonTrackerFooter;
    Button mCompletedLessonsButton;

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
