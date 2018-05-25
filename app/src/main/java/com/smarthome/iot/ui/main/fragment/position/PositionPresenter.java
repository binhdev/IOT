package com.smarthome.iot.ui.main.fragment.position;

import android.content.Context;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.smarthome.iot.data.repository.PositionRepository;
import com.smarthome.iot.data.source.remote.response.position.ListPositionResponse;
import com.smarthome.iot.utils.rx.BaseSchedulerProvider;

public class PositionPresenter implements PositionContract.Presenter {

    private PositionContract.View mView;
    private PositionRepository mPositionRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private Context context;

    public PositionPresenter(Context context, PositionRepository positionRepository, BaseSchedulerProvider schedulerProvider){
        this.context = Preconditions.checkNotNull(context);
        mPositionRepository = Preconditions.checkNotNull(positionRepository);
        mSchedulerProvider = Preconditions.checkNotNull(schedulerProvider);
    }
    @Override
    public void setView(PositionContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void positionList() {
        mPositionRepository.positionList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(positionListResponse -> handlePositionListSuccess(positionListResponse),
                        error -> handlePositionListFailed(error));
    }

    private void handlePositionListSuccess(ListPositionResponse positionListResponse){
        mView.setPositionList(positionListResponse.getPositionList());
    }

    private void handlePositionListFailed(Throwable err){
        Log.i("Error", err.getMessage());
    }
}
