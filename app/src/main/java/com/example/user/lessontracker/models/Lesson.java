package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.database.LessonTrackerSchema;
import com.example.user.lessontracker.database.LessonTrackerSchema.LessonTable;

import java.util.Date;

public class Lesson {

    private long mId;
    private long mCohortId;
    private long mTopicId;
    private Date mDate;

    public Lesson(long cohortId, long topicId) {
        mCohortId = cohortId;
        mTopicId = topicId;
        mDate = new Date();
    }

    public Lesson(long id, long cohortId, long topicId) {
        mId = id;
        mCohortId = cohortId;
        mTopicId = topicId;
        mDate = new Date();
    }

    public long getCohortId() {
        return mCohortId;
    }

    public void setCohortId(long newCohortId) {
        mCohortId =newCohortId;
    }

    public long getTopicId() {
        return mTopicId;
    }

    public void setTopicId(long newTopicId) {
        mTopicId =newTopicId;
    }

    public Date getDate() {
        return mDate;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId =newId;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(LessonTable.Cols.COHORT_ID, mCohortId);
        values.put(LessonTable.Cols.TOPIC_ID, mTopicId);
        values.put(LessonTable.Cols.DATE, mDate.getTime());

        return values;
    }
}
