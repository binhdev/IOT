package com.smarthome.iot.ui.base;

public interface IBasePresenter<T> {
    void setView(T view);

    void onStart();

    void onStop();
}
