package com.example.user.lessontracker.models;

import java.util.Date;

public class Lesson {

    private long mId;
    private long mCohortId;
    private long mTopicId;
    private Date mDate;

    public Lesson(long cohortId, long topicId) {
        mCohortId = cohortId;
        mTopicId = topicId;
        mDate = new Date();
    }

    public Lesson(long id, long cohortId, long topicId) {
        mId = id;
        mCohortId = cohortId;
        mTopicId = topicId;
        mDate = new Date();
    }
}
