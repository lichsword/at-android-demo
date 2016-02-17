package com.at.android.demo.section.asyncloadweather;

import java.util.ArrayList;
import java.util.List;

import com.at.android.demo.R;
import com.at.common.android.AtaListView;
import com.at.common.android.AtbAdapter;
import com.at.common.android.AtbController;
import com.at.common.android.AtcApplication;
import com.at.common.android.AtcListView;
import com.at.common.android.AtuLog;
import com.at.comon.third.baidu.AtcWeather;
import com.at.comon.third.baidu.AtcWeather.Callback;
import com.at.comon.third.baidu.WeatherData;
import com.at.comon.third.baidu.WeatherResponse;

import android.app.Activity;
import android.view.View;

@AtaListView(viewResId = R.id.list_weather, adapter = WeatherListAdapter.class, enablePtr = false)
public class Controller extends AtcListView {

	private static final String TAG = Controller.class.getSimpleName();

	private AtcWeather atcWeather;

	public Controller(Activity activity, View rootView, AtbController parentController) {
		super(activity, rootView, parentController);

		initContentView();
		initContentData();
	}

	@Override
	public void onDestroy() {
		atcWeather.onDestroy();
		super.onDestroy();
	}

	private void initContentView() {
	}

	private void initContentData() {
		atcWeather = new AtcWeather(activity, rootView, this);
		atcWeather.requestWeather(new Callback() {

			@Override
			public void onFinish(WeatherResponse response) {
				List<WeatherData> dataList = null;
				try {
					dataList = response.results.get(0).weather_data;
				} catch (NullPointerException e) {
					AtuLog.e(TAG, e.getMessage());
					dataList = null;
				}
				if (null != dataList) {
					final List<WeatherData> localList = dataList;
					AtcApplication.getHandler().post(new Runnable() {
						@Override
						public void run() {
							ArrayList<AtbAdapter.ATDataType> list = new ArrayList<AtbAdapter.ATDataType>();
							for (WeatherData item : localList) {
								list.add(new AtbAdapter.ATDataType(0, item));
							}
							listAdapter.setDataList(list);
						}
					});
				}
			}
		});

	}

}
