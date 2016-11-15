package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.database.LessonTrackerSchema;
import com.example.user.lessontracker.database.LessonTrackerSchema.OutcomeTable;

public class Outcome {

    private long mId;
    private long mLessonId;
    private long mLearningObjectiveId;

    public Outcome(long lessonId, long learningObjectiveId) {
        mLessonId = lessonId;
        mLearningObjectiveId  = learningObjectiveId;
    }

    public Outcome(long id, long lessonId, long learningObjectiveId) {
        mId = id;
        mLessonId = lessonId;
        mLearningObjectiveId  = learningObjectiveId;
    }

    public long getLessonId() {
        return mLessonId;
    }

    public void setLessonId(long newLessonId) {
        mLessonId = newLessonId;
    }

    public long getLearningObjectiveId() {
        return mLearningObjectiveId;
    }

    public void setLearningObjectiveId(long newLearningObjectiveId) {
        mLearningObjectiveId = newLearningObjectiveId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(OutcomeTable.Cols.LESSON_ID, mLessonId);
        values.put(OutcomeTable.Cols.LEARNING_OBJECTIVE_ID, mLearningObjectiveId);

        return values;
    }
}
