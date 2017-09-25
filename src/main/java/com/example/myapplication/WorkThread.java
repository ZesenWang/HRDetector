package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by wangz on 2017/8/8.
 */

public class WorkThread extends Thread {
    SurfaceHolder holder;
    WorkThread(SurfaceHolder holder) {
        this.holder = holder;
    }
    @Override
    public void run() {
        super.run();

        Paint paint = new Paint();
        paint.setColor(0xff000000);
        paint.setStrokeWidth(5f);

        int currentX = 1, currentY = 1, lastX = 0, lastY = 0;
        while (true){
            Canvas canvas = holder.lockCanvas();
            canvas.drawLine(lastX, lastY, currentX, currentY, paint);
            holder.unlockCanvasAndPost(canvas);
            lastX = currentX;
            lastY = currentY;
            currentX++;
            currentY++;
            try {
                this.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
