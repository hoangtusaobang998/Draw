package com.tt.dev.draw;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadService extends Service {
    private WindowManager mWindowManager;
    private View mChatHeadView;

    public ChatHeadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mChatHeadView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);


        //TODO THẦY ƠI EM NGHĨ LÀ NÓ NẰM TRONG PHẦN NÀY Ạ
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mChatHeadView, params);
//….
//….

//Drag and move chat head using user's touch action.
     //   chatHeadImage.setOnTouchListener(new View.OnTouchListener() {
//            private int lastAction;
//            private int initialX;
//            private int initialY;
//            private float initialTouchX;
//            private float initialTouchY;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        //remember the initial position.
//                        initialX = params.x;
//                        initialY = params.y;
//                        //get the touch location
//                        initialTouchX = event.getRawX();
//                        initialTouchY = event.getRawY();
//                        lastAction = event.getAction();
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        //As we implemented on touch listener with ACTION_MOVE,
//                        //we have to check if the previous action was ACTION_DOWN
//                        //to identify if the user clicked the view or not.
//                        if (lastAction == MotionEvent.ACTION_DOWN) {
//
//                        }
//                        lastAction = event.getAction();
//                        return true;
//                    case MotionEvent.ACTION_MOVE:
//                        //Calculate the X and Y coordinates of the view.
//                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
//                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
//                        //Update the layout with new X & Y coordinate
//                        mWindowManager.updateViewLayout(mChatHeadView, params);
//                        lastAction = event.getAction();
//                        return true;
//                }
//                return false;
//
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatHeadView != null) mWindowManager.removeView(mChatHeadView);

    }

    public static final Dialog createDialogWindows(int id_DIALOG, Context context) {
        Dialog dialog = new Dialog(context);
        ViewGroup.MarginLayoutParams paramsOpenTouch;
        View viewTouchDialog;
        viewTouchDialog = LayoutInflater.from(context).inflate(R.layout.abc, null);
        dialog.setContentView(viewTouchDialog);
        paramsOpenTouch = (ViewGroup.MarginLayoutParams) viewTouchDialog.getLayoutParams();
        paramsOpenTouch.width = ViewGroup.LayoutParams.MATCH_PARENT;
        paramsOpenTouch.height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);

        return dialog;
    }
}
