package com.smarthome.iot.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.smarthome.iot.R;
import com.smarthome.iot.ui.base.BaseActivity;
import com.smarthome.iot.ui.main.fragment.alarm.AlarmFragment;
import com.smarthome.iot.ui.main.fragment.alldevice.AllDeviceFragment;
import com.smarthome.iot.ui.main.fragment.position.PositionFragment;
import com.smarthome.iot.ui.main.fragment.group.GroupFragment;
import com.smarthome.iot.ui.main.fragment.notification.NotificationFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_dashboard:
                        selectedFragment = PositionFragment.newInstance();
                        break;

                    case R.id.action_group:
                        selectedFragment = GroupFragment.newInstance();
                        break;

                    case R.id.action_alldevice:
                        selectedFragment = AllDeviceFragment.newInstance();
                        break;

                    case R.id.action_alarm:
                        selectedFragment = AlarmFragment.newInstance();
                        break;

                    case R.id.action_notification:
                        selectedFragment = NotificationFragment.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, PositionFragment.newInstance());
        transaction.commit();
    }
}
