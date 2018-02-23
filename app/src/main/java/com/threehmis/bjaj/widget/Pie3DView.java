package com.threehmis.bjaj.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by llz on 2018/2/23.
 */

public class Pie3DView extends View {

    int areaX = 1;
    int areaY = 22;

    //饼图的宽高
    private int areaWidth = 360;
    private int areaHight = 300;

    private int legendHight = 10;

    //颜色数组
    private int[] colors =new int[]{
            Color.rgb(54,217,169),Color.rgb(0,171,255),Color.rgb(80,195,252)
    };
    //阴影数组
    private int[] shade_colors = new int[]{
            Color.rgb(26,164,123),Color.rgb(0,154,230),Color.rgb(21,178,255)
    };
    //区域百分比
    private int percent[] = new int []{60,300};

    private String title="标题";
    private int thickness= 20;
    float x, y;

    //默认可响应点击事件
    private boolean isOntouch= true;
    //下面的图例文字
    private String[] info = new String[]{"测试1","测试2"};


    //点击事件的计算
    private float centerX;
    private float centerY;

    private Context context;

    //图例文字的pain
    private Paint legendPaint = new Paint();
    //饼图的paint
    private Paint mainPaint ;
    /**
     * 数据画笔
     */
    private Paint dataPaint;

    /**
     * 数据文本的大小
     */
    private Rect dataTextBound = new Rect();

    /**
     * 半径
     */
    private float radius;
    /**
     * 弧形外接矩形
     */
    private RectF rectF;
    //默认显示右边
    private PieView.WHERE where  = PieView.WHERE.bottmo;

    public static enum WHERE{
        right ,bottmo
    }

    public Pie3DView(Context context) {
        super(context);
        init();
    }

    public Pie3DView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pie3DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mainPaint = new Paint();
        mainPaint.setStrokeWidth(2);
        mainPaint.setTextSize(12);
        mainPaint.setAntiAlias(true);


        dataPaint = new Paint();
        dataPaint.setStrokeWidth(2);
        dataPaint.setTextSize(12);
        dataPaint.setAntiAlias(true);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(areaWidth, widthMeasureSpec);
        int height = getMySize(areaHight, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }
    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);
        //开画
        for(int i=0;i <= thickness;i++){

            int tempAngle=0;


            //开始画图例区域
     /*       if(i  == 0 ){
                RectF rectF = new RectF(areaX, areaY- thickness, areaX + areaWidth, areaHight- thickness);
                centerX = rectF.centerX();
                centerY = rectF.centerY();
                int temp = legendHight + 20*//* height-320*//*;
                for(int j=0;j<percent.length;j++){
                    mainPaint.setColor(colors[j]);
                    canvas.drawArc(rectF , tempAngle,percent[j], true, mainPaint);
                    tempAngle+=percent[j];
                    //说明区域
                    RectF rect = new RectF(areaX, temp, areaX+40, temp-10);//标识区域
                    canvas.drawText(info[j], areaX+60, temp, legendPaint);
                    canvas.drawRect(rect, mainPaint);
                    temp += 25;
                }
            }*/
            //所占百分比
            float percenttemp;
            //所占度数
            float angle;
            for(int j=0;j< percent.length;j++){
                percenttemp = percent[j] / (float) 360;
                //画笔的颜色
                mainPaint.setColor(shade_colors[j]);
                //坐标点 start end
               RectF rectF = new RectF(areaX, areaY-i, areaX+areaWidth, areaHight-i);
                //设置扇形外接矩形
                //画弧
                canvas.drawArc(rectF, tempAngle,percent[j], true, mainPaint);
                tempAngle+=percent[j];// 记录下绘图的角度
                    //当前弧线中心点相对于纵轴的夹角度数,由于扇形的绘制是从三点钟方向开始，所以加90
                   // float arcCenterDegree = tempAngle - percent[j];
                //当前弧线中心点相对于纵轴的夹角度数,由于扇形的绘制是从三点钟方向开始，所以加90
                float arcCenterDegree = 90 + tempAngle - percent[j] / 2;
                drawData(canvas, arcCenterDegree, j, percenttemp);

            }
        }

        for (int i = 0; i < colors.length; i++) {
            if (isOntouch) {
                isOntouch = false;
                System.out.println(centerX + ""  + centerY);
                double d = Math.atan2(y-centerY, x-centerX)*180/Math.atan2(0.0, -1.0);
                if (d < 0) {
                    d = 360 + d;
                }
                int temp = 0;
                for (int j = 0; j < percent.length; j++) {

                    Log.e(Float.toString(y), Float.toString(areaHight));

                    if (d >temp && d < percent[j]+temp && y < areaHight) {
                        System.out.println(" " + colors[j]);
                    }
                    temp += percent[j];
                }
            }
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2;
        //设置半径为宽高最小值的1/4
        radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 4;

    }
    /**
     * 计算每段弧度的中心坐标
     *
     * @param degree 当前扇形中心度数
     */
    private float[] calculatePosition(float degree) {
        //由于Math.sin(double a)中参数a不是度数而是弧度，所以需要将度数转化为弧度
        //而Math.toRadians(degree)的作用就是将度数转化为弧度
        //sin 一二正，三四负 sin（180-a）=sin(a)
        //扇形弧线中心点距离圆心的x坐标
        float x = (float) (Math.sin(Math.toRadians(degree)) * radius);
        //cos 一四正，二三负
        //扇形弧线中心点距离圆心的y坐标
        float y = (float) (Math.cos(Math.toRadians(degree)) * radius);

        //每段弧度的中心坐标(扇形弧线中心点相对于view的坐标)
        float startX = centerX + x;
        float startY = centerY - y;

        float[] position = new float[2];
        position[0] = startX;
        position[1] = startY-90;
        return position;
    }
    /**
     * 绘制数据
     *
     * @param canvas  画布
     * @param degree  第i段弧线中心点相对于纵轴的夹角度数
     * @param i       第i段弧线
     * @param percent 数据百分比
     */
    private void drawData(Canvas canvas, float degree, int i, float percent) {
        //弧度中心坐标
        float startX = calculatePosition(degree)[0];
        float startY = calculatePosition(degree)[1];

        //获取名称文本大小
        dataPaint.getTextBounds(info[i], 0, info[i].length(), dataTextBound);
        //绘制名称数据，20为纵坐标偏移量
        canvas.drawText(info[i],
                startX - dataTextBound.width() / 2,
                startY + dataTextBound.height() / 2 - 20,
                dataPaint);


        //拼接百分比并获取文本大小
       /* DecimalFormat df = new DecimalFormat("0.0");
        String percentString = df.format(percent ) + "%";
        dataPaint.getTextBounds(percentString, 0, percentString.length(), dataTextBound);*/

        //绘制百分比数据，20为纵坐标偏移量
        canvas.drawText(percent+"",
                startX - dataTextBound.width() / 2,
                startY + dataTextBound.height() * 2 - 20,
                dataPaint);
    }
}
