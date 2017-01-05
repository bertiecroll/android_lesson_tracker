package com.example.user.lessontracker.models;

public class Cohort {

    private long mId;
    private String mName;

    public Cohort(String name) {
        mName = name;
    }

    public Cohort(long id, String name) {
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

    public void setId(long id) {
        mId = id;
    }
}
