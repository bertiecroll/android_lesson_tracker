package com.example.user.lessontracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Topic;

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

    public Topic getTopic() {
        long id = getLong(getColumnIndex(TopicTable.Cols.ID));
        long subject_id = getLong(getColumnIndex(TopicTable.Cols.SUBJECT_ID));
        String title = getString(getColumnIndex(TopicTable.Cols.TITLE));
        String detail = getString(getColumnIndex(TopicTable.Cols.DETAIL));

        Topic topic = new Topic(id, subject_id, title, detail);
        return topic;
    }
}
