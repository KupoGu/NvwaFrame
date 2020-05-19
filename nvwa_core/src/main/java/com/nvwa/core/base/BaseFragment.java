package com.nvwa.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.nvwa.core.bean.BusInfo;
import com.nvwa.core.bean.StatusInfo;
import com.nvwa.uikit.apply.pudding.Notice;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;

/**
 * @author G.god
 * Create by AS 2020/5/15 09:21
 */
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {
    private ViewGroup rootView;
    DB viewBinding;
    private RxPermissions rxPermissions;
    private static final String FRAGMENT_KEY = "nvwa_fragment_key";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() > 0) {
            rootView = (ViewGroup) inflater.inflate(getLayoutId(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            viewBinding = DataBindingUtil.bind(rootView);
            return rootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutInit(savedInstanceState);
    }

    protected void layoutInit(Bundle savedInstanceSate) {
        LiveEventBus
                .get(FRAGMENT_KEY, BusInfo.class)
                .observe(this, observer);
    }

    private Observer<BusInfo> observer = this::handBusEvent;

    public void handBusEvent(BusInfo busInfo) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (viewBinding != null) {
            viewBinding.unbind();
        }
    }

    protected void showMessage(StatusInfo statusInfo) {
        int type = statusInfo.type;
        String msg = statusInfo.message;
        try {
            if (type == 0) {
                Notice.getInstance().showSuccess(msg, (AppCompatActivity) getActivity());
            } else if (type == 1) {
                Notice.getInstance().showWarning(msg, (AppCompatActivity) getActivity());
            } else {
                Notice.getInstance().showError(msg, (AppCompatActivity) getActivity());
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void showProgress(String word) {
//        LoadingConfig loadingConfig = new LoadingConfig.Builder()
//                .isCanceledOnTouchOutside(false)
//                .setLoadingDrawable(R.drawable.rotate)
//                .isCancelable(false).build();
//        LoadingDialog.showProgress(getActivity(), word, loadingConfig);
    }

    protected void dismissProgress() {
//        LoadingDialog.dismissProgress();
    }

    public abstract int getLayoutId();

    protected RxPermissions getRxPermissions() {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
        }
        return rxPermissions;
    }


    protected DB getViewBinding() {
        return viewBinding;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewBinding != null) {
            viewBinding.unbind();
        }
    }
}
