/******************************************************************************
*
* File name : AtcWeather.java
* Create time : 2015年9月16日
* Author : lichs_000
* Description :
* History:
* 1. Date: 2015年9月16日
* Author: lichs_000
* Modification: Create class.
*
*****************************************************************************/
package com.at.comon.third.baidu;

import com.alibaba.fastjson.JSON;
import com.at.common.android.AtbController;
import com.at.common.android.AtcThread;
import com.at.common.android.AtuLog;
import com.at.common.http.ATHttpClient;
import com.at.common.http.ATHttpParameter;
import com.at.common.http.ATHttpResponse;

import android.app.Activity;
import android.view.View;

/**
 * @author lichs_000
 *
 */
public class AtcWeather extends AtbController {

	private static final String TAG = AtcWeather.class.getSimpleName();

	public AtcWeather(Activity activity, View rootView, AtbController parentController) {
		super(activity, rootView, parentController);
	}

	public void requestWeather(final Callback callback) {
		AtcThread thread = new AtcThread();
		thread.execute(TAG, null, new Runnable() {

			@Override
			public void run() {
				if (null != callback) {
					callback.onBegin();
				} // end if

				ATHttpClient httpClient = new ATHttpClient();
				ATHttpParameter[] parameter = new ATHttpParameter[] { new ATHttpParameter("location", "武汉"),
						new ATHttpParameter("output", "json"), new ATHttpParameter("ak", "BDEEPt05z9g4YAR0c6629KYk") };
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				try {
					ATHttpResponse response = httpClient.get("http://api.map.baidu.com/telematics/v3/weather",
							parameter);
					String result = response.asString();
					WeatherResponse weatherResponse = JSON.parseObject(result, WeatherResponse.class);
					AtuLog.i(TAG, result);
					if (null != callback) {
						callback.onFinish(weatherResponse);
					} // end if
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}, null);
	}

	public interface Callback {
		public void onBegin();

		public void onFinish(WeatherResponse response);
	}

}
