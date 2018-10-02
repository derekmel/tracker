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
    public static final String COURSE_TERM_ID = "termId";
    public static final String COURSE_NAME = "name";
    public static final String COURSE_START = "start";
    public static final String COURSE_END = "courseEnd";
    public static final String COURSE_STATUS = "status";


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
    public static final String COURSE_NOTE_COURSE_ID = "courseId";
    public static final String COURSE_NOTE_TEXT = "noteText";
    public static final String COURSE_NOTE_CREATED = "noteCreated";


    // Assessments table
    public static final String TABLE_ASSESSMENTS = "assessments";
    public static final String ASSESSMENTS_TABLE_ID = "id";
    public static final String ASSESSMENT_COURSE_ID = "assessmentCourseId";
    public static final String ASSESSMENT_TYPE = "type";
    public static final String ASSESSMENT_NAME = "name";
    public static final String ASSESSMENT_DESCRIPTION = "description";
    public static final String ASSESSMENT_DATETIME = "datetime";
    public static final String ASSESSMENT_NOTIFICATIONS = "notifications";


    // Goals table
    private static final String TABLE_GOAL = "goalDates";
    private static final String GOAL_DATE_ID = "goalDateId";
    private static final String GOAL_DESCRIPTION = "description";
    private static final String GOAL_DATE = "datetime";
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
                    COURSE_TERM_ID + " INTEGER, " +
                    COURSE_NAME + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
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
                    COURSE_NOTE_COURSE_ID + " INTEGER, " +
                    COURSE_NOTE_TEXT + " TEXT, " +
                    COURSE_NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + COURSE_NOTE_COURSE_ID + ") " + " REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" + ")";

    //Assessments SQL
    private static final String TABLE_ASSESSMENTS_CREATE =
            "CREATE TABLE " + TABLE_ASSESSMENTS + " (" +
                    ASSESSMENTS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    ASSESSMENT_TYPE + " TEXT, " +
                    ASSESSMENT_NAME + " TEXT, " +
                    ASSESSMENT_DESCRIPTION + " TEXT, " +
                    ASSESSMENT_DATETIME + " DATE, " +
                    ASSESSMENT_NOTIFICATIONS + " INTEGER, " +
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


// Terms data provider
    public long createTerm(String name, String start, String termEnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TERM_NAME, name);
        cv.put(TERM_START, start);
        cv.put(TERM_END, termEnd);
        long id = db.insert(TABLE_TERMS, null, cv);
        return id;
    }



    public boolean updateTerm(int id, String name, String start, String termEnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TERM_NAME, name);
        cv.put(TERM_START, start);
        cv.put(TERM_END, termEnd);
        db.update(
                TABLE_TERMS,
                cv,
                TERMS_TABLE_ID + " = ? ",
                new String[] { Integer.toString(id) }
        );
        return true;
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
            int id,
            String name,
            String start,
            String courseEnd,
            String status,
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
                new String[] { Integer.toString(id) }
        );
        for (Mentor mentor : mentors) {
            ContentValues cv1 = new ContentValues();
            cv1.put(MENTOR_NAME, mentor.getmentorname());
            cv1.put(MENTOR_PHONE, mentor.getmentorphone());
            cv1.put(MENTOR_EMAIL, mentor.getmentoremail());
            cv1.put(MENTOR_COURSE_ID, id);
            db.insert(TABLE_MENTOR, null, cv1);
        }
        db.delete(TABLE_MENTOR, MENTOR_COURSE_ID + " = " + id, null);
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
    public ArrayList<Mentor> getMentors(long courseId) {
        ArrayList<Mentor> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_MENTOR + " WHERE " + MENTOR_COURSE_ID + " = " + courseId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String mName = res.getString(1);
            String mPhone = res.getString(2);
            String mEmail = res.getString(3);
            a.add(new Mentor(mName, mPhone, mEmail));
            res.moveToNext();
        }
        res.close();
        return a;
    }




    //assessment stuff
    /*
    public boolean createAssessment(
            String name,
            String type,
            String datetime,
            long courseId,
            ArrayList<Goal> goalDates
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSESSMENT_NAME, name);
        cv.put(ASSESSMENT_TYPE, type);
        cv.put(ASSESSMENT_DATETIME, datetime);
        cv.put(ASSESSMENT_COURSE_ID, courseId);
        long assessmentId = db.insert(TABLE_ASSESSMENTS, null, cv);
        for (Goal goalDate : goalDates) {
            ContentValues cv1 = new ContentValues();
            cv1.put(GOAL_DESCRIPTION, goalDate.description());
            cv1.put(GOAL_DATE, goalDate.date());
            cv1.put(GOAL_ASSESSMENT_ID, assessmentId);
            db.insert(TABLE_GOAL, null, cv1);
        }
        return true;
    }
    */

/*
    public boolean updateAssessment(
            long id,
            String name,
            String type,
            String datetime,
            ArrayList<Goal> goalDates
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
                new String[] { Integer.toString(id) }
        );
        db.delete(TABLE_GOAL, GOAL_ASSESSMENT_ID + " = " + assessmentId, null);
        for (Goal goalDate : goalDates) {
            ContentValues cv1 = new ContentValues();
            cv1.put(GOAL_DESCRIPTION, goalDate.description());
            cv1.put(GOAL_DATE, goalDate.date());
            cv1.put(GOAL_ASSESSMENT_ID, assessmentId);
            db.insert(TABLE_GOAL, null, cv1);
        }
        return true;
    }
    */

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
            long mId = res.getLong(0);
            String name = res.getString(1);
            String type = res.getString(2);
            String due = res.getString(3);
            long tcourseId = res.getLong(4);
            a.add(new Assessment(mId, name, type, due, tcourseId));
            res.moveToNext();
        }
        res.close();
        return a;
    }




    public boolean deleteAssessment(long id, long assessmentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASSESSMENTS, ASSESSMENTS_TABLE_ID + " = " + id, null);
        db.delete(TABLE_GOAL, GOAL_ASSESSMENT_ID + " = " + assessmentId, null);
        return true;
    }



    //goal stuff
/*
    public ArrayList<GoalDate> getGoalDates(long assessmentId) {
        ArrayList<GoalDate> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(
                "SELECT * FROM " + TABLE_GOAL + " WHERE " + GOAL_ASSESSMENT_ID + " = " + assessmentId,
                null
        );
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String Description = res.getString(1);
            String Date = res.getString(2);
            a.add(new GoalDate(Description, Date));
            res.moveToNext();
        }
        res.close();
        return a;
    }
*/










}


