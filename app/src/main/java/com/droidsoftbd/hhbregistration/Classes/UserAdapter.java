package com.droidsoftbd.hhbregistration.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.droidsoftbd.hhbregistration.R;

import java.util.ArrayList;

/**
 * Created by BDDL-102 on 6/1/2018.
 */

public class UserAdapter extends ArrayAdapter {

    private UserInfo userInfo;
    private Context context;
    private ArrayList<UserInfo> userList;

    public UserAdapter(Context context, ArrayList<UserInfo> userList) {
        super(context, R.layout.custom_list_view,userList);
        this.context = context;
        this.userList = userList;
    }

    static class ViewHolder {
        TextView nameTV;
        TextView bloodgroupTV;
        TextView divisionTV;
        TextView mobileTV;
        TextView lastDonateDateTV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_view,null);
            viewHolder = new ViewHolder();
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.name_lv);
            viewHolder.bloodgroupTV = (TextView) convertView.findViewById(R.id.blood_group_lv);
            viewHolder.divisionTV = (TextView) convertView.findViewById(R.id.division_lv);
            viewHolder.mobileTV = (TextView) convertView.findViewById(R.id.mobile_lv);
            viewHolder.lastDonateDateTV = (TextView) convertView.findViewById(R.id.last_donate_date_lv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (userList!= null) {

            viewHolder.nameTV.setText("Name: "+userList.get(position).getFullName());
            viewHolder.bloodgroupTV.setText("Blood Group: "+userList.get(position).getBloodGroup());
            viewHolder.divisionTV.setText("Division: "+userList.get(position).getDivision());
            viewHolder.mobileTV.setText("Mobile: "+userList.get(position).getMobileNumber());
            viewHolder.lastDonateDateTV.setText("Last Donate: "+userList.get(position).getLastBloodDonateDate());
        } else {
            Toast.makeText(context, "Empty User List!!", Toast.LENGTH_LONG).show();
        }

        return convertView;
    }
}
