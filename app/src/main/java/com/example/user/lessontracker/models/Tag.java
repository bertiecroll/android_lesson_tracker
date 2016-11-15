package com.example.user.lessontracker.models;

/**
 * Created by user on 15/11/2016.
 */

public class Tag {

    private long mId;
    private String mTitle;

    public Tag(String title) {
        mTitle = title;
    }

    public Tag(long id, String title) {
        mId = id;
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String newTitle) {
        mTitle = newTitle;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }


}
