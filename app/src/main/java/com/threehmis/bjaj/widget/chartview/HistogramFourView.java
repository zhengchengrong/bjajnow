package com.threehmis.bjaj.widget.chartview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 柱状图
 * */
public class HistogramFourView extends View {

	private Paint xLinePaint;// 坐标轴 轴线 画笔：
	private Paint hLinePaint;// 坐标轴水平内部 虚线画笔
	private Paint titlePaint;// 绘制文本的画笔
	private Paint paint;// 矩形画笔 柱状图的样式信息
	private int[] progress;// 5
																			// 条，显示各个柱状的数据
	private int[] aniProgress;// 实现动画的值
	private final int TRUE = 1;// 在柱状图上显示数字
	private int[] text;// 设置点击事件，显示哪一条柱状的信息
	// 坐标轴左侧的数标
	private String[] ySteps;
	// 坐标轴底部的星期数
	private String[] xWeeks;
	private int flag;// 是否使用动画

	private HistogramAnimation ani;

	private double maxnum=25.0;

	public HistogramFourView(Context context) {
		super(context);
		init();
	}

	public HistogramFourView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {

//		progress = new int[]{ 25, 20, 4, 9 };// 4
//		ySteps = new String[] { maxnum+"25", "20", "15", "10", "5", "0" };
//		xWeeks = new String[] { "抽检次数", "整改数", "未回复数", "不良行为"};
		text = new int[] { 0, 0, 0, 0};
		aniProgress = new int[] { 0, 0, 0, 0};
		ani = new HistogramAnimation();
		ani.setDuration(500);

		xLinePaint = new Paint();
		hLinePaint = new Paint();
		titlePaint = new Paint();
		paint = new Paint();

		// 给画笔设置颜色
		xLinePaint.setColor(Color.DKGRAY);
		hLinePaint.setColor(Color.LTGRAY);
		titlePaint.setColor(Color.BLACK);


//		
//		InputStream is = getResources().openRawResource(R.drawable.bg_frame);  
//		bitmap = BitmapFactory.decodeStream(is);
	}

	public void start(int flag,double maxnum,int[] progress) {
		this.flag = flag;
		this.maxnum = maxnum;
		this.progress = progress;
		this.startAnimation(ani);


	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 横坐标的数据
		xWeeks = new String[] { "抽检次数", "整改数", "未回复数", "不良行为"};
		// 纵向坐标的数据
		ySteps = new String[] {(int)maxnum+"", (int)(maxnum/5)*4+"", (int)(maxnum/5)*3+"", (int)(maxnum/5)*2+"", (int)maxnum/5+"", "0" };

		int width = getWidth();
		// 获取高度
		int height = getHeight() - dp2px(50);
		// 绘制底部的线条 ,起点和终点 xLinePaint
		canvas.drawLine(dp2px(30), height + dp2px(3), width - dp2px(10), height
				+ dp2px(3), xLinePaint);
		
		// 绘制左部的Y轴线条
		canvas.drawLine(dp2px(30), height + dp2px(3), dp2px(30), dp2px(5), xLinePaint);

		int leftHeight = height - dp2px(15);// 左侧外周的 需要划分的高度：

		int hPerHeight = leftHeight / 5;// 高度分成5部分

		hLinePaint.setTextAlign(Align.CENTER);
		// 设置5条虚线
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(dp2px(30), dp2px(15) + i * hPerHeight, width
					- dp2px(10), dp2px(15) + i * hPerHeight, hLinePaint);
		}

		// 绘制 Y 周坐标
		titlePaint.setTextAlign(Align.CENTER);
		titlePaint.setTextSize(sp2px(12));
		titlePaint.setAntiAlias(true);  //抗锯齿
		titlePaint.setStyle(Paint.Style.FILL); //设置画笔实心、空心
		// 设置左部的数字
		for (int i = 0; i < ySteps.length; i++) {
			canvas.drawText(ySteps[i], dp2px(20), dp2px(18) + i * hPerHeight,
					titlePaint);
		}

		// 绘制 X 周 做坐标
		int xAxisLength = width - dp2px(10);
		int columCount = xWeeks.length + 1;
		int step = xAxisLength / columCount;

		// 设置底部的数字
		for (int i = 0; i < columCount - 1; i++) {
			// text, baseX, baseY, textPaint
			canvas.drawText(xWeeks[i], dp2px(12) + step * (i + 1), height
					+ dp2px(20), titlePaint);
		}

		// 绘制矩形
		if (aniProgress != null && aniProgress.length > 0) {
			for (int i = 0; i < aniProgress.length; i++) {// 循环遍历将4条柱状图形画出来
				int value = aniProgress[i];
				paint.setAntiAlias(true);// 抗锯齿效果
				paint.setStyle(Paint.Style.FILL);
				paint.setTextSize(sp2px(12));// 字体大小
				paint.setColor(Color.parseColor("#3398DB"));// 字体和柱状图颜色
				Rect rect = new Rect();// 柱状图的形状

				rect.left = step * (i + 1)-dp2px(5);
				rect.right = dp2px(25) + step * (i + 1)+dp2px(5);
				int rh = (int) (leftHeight - leftHeight * (value / maxnum));
				rect.top = rh + dp2px(15);
				rect.bottom = height;

				canvas.drawRect(rect, paint);//通过画笔颜色话柱状图
//				canvas.drawBitmap(bitmap, null, rect, paint);//通过。9图片话柱状图
				// 是否显示柱状图上方的数字
				paint.setTextAlign(Align.CENTER);
				canvas.drawText(value + "", dp2px(12) + step * (i + 1)
						, rh + dp2px(10), paint);

//				if (this.text[i] == TRUE) {
//
//					paint.setColor(Color.parseColor("#19000000"));// 字体颜色
//					Rect rect2 = new Rect();// 柱状图的形状
//
//					rect2.left = step * (i + 1)-dp2px(20);
//					rect2.right = dp2px(25) + step * (i + 1)+dp2px(20);
//					rect2.top =  dp2px(15);
//					rect2.bottom = height;
//					canvas.drawRect(rect2, paint);//通过画笔颜色话柱状图
//
//					paint.setTextAlign(Align.CENTER);
//					canvas.drawText(value + "", dp2px(12) + step * (i + 1)
//							, rh + dp2px(10), paint);
//				}

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
//	public boolean onTouchEvent(MotionEvent event) {
//		int step = (getWidth() - dp2px(10)) / (progress.length+1);
//		int x = (int) event.getX();
//		for (int i = 0; i < progress.length; i++) {
//			if (x > (dp2px(15) + step * (i + 1) - dp2px(15))
//					&& x < (dp2px(15) + step * (i + 1) + dp2px(15))) {
//				text[i] = 1;
//				for (int j = 0; j < progress.length; j++) {
//					if (i != j) {
//						text[j] = 0;
//					}
//				}
//				if (Looper.getMainLooper() == Looper.myLooper()) {
//					invalidate();
//				} else {
//					postInvalidate();
//				}
//			}
//		}
//		return super.onTouchEvent(event);
//	}

	/**
	 * 集成animation的一个动画类
	 * 
	 * @author 李垭超
	 */
	private class HistogramAnimation extends Animation {
		protected void applyTransformation(float interpolatedTime,
				Transformation t) {
			super.applyTransformation(interpolatedTime, t);
			if (interpolatedTime < 1.0f && flag == 2) {
				for (int i = 0; i < aniProgress.length; i++) {
					aniProgress[i] = (int) (progress[i] * interpolatedTime);
				}
			} else {
				for (int i = 0; i < aniProgress.length; i++) {
					aniProgress[i] = progress[i];
				}
			}
			invalidate();
		}
	}

}