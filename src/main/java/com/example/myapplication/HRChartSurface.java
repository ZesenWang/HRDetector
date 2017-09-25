package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by wangz on 2017/8/8.
 */

public class HRChartSurface extends SurfaceView implements SurfaceHolder.Callback {
    public static final String TAG = "myapplication2";
    WorkThread mWorkThread;
    public SurfaceHolder mHolder;

    public HRChartSurface(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public HRChartSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

    }

    public HRChartSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "HRSurfaceCreated: ");
        mHolder = surfaceHolder;
//        mWorkThread = new WorkThread(surfaceHolder);
//        mWorkThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //mWorkThread.destroy();
        Log.i(TAG, "HRSurfaceDestroyed: ");
    }
}
