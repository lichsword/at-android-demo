package com.at.android.demo.section.asyncloadweather;

import com.at.android.demo.R;
import com.at.common.android.AtbAdapter;
import com.at.comon.third.baidu.WeatherData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherListAdapter extends AtbAdapter {

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder viewHolder = null;

		ATDataType dataType = getItem(position);

		if (null == view) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_weather, null);
			viewHolder = new ViewHolder(view);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.fill(dataType);

		return view;
	}

	class ViewHolder {
		TextView tv_date;
		TextView tv_weather;
		TextView tv_degree;
		TextView tv_wind;
		ImageView imgv_day;
		ImageView imgv_night;

		public ViewHolder(View view) {
			tv_date = (TextView) view.findViewById(R.id.tv_date);
			tv_weather = (TextView) view.findViewById(R.id.tv_weather);
			tv_degree = (TextView) view.findViewById(R.id.tv_degree);
			tv_wind = (TextView) view.findViewById(R.id.tv_wind);
			imgv_day = (ImageView) view.findViewById(R.id.imgv_day);
			imgv_night = (ImageView) view.findViewById(R.id.imgv_night);
		}

		public void fill(ATDataType dataType) {
			WeatherData weatherData = (WeatherData) dataType.data;
			tv_weather.setText("天气:" + weatherData.weather);
			tv_degree.setText("温度:" + weatherData.temperature);
			tv_wind.setText("风力:" + weatherData.wind);
			tv_date.setText(weatherData.date);
		}
	}
}
