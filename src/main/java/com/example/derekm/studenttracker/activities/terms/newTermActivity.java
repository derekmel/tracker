package com.example.derekm.studenttracker.activities.terms;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Term;


public class newTermActivity extends AppCompatActivity {

    private DBOpenHelper db;
    private EditText nameInput;
    private EditText startInput;
    private EditText endInput;
    private Intent intent;
    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtermadd);

        db = new DBOpenHelper(this);
        nameInput = findViewById(R.id.name_input);
        startInput = findViewById(R.id.start_input);
        endInput = findViewById(R.id.end_input);

        Intent intent = getIntent();
        String name, start, end;
        if (intent.hasExtra("name")) {
            name = intent.getStringExtra("name");
            nameInput.setText(name);
        }
        if (intent.hasExtra("start")) {
            start = intent.getStringExtra("start");
            startInput.setText(start);
        }
        if (intent.hasExtra("end")) {
            end = intent.getStringExtra("end");
            endInput.setText(end);

        }

    }

    public void saveTermButtonHandler (View view) {
        String name = nameInput.getText().toString();
        String start = startInput.getText().toString();
        String end = endInput.getText().toString();
        long l = db.createTerm(name, start, end);

        Intent back = new Intent(this, termsListActivity.class);
        startActivity(back);

    }

    public void cancelAddTermButtonHandler (View view) {
        Intent termsIntent = new Intent(this, termsListActivity.class);
        startActivity(termsIntent);

    }

}
