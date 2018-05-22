package com.smarthome.iot.ui.main.fragment.group;

import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface GroupContract {
    interface View extends BaseView {
        void setGroupListView(List<Group> groupList);
    }

    interface Presenter extends BasePresenter<View> {
        void groupList();
    }
}
