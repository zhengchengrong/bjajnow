package com.threehmis.bjaj.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.utils.ScreenUtil;
import com.threehmis.bjaj.widget.datepicker.cons.DPMode;
import com.threehmis.bjaj.widget.datepicker.views.DatePicker;

import java.util.Calendar;

/**
 * 
 * @author CH
 *
 */
public class DataDialog extends Dialog {

	Activity context;

	GetdatapickerLisenter lisenter;


	public DataDialog(Activity context, GetdatapickerLisenter lisenter) {
		this(context, R.style.enterDialog);
		this.context = context;
		this.lisenter= lisenter;
	}

	public DataDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_dialog);
		Window window = getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.alpha = 1.0f;
		wl.gravity = Gravity.CENTER;
		wl.width = ScreenUtil.getScreenWidth(context);
		window.setAttributes(wl);
		setCancelable(true);
		setCanceledOnTouchOutside(true);

		DatePicker dataview = (DatePicker) findViewById(R.id.dataview);

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);

		dataview.setDate(year,month+1);
		dataview.setMode(DPMode.SINGLE);
		dataview.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
			@Override
			public void onDatePicked(String date) {

				lisenter.Getdata(date);
				dismiss();
			}
		});
	}

	public interface  GetdatapickerLisenter{

		void  Getdata(String data);


	}



}
