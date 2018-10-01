package com.example.derekm.studenttracker.adapters;

import android.content.ContentProvider;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.view.View;
import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.models.Course;
import com.example.derekm.studenttracker.models.Term;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class courseadapter extends ArrayAdapter<Course> {

    public courseadapter(Context context, ArrayList<Course> courses) {
        super(context, R.layout.list_courses, courses);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_courses, parent, false);

            Course onecItem = getItem(position);
            TextView term = convertView.findViewById(R.id.list_c_one);
            term.setText(onecItem.getName());

        }
        return convertView;

    }

}
