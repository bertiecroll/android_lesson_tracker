package com.example.user.lessontracker.models;

public class Tagging {

    private long mId;
    private long mTagId;
    private long mOutcomeId;

    public Tagging(long tagId, long outcomeId) {
        mTagId = tagId;
        mOutcomeId = outcomeId;
    }

    public Tagging(long id, long tagId, long outcomeId) {
        mId = id;
        mTagId = tagId;
        mOutcomeId = outcomeId;
    }



}
