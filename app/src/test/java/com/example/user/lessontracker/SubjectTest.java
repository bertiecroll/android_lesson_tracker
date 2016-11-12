package com.example.user.lessontracker;

import com.example.user.lessontracker.models.Subject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubjectTest {

    Subject subject;

    @Before
    public void before() {
        subject = new Subject("Ruby", "A dynamic programming language");
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

    @Test
    public void getSubjectDetail() {
        assertEquals("A dynamic programming language", subject.getDetail());
    }

    @Test
    public void setSubjectDetail() {
        subject.setDetail("Designed by Matz");
        assertEquals("Designed by Matz", subject.getDetail());
    }
}
