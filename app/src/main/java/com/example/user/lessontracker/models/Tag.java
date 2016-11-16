package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.database.LessonTrackerSchema;
import com.example.user.lessontracker.database.LessonTrackerSchema.TagTable;

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

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(TagTable.Cols.ID, mId);
        values.put(TagTable.Cols.ICON_RESOURCE_ID, mIconResourceId);
        values.put(TagTable.Cols.TITLE, mTitle);
        values.put(TagTable.Cols.TYPE, mType);

        return values;
    }

}
