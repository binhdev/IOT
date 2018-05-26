package com.smarthome.iot.ui.main.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.smarthome.iot.utils.MessageUtil;

public class BaseFragment extends Fragment {
    protected MessageUtil msgUtil = null;
    protected static Dialog dialogProgress = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.msgUtil = new MessageUtil();

        dialogProgress = msgUtil.initCustomDialogProgress(getContext(), null);
    }
}
