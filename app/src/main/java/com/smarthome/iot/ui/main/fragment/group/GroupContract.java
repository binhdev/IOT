package com.smarthome.iot.ui.main.fragment.group;

import com.smarthome.iot.data.model.Group;
import com.smarthome.iot.ui.base.BasePresenter;
import com.smarthome.iot.ui.base.BaseView;

import java.util.List;

public interface GroupContract {
    interface View extends BaseView {
        void setGroupListView(List<Group> groupList);
        void editGroupSuccess();
        void editGroupFailed();
        void deleteGroupSuccess();
        void deleteGroupFailed();
        void createGroupSuccess();
        void createGroupFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void allGroup();
        void deleteGroup(int id);
        void addGroup(Group group);
        void editGroup(Group group);
    }
}
