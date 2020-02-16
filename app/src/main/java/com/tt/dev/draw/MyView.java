package com.tt.dev.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import github.nisrulz.screenshott.ScreenShott;

public class MyView extends View {
    private float xDown = 0, yDown = 0, xUp = 0, yUp = 0;

    Paint mPaint;

    boolean touched = false;

    private OnEvent onEvent;

    private View view;

    public void setOnEvent(OnEvent onEvent) {
        this.onEvent = onEvent;
    }

    public MyView(Context context) {
        super(context);
        view = new View(context);
        init(context);
        this.setDrawingCacheEnabled(true);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = new View(context);
        init(context);
        this.setDrawingCacheEnabled(true);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        view = new View(context);
        init(context);
        this.setDrawingCacheEnabled(true);
    }

    private void init(Context context) {
        view = new View(context);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6f);
        this.setDrawingCacheEnabled(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        if (touched) {
            canvas.drawRect(xDown, yDown, xUp, yUp, mPaint);
            canvas.save();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                yDown = event.getY();
                Log.e("ACTION_DOWN", "ACTION_DOWN");
                onEvent.ACTION_DOWN(xDown, yDown);
                break;
            case MotionEvent.ACTION_MOVE:
                xUp = event.getX();
                yUp = event.getY();
                touched = true;
                Log.e("ACTION_MOVE", "ACTION_MOVE");
                onEvent.ACTION_MOVE(xDown, yDown);
                break;
            case MotionEvent.ACTION_UP:
                xUp = event.getX();
                yUp = event.getY();
                touched = true;
                Log.e("ACTION_UP", "ACTION_UP");

                onEvent.ACTION_UP(xDown, yDown, getDrawingCache(), view);
                break;
        }
        invalidate();
        return true;
    }



    public interface OnEvent {

        void ACTION_DOWN(float x, float y);

        void ACTION_MOVE(float x, float y);

        void ACTION_UP(float x, float y, Bitmap bitmap, View view);

    }


}
