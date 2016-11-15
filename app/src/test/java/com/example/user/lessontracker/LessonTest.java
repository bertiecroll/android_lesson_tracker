package com.example.user.lessontracker;

import com.example.user.lessontracker.models.Lesson;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LessonTest {

    Lesson lesson;
    Lesson lessonWithId;

    @Before
    public void before() {
        long cohortId = 7;
        long topicId = 1;
        Date date = new Date();
        lesson = new Lesson(cohortId, topicId, date.getTime(), 0);
        lessonWithId = new Lesson(1, cohortId, topicId, date.getTime(), 1);
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

    @Test
    public void getTopicId() {
        assertEquals(1, lesson.getTopicId());
    }

    @Test
    public void setTopicId() {
        lesson.setTopicId(5);
        assertEquals(5, lesson.getTopicId());
    }

    @Test
    public void getDate() {
        Date testDate = new Date();
        assertEquals(testDate.getTime(), lesson.getDate().getTime(), 0.001);
    }

    @Test
    public void getId() {
        assertEquals(1, lessonWithId.getId());
    }

    @Test
    public void setId() {
        lessonWithId.setId(2);
        assertEquals(2, lessonWithId.getId());
    }

    @Test
    public void hasLessonBeenTaught() {
        assertEquals(false, lesson.hasBeenTaught());
    }

    @Test
    public void canTeachLesson() {
        lesson.teach();
        assertEquals(true, lesson.hasBeenTaught());
    }


}
