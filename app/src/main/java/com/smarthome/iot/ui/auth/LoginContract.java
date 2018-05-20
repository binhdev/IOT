package com.smarthome.iot.ui.auth;

import com.smarthome.iot.ui.base.IBasePresenter;
import com.smarthome.iot.ui.base.IBaseView;

public interface LoginContract {

    interface IView extends IBaseView{

    }

    interface IPresenter extends IBasePresenter<IView> {
        void doLogin(String email, String password);
    }
}
