package com.smarthome.iot.ui.base;

public interface IBaseView {
    void showLoadingIndicator();

    void hideLoadingIndicator();

    void showLoginError(Throwable throwable);

    void startDashboardActivity();
}
