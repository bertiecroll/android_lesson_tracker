package com.example.user.lessontracker.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.lessontracker.database.LessonTrackerSchema.LearningObjectiveTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.LessonTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.OutcomeTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TagTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Tag;
import com.example.user.lessontracker.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class LessonTrackerDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
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
            ") on delete cascade, " + LessonTable.Cols.TAUGHT + " integer, "
            + LessonTable.Cols.DATE + " integer )";

    private static final String CREATE_TABLE_OUTCOME = "create table "
            + OutcomeTable.NAME + "(" + OutcomeTable.Cols.ID + " integer primary key autoincrement, "
            + OutcomeTable.Cols.LESSON_ID + " integer references " + LessonTable.NAME + "("
            + LessonTable.Cols.ID + ") on delete cascade, " + OutcomeTable.Cols.LEARNING_OBJECTIVE_ID
            + " integer references " + LearningObjectiveTable.NAME + "(" + LearningObjectiveTable.Cols.ID
            + ") on delete cascade )";

    private static final String CREATE_TABLE_TAG = "create table "
            + TagTable.NAME + "(" + TagTable.Cols.ID + " integer primary key autoincrement, "
            + TagTable.Cols.TITLE + " text )";


    public LessonTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_TOPIC);
        db.execSQL(CREATE_TABLE_LEARNING_OBJECTIVE);
        db.execSQL(CREATE_TABLE_LESSON);
        db.execSQL(CREATE_TABLE_OUTCOME);
        db.execSQL(CREATE_TABLE_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OutcomeTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TagTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LessonTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LearningObjectiveTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TopicTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SubjectTable.NAME);

        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_TOPIC);
        db.execSQL(CREATE_TABLE_LEARNING_OBJECTIVE);
        db.execSQL(CREATE_TABLE_LESSON);
        db.execSQL(CREATE_TABLE_OUTCOME);
        db.execSQL(CREATE_TABLE_TAG);
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

    public long saveLesson(Lesson lesson) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(LessonTable.NAME, null, lesson.getContentValues());
        lesson.setId(id);
        return id;
    }

    public void updateLesson(Lesson lesson) {
        SQLiteDatabase database = getDatabase();
        long id = lesson.getId();
        database.update(LessonTable.NAME, lesson.getContentValues(),
                LessonTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});

    }

    public Lesson findLesson(long id) {
        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME,
                LessonTable.Cols.ID + " = ?", new String[] { Long.toString(id)} );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLesson();
        } finally {
            cursor.close();
        }
    }

    public List<Lesson> findPendingLessons() {
        List<Lesson> lessons = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME,
                LessonTable.Cols.TAUGHT + " = ?", new String[] {"0"});

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lessons.add(cursor.getLesson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return lessons;
    }

    public List<Lesson> allLessons() {
        List<Lesson> lessons = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lessons.add(cursor.getLesson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return lessons;
    }

    // OUTCOME CRUD ACTIONS

    public void saveOutcome(Outcome outcome) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(OutcomeTable.NAME, null, outcome.getContentValues());
        outcome.setId(id);
    }

    public void updateOutcome(Outcome outcome) {
        SQLiteDatabase database = getDatabase();
        long id = outcome.getId();
        database.update(OutcomeTable.NAME, outcome.getContentValues(),
                LessonTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});
    }

    public Outcome findOutcome(long id) {
        LessonTrackerCursorWrapper cursor = query(OutcomeTable.NAME,
                OutcomeTable.Cols.ID + " = ?", new String[] { Long.toString(id)} );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getOutcome();

        } finally {
            cursor.close();
        }
    }

    public List<Outcome> allOutcomes() {
        List<Outcome> outcomes = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                outcomes.add(cursor.getOutcome());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return outcomes;
    }

    public List<Outcome> findOutcomesByLesson(long lessonId) {
        List<Outcome> outcomes = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(OutcomeTable.NAME,
                OutcomeTable.Cols.LESSON_ID + " = ?", new String[] {Long.toString(lessonId)});

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                outcomes.add(cursor.getOutcome());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return outcomes;
    }

    public List<Outcome> findOutcomesByLearningObjective(long learningObjectiveId) {
        List<Outcome> outcomes = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(OutcomeTable.NAME,
                OutcomeTable.Cols.LEARNING_OBJECTIVE_ID + " = ?",
                new String[] {Long.toString(learningObjectiveId)});

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                outcomes.add(cursor.getOutcome());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return outcomes;
    }

    // TAG CRUD ACTIONS

    public long saveTag(Tag tag) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(TagTable.NAME, null, tag.getContentValues());
        tag.setId(id);

        return id;
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
