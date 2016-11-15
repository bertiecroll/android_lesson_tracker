package com.example.user.lessontracker.models;

/**
 * Created by user on 15/11/2016.
 */

public class Tag {

    private long mId;
    private String mName;

    public Tag(String name) {
        mName = name;
    }

    public Tag(long id, String name) {
        mId = id;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String newName) {
        mName = newName;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }


}
