package com.smarthome.iot.ui.main.fragment.position;

import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface PositionContract {
    interface View extends BaseView {
        void setPositionList(List<Position> groupList);
    }

    interface Presenter extends BasePresenter<View> {
        void positionList();
    }
}
