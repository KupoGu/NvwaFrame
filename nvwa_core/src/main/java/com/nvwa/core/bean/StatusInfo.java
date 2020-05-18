package com.nvwa.core.bean;

import androidx.annotation.StringRes;

/**
 * @author G.god
 * Create by AS 2020/5/11 17:52
 */
public class StatusInfo {
    public String message;
    public int type;
    public int strMsg;

    public StatusInfo(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public StatusInfo(@StringRes int message, int type) {
        this.strMsg = message;
        this.type = type;
    }
}
