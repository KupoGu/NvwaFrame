package com.nvwa.uikit.apply.pudding;

import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import com.nvwa.uikit.pudding.Pudding;

/**
 * @author G.god
 * Create by AS 2020/5/15 10:02
 */
public class Notice {
    private static volatile Notice instance;

    public static Notice getInstance() {
        if (null == instance) {
            synchronized (Notice.class) {
                if (null == instance) {
                    instance = new Notice();
                }
            }
        }
        return instance;
    }


    public void showSuccess(String msg, AppCompatActivity appCompatActivity) {
        Pudding.create(appCompatActivity, choco -> {
            choco.setChocoBackgroundColor(Color.parseColor("#31c27c"));
            choco.setTitle(msg);
            return null;
        }).show();
    }

    public void showWarning(String msg, AppCompatActivity appCompatActivity) {
        Pudding.create(appCompatActivity, choco -> {
            choco.setChocoBackgroundColor(Color.parseColor("#ffffbb33"));
            choco.setTitle(msg);
            return null;
        }).show();
    }

    public void showError(String msg, AppCompatActivity appCompatActivity) {
        Pudding.create(appCompatActivity, choco -> {
            choco.setChocoBackgroundColor(Color.parseColor("#ff0000"));
            choco.setTitle(msg);
            return null;
        }).show();
    }
}
