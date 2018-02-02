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

public class HistogramFourRedView extends View {

	private Paint xLinePaint;// 坐标轴 轴线 画笔：
	private Paint hLinePaint;// 坐标轴水平内部 虚线画笔
	private Paint titlePaint;// 绘制文本的画笔
	private Paint paint;// 矩形画笔 柱状图的样式信息
	private int[] progress;// 5
	private int[] progress2;// 5
																			// 条，显示各个柱状的数据
	// 坐标轴左侧的数标
	private String[] ySteps;
	// 坐标轴底部的星期数
	private String[] xWeeks;

	private double maxnum=50;


	public HistogramFourRedView(Context context) {
		super(context);
		init();
	}

	public HistogramFourRedView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {

		//progress = new int[]{ 30, 28, 27, 36 };// 4
		//progress2 = new int[]{ 20, 10, 5, 4 };// 4




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

	public void start(double maxnum,int[] progress,int[] progress2) {
		this.maxnum=maxnum;
		this.progress=progress;
		this.progress2=progress2;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		ySteps = new String[] { (int)maxnum+"", (int)(maxnum/5)*4+"", (int)(maxnum/5)*3+"", (int)(maxnum/5)*2+"", (int)maxnum/5+"", "0" };
		xWeeks = new String[] { "第一季度", "第二季度", "第三季度", "第四季度"};

		int width = getWidth();
		int height = getHeight() - dp2px(50);
		// 绘制底部的线条
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
		if (progress != null && progress.length > 0) {
			for (int i = 0; i < progress.length; i++) {// 循环遍历将4条柱状图形画出来
				int value = progress[i];
				int value2 = progress2[i];
				paint.setAntiAlias(true);// 抗锯齿效果
				paint.setStyle(Paint.Style.FILL);
				paint.setTextSize(sp2px(12));// 字体大小
				paint.setColor(Color.parseColor("#3398DB"));// 字体颜色
				Rect rect = new Rect();// 柱状图的形状

				rect.left = step * (i + 1)-dp2px(5);
				rect.right = dp2px(25) + step * (i + 1)+dp2px(5);
				int rh = (int) (leftHeight - leftHeight * (value / maxnum));
				rect.top = rh + dp2px(15);
				rect.bottom = height;
				canvas.drawRect(rect, paint);//通过画笔颜色话柱状图

				paint.setColor(Color.parseColor("#FF6666"));// 柱状上部颜色粉红
				Rect rect2 = new Rect();// 柱状图的形状

				rect2.left = step * (i + 1)-dp2px(5);
				rect2.right = dp2px(25) + step * (i + 1)+dp2px(5);
				int rh2 = (int) (leftHeight - leftHeight * ((value2+value) / maxnum));
				rect2.top = rh2 + dp2px(15);
				rect2.bottom = rh + dp2px(15);
				canvas.drawRect(rect2, paint);//通过画笔颜色话上部柱状图

//				canvas.drawBitmap(bitmap, null, rect, paint);//通过。9图片话柱状图
				// 是否显示柱状图上方的数字

				paint.setColor(Color.parseColor("#FF6666"));// 柱状上部颜色粉红
					canvas.drawText(value2 + "",  step * (i + 1)
							- dp2px(5), rh2 + dp2px(10), paint);
				paint.setColor(Color.parseColor("#6DCAEC"));// 柱状上部颜色粉红
					canvas.drawText("/"+value, dp2px(10) + step * (i + 1)
							, rh2 + dp2px(10), paint);

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


}