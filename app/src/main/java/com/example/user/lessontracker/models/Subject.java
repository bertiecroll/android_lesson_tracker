package com.example.user.lessontracker.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.lessontracker.Teachable;
import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;

public class Subject implements Teachable {

    private int mId;
    private String mTitle;
    private String mDetail;
    private SQLiteDatabase mDatabase;

    public Subject(String title, String detail) {
        mTitle = title;
        mDetail = detail;
    }

    public Subject(int id, String title, String detail) {
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    private ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(SubjectTable.Cols.TITLE, mTitle);
        values.put(SubjectTable.Cols.DETAIL, mDetail);

        return values;
    }
}
