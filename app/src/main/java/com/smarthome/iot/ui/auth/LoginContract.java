package com.smarthome.iot.ui.auth;

import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

public interface LoginContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        void doLogin(String email, String password);
    }
}
