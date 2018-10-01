package com.example.derekm.studenttracker.activities.terms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.activities.courses.courseResultsActivity;
import com.example.derekm.studenttracker.activities.courses.coursesActivity;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Term;
import com.example.derekm.studenttracker.models.Course;

import java.util.ArrayList;


public class termResultsActivity extends AppCompatActivity {
    private DBOpenHelper db;
    public Term term;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_results);
        setTitle("Term Details");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();
        term = db.getTerm(intent.getLongExtra("termId", 1));
        TextView name = findViewById(R.id.term_name);
        TextView start = findViewById(R.id.term_start);
        TextView end = findViewById(R.id.term_end);
        name.setText(term.getName());
        start.setText(term.getStart());
        end.setText(term.getEnd());

    }

    public void coursesButtonHandler(View view) {
        Intent coursesIntent = new Intent(this, coursesActivity.class);
        coursesIntent.putExtra("termId", term.getId());
        startActivity(coursesIntent);


    }

    public void editButtonHandler(View view) {
        Intent intent = new Intent(this, newTermActivity.class);
        intent.putExtra("name" , term.getName());
        intent.putExtra("start", term.getStart());
        intent.putExtra("end", term.getEnd());
        startActivity(intent);

    }

    public void deleteButtonHandler(View view) {

        ArrayList<Course> courses = db.getCourses(term.getId());
        if (courses.size() > 0) {

            Toast.makeText(this, "The term has courses assigned to it and cannot be deleted", Toast.LENGTH_SHORT).show();
        }
        else {
            db.deleteTerm(term.getId());
            Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
        }

        Intent remove = new Intent(this, termsListActivity.class);
        startActivity(remove);


    }


}
