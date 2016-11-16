package com.example.user.lessontracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.user.lessontracker.database.LessonTrackerSchema.LearningObjectiveTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.LessonTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.OutcomeTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.SubjectTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TagTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TaggingTable;
import com.example.user.lessontracker.database.LessonTrackerSchema.TopicTable;
import com.example.user.lessontracker.models.LearningObjective;
import com.example.user.lessontracker.models.Lesson;
import com.example.user.lessontracker.models.Outcome;
import com.example.user.lessontracker.models.Subject;
import com.example.user.lessontracker.models.Tag;
import com.example.user.lessontracker.models.Tagging;
import com.example.user.lessontracker.models.Topic;

/**
 * Created by user on 13/11/2016.
 */

public class LessonTrackerCursorWrapper extends CursorWrapper {

    public LessonTrackerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Subject getSubject() {
        long id = getLong(getColumnIndex(SubjectTable.Cols.ID));
        String title = getString(getColumnIndex(SubjectTable.Cols.TITLE));
        String detail = getString(getColumnIndex(SubjectTable.Cols.DETAIL));

        Subject subject = new Subject(id, title, detail);
        return subject;
    }

    public Topic getTopic() {
        long id = getLong(getColumnIndex(TopicTable.Cols.ID));
        long subjectId = getLong(getColumnIndex(TopicTable.Cols.SUBJECT_ID));
        String title = getString(getColumnIndex(TopicTable.Cols.TITLE));
        String detail = getString(getColumnIndex(TopicTable.Cols.DETAIL));

        Topic topic = new Topic(id, subjectId, title, detail);
        return topic;
    }

    public LearningObjective getLearningObjective() {
        long id = getLong(getColumnIndex(LearningObjectiveTable.Cols.ID));
        long topicId = getLong(getColumnIndex(LearningObjectiveTable.Cols.TOPIC_ID));
        String title = getString(getColumnIndex(LearningObjectiveTable.Cols.TITLE));
        String detail = getString(getColumnIndex(LearningObjectiveTable.Cols.DETAIL));

        LearningObjective learningObjective = new LearningObjective(id, topicId, title, detail);
        return learningObjective;
    }

    public Lesson getLesson() {
        long id = getLong(getColumnIndex(LessonTable.Cols.ID));
        long cohortId = getLong(getColumnIndex(LessonTable.Cols.COHORT_ID));
        long topicId = getLong(getColumnIndex(LessonTable.Cols.TOPIC_ID));
        long date = getLong(getColumnIndex(LessonTable.Cols.DATE));
        int taught = getInt(getColumnIndex(LessonTable.Cols.TAUGHT));

        Lesson lesson = new Lesson(id, cohortId, topicId, date, taught);
        return lesson;
    }

    public Outcome getOutcome() {
        long id = getLong(getColumnIndex(OutcomeTable.Cols.ID));
        long lessonId = getLong(getColumnIndex(OutcomeTable.Cols.LESSON_ID));
        long learningObjectiveId = getLong(getColumnIndex(OutcomeTable.Cols.LEARNING_OBJECTIVE_ID));

        Outcome outcome = new Outcome(id, lessonId, learningObjectiveId);
        return outcome;
    }

    public Tag getTag() {
        long id = getLong(getColumnIndex(TagTable.Cols.ID));
        int iconResourceId = getInt(getColumnIndex(TagTable.Cols.ICON_RESOURCE_ID));
        String title = getString(getColumnIndex(TagTable.Cols.TITLE));

        Tag tag = new Tag(id, iconResourceId, title);
        return tag;
    }

    public Tagging getTagging() {
        long id = getLong(getColumnIndex(TaggingTable.Cols.ID));
        long tagId = getLong(getColumnIndex(TaggingTable.Cols.TAG_ID));
        long outcomeId = getLong(getColumnIndex(TaggingTable.Cols.OUTCOME_ID));

        Tagging tagging = new Tagging(id, tagId, outcomeId);
        return tagging;
    }
}
