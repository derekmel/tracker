package com.example.derekm.studenttracker.activities.courses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;


import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.adapters.noteadapter;
import com.example.derekm.studenttracker.models.Note;
import java.util.ArrayList;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class notesActivity extends AppCompatActivity {
    private DBOpenHelper db;
    private Course course;
    private Note note;
    private final Context context = this;

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
    list.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Note note = (Note) adapterView.getAdapter().getItem(i);

                    Intent intent = new Intent (notesActivity.this, noteResultsActivity.class);
                    intent.putExtra("courseId", course.getId());
                    startActivity(intent);

                }
            }
    );
    }


    public void newNoteAddButtonHandler (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Note");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.insertNote(course.getId(), input.getText().toString());
                recreate();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }






}
