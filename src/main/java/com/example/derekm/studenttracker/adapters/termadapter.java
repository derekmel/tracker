package com.example.derekm.studenttracker.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import com.example.derekm.studenttracker.R;
import com.example.derekm.studenttracker.models.Term;

import java.util.ArrayList;

public class termadapter extends ArrayAdapter<Term> {

    public termadapter(Context context, ArrayList<Term> terms) {
        super(context, R.layout.list_term, terms);

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_term, parent, false);

            Term oneItem = getItem(position);
            TextView term = convertView.findViewById(R.id.list_one);
            term.setText(oneItem.getName());

        }
        return convertView;
    }






}
