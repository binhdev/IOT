package com.smarthome.iot.ui.auth;

import com.smarthome.iot.ui.base.BasePresenter;

public interface LoginContract {

    interface IView{

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showLoginError(Throwable throwable);

        void startDashboardActivity();

    }

    interface IPresenter extends BasePresenter<LoginContract.IView>{

        void doLogin(String email, String password);

    }
}
