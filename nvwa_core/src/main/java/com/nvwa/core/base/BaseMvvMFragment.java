package com.nvwa.core.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.nvwa.core.bean.ActionInfo;
import com.nvwa.core.bean.BusInfo;

/**
 * @author G.god
 * Create by AS 2020/5/15 09:34
 */
public abstract class BaseMvvMFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<DB> {
    private VM viewModel;
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

    abstract int initVariableId();

    abstract VM initViewModel();

    private void doStatusEvent(ActionInfo params) {
    }

    public <VM extends AndroidViewModel> VM VMProviders(Fragment fragment, @NonNull Class modelClass) {
        return (VM) ViewModelProviders.of(fragment).get(modelClass);
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
