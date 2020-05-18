package com.nvwa.core.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nvwa.core.bean.ActionInfo;

/**
 * @author G.god
 * Create by AS 2020/5/15 10:53
 */
public abstract class BaseMvvmActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity<DB> {
    protected VM viewModel;
    private int viewModelId;


    @Override
    protected void init(Bundle savedInstanceState) {
        viewModelId = initVariableId();
        viewModel = initViewModel();
        registEvent();
        getLifecycle().addObserver(viewModel);
        if (null != viewModel) {
            subscribe(viewModel);
        }
        viewBinding.setVariable(viewModelId, viewModel);
        viewModel.registerBus();
    }

    /**
     * 绑定model
     *
     * @return
     */
    public abstract int initVariableId();

    /**
     * init model
     *
     * @return
     */
    protected abstract VM initViewModel();

    public <VM extends AndroidViewModel> VM VMProviders(@NonNull Class modelClass) {
        return (VM) new ViewModelProvider(this).get(modelClass);
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected void subscribe(VM model) {
    }

    private void registEvent() {
        viewModel.getUiLiveData().getFinishEvent().observe(this, v -> finish());
        viewModel.getUiLiveData().getShowNoticeEvent().observe(this, this::showMessage);
        viewModel.getUiLiveData().getShowProgressDialogEvent().observe(this, this::showProgress);
        viewModel.getUiLiveData().getDismissProgressDialogEvent().observe(this, aVoid -> dismissProgress());
        viewModel.getUiLiveData().getDoStatusEvent().observe(this, this::doStatusEvent);

    }


    protected void doStatusEvent(ActionInfo params) {

    }

    //刷新布局
    public void refreshLayout() {
        if (viewModel != null) {
            viewBinding.setVariable(viewModelId, viewModel);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.removeBus();
        }
    }
}
