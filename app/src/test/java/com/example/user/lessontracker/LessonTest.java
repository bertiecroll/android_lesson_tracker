package com.example.user.lessontracker;

import com.example.user.lessontracker.models.Lesson;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LessonTest {

    Lesson lesson;

    @Before
    public void before() {
        long cohortId = 7;
        long topicId = 1;
        lesson = new Lesson(cohortId, topicId);
    }

    @Test
    public void getCohortId() {
        assertEquals(7, lesson.getCohortId());
    }

    @Test
    public void setCohortId() {
        long newCohortId = 8;
        lesson.setCohortId(newCohortId);
        assertEquals(8, lesson.getCohortId());
    }
}
