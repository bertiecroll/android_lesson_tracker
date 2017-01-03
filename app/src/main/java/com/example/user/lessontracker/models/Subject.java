package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

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

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

}
