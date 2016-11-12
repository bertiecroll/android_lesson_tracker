package com.example.user.lessontracker;

import com.example.user.lessontracker.models.Topic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopicTest {

    Topic topic;

    @Before
    public void before() {
        topic = new Topic(1, "Collections", "How to organise large amounts of data");
    }

    @Test
    public void getTopicTitle() {
        assertEquals("Collections", topic.getTitle());
    }

    @Test
    public void setTopicTitle() {
        topic.setTitle("Loops");
        assertEquals("Loops", topic.getTitle());
    }

    @Test
    public void getTopicDetail() {
        assertEquals("How to organise large amounts of data", topic.getDetail());
    }

    @Test
    public void setTopicDetail() {
        topic.setDetail("Hashes and Arrays");
        assertEquals("Hashes and Arrays", topic.getDetail());
    }
}
