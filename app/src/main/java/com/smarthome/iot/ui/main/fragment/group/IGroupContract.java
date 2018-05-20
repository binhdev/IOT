package com.smarthome.iot.ui.main.fragment.group;

import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.ui.base.IBasePresenter;
import com.smarthome.iot.ui.base.IBaseView;

import java.util.List;

public interface IGroupContract {
    interface IView extends IBaseView {
        void setGroupListView(List<Group> groupList);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void groupList();
    }
}
