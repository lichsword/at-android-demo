/******************************************************************************
 *
 * File name : MainApplication.java
 * Create time : 2015年9月1日
 * Author : lichs_000
 * Description :
 * History:
 * 1. Date: 2015年9月1日
 * Author: lichs_000
 * Modification: Create class.
 *
 *****************************************************************************/
package com.at.common.android;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

/**
 * @author lichs_000
 */
public class AtcApplication extends Application implements Thread.UncaughtExceptionHandler {

	private static final String TAG = AtcApplication.class.getSimpleName();

	public static final String KEY_DATA = "key_data";

	private static AtcApplication sInstance;

	private static Handler sHandler;

	@Override
	public void onCreate() {
		Thread.setDefaultUncaughtExceptionHandler(this);

		sInstance = this;
		sHandler = new Handler(getMainLooper());

		super.onCreate();
	}

	public static AtcApplication getInstance() {
		return sInstance;
	}

	public static Handler getHandler() {
		return sHandler;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e(TAG, ex.getMessage(), ex);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
