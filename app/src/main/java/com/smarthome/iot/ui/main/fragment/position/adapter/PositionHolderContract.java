package com.smarthome.iot.ui.main.fragment.position.adapter;

import com.smarthome.iot.data.model.Position;

public interface PositionHolderContract {

    interface Presenter{
        void deletePosition(int[] ids);
        void createPosition(Position position);
        void editPosition(Position position);
    }

    interface View{
        void editPositionSuccess();
        void editPositionFailed();
        void deletePositionSuccess();
        void deletePositionFailed();
        void createPositionSuccess();
        void createPositionFailed();
    }
}
