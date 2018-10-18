package com.example.derekm.studenttracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.models.Note;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class noteadapter extends ArrayAdapter<Note> {

    public noteadapter(Context context, ArrayList<Note> notes) {
        super(context, R.layout.list_notes, notes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_notes, parent, false);

            Note onenItem = getItem(position);
            TextView note = convertView.findViewById(R.id.list_n_one);
            note.setText(onenItem.getNote());

        }
        return convertView;
    }
}