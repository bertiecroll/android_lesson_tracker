package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

public class Topic implements Teachable {

    private int mId;
    private int mSubjectId;
    private String mTitle;
    private String mDetail;

    public Topic(int subjectId, String title, String detail) {
        mSubjectId = subjectId;
        mTitle = title;
        mDetail = detail;
    }

    public Topic(int id, int subjectId, String title, String detail) {
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
