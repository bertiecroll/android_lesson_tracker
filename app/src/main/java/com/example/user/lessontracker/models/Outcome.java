package com.example.user.lessontracker.models;

public class Outcome {

    private long mId;
    private long mLessonId;
    private long mLearningObjectiveId;
    private boolean mObjectiveMet;

    public Outcome(long lessonId, long learningObjectiveId) {
        mLessonId = lessonId;
        mLearningObjectiveId  = learningObjectiveId;
        mObjectiveMet = false;
    }

    public Outcome(long id, long lessonId, long learningObjectiveId, int objectiveMet) {
        mId = id;
        mLessonId = lessonId;
        mLearningObjectiveId  = learningObjectiveId;
        mObjectiveMet = (objectiveMet == 1);
    }

    public long getLessonId() {
        return mLessonId;
    }

    public void setLessonId(long newLessonId) {
        mLessonId = newLessonId;
    }

    public long getLearningObjectiveId() {
        return mLearningObjectiveId;
    }

    public void setLearningObjectiveId(long newLearningObjectiveId) {
        mLearningObjectiveId = newLearningObjectiveId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long newId) {
        mId = newId;
    }

    public boolean hasObjectiveBeenMet() {
        return mObjectiveMet;
    }

    public int hasObjectiveBeenMetAsInt() {
        return (mObjectiveMet)? 1 : 0;
    }

    public void setObjectiveMet(boolean met) {
        mObjectiveMet = met;
    }

}
