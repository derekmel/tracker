package com.example.derekm.studenttracker.activities.terms;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.adapters.termadapter;
import com.example.derekm.studenttracker.models.Term;
import java.util.ArrayList;


public class termsListActivity extends AppCompatActivity {
    private DBOpenHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        setTitle("Terms");
        db = new DBOpenHelper(this);
        setTermsView();

    }

    private void setTermsView() {
        final ArrayList<Term> TermList = db.getTerms();
        ListAdapter Adapter = new termadapter(this, TermList);
        ListView list = findViewById(R.id.termslist);
        list.setAdapter(Adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Term term = (Term) adapterView.getAdapter().getItem(i);

                        Intent intent = new Intent (termsListActivity.this, termResultsActivity.class);
                        intent.putExtra("termId", term.getId());
                        startActivity(intent);
                    }
                }
        );
    }

    public void newTermAddButtonHandler(View view) {
        Intent newTermIntent = new Intent(this, newTermActivity.class);
        startActivity(newTermIntent);

    }

}
