package com.example.user.lessontracker;

public class Subject implements Teachable {

    private String mTitle;
    private String mDetail;

    public Subject(String title, String detail) {
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
