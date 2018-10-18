package com.example.derekm.studenttracker.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;

import com.example.derekm.studenttracker.activities.terms.termsListActivity;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Assessment;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Goal;
import com.example.derekm.studenttracker.R;

import java.util.ArrayList;

public class newAssessmentActivity extends AppCompatActivity {

    private DBOpenHelper db;
    private ArrayList<Goal> goalList;
    private Course course;
    private Assessment assessment;
    private Goal goal;
    private EditText nameInput;
    private EditText typeInput;
    private EditText dueInput;
    private EditText goaldateInput;
    private EditText descriptionInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newassessmentadd);
        db = new DBOpenHelper(this);
        setTitle("New Assessment");

        Intent intent = getIntent();
        long l = intent.getLongExtra("courseId", 100);
        long i = intent.getLongExtra("assessmentId", 100);
        long j = intent.getLongExtra("id", 100);
        course = db.getCourse(intent.getLongExtra("courseId", 100));

        nameInput = findViewById(R.id.assessment_name);
        typeInput = findViewById(R.id.assessment_type);
        dueInput = findViewById(R.id.due_input);
        descriptionInput = findViewById(R.id.description_input);
        goaldateInput = findViewById(R.id.goaldate_input);


        //logic to edit assessment info
        Intent intent1 = getIntent();
        String name, type, due, description, date, id, assessmentId;
        if (intent1.hasExtra("name")) {
            name = intent1.getStringExtra("name");
            nameInput.setText(name);
        }
        if (intent1.hasExtra("type")) {
            type = intent1.getStringExtra("type");
            typeInput.setText(type);
        }
        if (intent1.hasExtra("due")) {
            due = intent1.getStringExtra("due");
            dueInput.setText(due);
        }
        if (intent1.hasExtra("description")) {
            description = intent1.getStringExtra("description");
            descriptionInput.setText(description);
        }
        if (intent1.hasExtra("date")) {
            date = intent1.getStringExtra("date");
            goaldateInput.setText(date);
        }
        if (intent1.hasExtra("id")) {
            long d = intent1.getLongExtra("id", 1);
        }
        if (intent1.hasExtra("assessmentId")) {
            long f = intent1.getLongExtra("assessmentId", 1);
        }
    }

    public void saveButtonHandler (View view) {
        String name = nameInput.getText().toString();
        String type = typeInput.getText().toString();
        String date = dueInput.getText().toString();
        long courseId = course.getId();

        Intent intent1 = getIntent();
        if (intent1.hasExtra("id")) {
            assessment = db.getAssessment(intent1.getLongExtra("id", 1));
            goal = db.getGoal(intent1.getLongExtra("assessmentId", 1));

            long goalId = goal.getId();
            long assessmentId = assessment.getId();

            goalList = new ArrayList<>();
            goalList.add(new Goal(
                    goalId,
                    descriptionInput.getText().toString(),
                    goaldateInput.getText().toString(),
                    assessmentId
            ));

            db.updateAssessment(intent1.getLongExtra("id", 100), name, type, date, goalId, goalList);

        }
        else {
            long goalId = course.getId();
            long assesmentId = course.getId();
            goalList = new ArrayList<>();
            goalList.add(new Goal(
                    goalId,
                    descriptionInput.getText().toString(),
                    goaldateInput.getText().toString(),
                    assesmentId
            ));

            long l = db.createAssessment(name, type, date, courseId, goalList);
        }

        Intent back = new Intent(this, termsListActivity.class);
        startActivity(back);
        recreate();




    }

    public void cancelButtonHandler (View view) {
        Intent assessmentIntent = new Intent (this, termsListActivity.class);
        startActivity(assessmentIntent);

    }
}
