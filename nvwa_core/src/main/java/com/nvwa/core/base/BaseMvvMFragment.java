package com.nvwa.core.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nvwa.core.bean.ActionInfo;
import com.nvwa.core.bean.BusInfo;

/**
 * @author G.god
 * Create by AS 2020/5/15 09:34
 */
public abstract class BaseMvvMFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<DB> {
    protected VM viewModel;
    private int viewModelId;

    @Override
    protected void layoutInit(Bundle savedInstanceSate) {
        viewModelId = initVariableId();
        viewModel = initViewModel();
        registEvent();
        if (null != viewModel) {
            subscribe(viewModel);
        }
        viewBinding.setVariable(viewModelId, viewModel);
        getLifecycle().addObserver(viewModel);
        viewModel.registerBus();
    }

    private void registEvent() {
        viewModel.getUiLiveData().getFinishEvent().observe(this, v -> getActivity().finish());
        viewModel.getUiLiveData().getShowNoticeEvent().observe(this, statusInfo -> showMessage(statusInfo));
        viewModel.getUiLiveData().getShowProgressDialogEvent().observe(this, s -> showProgress(s));
        viewModel.getUiLiveData().getDoStatusEvent().observe(this, stringObjectMap -> doStatusEvent(stringObjectMap));
        viewModel.getUiLiveData().getDismissProgressDialogEvent().observe(this, aVoid -> dismissProgress());
        viewModel.getUiLiveData().getFinishEvent().observe(this, aVoid -> getActivity().finish());
    }

    public abstract int initVariableId();

    protected abstract VM initViewModel();

    protected void doStatusEvent(ActionInfo params) {
    }

    public <VM extends AndroidViewModel> VM VMProviders(@NonNull Class<VM> modelClass) {
        return (VM) new ViewModelProvider(this).get(modelClass);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (viewModel != null) {
            viewModel.removeBus();
        }
    }

    public void handBusEvent(BusInfo busInfo) {

    }

    //刷新布局
    public void refreshLayout() {
        if (viewModel != null) {
            viewBinding.setVariable(viewModelId, viewModel);
        }
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected void subscribe(VM model) {

    }
}
