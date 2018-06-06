package com.smarthome.iot.ui.main.fragment.position.adapter;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.repository.PositionRepository;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class PositionHolderPresenter implements PositionHolderContract.Presenter {

    private Context context;
    private PositionHolderContract.View mView;
    private PositionRepository mPositionRepository;
    private BaseSchedulerProvider mSchedulerProvider;

    public PositionHolderPresenter(Context context, PositionRepository positionRepository, BaseSchedulerProvider schedulerProvider,
                                   PositionHolderContract.View view){
        this.context = Preconditions.checkNotNull(context);
        mPositionRepository = Preconditions.checkNotNull(positionRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
        this.mView = view;
    }

    @Override
    public void addPosition(Position position) {
        mPositionRepository.createPosition(position)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleAddPositionSuccess(),
                        error -> handleAddPositionFailed());
    }

    @Override
    public void editPosition(Position position) {
        mPositionRepository.editPosition(position)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleEditPositionSuccess(),
                        error -> handleEditPositionFailed());
    }

    @Override
    public void deletePosition(int[] ids) {
        mPositionRepository.deletePosition(ids)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleDeletePositionSuccess(),
                        error -> handleDeletePositionFailed());
    }

    private void handleAddPositionSuccess(){
        mView.createPositionSuccess();
    }

    private void handleAddPositionFailed(){
        mView.createPositionFailed();
    }

    private void handleEditPositionSuccess(){
        mView.editPositionSuccess();
    }

    private void handleEditPositionFailed(){
        mView.editPositionFailed();
    }

    private void handleDeletePositionSuccess(){
        mView.deletePositionSuccess();
    }

    private void handleDeletePositionFailed(){
        mView.deletePositionFailed();
    }
}
