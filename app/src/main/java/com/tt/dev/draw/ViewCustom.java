package com.tt.dev.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ViewCustom extends View {

    private float xDown = 0, yDown = 0, xUp = 0, yUp = 0;
    private Bitmap bm;
    Paint mPaint;
    private float x, y;
    private float offsetX, offsetY;
    boolean touched = false;

    private OnEvent onEvent;


    public void setOnEvent(OnEvent onEvent) {
        this.onEvent = onEvent;
    }

    public ViewCustom(Context context) {
        super(context);
        init(context);
    }

    public ViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public ViewCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6f);
        bm = drawableToBitmap(getResources().getDrawable(R.drawable.ic_android_black_24dp));
        offsetX = bm.getWidth() / 2;
        offsetY = bm.getHeight() / 2;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        if (touched) {
            canvas.drawRect(xDown, yDown, xUp, yUp, mPaint);
        }
        canvas.drawBitmap(bm, xUp - offsetX, yUp - offsetY, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                yDown = event.getY();
                touched = true;
                break;
            case MotionEvent.ACTION_MOVE:
                xUp = event.getX();
                yUp = event.getY();
                touched = true;
                break;
            case MotionEvent.ACTION_UP:
                xUp = event.getX();
                yUp = event.getY();
                touched = false;
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
