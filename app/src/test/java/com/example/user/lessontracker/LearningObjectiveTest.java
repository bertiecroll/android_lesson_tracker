package com.example.user.lessontracker;

import com.example.user.lessontracker.models.LearningObjective;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LearningObjectiveTest {

    LearningObjective learningObjective;
    LearningObjective learningObjectiveWithId;
    String title = "Iterating an Array";
    String detail = "Learning how to iterate through an array";

    @Before
    public void before() {
        learningObjective = new LearningObjective(1, title , detail);
        learningObjectiveWithId = new LearningObjective(10, 1, title, detail);
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

    @Test
    public void getLearningObjectiveDetail() {
        assertEquals(detail, learningObjective.getDetail());
    }

    @Test
    public void setLearningObjectiveDetail() {
        String newDetail = "Using .each on arrays";
        learningObjective.setDetail(newDetail);
        assertEquals(newDetail, learningObjective.getDetail());
    }

    @Test
    public void getLearningObjectiveTopicId() {
        assertEquals(1, learningObjective.getTopicId());
    }

    @Test
    public void setLearningObjectiveTopicId() {
        long newTopicId = 2;
        learningObjective.setTopicId(newTopicId);
        assertEquals(2, learningObjective.getTopicId());
    }

    @Test
    public void getLearningObjectiveId() {
        assertEquals(10, learningObjectiveWithId.getId());
    }

    @Test
    public void setLearningObjectiveId() {
        long newId = 20;
        learningObjectiveWithId.setId(newId);
        assertEquals(20, learningObjectiveWithId.getId());
    }
}
