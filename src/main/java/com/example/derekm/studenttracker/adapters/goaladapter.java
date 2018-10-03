package com.example.derekm.studenttracker.adapters;

import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.derekm.studenttracker.models.Goal;
import com.example.derekm.studenttracker.R;

import java.util.ArrayList;

public class goaladapter extends ArrayAdapter<Goal> {

    public goaladapter(Context context, ArrayList<Goal> goalDates) {
        super(context, R.layout.list_goal, goalDates);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_goal, parent, false);

            Goal onegitem = getItem(position);
            TextView description = convertView.findViewById(R.id.list_g_description);
            TextView due = convertView.findViewById(R.id.list_g_date);


            description.setText(onegitem.getDescription());
            due.setText(onegitem.getDate());


        }
        return convertView;
    }

}
