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
    private int mSize;

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
        paint.setStrokeWidth(mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);





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

        }
    }

}
