package com.at.android.demo.section.asyncloadweather;

import com.at.android.demo.R;

import android.app.Activity;
import android.os.Bundle;

public class AsyncLoadDataActivity extends Activity {
	private Controller controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_load_weather);

		initContentView();
	}

	private void initContentView() {
		controller = new Controller(this, findViewById(R.id.rootView), null);
	}

	@Override
	protected void onDestroy() {
		controller.onDestroy();
		super.onDestroy();
	}

}
