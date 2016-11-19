package com.example.user.lessontracker.models;

public class Tag {

    private long mId;
    private int mIconResourceId;
    private String mTitle;
    private String mType;

    public Tag(int iconResourceId, String title, String type) {
        mIconResourceId = iconResourceId;
        mTitle = title;
        mType = type;
    }

    public Tag(long id, int iconResourceId, String title, String type) {
        mId = id;
        mIconResourceId = iconResourceId;
        mTitle = title;
        mType = type;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String newTitle) {
        mTitle = newTitle;
    }

    public int getIconResourceId() {
        return mIconResourceId;
    }

    public void setIconResourceId(int newIconResourceId) {
        mIconResourceId = newIconResourceId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }

    public String getType() {
        return mType;
    }

    private void setType(String newType) {
        mType = newType;
    }

}
