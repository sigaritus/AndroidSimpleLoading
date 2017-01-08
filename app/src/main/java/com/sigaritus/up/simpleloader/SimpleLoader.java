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
    private  int mTitleTextSize;
    private String mLoadingText;
    private float mSpeed;
    private int mLoaderColor;
    private Paint paint;
    private float mSize;
    private int mWidth = 20;
    private int typeFlag = 0;
    private boolean startLoading;
    private String TAG = "SimpleLoader";


    private int line1_x1 = 100;
    private int line1_y1 = 0;
    private int line1_x2 = 0;
    private int line1_y2 = 100;

    private int line2_x1 = 0;
    private int line2_y1 = 0;
    private int line2_x2 = 100;
    private int line2_y2 = 100;


    public SimpleLoader(Context context) {
        this(context, null, 0);
    }

    public SimpleLoader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
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
//                    // 默认设置为16sp，TypeValue也可以把sp转化为px
//                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }

        }


        a.recycle();


    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(mLoaderColor);
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
        paint.setColor(Color.YELLOW);

        canvas.drawLine(line1_x1,line1_y1,line1_x2,line1_y2,paint);
        canvas.drawLine(line2_x1+100,line2_y1,line2_x2+100,line2_y2,paint);

        canvas.drawLine(line1_x1+200,line1_y1,line1_x2+200,line1_y2,paint);
        canvas.drawLine(line2_x1+300,line2_y1,line2_x2+300,line2_y2,paint);

        canvas.drawLine(line1_x1+400,line1_y1,line1_x2+400,line1_y2,paint);
        canvas.drawLine(line2_x1+500,line2_y1,line2_x2+500,line2_y2,paint);



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
                switch (typeFlag % 3) {
                    case 0:
                        line1_x1 = 100;
                        line1_y1 = 0;
                        line1_x2 = 0;
                        line1_y2 = 100;

                        line2_x1 = 0;
                        line2_y1 = 0;
                        line2_x2 = 100;
                        line2_y2 = 100;
                        break;
                    case 1:
                        line1_x1 = 0;
                        line1_y1 = 50;
                        line1_x2 = 100;
                        line1_y2 = 50;

                        line2_x1 = 0;
                        line2_y1 = 50;
                        line2_x2 = 100;
                        line2_y2 = 50;
                        break;
                    case 2:
                        line1_x1 = 0;
                        line1_y1 = 0;
                        line1_x2 = 100;
                        line1_y2 = 100;

                        line1_x1 = 100;
                        line1_y1 = 0;
                        line1_x2 = 0;
                        line1_y2 = 100;
                        break;
                    default:
                        break;
                }
                try {
                    Thread.sleep((long) (mSpeed * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                typeFlag++;
//                Log.i(TAG,typeFlag+"--");

                postInvalidate();

            }
        }
    }

}
