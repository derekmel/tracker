package com.example.derekm.studenttracker.activities.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.io.Serializable;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.adapters.mentoradapter;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Term;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Mentor;
import com.example.derekm.studenttracker.activities.assessments.assesmentsActivity;

import java.util.ArrayList;
import android.widget.ListView;
import java.util.List;

public class courseResultsActivity extends AppCompatActivity {
    private DBOpenHelper db;
    public Term term;
    public Course course;
    public Mentor mentor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_results);
        setTitle("Course Details");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();

        term = db.getTerm(intent.getLongExtra("termId", 1));
        course = db.getCourse(intent.getLongExtra("courseId", 1));

        TextView name = findViewById(R.id.course_name);
        TextView start = findViewById(R.id.course_start);
        TextView end = findViewById(R.id.course_end);
        TextView status = findViewById(R.id.course_status);
        name.setText(course.getName());
        start.setText(course.getStart());
        end.setText(course.getEnd());
        status. setText(course.getStatus());


        ArrayList<Mentor> mentorList = db.getMentors(course.getId());
        ListAdapter Adapter = new mentoradapter(this, mentorList);
        ListView list = findViewById(R.id.list_view_mentors);
        list.setAdapter(Adapter);


    }

    public void addMentorButtonHandler(View view) {

    }

    public void removeMentorButtonHandler(View view) {

    }

    public void deleteButtonHandler(View view) {
        db.deleteCourse(course.getId());
        Intent remove = new Intent(this, coursesActivity.class);
        startActivity(remove);

    }

    public void assessmentsButtonHandler(View view) {
        Intent assessintent = new Intent(this, assesmentsActivity.class);
        assessintent.putExtra("courseId", course.getId());
        startActivity(assessintent);

    }

    public void notesButtonHandler(View view) {
        Intent notesintent = new Intent(this, notesActivity.class);
        notesintent.putExtra("courseId", course.getId());
        startActivity(notesintent);

    }

    public void editButtonHandler(View view) {
        Intent intent1 = new Intent(this, newCourseActivity.class);
        intent1.putExtra("id", course.getId());
        intent1.putExtra("name" , course.getName());
        intent1.putExtra("start", course.getStart());
        intent1.putExtra("end", course.getEnd());
        intent1.putExtra("status", course.getStatus());
        //how to pass the mentor arraylist to newcourse activity?????
        //intent.putExtra("name", mentor.getmentorname());
        //intent.putExtra("phone", mentor.getmentorphone());
        //intent.putExtra("email", mentor.getmentoremail());
        startActivity(intent1);

    }

}
