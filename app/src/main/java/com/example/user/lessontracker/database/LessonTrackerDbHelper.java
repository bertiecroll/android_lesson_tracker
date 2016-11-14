package com.example.user.lessontracker.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.lessontracker.database.LessonTrackerSchema.LearningObjectiveTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.LessonTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
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
            + TopicTable.Cols.SUBJECT_ID + " integer references " + SubjectTable.NAME + "("
            + SubjectTable.Cols.ID + ") on delete cascade, " + TopicTable.Cols.TITLE + " text, "
            + TopicTable.Cols.DETAIL + " text )";

    private static final String CREATE_TABLE_LEARNING_OBJECTIVE = "create table "
            + LearningObjectiveTable.NAME + "( " + LearningObjectiveTable.Cols.ID +
            " integer primary key autoincrement, " + LearningObjectiveTable.Cols.TOPIC_ID +
            " integer references " + TopicTable.NAME + "(" + TopicTable.Cols.ID +
            ") on delete cascade, " + LearningObjectiveTable.Cols.TITLE + " text, "
            + LearningObjectiveTable.Cols.DETAIL + " text )";

    private static final String CREATE_TABLE_LESSON = "create table "
            + LessonTable.NAME + "(" + LessonTable.Cols.ID + " integer primary key autoincrement, "
            + LessonTable.Cols.COHORT_ID + " integer, " + LessonTable.Cols.TOPIC_ID +
            " integer references " + TopicTable.NAME + "(" + TopicTable.Cols.ID +
            ") on delete cascade, " + LessonTable.Cols.DATE + " integer )";


    public LessonTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_TOPIC);
        db.execSQL(CREATE_TABLE_LEARNING_OBJECTIVE);
        db.execSQL(CREATE_TABLE_LESSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // SUBJECT CRUD ACTIONS

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

    // TOPIC CRUD ACTIONS

    public void saveTopic(Topic topic) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(TopicTable.NAME, null, topic.getContentValues());
        topic.setId(id);
    }

    public void updateTopic(Topic topic) {
        SQLiteDatabase database = getDatabase();
        database.update(TopicTable.NAME, topic.getContentValues(),
                TopicTable.Cols.ID + " = ?", new String[] { String.valueOf(topic.getId())});
    }

    public Topic findTopic(long id) {
        LessonTrackerCursorWrapper cursor = query(TopicTable.NAME,
                TopicTable.Cols.ID + " = ?", new String[] { Long.toString(id)} );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTopic();
        } finally {
            cursor.close();
        }
    }

    public List<Topic> findTopicsBySubject(long subjectId) {
        List<Topic> topics = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(TopicTable.NAME,
                TopicTable.Cols.SUBJECT_ID + " = ?", new String[] {Long.toString(subjectId)} );

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                topics.add(cursor.getTopic());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return topics;
    }

    public List<Topic> allTopics() {
        List<Topic> topics = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(TopicTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                topics.add(cursor.getTopic());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return topics;
    }

    // LEARNING OBJECTIVE CRUD ACTIONS

    public void saveLearningObjective(LearningObjective learningObjective) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(LearningObjectiveTable.NAME, null, learningObjective.getContentValues());
        learningObjective.setId(id);
    }

    public void updateLearningObjective(LearningObjective learningObjective) {
        SQLiteDatabase database = getDatabase();
        long id = learningObjective.getId();
        database.update(LearningObjectiveTable.NAME, learningObjective.getContentValues(),
                LearningObjectiveTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});
    }

    public LearningObjective findLearningObjective(long id) {
        LessonTrackerCursorWrapper cursor = query(LearningObjectiveTable.NAME,
                LearningObjectiveTable.Cols.ID + " = ?", new String[] { Long.toString(id)} );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLearningObjective();
        } finally {
            cursor.close();
        }
    }

    public List<LearningObjective> findLearningObjectivesByTopic(long topicId) {
        List<LearningObjective> learningObjectives = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(LearningObjectiveTable.NAME,
                LearningObjectiveTable.Cols.TOPIC_ID + " = ?", new String[] {Long.toString(topicId)});

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                learningObjectives.add(cursor.getLearningObjective());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return learningObjectives;
    }

    public List<LearningObjective> allLearningObjectives() {
        List<LearningObjective> learningObjectives = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(LearningObjectiveTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                learningObjectives.add(cursor.getLearningObjective());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return learningObjectives;
    }

    // LESSON CRUD ACTIONS

    public void saveLesson(Lesson lesson) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(LessonTable.NAME, null, lesson.getContentValues());
        lesson.setId(id);
    }

    public void updateLesson(Lesson lesson) {
        SQLiteDatabase database = getDatabase();
        long id = lesson.getId();
        database.update(LessonTable.NAME, lesson.getContentValues(),
                LessonTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});

    }

    // PRIVATE HELPERS

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
