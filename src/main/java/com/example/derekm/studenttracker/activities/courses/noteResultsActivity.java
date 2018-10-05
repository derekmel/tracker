package com.example.derekm.studenttracker.activities.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;
import android.view.View;
import android.view.Menu;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Note;

public class noteResultsActivity extends AppCompatActivity {

    private DBOpenHelper db;
    public Course course;
    public Note note;
    private ShareActionProvider mShareActionProvider;
    private Intent mShareIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_results);
        setTitle("Note Details");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();

        course = db.getCourse(intent.getLongExtra("courseId", 1));
        note = db.getNote(intent.getLongExtra("id", 1));

        TextView notetex = findViewById(R.id.note_input);

        notetex.setText(note.getNote());

    }


    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, note.getNote().toString());

        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(mShareIntent);
        }
        return true;
    }


    public void deleteNoteButtonHandler (View view) {
        db.deleteNote(note.getId());
        Intent remove = new Intent(this, notesActivity.class);
        startActivity(remove);

    }
}
