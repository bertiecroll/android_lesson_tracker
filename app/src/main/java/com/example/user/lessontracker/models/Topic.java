package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.Teachable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;

public class Topic implements Teachable {

    private long mId;
    private int mSubjectId;
    private String mTitle;
    private String mDetail;

    public Topic(int subjectId, String title, String detail) {
        mSubjectId = subjectId;
        mTitle = title;
        mDetail = detail;
    }

    public Topic(long id, int subjectId, String title, String detail) {
        mId = id;
        mSubjectId = subjectId;
        mTitle = title;
        mDetail = detail;
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

    public int getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(int subjectId) {
        mSubjectId = subjectId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(TopicTable.Cols.SUBJECT_ID, mSubjectId);
        values.put(TopicTable.Cols.TITLE, mTitle);
        values.put(TopicTable.Cols.DETAIL, mDetail);

        return values;
    }
}
