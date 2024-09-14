package com.hxyc.myframework.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * 自定义Log工具类
 */
public class Logger {

    public static final String DEFAULT_TAG = "Logger";
    public static String TAG;

    private static final int SHOW_LOG = 0;
    private static final int HIDE_LOG = 7;
    private static int LOG_LEVEL = SHOW_LOG;
    private static int VERBOSE = Log.VERBOSE;
    private static int DEBUG = Log.DEBUG;
    private static int INFO = Log.INFO;
    private static int WARN = Log.WARN;
    private static int ERROR = Log.ERROR;

    public static void setDevelopMode(boolean debug) {
        if (debug) {
            LOG_LEVEL = SHOW_LOG;
        } else {
            LOG_LEVEL = HIDE_LOG;
        }
    }

    public static void setLogLevel(int logLevel) {
        LOG_LEVEL = logLevel;
    }

    public static void v(String tag, String msg) {
        if (LOG_LEVEL <= VERBOSE && !TextUtils.isEmpty(msg)) {
            if (TextUtils.isEmpty(tag)) tag = DEFAULT_TAG;
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_LEVEL <= DEBUG && !TextUtils.isEmpty(msg)) {
            if (TextUtils.isEmpty(tag)) tag = DEFAULT_TAG;
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_LEVEL <= INFO && !TextUtils.isEmpty(msg)) {
            if (TextUtils.isEmpty(tag)) tag = DEFAULT_TAG;
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_LEVEL <= WARN && !TextUtils.isEmpty(msg)) {
            if (TextUtils.isEmpty(tag)) tag = DEFAULT_TAG;
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_LEVEL <= ERROR && !TextUtils.isEmpty(msg)) {
            if (TextUtils.isEmpty(tag)) tag = DEFAULT_TAG;
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, Throwable throwable) {
        if (LOG_LEVEL <= ERROR && throwable != null) {
            if (TextUtils.isEmpty(tag)) tag = DEFAULT_TAG;
            Log.e(tag, throwable.getLocalizedMessage());
        }
    }

    public static void v(String str) {
        v(TAG, str);
    }

    public static void d(String str) {
        d(TAG, str);
    }

    public static void i(String str) {
        i(TAG, str);
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void e(String str) {
        e(TAG, str);
    }

    public static void v(Number number) {
        v(TAG, "Number is :" + String.valueOf(number));
    }

    public static void d(Number number) {
        d(TAG, "Number is :" + String.valueOf(number));
    }

    public static void i(Number number) {
        i(TAG, "Number is :" + String.valueOf(number));
    }

    public static void w(Number number) {
        w(TAG, "Number is :" + String.valueOf(number));
    }

    public static void e(Number number) {
        e(TAG, "Number is :" + String.valueOf(number));
    }

    public static void e(Throwable throwable) {
        e(TAG, throwable);
    }

}
