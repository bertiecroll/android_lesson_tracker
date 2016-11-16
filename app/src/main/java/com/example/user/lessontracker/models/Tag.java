package com.example.user.lessontracker.models;

import android.content.ContentValues;

import com.example.user.lessontracker.database.LessonTrackerSchema;
import com.example.user.lessontracker.database.LessonTrackerSchema.TagTable;

/**
 * Created by user on 15/11/2016.
 */

public class Tag {

    private long mId;
    private int mIconResourceId;
    private String mTitle;

    public Tag(int iconResourceId, String title) {
        mIconResourceId = iconResourceId;
        mTitle = title;
    }

    public Tag(long id, int iconResourceId, String title) {
        mId = id;
        mIconResourceId = iconResourceId;
        mTitle = title;
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

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(TagTable.Cols.ID, mId);
        values.put(TagTable.Cols.ICON_RESOURCE_ID, mIconResourceId);
        values.put(TagTable.Cols.TITLE, mTitle);

        return values;
    }

}
