package com.sigaritus.up.simpleloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/1/1.
 */
public class SimpleLoader extends View {
    private String mLoadingText;
    private float mSpeed;
    private int mLoaderColor;
    private Paint paint;
    private float mSize;
    private int mWidth;
    private int typeFlag = 0;
    private boolean startLoading;

    private int line1_x1;
    private int line1_y1;
    private int line1_x2;
    private int line1_y2;

    private int line2_x1;
    private int line2_y1;
    private int line2_x2;
    private int line2_y2;


    public SimpleLoader(Context context) {
        this(context,null,0);
    }

    public SimpleLoader(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SimpleLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
        if(null!=attrs){
            TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.SimpleLoader);

            mLoadingText = a.getString(R.styleable.SimpleLoader_text);
            mSpeed = a.getFloat(R.styleable.SimpleLoader_speed,0.5f);
            mLoaderColor = a.getColor(R.styleable.SimpleLoader_loaderColor,0Xffffff);

            a.recycle();


        }

    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(mLoaderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(mWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        *
        *      水平的线 以中点为中心 顺时针旋转60度 ，逆时针旋转六十度
        *
        *
        */
        canvas.drawLine(line1_x1,line1_y1,line1_x2,line1_y2,paint);
        canvas.drawLine(line2_x1,line2_y1,line2_x2,line2_y2,paint);

        canvas.drawLine(line1_x1+100,line1_y1,line1_x2+100,line1_y2,paint);
        canvas.drawLine(line2_x1+100,line2_y1,line2_x2+100,line2_y2,paint);

        canvas.drawLine(line1_x1+200,line1_y1,line1_x2+200,line1_y2,paint);
        canvas.drawLine(line2_x1+200,line2_y1,line2_x2+200,line2_y2,paint);



    }


    public void loading(){
        startLoading =!startLoading;
        new PaintingThread().start();

    }



    public String getLoadingText() {
        return mLoadingText;
    }

    public void setLoadingText(String mLoadingText) {
        this.mLoadingText = mLoadingText;
    }

    public float getSpeed() {
        return mSpeed;
    }

    public void setSpeed(float mSpeed) {
        this.mSpeed = mSpeed;
    }

    public int getColor() {
        return mLoaderColor;
    }

    public void setColor(int mColor) {
        this.mLoaderColor = mColor;
    }

    private class PaintingThread extends Thread{
        @Override
        public void run() {
            while (startLoading){
                  switch (typeFlag % 3){
                      case 0:
                          line1_x1 = 50;
                          line1_y1 = 0;
                          line1_x2 = 0;
                          line1_y2 = 50;

                          line2_x1 = 0;
                          line2_y1 = 0;
                          line2_x2 = 50;
                          line2_y2 = 50;
                          break;
                      case 1:
                          line1_x1 = 0;
                          line1_y1 = 25;
                          line1_x2 = 50;
                          line1_y2 = 25;

                          line2_x1 = 0;
                          line2_y1 = 25;
                          line2_x2 = 50;
                          line2_y2 = 25;
                          break;
                      case 2:
                          line1_x1 = 0;
                          line1_y1 = 0;
                          line1_x2 = 50;
                          line1_y2 = 50;

                          line1_x1 = 50;
                          line1_y1 = 0;
                          line1_x2 = 0;
                          line1_y2 = 50;
                          break;
                      default:
                          break;
                  }
                try {
                    Thread.sleep((long)(mSpeed*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                typeFlag++;
                postInvalidate();
            }
        }
    }

}
