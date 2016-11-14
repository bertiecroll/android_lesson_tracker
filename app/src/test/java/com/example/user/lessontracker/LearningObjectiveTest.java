package com.example.user.lessontracker;

import com.example.user.lessontracker.models.LearningObjective;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LearningObjectiveTest {

    LearningObjective learningObjective;
    String title = "Iterating an Array";
    String detail = "Learning how to iterate through an array";

    @Before
    public void before() {
        learningObjective = new LearningObjective(1, title , detail);
    }

    @Test
    public void getLearningObjectiveTitle() {
        assertEquals(title, learningObjective.getTitle());
    }

    @Test
    public void setLearningObjectiveTitle() {
        String newTitle = "Iterating hashes";
        learningObjective.setTitle(newTitle);
        assertEquals(newTitle, learningObjective.getTitle());
    }
}
