package com.nvwa.uikit.apply.pudding;

import com.nvwa.core.base.BaseMvvmActivity;
import com.nvwa.uikit.apply.R;
import com.nvwa.uikit.apply.databinding.ActivityPuddingBinding;

public class PuddingActivity extends BaseMvvmActivity<ActivityPuddingBinding, PuddingViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pudding;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    protected PuddingViewModel initViewModel() {
        return VMProviders(PuddingViewModel.class);
    }
}