package com.example.user.lessontracker.database;

public class LessonTrackerSchema {

    public static final class SubjectTable {
        public static final String NAME = "subjects";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String TITLE = "title";
            public static final String DETAIL = "detail";
        }
    }

    public static final class TopicTable {
        public static final String NAME = "topics";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String SUBJECT_ID = "subject_id";
            public static final String TITLE = "title";
            public static final String DETAIL = "detail";
        }
    }

}
