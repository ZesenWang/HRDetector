package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by wangz on 2017/8/16.
 */

public class PhotoView extends View {
    int[] pixels;
    int picw, pich;
    public PhotoView(Context context, int[] pixels, int pixw, int pixh) {
        super(context);
        this.pixels = pixels;
        this.picw = pixw;
        this.pich = pixh;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        for(int h = 0; h < 300; h++) {
            for(int w = 0; w < 300; w++) {
                int index = h * picw + w;

                paint.setColor(pixels[index] | 0xff000000);
                canvas.drawPoint(w, h, paint);
            }
        }
    }
}
