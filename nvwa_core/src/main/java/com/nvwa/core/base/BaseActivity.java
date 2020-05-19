package com.nvwa.core.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.gyf.immersionbar.ImmersionBar;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.nvwa.core.R;
import com.nvwa.core.bean.BusInfo;
import com.nvwa.core.bean.StatusInfo;
import com.nvwa.uikit.apply.pudding.Notice;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;

/**
 * @author G.god
 * Create by AS 2020/5/15 10:19
 */
public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {
    protected DB viewBinding;
    protected RxPermissions rxPermissions;
    protected Toolbar mToolBar;
    private static final String ACTIVITY_KEY = "nvwa_activity_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            viewBinding = DataBindingUtil.setContentView(this, getLayoutId());
            viewBinding.setLifecycleOwner(this);
        }
        reloadToolbar();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        init(savedInstanceState);
    }

    protected void initImmersionBar() {
        if (null != mToolBar) {
            setSupportActionBar(mToolBar);
            ImmersionBar.with(this).titleBar(mToolBar).init();
        }
    }

    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void showBackButton(boolean show) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(show);
    }

    protected RxPermissions getRxPermissions() {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        return rxPermissions;
    }

    public void showMessage(StatusInfo statusInfo) {
        int type = statusInfo.type;
        String msg = TextUtils.isEmpty(statusInfo.message) ? getResources().getString(statusInfo.strMsg) : statusInfo.message;
        try {
            if (type == 0) {
                Notice.getInstance().showSuccess(msg, this);
            } else if (type == 1) {
                Notice.getInstance().showWarning(msg, this);
            } else {
                Notice.getInstance().showError(msg, this);
            }
        } catch (Exception e) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void showProgress(String word) {
    }

    protected void dismissProgress() {
    }

    private void reloadToolbar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
        } else {

        }
    }

    protected void setTitle(String title) {
        if (mToolBar != null) {
            mToolBar.setTitle(title);
        }
    }


    protected DB getViewBinding() {
        return viewBinding;
    }

    protected void init(Bundle savedInstanceState) {
        LiveEventBus
                .get(ACTIVITY_KEY, BusInfo.class)
                .observe(this, observer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Observer<BusInfo> observer = this::handBusEvent;

    public void handBusEvent(BusInfo busInfo) {

    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewBinding != null) {
            viewBinding.unbind();
        }
    }
}
