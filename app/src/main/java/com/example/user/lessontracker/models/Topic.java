package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

public class Topic implements Teachable {

    private long mId;
    private long mSubjectId;
    private String mTitle;
    private String mDetail;

    public Topic(long subjectId, String title, String detail) {
        mSubjectId = subjectId;
        mTitle = title;
        mDetail = detail;
    }

    public Topic(long id, long subjectId, String title, String detail) {
        mId = id;
        mSubjectId = subjectId;
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

    public long getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(long subjectId) {
        mSubjectId = subjectId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

}
