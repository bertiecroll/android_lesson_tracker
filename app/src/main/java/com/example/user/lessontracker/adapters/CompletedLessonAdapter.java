package com.example.user.lessontracker.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;

import java.util.List;

public class CompletedLessonAdapter extends ArrayAdapter {



    LessonTrackerDbHelper mDbHelper;

    public CompletedLessonAdapter(Context context, List<Lesson> lessons) {
        super(context, R.layout.item_lesson, lessons);
        mDbHelper = new LessonTrackerDbHelper(context);
    }

}
