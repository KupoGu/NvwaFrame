package com.nvwa.core.livedata;

import com.nvwa.core.bean.ActionInfo;
import com.nvwa.core.bean.StatusInfo;

/**
 * @author G.god
 * Create by AS 2020/5/11 17:31
 */
public final class UILiveData extends SingleLiveEvent {
    private SingleLiveEvent<Void> finishEvent;
    private SingleLiveEvent<Void> finishAllEvent;
    private SingleLiveEvent<StatusInfo> showNoticeEvent;
    private SingleLiveEvent<String> showProgressDialogEvent;
    private SingleLiveEvent<Void> dismissProgressDialogEvent;
    private SingleLiveEvent<ActionInfo> doStatusEvent;
    private SingleLiveEvent<String> callEvent;



    public SingleLiveEvent<String> getCallEvent() {
        return callEvent = createLiveData(callEvent);
    }

    public SingleLiveEvent<ActionInfo> getDoStatusEvent() {
        return doStatusEvent = createLiveData(doStatusEvent);
    }


    public SingleLiveEvent<StatusInfo> getShowNoticeEvent() {
        return showNoticeEvent = createLiveData(showNoticeEvent);
    }

    public SingleLiveEvent<String> getShowProgressDialogEvent() {
        return showProgressDialogEvent = createLiveData(showProgressDialogEvent);
    }

    public SingleLiveEvent<Void> getDismissProgressDialogEvent() {
        return dismissProgressDialogEvent = createLiveData(dismissProgressDialogEvent);
    }


    public SingleLiveEvent<Void> getFinishEvent() {
        return finishEvent = createLiveData(finishEvent);
    }

    public SingleLiveEvent<Void> getFinishAllEvent() {
        return finishAllEvent = createLiveData(finishAllEvent);
    }

    private SingleLiveEvent createLiveData(SingleLiveEvent liveData) {
        if (liveData == null) {
            liveData = new SingleLiveEvent();
        }
        return liveData;
    }


}
