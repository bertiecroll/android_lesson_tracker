package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.Teachable;
import com.example.user.lessontracker.database.LessonTrackerSchema;
import com.example.user.lessontracker.database.LessonTrackerSchema.LearningObjectiveTable;

public class LearningObjective implements Teachable {

    long mId;
    long mTopicId;
    String mTitle;
    String mDetail;

    public LearningObjective(long topicId, String title, String detail) {
        mTopicId = topicId;
        mTitle = title;
        mDetail = detail;
    }

    public LearningObjective(long id, long topicId, String title, String detail) {
        mId = id;
        mTopicId = topicId;
        mTitle = title;
        mDetail = detail;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String newTitle) {
        mTitle = newTitle;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String newDetail) {
        mDetail = newDetail;
    }

    public long getTopicId() {
        return mTopicId;
    }

    public void setTopicId(long newTopicId) {
        mTopicId = newTopicId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(LearningObjectiveTable.Cols.TOPIC_ID, mTopicId);
        values.put(LearningObjectiveTable.Cols.TITLE, mTitle);
        values.put(LearningObjectiveTable.Cols.DETAIL, mDetail);

        return values;
    }
}
