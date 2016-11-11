package com.example.user.lessontracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubjectTest {

    Subject subject;

    @Before
    public void before() {
        subject = new Subject("Ruby");
    }

    @Test
    public void getSubjectTitle() {
        assertEquals("Ruby", subject.getTitle());
    }

    @Test
    public void setSubjectTitle() {
        subject.setTitle("Java");
        assertEquals("Java", subject.getTitle());
    }
}
