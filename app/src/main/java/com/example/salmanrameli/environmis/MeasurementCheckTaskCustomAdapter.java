package com.example.salmanrameli.environmis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class MeasurementCheckTaskCustomAdapter extends BaseAdapter {
    private GPSTracker gps;

    private Context context;
    private ArrayList<TaskToDo> taskToDos;

    private double distance;
    private double current_latitude_val;
    private double current_longitude_val;
    private double supposed_latitude_val;
    private double supposed_longitude_val;

    MeasurementCheckTaskCustomAdapter(Context context, ArrayList<TaskToDo> taskToDos) {
        this.context = context;
        this.taskToDos = taskToDos;

        gps = new GPSTracker(context);

        if(gps.canGetLocation()) {
            current_latitude_val = gps.getLatitude();
            current_longitude_val = gps.getLongitude();
        }
        else {
            Toast.makeText(context, "Can't get location", Toast.LENGTH_SHORT).show();
        }
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
        TextView location_text = (TextView) convertView.findViewById(R.id.checkTaskLocationName);
        TextView todo_key = (TextView) convertView.findViewById(R.id.todoKey);
        Button do_task = (Button) convertView.findViewById(R.id.buttonDoThisTask);

        final TaskToDo taskToDo = (TaskToDo) this.getItem(position);

        latitude_text.setText(taskToDo.getLocation_latitude());
        longitude_text.setText(taskToDo.getLocation_longitude());
        location_text.setText(taskToDo.getLocation_name());
        todo_key.setText(taskToDo.getTodo_key());

        supposed_latitude_val = Double.parseDouble(taskToDo.getLocation_latitude());
        supposed_longitude_val = Double.parseDouble(taskToDo.getLocation_longitude());

        distance = getDistance(supposed_latitude_val, supposed_longitude_val, current_latitude_val, current_longitude_val);

        if(distance > 0.5) {
            do_task.setEnabled(false);
        }
        else {
            do_task.setEnabled(true);

            do_task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CreateReport.class);

                    intent.putExtra("todo_key", taskToDo.getTodo_key());

                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }

    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;

        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
