package com.smarthome.iot.ui.base;

public interface BaseView {
    void showLoadingIndicator();

    void hideLoadingIndicator();

    void showLoginError(Throwable throwable);

    void startDashboardActivity();
}
