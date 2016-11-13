package com.example.user.lessontracker.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class LessonTrackerDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "lessonTracker.db";

    private static final String CREATE_TABLE_SUBJECT = "create table "
            + SubjectTable.NAME + "( " + SubjectTable.Cols.ID + " integer primary key autoincrement, "
            + SubjectTable.Cols.TITLE + " text, " + SubjectTable.Cols.DETAIL + " text )";

    private static final String CREATE_TABLE_TOPIC = "create table "
            + TopicTable.NAME + "( " + TopicTable.Cols.ID + " integer primary key autoincrement, "
            + TopicTable.Cols.SUBJECT_ID + " integer, " + TopicTable.Cols.TITLE + " text, "
            + TopicTable.Cols.DETAIL + " text )";

    public LessonTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_TOPIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveSubject(Subject subject) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(SubjectTable.NAME, null, subject.getContentValues());
        subject.setId(id);
    }

    public void updateSubject(Subject subject) {
        SQLiteDatabase database = getDatabase();
        database.update(SubjectTable.NAME, subject.getContentValues(),
                SubjectTable.Cols.ID + " = ?", new String[] { String.valueOf(subject.getId())});
    }

    public Subject findSubject(long id) {
        LessonTrackerCursorWrapper cursor = query(SubjectTable.NAME,
                SubjectTable.Cols.ID + " = ?", new String[] { Long.toString(id)} );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getSubject();
        } finally {
            cursor.close();
        }
    }

    public List<Subject> allSubjects() {
        List<Subject> subjects = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(SubjectTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                subjects.add(cursor.getSubject());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return subjects;
    }

    private SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }

    private LessonTrackerCursorWrapper query(String tableName, String whereClause, String[] whereArgs) {
        SQLiteDatabase database = getDatabase();
        Cursor cursor = database.query(
            tableName, null, whereClause, whereArgs, null, null, null);
        return new LessonTrackerCursorWrapper(cursor);
    }


}
