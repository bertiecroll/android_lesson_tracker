package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

public class Topic implements Teachable {

    private String mTitle;
    private String mDetail;

    public Topic(String title, String detail) {
        mTitle = title;
        mDetail = detail;
    }
}
