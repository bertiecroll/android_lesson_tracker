package com.example.user.lessontracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.user.lessontracker.models.Subject;

/**
 * Created by user on 13/11/2016.
 */

public class LessonTrackerCursorWrapper extends CursorWrapper {

    public LessonTrackerCursorWrapper(Cursor cursor) {
        super(cursor);
    }
}
