package com.example.derekm.studenttracker.activities.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;

import java.util.ArrayList;

import com.example.derekm.studenttracker.activities.terms.termsListActivity;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.models.Mentor;
import com.example.derekm.studenttracker.models.Term;
import com.example.derekm.studenttracker.models.Course;

public class newCourseActivity  extends AppCompatActivity {

    private DBOpenHelper db;
    private ArrayList<Mentor> mentorList;
    private Term term;
    private Course course;
    private Mentor mentor;
    private EditText cnameInput;
    private EditText startInput;
    private EditText endInput;
    private EditText statusInput;
    private EditText nameInput;
    private EditText phoneInput;
    private EditText emailInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcourseadd);
        setTitle("New Course");

        db = new DBOpenHelper(this);
        Intent intent = getIntent();
        long l = intent.getLongExtra("termId", 100);
        long i = intent.getLongExtra("id", 100);
        long j = intent.getLongExtra("courseId", 100);
        term = db.getTerm(intent.getLongExtra("termId", 100));
        //course = db.getCourse(intent.getLongExtra("courseId", 1));
        //mentorList = db.getMentors(course.getId());
        //long mentorId = mentor.getCourseId();

        cnameInput = findViewById(R.id.cname_input);
        startInput = findViewById(R.id.start_input);
        endInput = findViewById(R.id.end_input);
        statusInput = findViewById(R.id.status_input);
        nameInput = findViewById(R.id.name_input);
        phoneInput = findViewById(R.id.phone_input);
        emailInput = findViewById(R.id.email_input);


        Intent intent1 = getIntent();
        String cname, start, end, status, name, phone, email, id, mid;
        if (intent1.hasExtra("cname")) {
            cname=intent1.getStringExtra("cname");
            cnameInput.setText(cname);
        }
        if (intent1.hasExtra("start")) {
            start=intent1.getStringExtra("start");
            startInput.setText(start);
        }
        if (intent1.hasExtra("end")) {
            end=intent1.getStringExtra("end");
            endInput.setText(end);
        }
        if (intent1.hasExtra("status")) {
            status=intent1.getStringExtra("status");
            statusInput.setText(status);
        }
        if (intent1.hasExtra("name")) {
            name=intent1.getStringExtra("name");
            nameInput.setText(name);
        }
        if (intent1.hasExtra("phone")) {
            phone=intent1.getStringExtra("phone");
            phoneInput.setText(phone);
        }
        if (intent1.hasExtra("email")) {
            email=intent1.getStringExtra("email");
            emailInput.setText(email);
        }
        if (intent1.hasExtra("id")) {
            long d = intent1.getLongExtra("id", 1);
        }
        if (intent1.hasExtra("courseId")) {
            long d = intent1.getLongExtra("courseId", 1);
        }



    }


    public void saveCourseButtonHandler (View view) {
        String cname = cnameInput.getText().toString();
        String start = startInput.getText().toString();
        String end = endInput.getText().toString();
        String status = statusInput.getText().toString();
        long termId = term.getId();

        Intent intent1 = getIntent();
        if (intent1.hasExtra("id")) { //if there are courses already existing
            course = db.getCourse(intent1.getLongExtra("id", 1));
            mentor = db.getMentor(intent1.getLongExtra("courseId",1));
            System.out.println(mentor);

            long mentorId = mentor.getId();
            System.out.println(mentor.getId());
            long courseId = course.getId();
            System.out.println(courseId);
            mentorList = new ArrayList<>();
            mentorList.add(new Mentor(
                    mentorId,
                    nameInput.getText().toString(),
                    phoneInput.getText().toString(),
                    emailInput.getText().toString(),
                    courseId
                    ));

            db.updateCourse(intent1.getLongExtra("id", 100), cname, start,
                    end, status, mentorId, mentorList);
        }
        else { //if no courses exist yet
            long courseId = term.getId();
            long mentorId = term.getId();
            mentorList = new ArrayList<>();
            mentorList.add(new Mentor(
                    mentorId,
                    nameInput.getText().toString(),
                    phoneInput.getText().toString(),
                    emailInput.getText().toString(),
                    courseId

            ));

            long l = db.createCourse(cname, start, end, status, termId, mentorList);
        }

        Intent back = new Intent(this, termsListActivity.class);
        startActivity(back);

    }


    public void cancelAddCourseButtonHandler (View view) {
        Intent courseIntent = new Intent(this, termsListActivity.class);
        startActivity(courseIntent);

    }
}
