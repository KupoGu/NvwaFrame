package com.nvwa.core.bean;

import java.util.Map;

/**
 * @author G.god
 * Create by AS 2020/5/11 17:52
 */
public class ActionInfo {
    public int actionType;
    public Map<String,Object> actionParam;

    public ActionInfo(int actionType, Map<String, Object> actionParam) {
        this.actionType = actionType;
        this.actionParam = actionParam;
    }
}
