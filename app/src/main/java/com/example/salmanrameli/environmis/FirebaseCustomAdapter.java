package com.example.salmanrameli.environmis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class FirebaseCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Reports> reports;


    FirebaseCustomAdapter(Context context, ArrayList<Reports> reports) {
        this.context = context;
        this.reports = reports;
    }
    @Override
    public int getCount() {
        return reports.size();
    }

    @Override
    public Object getItem(int position) {
        return reports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_report_list, parent, false);
        }

        TextView location_latitude_text = (TextView) convertView.findViewById(R.id.list_report_location_latitude_);
        TextView location_longitude_text = (TextView) convertView.findViewById(R.id.list_report_location_longitude_);
        TextView date_text = (TextView) convertView.findViewById(R.id.report_date);
        TextView result = (TextView) convertView.findViewById(R.id.measurement_result);

        final Reports r = (Reports) this.getItem(position);

        location_latitude_text.setText(r.getLocation_latitude());
        location_longitude_text.setText(r.getLocation_longitude());
        date_text.setText(r.getMeasurement_date());
        result.setText(r.getMeasurement_result());

        return convertView;
    }
}
