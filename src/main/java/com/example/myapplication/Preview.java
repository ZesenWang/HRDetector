package com.example.myapplication;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by wangz on 2017/8/9.
 */

public class Preview extends SurfaceView implements SurfaceHolder.Callback {
    public static final String TAG = "myapplication2";
    SurfaceHolder mHolder;
    Camera mCamera;

    public void setCamera(Camera camera) {
        mCamera = camera;
    }
    public Preview(Context context) {
        super(context);
        init(context);
    }

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Preview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "PreviewSurfaceCreated: ");
        if (mCamera != null) {
            mCamera.release();
        }
        mCamera = Camera.open();
        ((MainActivity)getContext()).setCamera(mCamera);
        mCamera.setDisplayOrientation(90);
        mCamera.getParameters().setPreviewSize(800, 200);

        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "PreviewSurfaceDestroyed: ");
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mCamera.release();
    }
}
