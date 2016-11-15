package com.example.user.lessontracker.models;

public class Outcome {

    long mId;
    long mLessonId;
    long mLearningObjectiveId;

    public Outcome(long lessonId, long learningObjectiveId) {
        mLessonId = lessonId;
        mLearningObjectiveId  = learningObjectiveId;
    }

    public Outcome(long id, long lessonId, long learningObjectiveId) {
        mId = id;
        mLessonId = lessonId;
        mLearningObjectiveId  = learningObjectiveId;
    }
}
