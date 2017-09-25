package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

/**
 * Created by wangz on 2017/8/8.
 */

public class HRChart extends View {
    float mSampleHeight[] = new float[1000];

    public HRChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < 250; i++) {
            mSampleHeight[i] = i * 3;
        }
        for(int i = 250, j = 250; j > 0; i++, j--) {
            mSampleHeight[i] = j * 3;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xff000000);

        float lastX = 0, lastY = mSampleHeight[0];
        for(int i = 0; i < 500; lastX = i, lastY = mSampleHeight[i], i++) {
            canvas.drawLine(lastX, lastY, i, mSampleHeight[i], paint);
        }
    }
}
