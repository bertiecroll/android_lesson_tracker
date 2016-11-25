package com.example.user.lessontracker.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lesson {

    private long mId;
    private long mCohortId;
    private long mTopicId;
    private Date mDate;
    private boolean mTaught;
    private long mDuration;
    private String mNotes;

    public Lesson(long cohortId, long topicId, long date, int taught) {
        mCohortId = cohortId;
        mTopicId = topicId;
        mDate = new Date(date);
        mTaught = (taught == 1);
        mDuration = 0;
        mNotes = "";
    }

    public Lesson(long id, long cohortId, long topicId, long date, int taught, long duration, String notes) {
        mId = id;
        mCohortId = cohortId;
        mTopicId = topicId;
        mDate = new Date(date);
        mTaught = (taught == 1);
        mDuration = duration;
        mNotes = notes;
    }

    public String toString() {
        return "Cohort: " + mCohortId + ". Date: " + printDate();
    }

    public String printDate() {
        String date = new SimpleDateFormat("dd/MM/yy").format(mDate);
        return date;
    }

    public long getCohortId() {
        return mCohortId;
    }

    public void setCohortId(long newCohortId) {
        mCohortId =newCohortId;
    }

    public long getTopicId() {
        return mTopicId;
    }

    public void setTopicId(long newTopicId) {
        mTopicId =newTopicId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(long julianDate) {
        mDate = new Date(julianDate);
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId =newId;
    }

    public boolean hasBeenTaught() {
        return mTaught;
    }

    public void teach(long duration) {
        mTaught = true;
        mDuration = duration;
    }

    public long getDuration() {
        return mDuration;
    }

    public int taughtAsInt() {
        return (mTaught)? 1 : 0;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String newNotes) {
        mNotes = newNotes;
    }
}
