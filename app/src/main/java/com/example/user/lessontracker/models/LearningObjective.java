package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

public class LearningObjective {

    long mId;
    long mTopicId;
    String mTitle;
    String mDetail;

    public LearningObjective(long topicId, String title, String detail) {
        mTopicId = topicId;
        mTitle = title;
        mDetail = detail;
    }

    public LearningObjective(long id, long topicId, String title, String detail) {
        mId = id;
        mTopicId = topicId;
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
