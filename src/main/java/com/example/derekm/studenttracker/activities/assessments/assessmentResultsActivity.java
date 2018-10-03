package com.example.derekm.studenttracker.activities.assessments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.derekm.studenttracker.adapters.goaladapter;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Goal;
import com.example.derekm.studenttracker.models.Assessment;
import com.example.derekm.studenttracker.R;

import org.w3c.dom.Text;

public class assessmentResultsActivity extends AppCompatActivity {
    private DBOpenHelper db;
    private Course course;
    private Goal goal;
    private Assessment assessment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_results);
        setTitle("Assessment Details");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();

        course = db.getCourse(intent.getLongExtra("courseId", 1));
        assessment = db.getAssessment(intent.getLongExtra("assessmentId", 1));

        TextView name = findViewById(R.id.assessment_name);
        TextView type = findViewById(R.id.assessment_type);
        TextView date = findViewById(R.id.assessment_due);

        name.setText(assessment.getName());
        type.setText(assessment.getType());
        date.setText(assessment.getDue());


        ArrayList<Goal> goalList = db.getGoalDates(assessment.getAssessmentId());
        ListAdapter Adapter = new goaladapter(this, goalList);
        ListView list = findViewById(R.id.list_view_goals);
        list.setAdapter(Adapter);
    }
}
