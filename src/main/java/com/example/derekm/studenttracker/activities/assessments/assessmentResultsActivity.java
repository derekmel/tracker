package com.example.derekm.studenttracker.activities.assessments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.view.View;
import android.widget.Toast;

import com.example.derekm.studenttracker.Receiver;
import com.example.derekm.studenttracker.adapters.goaladapter;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Goal;
import com.example.derekm.studenttracker.models.Assessment;
import com.example.derekm.studenttracker.R;

import static android.widget.Toast.LENGTH_SHORT;

public class assessmentResultsActivity extends AppCompatActivity {
    private DBOpenHelper db;
    private Course course;
    private ArrayList<Goal> goal;
    private Assessment assessment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_results);
        setTitle("Assessment Details");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();

        course = db.getCourse(intent.getLongExtra("courseId", 1));
        assessment = db.getAssessment(intent.getLongExtra("assessmentId", 1));
        goal = db.getGoalDates(intent.getLongExtra("date", assessment.getAssessmentId()));


        TextView name = findViewById(R.id.assessment_name);
        TextView type = findViewById(R.id.assessment_type);
        TextView date = findViewById(R.id.assessment_due);

        name.setText(assessment.getName());
        type.setText(assessment.getType());
        date.setText(assessment.getDue());


        final ArrayList<Goal> goalList = db.getGoalDates(assessment.getAssessmentId());
        ListAdapter Adapter = new goaladapter(this, goalList);
        ListView list = findViewById(R.id.list_view_goals);
        list.setAdapter(Adapter);



    }

    public void editButtonHandler (View view) {
        //todo add edit functionality for assessments
        Intent intent1 = new Intent(this, newAssessmentActivity.class);
        intent1.putExtra("courseId", assessment.getCourseId());
        intent1.putExtra("assessmentId=", assessment.getAssessmentId());
        intent1.putExtra("name", assessment.getName());
        intent1.putExtra("date", assessment.getDue());
        intent1.putExtra("type", assessment.getType());
        startActivity(intent1);

    }

    public void deleteButtonHandler (View view) {
        db.deleteAssessment(assessment.getAssessmentId());
        Intent remove = new Intent(this, assesmentsActivity.class);
        startActivity(remove);

    }

    public void alertButtonHandler (View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final String anything = goal.get(0).date;

        final String anything2 = assessment.getName();

        alert.setMessage("Do you want an alert on your goal date?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alarmHandler(anything, anything2);
                Toast.makeText(getApplicationContext(), "Alert set", LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Alert not set", LENGTH_SHORT).show();
            }
        });
        alert.create().show();


    }

    public long convertStringtoMilli (String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = date;
        Date date2 = sdf.parse(dateInString);
        long milli = date2.getTime();
        System.out.println(milli);
        Calendar calendar = dateToCalendar(date2);
        long now = System.currentTimeMillis();
        System.out.println(now);
        long diff = milli-now;
        System.out.println(diff);
        if(diff <= 10000) {
            diff = 3000;
        }
        long delay = System.currentTimeMillis() + diff;
        return delay;
    }
    //Convert Date to Calendar
    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public void alarmHandler (String variable, String variable2) {
        AlarmManager alarms = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

        Receiver receiver = new Receiver();
        IntentFilter filter = new IntentFilter("ALARM_ACTION");
        registerReceiver(receiver, filter);


        Intent intent2 = new Intent("ALARM_ACTION");
        intent2.putExtra("test text", "Assessment " + variable2 + " is due today");
        PendingIntent operation = PendingIntent.getBroadcast(getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);


        try {
            alarms.set(AlarmManager.RTC_WAKEUP, convertStringtoMilli(variable), operation) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
