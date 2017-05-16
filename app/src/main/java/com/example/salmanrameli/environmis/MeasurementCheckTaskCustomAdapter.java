package com.example.salmanrameli.environmis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class MeasurementCheckTaskCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TaskToDo> taskToDos;

    MeasurementCheckTaskCustomAdapter(Context context, ArrayList<TaskToDo> taskToDos) {
        this.context = context;
        this.taskToDos = taskToDos;
    }

    @Override
    public int getCount() {
        return taskToDos.size();
    }

    @Override
    public Object getItem(int position) {
        return taskToDos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_measurement_check_task, parent, false);
        }

        TextView latitude_text = (TextView) convertView.findViewById(R.id.checkTaskLocationLatitudeTextView);
        TextView longitude_text = (TextView) convertView.findViewById(R.id.checkTaskLocationLongitudeTextView);
        Button do_task = (Button) convertView.findViewById(R.id.buttonDoThisTask);

        final TaskToDo taskToDo = (TaskToDo) this.getItem(position);

        latitude_text.setText(taskToDo.getLocation_latitude());
        longitude_text.setText(taskToDo.getLocation_longitude());

        return convertView;
    }
}
