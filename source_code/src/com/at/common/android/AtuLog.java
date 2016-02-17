package com.at.common.android;

import android.text.TextUtils;

/**
 * 日志管理工具类
 * <p/>
 * 从低到高: VERBOSE --- DEBUG --- INFO --- WARN --- ERROR
 * <p/>
 * Created by lichsword on 15/6/10.
 */
public class AtuLog {

    /**
     * 显示冗余或更高级别日志。
     */
    public static final int VERBOSE = 1;
    /**
     * 显示调试或更高级别日志。
     */
    public static final int DEBUG = 2;
    /**
     * 显示信息或更高级别日志。
     */
    public static final int INFO = 3;
    /**
     * 显示错误或更高级别日志。
     */
    public static final int WARN = 4;
    /**
     * 显示错误或更高级别日志。
     */
    public static final int ERROR = 5;
    /**
     * 任何日志都不输出，相当于关闭日志。
     */
    public static final int NOTHING = 6;

    /**
     *
     */
    public static int LEVEL = VERBOSE;

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE && !TextUtils.isEmpty(msg)) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG && !TextUtils.isEmpty(msg)) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO && !TextUtils.isEmpty(msg)) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN && !TextUtils.isEmpty(msg)) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR && !TextUtils.isEmpty(msg)) {
            android.util.Log.e(tag, msg);
        }
    }

}
