package com.example.derekm.studenttracker.activities.assessments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;

import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Goal;
import com.example.derekm.studenttracker.R;

import java.util.ArrayList;

public class newAssessmentActivity extends AppCompatActivity {

    private DBOpenHelper db;
    private ArrayList<Goal> goalList;
    private Course course;
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
        course = db.getCourse(intent.getLongExtra("courseId", 1));

        nameInput = findViewById(R.id.assessment_name);
        typeInput = findViewById(R.id.assessment_type);
        dueInput = findViewById(R.id.due_input);
        descriptionInput = findViewById(R.id.description_input);
        goaldateInput = findViewById(R.id.goaldate_input);


        //logic to edit assessment info
    }

    public void saveButtonHandler (View view) {
        String name = nameInput.getText().toString();
        String type = typeInput.getText().toString();
        String date = dueInput.getText().toString();
        long courseId = course.getId();

        goalList = new ArrayList<>();
        goalList.add(new Goal(
                descriptionInput.getText().toString(),
                goaldateInput.getText().toString()
        ));

        long l = db.createAssessment(name, type, date, courseId, goalList);

        Intent back = new Intent(this, assesmentsActivity.class);
        startActivity(back);




    }

    public void cancelButtonHandler (View view) {
        Intent assessmentIntent = new Intent (this, assesmentsActivity.class);
        startActivity(assessmentIntent);

    }
}
