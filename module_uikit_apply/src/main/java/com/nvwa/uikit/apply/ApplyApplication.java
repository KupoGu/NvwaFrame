package com.nvwa.uikit.apply;

import com.nvwa.core.base.BaseApplication;
import com.nvwa.core.crash.CrashActivity;
import com.nvwa.core.crash.CrashConfig;

/**
 * @author G.god
 * Create by AS 2020/5/15 16:21
 */
public class ApplyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashConfig.Builder.create()
                .backgroundMode(CrashConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_crash) //错误图标
                .restartActivity(ApplyActivity.class) //重新启动后的activity
                .errorActivity(CrashActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }
}
