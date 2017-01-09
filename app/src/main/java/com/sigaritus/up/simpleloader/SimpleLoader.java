package com.sigaritus.up.simpleloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/1/1.
 */
public class SimpleLoader extends View {
    private  int mTextSize;
    private String mLoadingText;
    private float mSpeed;
    private int mLoaderColor;
    private Paint paint;
    private int mSize;
    private int mWidth = 20;
    private int typeFlag = 0;
    private boolean startLoading;
    private String TAG = "SimpleLoader";


    private int line1_x1 ;
    private int line1_y1 ;
    private int line1_x2 ;
    private int line1_y2 ;

    private int line2_x1 ;
    private int line2_y1 ;
    private int line2_x2 ;
    private int line2_y2 ;


    public SimpleLoader(Context context) {
        this(context, null, 0);
    }

    public SimpleLoader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        setWillNotDraw(false);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleLoader);


        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SimpleLoader_text:
                    mLoadingText = a.getString(attr);
                    break;
                case R.styleable.SimpleLoader_loaderColor:
                    // 默认颜色设置为黑色
                    mLoaderColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.SimpleLoader_speed:

                    mSpeed = a.getFloat(attr, 0.5f);


                    break;
                case R.styleable.SimpleLoader_textSize:

                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

                case R.styleable.SimpleLoader_loaderSize:

                    mSize = a.getInteger(attr,100);
                    break;


            }

        }

        initPaint();
        a.recycle();


    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(mLoaderColor);
        Log.i(TAG, "initPaint: "+mLoaderColor);
        paint.setStyle(Paint.Style.STROKE);

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

        /*
        *
        *      水平的线 以中点为中心 顺时针旋转60度 ，逆时针旋转六十度
        *
        *
        */


        canvas.drawLine(line1_x1,line1_y1,line1_x2,line1_y2,paint);
        canvas.drawLine(line2_x1+mSize,line2_y1,line2_x2+mSize,line2_y2,paint);

        canvas.drawLine(line1_x1+mSize*2,line1_y1,line1_x2+mSize*2,line1_y2,paint);
        canvas.drawLine(line2_x1+mSize*3,line2_y1,line2_x2+mSize*3,line2_y2,paint);

        canvas.drawLine(line1_x1+mSize*4,line1_y1,line1_x2+mSize*4,line1_y2,paint);
        canvas.drawLine(line2_x1+mSize*5,line2_y1,line2_x2+mSize*5,line2_y2,paint);



        Log.i(TAG, "ondraw--");
    }


    public void loading(boolean flag) {
        startLoading = flag;
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

    private class PaintingThread extends Thread {
        @Override
        public void run() {
            while (startLoading) {
                switch (typeFlag % 4) {
                    case 0:
                        line1_x1 = mSize;
                        line1_y1 = 0;
                        line1_x2 = 0;
                        line1_y2 = mSize;

                        line2_x1 = 0;
                        line2_y1 = 0;
                        line2_x2 = mSize;
                        line2_y2 = mSize;
                        break;
                    case 1:
                        line1_x1 = 0;
                        line1_y1 = mSize/2;
                        line1_x2 = mSize;
                        line1_y2 = mSize/2;

                        line2_x1 = 0;
                        line2_y1 = mSize/2;
                        line2_x2 = mSize;
                        line2_y2 = mSize/2;
                        break;
                    case 2:
                        line1_x1 = 0;
                        line1_y1 = 0;
                        line1_x2 = mSize;
                        line1_y2 = mSize;

                        line2_x1 = mSize;
                        line2_y1 = 0;
                        line2_x2 = 0;
                        line2_y2 = mSize;
                        break;
                    case 3:
                        line1_x1 = 0;
                        line1_y1 = mSize/2;
                        line1_x2 = mSize;
                        line1_y2 = mSize/2;

                        line2_x1 = 0;
                        line2_y1 = mSize/2;
                        line2_x2 = mSize;
                        line2_y2 = mSize/2;
                        break;
                    default:
                        break;
                }
                try {
                    Thread.sleep((long) (mSpeed * 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                typeFlag++;

                postInvalidate();

            }
        }
    }

}
