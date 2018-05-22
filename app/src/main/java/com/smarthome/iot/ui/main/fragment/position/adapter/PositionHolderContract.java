package com.smarthome.iot.ui.main.fragment.position.adapter;

import com.smarthome.iot.data.model.Position;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

public interface PositionHolderContract {

    interface Presenter{
        void deletePosition(Position position);
        void createPosition(Position position);
    }

    interface View{
        void updatePositionList();
        void deletePositionSuccess();
        void deletePositionFailed();
        void createPositionSuccess();
        void createPositionFailed();
    }
}
