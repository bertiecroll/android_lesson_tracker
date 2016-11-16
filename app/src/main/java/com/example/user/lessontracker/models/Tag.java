package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.database.LessonTrackerSchema;
import com.example.user.lessontracker.database.LessonTrackerSchema.TagTable;

public class Tag {

    private long mId;
    private int mIconResourceId;
    private String mTitle;
    private boolean mType;

    public Tag(int iconResourceId, String title, int type) {
        mIconResourceId = iconResourceId;
        mTitle = title;
        mType = (type == 1);
    }

    public Tag(long id, int iconResourceId, String title, int type) {
        mId = id;
        mIconResourceId = iconResourceId;
        mTitle = title;
        mType = (type == 1);
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

    public boolean isPositive() {
        return mType;
    }

    private int typeAsInt() {
        return (mType)? 1 : 0;
    }

    private String typeAsString() {
        return (mType)? "positive" : "negative";
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(TagTable.Cols.ID, mId);
        values.put(TagTable.Cols.ICON_RESOURCE_ID, mIconResourceId);
        values.put(TagTable.Cols.TITLE, mTitle);

        return values;
    }

}
