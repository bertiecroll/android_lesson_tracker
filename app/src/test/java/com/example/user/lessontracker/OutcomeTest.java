package com.example.user.lessontracker;

import com.example.user.lessontracker.models.Outcome;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OutcomeTest {

    Outcome outcome;

    @Before
    public void before() {
        long lessonId = 1;
        long learningObjectiveId = 2;
        outcome = new Outcome(lessonId, learningObjectiveId);
    }

    @Test
    public void getLessonId() {
        assertEquals(1, outcome.getLessonId());
    }

    @Test
    public void setLessonId() {
        long newLessonId = 5;
        outcome.setLessonId(newLessonId);
        assertEquals(5, outcome.getLessonId());
    }

    @Test
    public void getLearningObjectiveId() {
        assertEquals(2, outcome.getLearningObjectiveId());
    }

    @Test
    public void setLearningObjectiveId() {
        long newLearningObjectiveId = 3;
        outcome.setLearningObjectiveId(newLearningObjectiveId);
        assertEquals(3, outcome.getLearningObjectiveId());
    }
}
