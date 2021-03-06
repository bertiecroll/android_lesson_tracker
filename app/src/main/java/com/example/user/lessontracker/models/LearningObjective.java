package com.example.user.lessontracker.models;

import com.example.user.lessontracker.Teachable;

public class LearningObjective implements Teachable {

    private long mId;
    private long mTopicId;
    private String mTitle;
    private String mDetail;

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

    public long getTopicId() {
        return mTopicId;
    }

    public void setTopicId(long newTopicId) {
        mTopicId = newTopicId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }
}
