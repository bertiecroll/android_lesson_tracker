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

    public long getTagId() {
        return mTagId;
    }

    public void setTagId(long newTagId) {
        mTagId = newTagId;
    }

    public long getOutcomeId() {
        return mOutcomeId;
    }

    public void setOutcomeId(long newOutcomeId) {
        mOutcomeId = newOutcomeId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }



}
