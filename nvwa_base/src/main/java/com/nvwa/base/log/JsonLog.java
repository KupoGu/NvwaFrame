package com.nvwa.base.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author G.god
 * Create by AS 2020/5/15 16:01
 */
public class JsonLog {
    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(NvwaLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(NvwaLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        Log.d(tag, "╔-----------------------------------------------------------------------------------------------------------------------------------");
        message = headString + NvwaLog.LINE_SEPARATOR + message;
        String[] lines = message.split(NvwaLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        Log.d(tag, "╚-----------------------------------------------------------------------------------------------------------------------------------");
    }
}
