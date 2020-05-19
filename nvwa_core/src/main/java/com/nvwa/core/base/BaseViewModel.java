package com.nvwa.core.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.nvwa.core.bean.ActionInfo;
import com.nvwa.core.bean.BusInfo;
import com.nvwa.core.bean.StatusInfo;
import com.nvwa.core.livedata.UILiveData;

/**
 * @author G.god
 * Create by AS 2020/5/11 17:10
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel {


    protected M model;
    protected UILiveData uiLiveData;
    private static final String MODEL_KEY = "nvwa_model_key";


    public BaseViewModel(@NonNull Application application) {
        this(application, null);
    }

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        this.model = model;
    }

    public UILiveData getUiLiveData() {
        if (uiLiveData == null) {
            uiLiveData = new UILiveData();
        }
        return uiLiveData;
    }

    public void showProgress(String msg) {
        uiLiveData.getShowProgressDialogEvent().postValue(msg);
    }

    public void dismissProgress() {
        uiLiveData.getShowProgressDialogEvent().call();
    }

    public void showNotice(StatusInfo statusInfo) {
        uiLiveData.getShowNoticeEvent().postValue(statusInfo);
    }

    public void actionEvent(ActionInfo actionInfo) {
        uiLiveData.getDoStatusEvent().postValue(actionInfo);
    }

    public void finishActivity() {
        uiLiveData.getFinishEvent().call();
    }

    public void finishAllActivity() {
        uiLiveData.getFinishAllEvent().call();
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void registerBus() {
        LiveEventBus
                .get(MODEL_KEY, BusInfo.class)
                .observeForever(observer);
    }

    @Override
    public void removeBus() {
        LiveEventBus
                .get(MODEL_KEY, BusInfo.class)
                .removeObserver(observer);
    }

    private Observer<BusInfo> observer = new Observer<BusInfo>() {
        @Override
        public void onChanged(@Nullable BusInfo info) {
            handBusEvent(info);
        }
    };

    public void handBusEvent(BusInfo busInfo) {

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (model != null) {
            model.clear();
        }
    }

}
