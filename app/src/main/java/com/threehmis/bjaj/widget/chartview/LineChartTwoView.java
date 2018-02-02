package com.threehmis.bjaj.widget.chartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class LineChartTwoView extends View {

	private Paint hLinePaint;// 坐标轴水平内部 虚线画笔 设置6条虚线
	private Paint titlePaint;// 绘制文本的画笔 绘制 Y 周坐标zhi
	private Paint linePaint;// 线上的园点
	private Paint paint;// 红线的样式信息
	private int[] text;// 折线的转折点 线数值
	private int[] textgry;// 第二折线的转折点 线数值
	int x, y, preX, preY;

//	private HistogramAnimation ani;
	private int flag;

	// 坐标轴左侧的数标
	private String[] nums;
	// 坐标轴底部的季度数
	private String[] season;

	private int[] text2;// 设置点击事件，显示哪一条柱状的信息
	private final int TRUE = 1;// 在柱状图上显示数字

	public LineChartTwoView(Context context) {
		super(context);
		init(context, null);
	}

	public LineChartTwoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {

		nums = new String[] { "15", "12", "9", "6", "3", "0" };
		season = new String[] { "第一季度", "第二季度", "第三季度", "第四季度" };

		text = new int[] { 9, 6, 15, 3 };
		textgry = new int[] { 5, 3, 2, 1 };
		text2 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		// ani = new HistogramAnimation();
		// ani.setDuration(4000);

		linePaint = new Paint();
		titlePaint = new Paint();
		hLinePaint = new Paint();
		hLinePaint.setColor(Color.LTGRAY);

	}

	public void start(int flag) {
		this.flag = flag;
		// this.startAnimation(ani);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight() - dp2px(20);

		// 绘制xy的线条
		Paint xLinePaint = new Paint();
		xLinePaint.setColor(Color.BLACK);
		// 绘制底部的线条
		canvas.drawLine(dp2px(30), height, width - dp2px(30), height, xLinePaint);
		// 绘制左部的Y轴线条
		canvas.drawLine(dp2px(30), height, dp2px(30), dp2px(10), xLinePaint);

		int leftHeight = height - dp2px(15);// 左侧外周的 需要划分的高度：
		int hPerHeight = leftHeight / 5;// 分成6部分

		hLinePaint.setTextAlign(Align.CENTER);
		// 设置6条虚线
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(dp2px(30), i * hPerHeight + dp2px(15), width - dp2px(30), i * hPerHeight + dp2px(15),
					hLinePaint);
		}

		// 绘制 Y 周坐标zhi
		titlePaint.setTextAlign(Align.RIGHT);
		titlePaint.setTextSize(sp2px(12));
		titlePaint.setAntiAlias(true); // 抗锯齿
		titlePaint.setStyle(Paint.Style.FILL); // 设置画笔实心、空心
		// 设置左部的数字
		for (int i = 0; i < nums.length; i++) {
			canvas.drawText(nums[i], dp2px(25), i * hPerHeight + dp2px(20), titlePaint);
			Log.d("CD", "hhh=" + i * hPerHeight + dp2px(20));
		}

		// 绘制 X 周 等分做坐标
		int xAxisLength = width - dp2px(60);
		int columCount = season.length - 1;
		int step = xAxisLength / columCount;

		// 设置底部的数字
		for (int i = 0; i < columCount + 1; i++) {

			canvas.drawText(season[i], step * (i) + dp2px(55), height + dp2px(15), titlePaint);
		}

		Path path = new Path();// 折线图的路径
		Path pathgry = new Path();// 第二折线图的路径
		// 线上的点
		linePaint.setColor(Color.parseColor("#bb2222"));
		linePaint.setAntiAlias(true);
		linePaint.setStrokeWidth(dp2px(1)); // 线宽
		linePaint.setStyle(Paint.Style.STROKE);
		// 路径划线
		paint = new Paint();
		paint.setColor(Color.parseColor("#bb2222"));
		paint.setStyle(Paint.Style.STROKE);// 线必须空心
		paint.setStrokeWidth(dp2px(1));

		for (int i = 0; i < 4; i++) {// 画x线
			// 路径
			x = i * step + dp2px(30);
			if (i == 0) {
				path.moveTo(x, (float) (leftHeight - text[i] * (leftHeight / 15.0) + dp2px(15)));
				pathgry.moveTo(x, (float) (leftHeight - textgry[i] * (leftHeight / 15.0) + dp2px(15)));
			} else {
				path.lineTo(x, (float) (leftHeight - text[i] * (leftHeight / 15.0) + dp2px(15)));
				pathgry.lineTo(x, (float) (leftHeight - textgry[i] * (leftHeight / 15.0) + dp2px(15)));
			}

			// 线上的点
			linePaint.setColor(Color.parseColor("#bb2222"));
			canvas.drawCircle(x, (float) (leftHeight - text[i] * (leftHeight / 15.0) + dp2px(15)), dp2px(4), linePaint);
			linePaint.setColor(Color.parseColor("#2F4554"));
			canvas.drawCircle(x, (float) (leftHeight - textgry[i] * (leftHeight / 15.0) + dp2px(15)), dp2px(4), linePaint);

			// 路径划线
			paint.setColor(Color.parseColor("#bb2222"));
			canvas.drawPath(path, paint);
			paint.setColor(Color.parseColor("#2F4554"));
			canvas.drawPath(pathgry, paint);


		}
		
		for (int i = 0; i < 4; i++) {
		// 点击后是否显示线性图上的标注
					if (this.text2[i] == TRUE) {

						xLinePaint.setAntiAlias(true);// 抗锯齿效果
						xLinePaint.setStyle(Paint.Style.FILL);
						xLinePaint.setTextSize(sp2px(10));// 字体大小
						// 画竖线
						canvas.drawLine(i * step + dp2px(30), height, i * step + dp2px(30), dp2px(15), xLinePaint);

						if (i == 3) {
							// 点击第四象限
							// 通过画笔颜色话柱状标注
							xLinePaint.setColor(Color.parseColor("#99000000"));// 字体颜色
							Rect rect = new Rect();// 柱状标注的形状
							rect.left = dp2px(10) + (i - 1) * step + dp2px(30);
							rect.right = dp2px(80) + dp2px(10) + (i - 1) * step + dp2px(30);
							rect.top = height / 2 - dp2px(25);
							rect.bottom = height / 2 + dp2px(25);
							canvas.drawRect(rect, xLinePaint);

							// 标注字
							xLinePaint.setColor(Color.parseColor("#ffffff"));
							canvas.drawText(season[i] + "", dp2px(12) + (i - 1) * step + dp2px(30), height / 2 - dp2px(10),
									xLinePaint);

							canvas.drawText("不合格数量:" + text[i], dp2px(24) + (i - 1) * step + dp2px(30), height / 2 + dp2px(5),
									xLinePaint);
							// 标注上的点
							xLinePaint.setColor(Color.parseColor("#bb2222"));
							canvas.drawCircle(dp2px(17) + (i - 1) * step + dp2px(30), height / 2 + dp2px(1), dp2px(3),
									xLinePaint);

							xLinePaint.setColor(Color.parseColor("#ffffff"));
							canvas.drawText("未处理数量:" + textgry[i], dp2px(24) + (i - 1) * step + dp2px(30), height / 2 + dp2px(19),
									xLinePaint);
							// 标注上的点2
							xLinePaint.setColor(Color.parseColor("#2F4554"));
							canvas.drawCircle(dp2px(17) + (i - 1) * step + dp2px(30), height / 2 + dp2px(15), dp2px(3),
									xLinePaint);
						} else {
							// 通过画笔颜色话柱状标注
							xLinePaint.setColor(Color.parseColor("#99000000"));// 字体颜色
							Rect rect = new Rect();// 柱状图的形状
							rect.left = dp2px(10) + i * step + dp2px(30);
							rect.right = dp2px(80) + dp2px(10) + i * step + dp2px(30);
							rect.top = height / 2 - dp2px(25);
							rect.bottom = height / 2 + dp2px(25);
							canvas.drawRect(rect, xLinePaint);

							// 标注字
							xLinePaint.setColor(Color.parseColor("#ffffff"));
							canvas.drawText(season[i] + "", dp2px(12) + i * step + dp2px(30), height / 2 - dp2px(10),
									xLinePaint);

							// 标注字1
							canvas.drawText("不合格率:" + text[i], dp2px(24) + i * step + dp2px(30), height / 2 + dp2px(5),
									xLinePaint);
							// 标注上的点
							xLinePaint.setColor(Color.parseColor("#bb2222"));
							canvas.drawCircle(dp2px(17) + i * step + dp2px(30), height / 2 + dp2px(1), dp2px(3), xLinePaint);

							// 标注字2
							xLinePaint.setColor(Color.parseColor("#ffffff"));
							canvas.drawText("未处理数量:" + textgry[i], dp2px(24) + i * step + dp2px(30), height / 2 + dp2px(19),
									xLinePaint);
							// 标注上的点2
							xLinePaint.setColor(Color.parseColor("#2F4554"));
							canvas.drawCircle(dp2px(17) + i * step + dp2px(30), height / 2 + dp2px(15), dp2px(3), xLinePaint);

						}
					}
		 }
					

	}

	private int dp2px(int value) {
		float v = getContext().getResources().getDisplayMetrics().density;
		return (int) (v * value + 0.5f);
	}

	private int sp2px(int value) {
		float v = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (v * value + 0.5f);
	}

	/**
	 * 设置点击事件，是否显示数字
	 */
	public boolean onTouchEvent(MotionEvent event) {
		int step = getWidth() / 4;
		int x = (int) event.getX();
		for (int i = 0; i < season.length; i++) {
			if (x > step * i && x < step * (i + 1)) {
				text2[i] = 1;
				for (int j = 0; j < season.length; j++) {
					if (i != j) {
						text2[j] = 0;
					}
				}
				if (Looper.getMainLooper() == Looper.myLooper()) {
					invalidate();
				} else {
					postInvalidate();
				}
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 集成animation的一个动画类
	 * 
	 * @author 李垭超
	 */
//	private class HistogramAnimation extends Animation {
//		@Override
//		protected void applyTransformation(float interpolatedTime, Transformation t) {
//			super.applyTransformation(interpolatedTime, t);
//			if (interpolatedTime < 1.0f && flag == 2) {
//				preX = (int) ((getWidth() - dp2px(30)) * interpolatedTime);
//			} else {
//
//				preX = getWidth();
//
//			}
//			invalidate();
//		}
//	}

}