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

    public static final class LearningObjectiveTable {
        public static final String NAME = "learningObjectives";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String TOPIC_ID = "topic_id";
            public static final String TITLE = "title";
            public static final String DETAIL = "detail";
        }
    }

    public static final class LessonTable {
        public static final String NAME = "lessons";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String COHORT_ID = "cohort_id";
            public static final String TOPIC_ID = "topic_id";
            public static final String DATE = "date";
            public static final String TAUGHT = "taught";
            public static final String DURATION = "duration";
            public static final String NOTES = "notes";
        }
    }

    public static final class OutcomeTable {
        public static final String NAME = "outcomes";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String LESSON_ID = "lesson_id";
            public static final String LEARNING_OBJECTIVE_ID = "learning_objective_id";
        }
    }

    public static final class TagTable {
        public static final String NAME = "tags";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String ICON_RESOURCE_ID = "icon_resource_id";
            public static final String TITLE = "title";
            public static final String TYPE = "type";
        }
    }

    public static final class TaggingTable {
        public static final String NAME = "taggings";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String TAG_ID = "tag_id";
            public static final String OUTCOME_ID = "outcome_id";
        }
    }

}
