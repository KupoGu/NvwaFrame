package com.nvwa.uikit.tab.listener;

import androidx.annotation.DrawableRes;

/**
 * @author G.god
 * Create by AS 2020/5/15 09:01
 */
public interface TabModel {

    String getTabTitle();

    @DrawableRes
    int getTabSelectedIcon();

    @DrawableRes
    int getTabUnselectedIcon();
}
