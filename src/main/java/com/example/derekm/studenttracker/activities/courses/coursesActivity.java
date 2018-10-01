package com.example.derekm.studenttracker.activities.courses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.adapters.courseadapter;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Term;
import com.example.derekm.studenttracker.models.Course;

import java.util.ArrayList;

public class coursesActivity extends AppCompatActivity {

    private DBOpenHelper db;
    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        setTitle("Courses");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();

        long id = intent.getLongExtra("termId", 1);
        term = db.getTerm(id);
        setCoursesView();
    }

    private void setCoursesView() {
        final ArrayList<Course> CourseList = db.getCourses(term.getId());
        ListAdapter Adapter = new courseadapter(this, CourseList);
        ListView list = findViewById(R.id.courseslist);
        list.setAdapter(Adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Course course = (Course) adapterView.getAdapter().getItem(i);

                        Intent intent = new Intent (coursesActivity.this, courseResultsActivity.class);
                        intent.putExtra("courseId", course.getId());
                        intent.putExtra("termId", term.getId());
                        startActivity(intent);
                    }
                }
        );
    }


    public void newCourseAddButtonHandler(View view) {
        Intent newTermIntent = new Intent(this, newCourseActivity.class);
        newTermIntent.putExtra("termId", term.getId());
        startActivity(newTermIntent);
    }
}
