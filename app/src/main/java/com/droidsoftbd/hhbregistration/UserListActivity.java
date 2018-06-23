package com.droidsoftbd.hhbregistration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.droidsoftbd.hhbregistration.Classes.DBManager;
import com.droidsoftbd.hhbregistration.Classes.UserAdapter;
import com.droidsoftbd.hhbregistration.Classes.UserInfo;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private ListView userListView;
    private ArrayList<UserInfo> allUserList;
    private UserAdapter adapter;
    private DBManager manager;

    public static final String DATA_SAVED_BROADCAST = "com.droidsoftbd.datasaved";
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        userListView = findViewById(R.id.userListView);
        manager = new DBManager(UserListActivity.this);

        //Show all stored list
        showAllUserList();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                showAllUserList();
            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));

    }

    private void showAllUserList() {
        //allUserList.clear();
        allUserList = manager.getAllUserInfos();
        adapter = new UserAdapter(this,allUserList);
        userListView.setAdapter(adapter);
    }
}
