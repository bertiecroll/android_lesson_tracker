package com.example.user.lessontracker;

public class Subject implements Teachable {

    private String mTitle;

    public Subject(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String newTitle) {
        mTitle = newTitle;
    }
}
