package com.example.derekm.studenttracker.adapters;

import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.derekm.studenttracker.models.Assessment;
import com.example.derekm.studenttracker.R;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class assessmentadapter extends ArrayAdapter<Assessment> {

    public assessmentadapter(Context context, ArrayList<Assessment> assessments) {
        super(context, R.layout.list_assessments, assessments);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_assessments, parent, false);

            Assessment oneaItem = getItem(position);
            TextView assessment = convertView.findViewById(R.id.list_a_one);
            assessment.setText(oneaItem.getName());

        }
        return convertView;
    }

}
