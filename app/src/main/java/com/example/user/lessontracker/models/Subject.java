package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.Teachable;
import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;

public class Subject implements Teachable {

    private long mId;
    private String mTitle;
    private String mDetail;

    public Subject(String title, String detail) {
        mTitle = title;
        mDetail = detail;
    }

    public Subject(long id, String title, String detail) {
        mId = id;
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

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(SubjectTable.Cols.TITLE, mTitle);
        values.put(SubjectTable.Cols.DETAIL, mDetail);

        return values;
    }
}
