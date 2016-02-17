package com.at.android.demo.section.asyncloadweather;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.at.android.demo.R;
import com.at.common.AtuFile;
import com.at.common.android.AtaListView;
import com.at.common.android.AtbAdapter;
import com.at.common.android.AtbController;
import com.at.common.android.AtcApplication;
import com.at.common.android.AtcListView;
import com.at.common.android.AtuLog;
import com.at.common.android.AtuStorage;
import com.at.comon.third.baidu.AtcWeather;
import com.at.comon.third.baidu.AtcWeather.Callback;
import com.at.comon.third.baidu.WeatherData;
import com.at.comon.third.baidu.WeatherResponse;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@AtaListView(viewResId = R.id.list_weather, adapter = WeatherListAdapter.class, enablePtr = false)
public class Controller extends AtcListView {

	private static final String TAG = Controller.class.getSimpleName();

	private TextView tv_info;

	/**
	 * controller
	 */
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
