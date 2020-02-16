package com.tt.dev.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import github.nisrulz.screenshott.ScreenShott;

public class MainActivity extends AppCompatActivity {

    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.myView);


        myView.setOnEvent(new MyView.OnEvent() {
            @Override
            public void ACTION_DOWN(float x, float y) {
                Log.e("X-Y", x + "-" + y);
            }

            @Override
            public void ACTION_MOVE(float x, float y) {
                Log.e("X1-Y1", x + "-" + y);
            }

            @Override
            public void ACTION_UP(float x, float y, Bitmap bitmap, View view) {
                if (bitmap != null) {
                    ((ImageView) findViewById(R.id.img)).setImageBitmap(bitmap);
                    Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_SHORT).show();
                }
                Log.e("X2-Y2", x + "-" + y);
            }
        });
    }
}
