package com.example.user.lessontracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.models.Subject;

/**
 * Created by user on 13/11/2016.
 */

public class LessonTrackerCursorWrapper extends CursorWrapper {

    public LessonTrackerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Subject getSubject() {
        long id = getLong(getColumnIndex(SubjectTable.Cols.ID));
        String title = getString(getColumnIndex(SubjectTable.Cols.TITLE));
        String detail = getString(getColumnIndex(SubjectTable.Cols.DETAIL));

        Subject subject = new Subject(id, title, detail);
        return subject;
    }
}
