package com.example.user.lessontracker;

import com.example.user.lessontracker.models.Outcome;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OutcomeTest {

    Outcome outcome;
    Outcome outcomeWithId;

    @Before
    public void before() {
        long lessonId = 1;
        long learningObjectiveId = 2;
        outcome = new Outcome(lessonId, learningObjectiveId);
        outcomeWithId = new Outcome(1, lessonId, learningObjectiveId);
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

    @Test
    public void getId() {
        assertEquals(1, outcomeWithId.getId());
    }

    @Test
    public void setId() {
        long newId = 10;
        outcomeWithId.setId(newId);
        assertEquals(10, outcomeWithId.getId());
    }
}
