package com.smarthome.iot.ui.main.fragment.dashboard;

import com.smarthome.iot.ui.base.IBasePresenter;
import com.smarthome.iot.ui.base.IBaseView;

public interface IDashboardContract {
    interface IView extends IBaseView{
    }

    interface IPresenter extends IBasePresenter<IView>{

    }
}
