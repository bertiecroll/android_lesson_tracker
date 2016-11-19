package com.example.user.lessontracker.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.fragments.LessonListFragment;

public abstract class LessonTrackerActivity extends AppCompatActivity {

    protected abstract void getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        getFragment();
    }
}
