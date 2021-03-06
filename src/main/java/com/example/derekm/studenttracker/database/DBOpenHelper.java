package com.example.derekm.studenttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import com.example.derekm.studenttracker.models.Term;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Note;
import com.example.derekm.studenttracker.models.Mentor;
import com.example.derekm.studenttracker.models.Assessment;
import com.example.derekm.studenttracker.models.Goal;

public class DBOpenHelper extends SQLiteOpenHelper{

    // constants for db name and version
    private static final String DATABASE_NAME = "scheduler.db";

    //Terms Table
    public static final String TABLE_TERMS = "terms";
    public static final String TERMS_TABLE_ID = "id";
    public static final String TERM_NAME = "name";
    public static final String TERM_START = "start";
    public static final String TERM_END = "termEnd";


    //Courses Table
    public static final String TABLE_COURSES = "courses";
    public static final String COURSE_TABLE_ID = "id";
    public static final String COURSE_NAME = "name";
    public static final String COURSE_START = "start";
    public static final String COURSE_END = "courseEnd";
    public static final String COURSE_STATUS = "status";
    public static final String COURSE_TERM_ID = "termId";


    //Mentor Table
    private static final String TABLE_MENTOR = "mentor";
    private static final String MENTOR_TABLE_ID = "id";
    private static final String MENTOR_NAME = "name";
    private static final String MENTOR_PHONE = "phone";
    private static final String MENTOR_EMAIL = "email";
    private static final String MENTOR_COURSE_ID = "courseId";


    // Course Notes table
    public static final String TABLE_COURSE_NOTES = "courseNotes";
    public static final String COURSE_NOTES_TABLE_ID = "id";
    public static final String COURSE_NOTE_TEXT = "noteText";
    public static final String COURSE_NOTE_CREATED = "noteCreated";
    public static final String COURSE_NOTE_COURSE_ID = "courseId";


    // Assessments table
    public static final String TABLE_ASSESSMENTS = "assessments";
    public static final String ASSESSMENTS_TABLE_ID = "id";
    public static final String ASSESSMENT_NAME = "name";
    public static final String ASSESSMENT_TYPE = "type";
    public static final String ASSESSMENT_DATETIME = "date";
    public static final String ASSESSMENT_NOTIFICATIONS = "notifications";
    public static final String ASSESSMENT_COURSE_ID = "courseId";


    // Goals table
    private static final String TABLE_GOAL = "goals";
    private static final String GOAL_DATE_ID = "id";
    private static final String GOAL_DESCRIPTION = "description";
    private static final String GOAL_DATE = "date";
    private static final String GOAL_ASSESSMENT_ID = "assessmentId";



    //Create tables
    //Terms SQL
    private static final String TERMS_TABLE_CREATE =
            "CREATE TABLE " + TABLE_TERMS + " (" +
                    TERMS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_NAME + " TEXT, " +
                    TERM_START + " DATE, " +
                    TERM_END + " DATE " +
                    ")";

    //Courses SQL
    private static final String TABLE_COURSES_CREATE =
            "CREATE TABLE " + TABLE_COURSES + " (" +
                    COURSE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_NAME + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
                    COURSE_TERM_ID + " INTEGER, " +
                    "FOREIGN KEY(" + COURSE_TERM_ID + ") " + " REFERENCES " + TABLE_TERMS + "(" + TERMS_TABLE_ID + ")" + ")";

    //Mentors SQL
    private static final String TABLE_MENTOR_CREATE =
            "CREATE TABLE " + TABLE_MENTOR + " (" +
                    MENTOR_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MENTOR_NAME + " TEXT, " +
                    MENTOR_PHONE + " TEXT, " +
                    MENTOR_EMAIL + " TEXT, " +
                    MENTOR_COURSE_ID + " INTEGER, " +
                    "FOREIGN KEY(" + MENTOR_COURSE_ID + ") " + "  REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" + ")";

    //Course notes SQL
    private static final String TABLE_COURSE_NOTES_CREATE =
            "CREATE TABLE " + TABLE_COURSE_NOTES + " (" +
                    COURSE_NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_NOTE_TEXT + " TEXT, " +
                    COURSE_NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    COURSE_NOTE_COURSE_ID + " INTEGER, " +
                    "FOREIGN KEY(" + COURSE_NOTE_COURSE_ID + ") " + " REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" + ")";

    //Assessments SQL
    private static final String TABLE_ASSESSMENTS_CREATE =
            "CREATE TABLE " + TABLE_ASSESSMENTS + " (" +
                    ASSESSMENTS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_NAME + " TEXT, " +
                    ASSESSMENT_TYPE + " TEXT, " +
                    //ASSESSMENT_DESCRIPTION + " TEXT, " +
                    ASSESSMENT_DATETIME + " DATE, " +
                    ASSESSMENT_NOTIFICATIONS + " INTEGER, " +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    "FOREIGN KEY(" + ASSESSMENT_COURSE_ID + ") " + " REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" + ")";

    // goal dates sql
    private static final String TABLE_GOALS_CREATE =
            "CREATE TABLE " + TABLE_GOAL + " (" +
                    GOAL_DATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GOAL_DESCRIPTION + " TEXT, " +
                    GOAL_DATE + " TEXT, " +
                    GOAL_ASSESSMENT_ID + " INTEGER, " +
                    "FOREIGN KEY(" + GOAL_ASSESSMENT_ID + ") " + " REFERENCES " + TABLE_ASSESSMENTS + "(" + ASSESSMENTS_TABLE_ID + ")" + ")";



    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TERMS_TABLE_CREATE);
        db.execSQL(TABLE_COURSES_CREATE);
        db.execSQL(TABLE_COURSE_NOTES_CREATE);
        db.execSQL(TABLE_ASSESSMENTS_CREATE);
        db.execSQL(TABLE_GOALS_CREATE);
        db.execSQL(TABLE_MENTOR_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_NOTES + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOAL + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENTOR + ";");
        onCreate(db);

    }


// Terms data
    public long createTerm(String name, String start, String termEnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TERM_NAME, name);
        cv.put(TERM_START, start);
        cv.put(TERM_END, termEnd);
        long id = db.insert(TABLE_TERMS, null, cv);
        return id;
    }



    public void deleteTerm(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TERMS, TERMS_TABLE_ID + " = " + id, null);

    }



    public Term getTerm(long id) {
        //id = _id;
        Term term;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_TERMS + " WHERE " + TERMS_TABLE_ID + " = " + id,
                null
        );
        cursor.moveToFirst();
        term = new Term(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        cursor.close();
        return term;
    }

    public ArrayList<Term> getTerms() {
        ArrayList<Term> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TERMS, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            String start = cursor.getString(2);
            String end = cursor.getString(3);
            a.add(new Term(id, name, start, end));
            cursor.moveToNext();
        }
        cursor.close();
        return a;
    }


    //Courses stuff
    public long createCourse(
            String name,
            String start,
            String courseEnd,
            String status,
            long termId,
            ArrayList<Mentor> mentors
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSE_NAME, name);
        cv.put(COURSE_START, start);
        cv.put(COURSE_END, courseEnd);
        cv.put(COURSE_STATUS, status);
        cv.put(COURSE_TERM_ID, termId);
        long courseId = db.insert(TABLE_COURSES, null, cv);
        for (Mentor mentor : mentors) {
            ContentValues cv1 = new ContentValues();
            cv1.put(MENTOR_NAME, mentor.getmentorname());
            cv1.put(MENTOR_PHONE, mentor.getmentorphone());
            cv1.put(MENTOR_EMAIL, mentor.getmentoremail());
            cv1.put(MENTOR_COURSE_ID, courseId);
            db.insert(TABLE_MENTOR, null, cv1);
        }
        return courseId;
    }

    public boolean updateCourse(
            long id,
            String name,
            String start,
            String courseEnd,
            String status,
            long mentorId,
            ArrayList<Mentor> mentors
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSE_NAME, name);
        cv.put(COURSE_START, start);
        cv.put(COURSE_END, courseEnd);
        cv.put(COURSE_STATUS, status);
        db.update(
                TABLE_COURSES,
                cv,
                COURSE_TABLE_ID + " = ? ",
                new String[] { Long.toString(id) }
        );
        for (Mentor mentor : mentors) {
            ContentValues cv1 = new ContentValues();
            cv1.put(MENTOR_NAME, mentor.getmentorname());
            cv1.put(MENTOR_PHONE, mentor.getmentorphone());
            cv1.put(MENTOR_EMAIL, mentor.getmentoremail());
            cv1.put(MENTOR_COURSE_ID, id);

            //db.insert(TABLE_MENTOR, null, cv1);
            //this line works on update, but also updates on Add Mentor button instead of adding new mentor.
            db.update(TABLE_MENTOR, cv1,MENTOR_TABLE_ID + " = ?", new String[] {Long.toString(mentorId)});
        }
        return true;
    }



    public boolean deleteCourse(long courseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSES, COURSE_TABLE_ID + " = " + courseId, null);
        db.delete(TABLE_ASSESSMENTS, ASSESSMENT_COURSE_ID + " = " + courseId, null);
        db.delete(TABLE_COURSE_NOTES, COURSE_NOTE_COURSE_ID + " = " + courseId, null);
        return true;
    }

    public Course getCourse(long courseId) {
        Course course;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_COURSES + " WHERE " + COURSE_TABLE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        course = new Course(
                res.getLong(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getLong(5)
        );
        res.close();
        return course;
    }



    public ArrayList<Course> getCourses(long termId) {
        ArrayList<Course> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_COURSES + " WHERE " + COURSE_TERM_ID + " = " + termId,
                null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            long id = res.getLong(0);
            String name = res.getString(1);
            String status = res.getString(2);
            String start = res.getString(3);
            String courseEnd = res.getString(4);
            long tTermId = res.getLong(5);
            a.add(new Course(id, name, status, start, courseEnd, tTermId));
            res.moveToNext();
        }
        res.close();
        return a;
    }



    //notes stuff
    public void insertNote(long courseId, String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSE_NOTE_COURSE_ID, courseId);
        cv.put(COURSE_NOTE_TEXT, noteText);
        db.insert(TABLE_COURSE_NOTES, null, cv);
    }

    public void updateNote(int id, String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COURSE_NOTE_TEXT, noteText);
        db.update(
                TABLE_COURSE_NOTES,
                cv,
                COURSE_NOTES_TABLE_ID + " = ?",
                new String[] { Integer.toString(id)}
        );
    }

    public void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE_NOTES, COURSE_NOTES_TABLE_ID + " = " + id, null);
    }

    public Note getNote(long id) {
        Note note;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_COURSE_NOTES + " WHERE " + COURSE_NOTES_TABLE_ID + " = " + id, null
        );
        res.moveToFirst();
        note = new Note(
                res.getLong(0),
                res.getString(1),
                res.getLong(2)
        );
        res.close();
        return note;

    }

    public ArrayList<Note> getNotes(long courseId) {
        ArrayList<Note> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_COURSE_NOTES + " WHERE " + COURSE_NOTE_COURSE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            long id = res.getInt(0);
            String note = res.getString(1);
            long coursenId = res.getLong(2);
            a.add(new Note(id, note, coursenId));
            res.moveToNext();
        }
        res.close();
        return a;
    }


    //mentor stuff

    public boolean createMentor(
            String name,
            String phone,
            String email,
            long courseId,
            ArrayList<Mentor> mentors
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Mentor mentor : mentors) {
            ContentValues cv = new ContentValues();
            cv.put(MENTOR_NAME, mentor.getmentorname());
            cv.put(MENTOR_PHONE, mentor.getmentorphone());
            cv.put(MENTOR_EMAIL, mentor.getmentoremail());
            cv.put(MENTOR_COURSE_ID, courseId);
            db.insert(TABLE_MENTOR, null, cv);
        }
        return true;

    }


    public ArrayList<Mentor> getMentors(long courseId) {
        ArrayList<Mentor> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_MENTOR + " WHERE " + MENTOR_COURSE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            Long id = res.getLong(0);
            String mName = res.getString(1);
            String mPhone = res.getString(2);
            String mEmail = res.getString(3);
            Long mcourseId = res.getLong(4);
            a.add(new Mentor(id, mName, mPhone, mEmail, mcourseId));
            res.moveToNext();
        }
        res.close();
        return a;
    }

    public Mentor getMentor(long courseId) {
        Mentor mentor;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MENTOR + " WHERE " + MENTOR_COURSE_ID + " = " + courseId;
        Cursor res = db.rawQuery(
                query, null
        );
        res.moveToFirst();
        mentor = new Mentor(
                res.getLong(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getLong(4)
        );
        res.close();
        return mentor;
    }



    public boolean deleteMentor(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MENTOR, MENTOR_TABLE_ID + " = " + id, null);
        return true;

    }



    //assessment stuff

    public long createAssessment(
            String name,
            String type,
            String date,
            long courseId,
            ArrayList<Goal> goalDates
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSESSMENT_NAME, name);
        cv.put(ASSESSMENT_TYPE, type);
        cv.put(ASSESSMENT_DATETIME, date);
        cv.put(ASSESSMENT_COURSE_ID, courseId);
        long id = db.insert(TABLE_ASSESSMENTS, null, cv);
        for (Goal goalDate : goalDates) {
            ContentValues cv1 = new ContentValues();
            cv1.put(GOAL_DESCRIPTION, goalDate.getDescription());
            cv1.put(GOAL_DATE, goalDate.getDate());
            cv1.put(GOAL_ASSESSMENT_ID, id);
            db.insert(TABLE_GOAL, null, cv1);
        }
        return id;
    }


    public boolean updateAssessment(
            long id,
            String name,
            String type,
            String datetime,
            long goalId,
            ArrayList<Goal> goals
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSESSMENT_NAME, name);
        cv.put(ASSESSMENT_TYPE, type);
        cv.put(ASSESSMENT_DATETIME, datetime);
        db.update(
                TABLE_ASSESSMENTS,
                cv,
                ASSESSMENTS_TABLE_ID + " = ?",
                new String[] { Long.toString(id) }
        );
        //db.delete(TABLE_GOAL, GOAL_ASSESSMENT_ID + " = " + id, null);
        for (Goal goal : goals) {
            ContentValues cv1 = new ContentValues();
            cv1.put(GOAL_DESCRIPTION, goal.getDescription());
            cv1.put(GOAL_DATE, goal.getDate());
            cv1.put(GOAL_ASSESSMENT_ID, id);
            db.update(TABLE_GOAL, cv1, GOAL_DATE_ID + " = ?", new String[] {Long.toString(goalId)});
        }
        return true;
    }


    public Assessment getAssessment(long id) {
        Assessment assessment;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_ASSESSMENTS + " WHERE " + ASSESSMENTS_TABLE_ID + " = " + id,
                null
        );
        res.moveToFirst();
        assessment = new Assessment(
                res.getLong(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getLong(4)
        );
        res.close();
        return assessment;
    }

    public ArrayList<Assessment> getAssessments(long courseId) {
        ArrayList<Assessment> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_ASSESSMENTS + " WHERE " + ASSESSMENT_COURSE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            long id = res.getLong(0);
            String name = res.getString(1);
            String type = res.getString(2);
            String due = res.getString(3);
            long CourseId = res.getLong(4);
            a.add(new Assessment(id, name, type, due, CourseId));
            res.moveToNext();
        }
        res.close();
        return a;
    }




    public boolean deleteAssessment(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASSESSMENTS, ASSESSMENTS_TABLE_ID + " = " + id, null);
        db.delete(TABLE_GOAL, GOAL_ASSESSMENT_ID + " = " + id, null);
        return true;
    }



    //goal stuff

    public ArrayList<Goal> getGoals(long assessmentId) {
        ArrayList<Goal> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_GOAL + " WHERE " + GOAL_ASSESSMENT_ID + " = " + assessmentId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            Long id = res.getLong(0);
            String Description = res.getString(1);
            String Date = res.getString(2);
            Long massessmentId = res.getLong(3);
            a.add(new Goal(id, Description, Date, massessmentId));
            res.moveToNext();
        }
        res.close();
        return a;
    }

    public Goal getGoal(long assessmentId) {
        Goal goal;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_GOAL + " WHERE " + GOAL_ASSESSMENT_ID + " = " + assessmentId;
        Cursor res = db.rawQuery(
                query, null
        );
        res.moveToFirst();
        goal = new Goal(
                res.getLong(0),
                res.getString(1),
                res.getString(2),
                res.getLong(3)
        );
        res.close();
        return goal;
    }




}


