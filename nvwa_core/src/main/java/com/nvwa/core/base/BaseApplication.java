package com.nvwa.core.base;

import android.app.Application;

import com.jeremyliao.liveeventbus.BuildConfig;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.nvwa.base.log.NvwaLog;

/**
 * @author G.god
 * Create by AS 2020/5/15 15:48
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NvwaLog.init(BuildConfig.DEBUG, "nvwa");
        LiveEventBus.config().autoClear(true).enableLogger(true).lifecycleObserverAlwaysActive(true);
    }
}
