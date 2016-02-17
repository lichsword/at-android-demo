package com.at.android.demo.section.asyncloadweather;

import com.at.android.demo.R;
import com.at.common.android.AtbAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherListAdapter extends AtbAdapter {

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		append(String.format("天气:%s, 温度:%s, 风力:%s, 白天图像:%s, 夜晚图像:%s", item.date, item.temperature, item.wind,
				item.dayPictureUrl, item.nightPictureUrl));

		View view = convertView;
		ViewHolder viewHolder = null;

		ATDataType dataType = getItem(position);

		if (null == view) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_weather, null);
			viewHolder = new ViewHolder(view, dataType);
		} else {

		}

		return null;
	}

	class ViewHolder {
		TextView tv_weather;
		TextView tv_degree;
		TextView tv_wind;
		ImageView imgv_day;
		ImageView imgv_night;

		public ViewHolder(View view, ATDataType dataType) {

		}

		public void fill() {

		}
	}
}
