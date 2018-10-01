package com.example.derekm.studenttracker.adapters;

import com.example.derekm.studenttracker.models.Mentor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import com.example.derekm.studenttracker.R;

public class newmentoradapter extends ArrayAdapter<Mentor> {

    private ArrayList<Mentor> mentors;
    private Context context;

    public newmentoradapter(Context context, ArrayList<Mentor> mentors) {
        super(context, R.layout.list_add_new_mentor, mentors);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_add_new_mentor, parent, false);
        }

        Mentor onemItem = getItem(position);
        TextView name = convertView.findViewById(R.id.list_m_name);
        TextView phone = convertView.findViewById(R.id.list_m_phone);
        TextView email = convertView.findViewById(R.id.list_m_email);
        //Button editbutton = convertView.findViewById(R.id.edit);
        Button removebutton = convertView.findViewById(R.id.remove);

        name.setText(onemItem.getmentorname());
        phone.setText(onemItem.getmentorphone());
        email.setText(onemItem.getmentoremail());
        //editbutton.setOnClickListener(editButtonHandler(position));
        removebutton.setOnClickListener(removeButtonHandler(position));

        return convertView;
    }



    ///not needed????
    private View.OnClickListener editButtonHandler(final int i) {
        return null;


    }

    private View.OnClickListener removeButtonHandler(final int i) {
        return null;

    }
}
