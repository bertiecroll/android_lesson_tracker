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
}
