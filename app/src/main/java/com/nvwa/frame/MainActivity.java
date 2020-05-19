package com.nvwa.frame;

import com.nvwa.core.base.BaseMvvmActivity;
import com.nvwa.frame.databinding.ActivityMainBinding;

public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.mainViewModel;
    }

    @Override
    protected MainViewModel initViewModel() {
        return VMProviders(this, MainViewModel.class);
    }
}