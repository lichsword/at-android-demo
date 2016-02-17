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
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

@AtaListView(viewResId = R.id.list_weather, adapter = WeatherListAdapter.class, enablePtr = false)
public class Controller extends AtcListView {

	private static final String TAG = Controller.class.getSimpleName();

	private AtcWeather atcWeather;

	private ImageView imgv_loading;

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

		imgv_loading = (ImageView) activity.findViewById(R.id.imgv_loading);

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
					final ArrayList<AtbAdapter.ATDataType> list = new ArrayList<AtbAdapter.ATDataType>();
					for (WeatherData item : localList) {
						list.add(new AtbAdapter.ATDataType(0, item));
					}
					AtcApplication.getHandler().post(new Runnable() {
						@Override
						public void run() {

							listAdapter.setDataList(list);

							imgv_loading.setVisibility(View.GONE);
						}
					});
				}

			}

			@Override
			public void onBegin() {
				AtcApplication.getHandler().post(new Runnable() {
					@Override
					public void run() {
						imgv_loading.setVisibility(View.VISIBLE);
						AnimationDrawable animationDrawable = (AnimationDrawable) imgv_loading.getDrawable();
						animationDrawable.start();
					}
				});

			}
		});

	}

}
