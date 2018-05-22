package com.smarthome.iot.ui.base;

public interface BasePresenter<T> {
    void setView(T view);

    void onStart();

    void onStop();
}
