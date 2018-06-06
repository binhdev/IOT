package com.smarthome.iot.ui.main.fragment.group.adapter;

import com.smarthome.iot.data.model.Group;

public interface GroupAdapterContract {
    interface Presenter{
        void deleteGroup(int id);
        void editGroup(Group group);
    }

    interface View{
        void deleteGroupSuccess();
        void deleteGroupFailed();
        void editGroupSuccess();
        void editGroupFailed();
    }
}
