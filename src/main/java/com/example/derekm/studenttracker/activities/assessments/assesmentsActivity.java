package com.example.derekm.studenttracker.activities.assessments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.models.Assessment;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.adapters.assessmentadapter;

public class assesmentsActivity extends AppCompatActivity {
    private DBOpenHelper db;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesments);
        setTitle("Assessments");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("courseId", 1);
        course = db.getCourse(id);
        //setAssessmentsView();
    }

    /*private void setAssessmentsView() {
        final ArrayList<Assessment> AssessmentList = db.getAssessments(course.getId());
        ListAdapter Adapter = new assessmentadapter(this, AssessmentList);
        ListView list = findViewById(R.id.assessmentslist);
        list.setAdapter(Adapter);
        list.setOnItemClickListener();


    }*/
}
