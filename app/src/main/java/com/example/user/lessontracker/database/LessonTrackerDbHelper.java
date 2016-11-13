package com.example.user.lessontracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;
import com.example.user.lessontracker.models.Subject;

public class LessonTrackerDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "lessonTracker.db";

    private static final String CREATE_TABLE_SUBJECT = "create table "
            + SubjectTable.NAME + "( _id integer primary key autoincrement, "
            + SubjectTable.Cols.TITLE + " text, " + SubjectTable.Cols.DETAIL + " text )";

    private static final String CREATE_TABLE_TOPIC = "create table "
            + TopicTable.NAME + "( _id integer primary key autoincrement, "
            + TopicTable.Cols.TITLE + " text, " + TopicTable.Cols.DETAIL + " text )";

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
        SQLiteDatabase database = this.getWritableDatabase();
        long id = database.insert(SubjectTable.NAME, null, subject.getContentValues());
        subject.setId(id);
    }

    public void updateSubject(Subject subject) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.update(SubjectTable.NAME, subject.getContentValues(),
                "_id = ?", new String[] { String.valueOf(subject.getId())});
    }


}
