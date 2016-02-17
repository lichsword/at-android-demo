package com.at.android.demo;

import com.at.android.demo.section.asyncloadweather.AsyncLoadDataActivity;
import com.at.common.android.AtbLaunchActivity;
import com.at.common.android.AtuGrls;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AtbLaunchActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initContentView();
	}

	@Override
	protected void restartLauncher() {
		AtuGrls.jump(this, MainActivity.class);

	}

	private void initContentView() {
		findViewById(R.id.btn_asyncloaddata).setOnClickListener(onClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_asyncloaddata:
				AtuGrls.jump(MainActivity.this, AsyncLoadDataActivity.class);
				break;
			default:
				break;
			}

		}
	};
}
