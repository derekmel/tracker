package com.example.derekm.studenttracker.adapters;

import android.widget.ArrayAdapter;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.models.Mentor;
import java.util.ArrayList;
import android.content.Context;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;

public class mentoradapter extends ArrayAdapter<Mentor> {

    public mentoradapter(Context context, ArrayList<Mentor> mentors) {
        super(context, R.layout.list_mentor, mentors);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_mentor, parent, false);

            Mentor onemItem = getItem(position);
            TextView name = convertView.findViewById(R.id.list_m_name);
            TextView phone = convertView.findViewById(R.id.list_m_phone);
            TextView email = convertView.findViewById(R.id.list_m_email);

            name.setText(onemItem.getmentorname());
            phone.setText(onemItem.getmentorphone());
            email.setText(onemItem.getmentoremail());
        }
        return convertView;
    }
}

