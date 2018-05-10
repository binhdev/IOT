package com.smarthome.iot.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smarthome.iot.utils.MessageUtil;

public abstract class BaseActivity extends AppCompatActivity {
    protected MessageUtil msgUtil = null;
    protected static Dialog dialogProgress = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.msgUtil = new MessageUtil();
    }
}
