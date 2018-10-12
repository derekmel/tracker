package com.example.derekm.studenttracker.activities.courses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.io.Serializable;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.Receiver;
import com.example.derekm.studenttracker.adapters.mentoradapter;
import com.example.derekm.studenttracker.database.DBOpenHelper;
import com.example.derekm.studenttracker.models.Term;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Mentor;
import com.example.derekm.studenttracker.activities.assessments.assesmentsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class courseResultsActivity extends AppCompatActivity {
    private DBOpenHelper db;
    public Term term;
    public Course course;
    public Mentor mentor;
    private ArrayList<Mentor> mentors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_results);
        setTitle("Course Details");
        db = new DBOpenHelper(this);
        Intent intent = getIntent();

        term = db.getTerm(intent.getLongExtra("termId", 1));
        course = db.getCourse(intent.getLongExtra("courseId", 1));
        mentors = db.getMentors(intent.getLongExtra("name", 1));

        TextView name = findViewById(R.id.course_name);
        TextView start = findViewById(R.id.course_start);
        TextView end = findViewById(R.id.course_end);
        TextView status = findViewById(R.id.course_status);
        name.setText(course.getName());
        start.setText(course.getStart());
        end.setText(course.getEnd());
        status. setText(course.getStatus());


        ArrayList<Mentor> mentorList = db.getMentors(course.getId());
        ListAdapter Adapter = new mentoradapter(this, mentorList);
        ListView list = findViewById(R.id.list_view_mentors);
        list.setAdapter(Adapter);


    }

    public void addMentorButtonHandler(View view) {
        //todo add mentor button


    }

    public void removeMentorButtonHandler(View view) {
        //todo remove mentor button
        //ArrayList<Mentor> mentors = db.getMentors(course.getId());
        //mentor.getmentorname();
        //mentor.getId();
        //mentors.remove(mentor.getId());
        //course.getId();
        //mentor.getId();
        //db.deleteMentor(mentor.getId());
        //recreate();

    }


    public void deleteButtonHandler(View view) {
        db.deleteCourse(course.getId());
        Intent remove = new Intent(this, coursesActivity.class);
        startActivity(remove);

    }

    public void assessmentsButtonHandler(View view) {
        Intent assessintent = new Intent(this, assesmentsActivity.class);
        assessintent.putExtra("courseId", course.getId());
        startActivity(assessintent);

    }

    public void notesButtonHandler(View view) {
        Intent notesintent = new Intent(this, notesActivity.class);
        notesintent.putExtra("courseId", course.getId());
        startActivity(notesintent);

    }

    public void editButtonHandler(View view) {
        //todo edit functionality on courses
        Intent intent1 = new Intent(this, newCourseActivity.class);
        intent1.putExtra("id", course.getId());
        intent1.putExtra("name" , course.getName());
        intent1.putExtra("start", course.getStart());
        intent1.putExtra("end", course.getEnd());
        intent1.putExtra("status", course.getStatus());
        //how to pass the mentor arraylist to newcourse activity?????
        //intent1.putExtra("name", mentor.getmentorname());
        //intent1.putExtra("phone", mentor.getmentorphone());
        //intent1.putExtra("email", mentor.getmentoremail());
        startActivity(intent1);

    }

    public void startAlertButtonHandler(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final String anything = course.getStart();
        System.out.println(anything);

        final String anything2 = course.getName();

        alert.setMessage("Do you want an alert on your start date?");
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
        intent2.putExtra("test text", "Course " + variable2 + " is starting today");
        PendingIntent operation = PendingIntent.getBroadcast(getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);


        try {
            alarms.set(AlarmManager.RTC_WAKEUP, convertStringtoMilli(variable), operation) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }






    public void endAlertButtonHandler(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final String anything3 = course.getEnd();
        System.out.println(anything3);

        final String anything4 = course.getName();

        alert.setMessage("Do you want an alert on your end date?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alarmHandler2(anything3, anything4);
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

    public long convertStringtoMilli2 (String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = date;
        Date date2 = sdf.parse(dateInString);
        long milli = date2.getTime();
        System.out.println(milli);
        Calendar calendar = dateToCalendar2(date2);
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
    private Calendar dateToCalendar2(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public void alarmHandler2 (String variable3, String variable4) {
        AlarmManager alarms = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

        Receiver receiver = new Receiver();
        IntentFilter filter = new IntentFilter("ALARM_ACTION");
        registerReceiver(receiver, filter);


        Intent intent2 = new Intent("ALARM_ACTION");
        intent2.putExtra("test text", "Course " + variable4 + " is ending today");
        PendingIntent operation = PendingIntent.getBroadcast(getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);


        try {
            alarms.set(AlarmManager.RTC_WAKEUP, convertStringtoMilli2(variable3), operation) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
