package com.example.user.lessontracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.lessontracker.database.LessonTrackerSchema.CohortTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.LearningObjectiveTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.LessonTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.OutcomeTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TagTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TaggingTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;
import com.example.user.lessontracker.models.Cohort;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Tag;
import com.example.user.lessontracker.models.Tagging;
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
            + LessonTable.Cols.COHORT_ID + " integer references " + CohortTable.NAME +
            "(" + CohortTable.Cols.ID + ") on delete cascade, " + LessonTable.Cols.TOPIC_ID +
            " integer references " + TopicTable.NAME + "(" + TopicTable.Cols.ID +
            ") on delete cascade, " + LessonTable.Cols.TAUGHT + " integer, "
            + LessonTable.Cols.DURATION + " integer, "
            + LessonTable.Cols.DATE + " integer, " + LessonTable.Cols.NOTES + " text)";

    private static final String CREATE_TABLE_OUTCOME = "create table "
            + OutcomeTable.NAME + "(" + OutcomeTable.Cols.ID + " integer primary key autoincrement, "
            + OutcomeTable.Cols.LESSON_ID + " integer references " + LessonTable.NAME + "("
            + LessonTable.Cols.ID + ") on delete cascade, " + OutcomeTable.Cols.LEARNING_OBJECTIVE_ID
            + " integer references " + LearningObjectiveTable.NAME + "(" + LearningObjectiveTable.Cols.ID
            + ") on delete cascade, " + OutcomeTable.Cols.OBJECTIVE_MET + " integer)";

    private static final String CREATE_TABLE_TAG = "create table "
            + TagTable.NAME + "(" + TagTable.Cols.ID + " integer primary key autoincrement, "
            + TagTable.Cols.ICON_RESOURCE_ID + " integer, " + TagTable.Cols.TITLE + " text, "
            + TagTable.Cols.TYPE + " text)";

    private static final String CREATE_TABLE_TAGGING = "create table "
            + TaggingTable.NAME + "(" + TaggingTable.Cols.ID + " integer primary key autoincrement, "
            + TaggingTable.Cols.TAG_ID + " integer references " + TagTable.NAME + "("
            + TagTable.Cols.ID + ") on delete cascade, " + TaggingTable.Cols.OUTCOME_ID
            + " integer references " + OutcomeTable.NAME + "(" + OutcomeTable.Cols.ID
            + ") on delete cascade )";

    private static final String CREATE_TABLE_COHORT = "create table "
            + CohortTable.NAME + "(" + CohortTable.Cols.ID + " integer primary key autoincrement, "
            + CohortTable.Cols.NAME + " text )";

    private static final String ADD_DEFAULT_FIX_TAG = "insert into " + TagTable.NAME + "("
            + TagTable.Cols.ICON_RESOURCE_ID + ", " + TagTable.Cols.TITLE + ", " + TagTable.Cols.TYPE
            + ") values ( 17301570, 'Some sections need fixed', 'improvement' )";

    private static final String ADD_DEFAULT_REDO_TAG = "insert into " + TagTable.NAME + "("
            + TagTable.Cols.ICON_RESOURCE_ID + ", " + TagTable.Cols.TITLE + ", " + TagTable.Cols.TYPE
            + ") values ( 17301581, 'Redo required', 'improvement' )";

    private static final String ADD_DEFAULT_NO_TIME_TAG = "insert into " + TagTable.NAME + "("
            + TagTable.Cols.ICON_RESOURCE_ID + ", " + TagTable.Cols.TITLE + ", " + TagTable.Cols.TYPE
            + ") values ( 17301578, 'Not Enough Time', 'improvement' )";

//    private static final String ADD_DEFAULT_PASS_TAG = "insert into " + TagTable.NAME + "("
//            + TagTable.Cols.ICON_RESOURCE_ID + ", " + TagTable.Cols.TITLE + ", " + TagTable.Cols.TYPE
//            + ") values ( 17301515, 'Objective Met', 'positive' )";
//
//    private static final String ADD_DEFAULT_FAIL_TAG = "insert into " + TagTable.NAME + "("
//            + TagTable.Cols.ICON_RESOURCE_ID + ", " + TagTable.Cols.TITLE + ", " + TagTable.Cols.TYPE
//            + ") values ( 17301564, 'Unable to Meet Objective', 'negative' )";


    public LessonTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_TOPIC);
        db.execSQL(CREATE_TABLE_LEARNING_OBJECTIVE);
        db.execSQL(CREATE_TABLE_COHORT);
        db.execSQL(CREATE_TABLE_LESSON);
        db.execSQL(CREATE_TABLE_OUTCOME);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_TAGGING);
        db.execSQL(ADD_DEFAULT_NO_TIME_TAG);
        db.execSQL(ADD_DEFAULT_FIX_TAG);
        db.execSQL(ADD_DEFAULT_REDO_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaggingTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OutcomeTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TagTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LessonTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CohortTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LearningObjectiveTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TopicTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SubjectTable.NAME);

        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_TOPIC);
        db.execSQL(CREATE_TABLE_LEARNING_OBJECTIVE);
        db.execSQL(CREATE_TABLE_COHORT);
        db.execSQL(CREATE_TABLE_LESSON);
        db.execSQL(CREATE_TABLE_OUTCOME);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_TAGGING);
    }

    // SUBJECT CRUD ACTIONS

    public void saveSubject(Subject subject) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(SubjectTable.NAME, null, getSubjectValues(subject));
        subject.setId(id);
    }

    public void updateSubject(Subject subject) {
        SQLiteDatabase database = getDatabase();
        database.update(SubjectTable.NAME, getSubjectValues(subject),
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

    public long saveTopic(Topic topic) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(TopicTable.NAME, null, getTopicValues(topic));
        topic.setId(id);
        return id;
    }

    public void updateTopic(Topic topic) {
        SQLiteDatabase database = getDatabase();
        database.update(TopicTable.NAME, getTopicValues(topic),
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
        long id = database.insert(LearningObjectiveTable.NAME, null, getLearningObjectiveValues(learningObjective));
        learningObjective.setId(id);
    }

    public void updateLearningObjective(LearningObjective learningObjective) {
        SQLiteDatabase database = getDatabase();
        long id = learningObjective.getId();
        database.update(LearningObjectiveTable.NAME, getLearningObjectiveValues(learningObjective),
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
        long id = database.insert(LessonTable.NAME, null, getLessonValues(lesson));
        lesson.setId(id);
        return id;
    }

    public void updateLesson(Lesson lesson) {
        SQLiteDatabase database = getDatabase();
        long id = lesson.getId();
        database.update(LessonTable.NAME, getLessonValues(lesson),
                LessonTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});

    }

    public Lesson findLesson(long id) {
        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME,
                LessonTable.Cols.ID + " = ?", new String[] { Long.toString(id) } );

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

    public List<Lesson> findCompletedLessons() {
        List<Lesson> lessons = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME,
                LessonTable.Cols.TAUGHT + " = ?", new String[] {"1"});

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

    public List<Lesson> findLessonsByTopic(long topicId, boolean taught) {
        List<Lesson> lessons = new ArrayList<>();
        String taughtClause = (taught) ? "1" : "0";

        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME,
                LessonTable.Cols.TOPIC_ID + " = ? AND " + LessonTable.Cols.TAUGHT + " = ? ",
                new String[] {Long.toString(topicId), taughtClause});

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

    public int countLessonsByTopic(long topicId, boolean taught) {
        String taughtClause = (taught) ? "1" : "0";

        LessonTrackerCursorWrapper cursor = query(LessonTable.NAME,
                LessonTable.Cols.TOPIC_ID + " = ? AND " + LessonTable.Cols.TAUGHT + " = ? ",
                new String[] { Long.toString(topicId), taughtClause });

        try {
            return cursor.getCount();

        } finally {
            cursor.close();
        }
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

    public double averageLessonDurationByTopic(long topicId) {
        SQLiteDatabase database = getDatabase();

        Cursor cursor = database.query(
                LessonTable.NAME, new String[] { "AVG(" + LessonTable.Cols.DURATION + ")" },
                LessonTable.Cols.TOPIC_ID + " = ?", new String[] { Long.toString(topicId) },
                null, null, null);

        try {
            if (cursor.getCount() == 0) {
                return 0;
            }
            cursor.moveToFirst();
            return cursor.getDouble(0);

        } finally {
            cursor.close();
        }
    }

    // OUTCOME CRUD ACTIONS

    public void saveOutcome(Outcome outcome) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(OutcomeTable.NAME, null, getOutcomeValues(outcome));
        outcome.setId(id);
    }

    public void updateOutcome(Outcome outcome) {
        SQLiteDatabase database = getDatabase();
        long id = outcome.getId();
        database.update(OutcomeTable.NAME, getOutcomeValues(outcome),
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

        LessonTrackerCursorWrapper cursor = query(OutcomeTable.NAME, null, null);

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

    public int countOutcomesByLearningObjective(long learningObjectiveId, boolean met) {
        String metClause = (met) ? "1" : "0";

        LessonTrackerCursorWrapper cursor = query(OutcomeTable.NAME,
                OutcomeTable.Cols.LEARNING_OBJECTIVE_ID + " = ? AND " + OutcomeTable.Cols.OBJECTIVE_MET + " = ? ",
                new String[] { Long.toString(learningObjectiveId), metClause });

        try {
            return cursor.getCount();

        } finally {
            cursor.close();
        }
    }

    // TAG CRUD ACTIONS

    public long saveTag(Tag tag) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(TagTable.NAME, null, getTagValues(tag));
        tag.setId(id);

        return id;
    }

    public void updateTag(Tag tag) {
        SQLiteDatabase database = getDatabase();
        long id = tag.getId();
        database.update(TagTable.NAME, getTagValues(tag),
                LessonTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});
    }

    public Tag findTag(long id) {
        LessonTrackerCursorWrapper cursor = query(TagTable.NAME,
                TagTable.Cols.ID + " = ?", new String[] { Long.toString(id)} );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTag();

        } finally {
            cursor.close();
        }
    }

    public List<Tag> allTags() {
        List<Tag> tags = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(TagTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tags.add(cursor.getTag());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tags;
    }

    public List<Tag> findOutcomeTags(long outcomeId) {
        List<Tag> tags = new ArrayList<>();
        SQLiteDatabase database = getDatabase();

        String tableName = TagTable.NAME + " inner join " + TaggingTable.NAME + " on "
                + TagTable.NAME + "." + TagTable.Cols.ID + " = " + TaggingTable.NAME + "."
                + TaggingTable.Cols.TAG_ID;
        String[] tableColumns = new String[] {
                TagTable.NAME + "." + TagTable.Cols.ID,
                TagTable.NAME + "." + TagTable.Cols.ICON_RESOURCE_ID,
                TagTable.NAME + "." + TagTable.Cols.TITLE,
                TagTable.NAME + "." + TagTable.Cols.TYPE
        };

        String whereClause = TaggingTable.Cols.OUTCOME_ID + " = ?";

        Cursor cursor = database.query(
                tableName, tableColumns, whereClause,
                new String[] { Long.toString(outcomeId) }, null, null, null);

        LessonTrackerCursorWrapper lessonTrackerCursorWrapper =
                new LessonTrackerCursorWrapper(cursor);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tags.add(lessonTrackerCursorWrapper.getTag());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tags;
    }

    public List<Long> findOutcomeTagIds(long outcomeId) {
        List<Long> tagIds = new ArrayList<>();
        SQLiteDatabase database = getDatabase();

        String tableName = TagTable.NAME + " inner join " + TaggingTable.NAME + " on "
                + TagTable.NAME + "." + TagTable.Cols.ID + " = " + TaggingTable.NAME + "."
                + TaggingTable.Cols.TAG_ID;
        String[] tableColumns = new String[] {
                TagTable.NAME + "." + TagTable.Cols.ID,
        };

        String whereClause = TaggingTable.Cols.OUTCOME_ID + " = ?";

        Cursor cursor = database.query(
                tableName, tableColumns, whereClause,
                new String[] { Long.toString(outcomeId) }, null, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tagIds.add(cursor.getLong(cursor.getColumnIndex(TagTable.Cols.ID)));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tagIds;
    }

    // TAGGING CRUD ACTIONS

    public long saveTagging(Tagging tagging) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(TaggingTable.NAME, null, getTaggingValues(tagging));
        tagging.setId(id);
        return id;
    }

    public void updateTagging(Tagging tagging) {
        SQLiteDatabase database = getDatabase();
        long id = tagging.getId();
        database.update(TaggingTable.NAME, getTaggingValues(tagging),
                TaggingTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});
    }

    public Tagging findTagging(long id) {
        LessonTrackerCursorWrapper cursor = query(TaggingTable.NAME,
                TaggingTable.Cols.ID + " = ?", new String[] { Long.toString(id) } );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTagging();

        } finally {
            cursor.close();
        }
    }

    public List<Tagging> findTaggingsByOutcome(long outcomeId) {
        List<Tagging> taggings = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(TaggingTable.NAME,
                TaggingTable.Cols.OUTCOME_ID + " = ?", new String[] { Long.toString(outcomeId) } );

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                taggings.add(cursor.getTagging());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return taggings;
    }

    public List<Tagging> allTagging() {
        List<Tagging> taggings = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(TaggingTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                taggings.add(cursor.getTagging());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return taggings;
    }

    // COHORT CRUD ACTIONS

    public void saveCohort(Cohort cohort) {
        SQLiteDatabase database = getDatabase();
        long id = database.insert(CohortTable.NAME, null, getCohortValues(cohort));
        cohort.setId(id);
    }

    public void updateCohort(Cohort cohort) {
        SQLiteDatabase database = getDatabase();
        database.update(CohortTable.NAME, getCohortValues(cohort),
                CohortTable.Cols.ID + " = ?", new String[] { String.valueOf(cohort.getId())});
    }

    public Cohort findCohort(long id) {
        LessonTrackerCursorWrapper cursor = query(CohortTable.NAME,
                CohortTable.Cols.ID + " = ?", new String[] { Long.toString(id)} );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCohort();
        } finally {
            cursor.close();
        }
    }

    public List<Cohort> allCohorts() {
        List<Cohort> cohorts = new ArrayList<>();

        LessonTrackerCursorWrapper cursor = query(CohortTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                cohorts.add(cursor.getCohort());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return cohorts;
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

    private ContentValues getSubjectValues(Subject subject) {
        ContentValues values = new ContentValues();
        values.put(SubjectTable.Cols.TITLE, subject.getTitle());
        values.put(SubjectTable.Cols.DETAIL, subject.getDetail());

        return values;
    }

    public ContentValues getTopicValues(Topic topic) {
        ContentValues values = new ContentValues();
        values.put(TopicTable.Cols.SUBJECT_ID, topic.getSubjectId());
        values.put(TopicTable.Cols.TITLE, topic.getTitle());
        values.put(TopicTable.Cols.DETAIL, topic.getDetail());

        return values;
    }

    public ContentValues getTaggingValues(Tagging tagging) {
        ContentValues values = new ContentValues();
        values.put(TaggingTable.Cols.TAG_ID, tagging.getTagId());
        values.put(TaggingTable.Cols.OUTCOME_ID, tagging.getOutcomeId());

        return values;
    }

    public ContentValues getTagValues(Tag tag) {
        ContentValues values = new ContentValues();
        values.put(TagTable.Cols.ICON_RESOURCE_ID, tag.getIconResourceId());
        values.put(TagTable.Cols.TITLE, tag.getTitle());
        values.put(TagTable.Cols.TYPE, tag.getType());

        return values;
    }

    public ContentValues getOutcomeValues(Outcome outcome) {
        ContentValues values = new ContentValues();
        values.put(OutcomeTable.Cols.LESSON_ID, outcome.getLessonId());
        values.put(OutcomeTable.Cols.LEARNING_OBJECTIVE_ID, outcome.getLearningObjectiveId());
        values.put(OutcomeTable.Cols.OBJECTIVE_MET, outcome.hasObjectiveBeenMetAsInt());

        return values;
    }

    public ContentValues getLessonValues(Lesson lesson) {
        ContentValues values = new ContentValues();
        values.put(LessonTable.Cols.COHORT_ID, lesson.getCohortId());
        values.put(LessonTable.Cols.TOPIC_ID, lesson.getTopicId());
        values.put(LessonTable.Cols.DATE, lesson.getDate().getTime());
        values.put(LessonTable.Cols.TAUGHT, lesson.taughtAsInt());
        values.put(LessonTable.Cols.DURATION, lesson.getDuration());
        values.put(LessonTable.Cols.NOTES, lesson.getNotes());

        return values;
    }

    public ContentValues getLearningObjectiveValues(LearningObjective learningObjective) {
        ContentValues values = new ContentValues();
        values.put(LearningObjectiveTable.Cols.TOPIC_ID, learningObjective.getTopicId());
        values.put(LearningObjectiveTable.Cols.TITLE, learningObjective.getTitle());
        values.put(LearningObjectiveTable.Cols.DETAIL, learningObjective.getDetail());

        return values;
    }

    private ContentValues getCohortValues(Cohort cohort) {
        ContentValues values = new ContentValues();
        values.put(CohortTable.Cols.NAME, cohort.getName());

        return values;
    }
}
