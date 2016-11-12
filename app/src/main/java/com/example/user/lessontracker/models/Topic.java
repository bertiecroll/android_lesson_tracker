package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

public class Topic implements Teachable {

    private int mSubjectId;
    private String mTitle;
    private String mDetail;

    public Topic(int subjectId, String title, String detail) {
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
}
