package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

public class Subject implements Teachable {

    private int mId;
    private String mTitle;
    private String mDetail;

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
}
