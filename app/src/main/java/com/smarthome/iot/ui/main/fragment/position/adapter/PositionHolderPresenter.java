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
    public void deletePosition(Position position) {
        mPositionRepository.deletePosition(position)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleDeletePositionSuccess(),
                        error -> handleDeletePositionFailed());
    }

    @Override
    public void createPosition(Position position) {
        mPositionRepository.createPosition(position)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((s) -> handleCreatePositionSuccess(),
                        error -> handleCreatePositionFailed());
    }

    private void handleCreatePositionSuccess(){
        mView.createPositionSuccess();
    }

    private void handleCreatePositionFailed(){
        mView.createPositionFailed();
    }

    private void handleDeletePositionSuccess(){
        mView.deletePositionSuccess();
    }

    private void handleDeletePositionFailed(){
        mView.deletePositionFailed();
    }
}
