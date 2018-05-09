package com.smarthome.iot.ui.auth;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.LoginRepository;
import com.smarthome.iot.data.source.remote.response.LoginResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class LoginPresenter implements LoginContract.IPresenter{
    private LoginContract.IView mView;
    private LoginRepository mLoginReposity;
    private BaseSchedulerProvider mSchedulerProvider;


    public LoginPresenter(LoginRepository loginReposity, BaseSchedulerProvider schedulerProvider){
        mLoginReposity = Preconditions.checkNotNull(loginReposity);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }
    @Override
    public void setView(LoginContract.IView view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void doLogin(String email, String password) {
        mView.showLoadingIndicator();
        mLoginReposity.doLogin(email, password)
                .subscribeOn(mSchedulerProvider.io())
                .subscribeOn(mSchedulerProvider.ui()).subscribe(loginResponse -> handleLoginSuccess(loginResponse),
                error -> handleLoginFailed(error));
    }

    private void handleLoginFailed(Throwable e) {
        mView.hideLoadingIndicator();
    }

    private void handleLoginSuccess(LoginResponse loginResponse) {
        mView.hideLoadingIndicator();
    }

}
