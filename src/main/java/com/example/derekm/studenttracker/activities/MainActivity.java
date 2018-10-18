package com.example.derekm.studenttracker.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.activities.terms.termsListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void termsButtonClickHandler(View view) {
        Intent termsIntent = new Intent(this, termsListActivity.class);
        startActivity(termsIntent);
    }


}
