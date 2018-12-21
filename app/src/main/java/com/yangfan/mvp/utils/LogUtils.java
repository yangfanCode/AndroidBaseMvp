package com.yangfan.mvp.utils;

import android.util.Log;

import com.yangfan.mvp.enums.AppEnvEnum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LogUtils {
    private final static String TAG = "LogUtil";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static AppEnvEnum appEnvEnum = AppEnvEnum.DEBUG;

    public static void setAppEnvEnum(AppEnvEnum appEnvEnum) {
        LogUtils.appEnvEnum = appEnvEnum;
    }

    public static void LogD(Class classz, String str) {
        if (appEnvEnum != null && appEnvEnum == AppEnvEnum.DEBUG)
            Log.d(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogI(Class classz, String str) {
        if (appEnvEnum != null && appEnvEnum == AppEnvEnum.DEBUG)
            Log.i(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogE(Class classz, String str) {
        if (appEnvEnum != null && appEnvEnum == AppEnvEnum.DEBUG)
            Log.e(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogV(Class classz, String str) {
        if (appEnvEnum != null && appEnvEnum == AppEnvEnum.DEBUG)
            Log.v(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogException(Class c, Throwable e) {
        if (appEnvEnum != null && appEnvEnum == AppEnvEnum.DEBUG) {
            try {
                StringBuilder exceptionInfo = new StringBuilder();
                if (e == null) {
                    exceptionInfo.append("Exception:"
                            + "e is null,probably null pointer exception"
                            + "\n");
                } else {
                    e.printStackTrace();
                    exceptionInfo.append(e.getClass().getCanonicalName() + ":"
                            + e.getMessage() + "\n");
                    StackTraceElement[] stes = e.getStackTrace();
                    for (StackTraceElement ste : stes) {
                        exceptionInfo.append("at " + ste.getClassName() + "$"
                                + ste.getMethodName() + "$" + ste.getFileName()
                                + ":" + ste.getLineNumber() + "\n");
                    }
                }
                LogE(c, exceptionInfo.toString());
            } catch (Exception ex) {
                LogE(c, ex.toString());
            }
        }

    }

    public static void LogJson(Class classz,String msg) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(TAG, true);
        message = classz.getCanonicalName() + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(TAG, "║ " + line);
        }
        printLine(TAG, false);
    }

    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
