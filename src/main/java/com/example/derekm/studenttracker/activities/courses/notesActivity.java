package com.example.derekm.studenttracker.activities.courses;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;


import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.adapters.noteadapter;
import com.example.derekm.studenttracker.models.Note;
import java.util.ArrayList;
import android.widget.ListAdapter;
import android.widget.ListView;

public class notesActivity extends AppCompatActivity {
    private DBOpenHelper db;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        setTitle("Notes");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("courseId", 1);
        course = db.getCourse(id);
        setNotesView();


    }

    private void setNotesView() {
    final ArrayList<Note> NoteList = db.getNotes(course.getId());
    ListAdapter Adapter = new noteadapter(this, NoteList);
    ListView list = findViewById(R.id.noteslist);
    list.setAdapter(Adapter);
    //list.setOnItemClickListener();
    }


}
