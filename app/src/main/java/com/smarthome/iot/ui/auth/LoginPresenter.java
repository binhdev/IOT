package com.smarthome.iot.ui.auth;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.LoginRepository;
import com.smarthome.iot.data.source.remote.response.auth.LoginResponse;
import com.smarthome.iot.utils.AppPrefs;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View mView;
    private LoginRepository mLoginReposity;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;


    public LoginPresenter(Context context, LoginRepository loginReposity, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mLoginReposity = Preconditions.checkNotNull(loginReposity);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }
    @Override
    public void setView(LoginContract.View view) {
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
                .observeOn(mSchedulerProvider.ui()).subscribe(loginResponse -> handleLoginSuccess(loginResponse),
                error -> handleLoginFailed(error));
    }

    private void handleLoginFailed(Throwable e) {
    }

    private void handleLoginSuccess(LoginResponse loginResponse) {
        mView.hideLoadingIndicator();
        AppPrefs.getInstance(context).putApiAccessToken(loginResponse.getData().getAccessToken());
        AppPrefs.getInstance(context).putApiRefreshToken(loginResponse.getData().getRefreshToken());
        mView.startDashboardActivity();
    }

}
