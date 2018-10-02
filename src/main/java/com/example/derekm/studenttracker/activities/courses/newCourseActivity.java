package com.example.derekm.studenttracker.activities.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;

import java.util.ArrayList;

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
        term = db.getTerm(intent.getLongExtra("termId", 1));

        cnameInput = findViewById(R.id.cname_input);
        startInput = findViewById(R.id.start_input);
        endInput = findViewById(R.id.end_input);
        statusInput = findViewById(R.id.status_input);
        nameInput = findViewById(R.id.name_input);
        phoneInput = findViewById(R.id.phone_input);
        emailInput = findViewById(R.id.email_input);


        //why is this adding a new courses entry??

        Intent intent1 = getIntent();
        String name, start, end, status, mname, phone, email;
        if (intent1.hasExtra("name")) {
            name=intent1.getStringExtra("name");
            cnameInput.setText(name);
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
        /*if (intent1.hasExtra("name")) {
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
        }*/


    }


    public void saveCourseButtonHandler (View view) {
        String name = cnameInput.getText().toString();
        String start = startInput.getText().toString();
        String end = endInput.getText().toString();
        String status = statusInput.getText().toString();
        long termId = term.getId();//course needs to be attached to a specific term ID
        //db.deleteCourse(getIntent().getLongExtra("id", 1));  This is deleting the entry on a new course

        mentorList = new ArrayList<>();
        mentorList.add(new Mentor(
                nameInput.getText().toString(),
                phoneInput.getText().toString(),
                emailInput.getText().toString()

        ));


        long l = db.createCourse(name, start, end, status, termId, mentorList);

        Intent back = new Intent(this, coursesActivity.class);
        startActivity(back);

    }


    public void cancelAddCourseButtonHandler (View view) {
        Intent courseIntent = new Intent(this, coursesActivity.class);
        startActivity(courseIntent);

    }
}
