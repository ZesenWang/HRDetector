package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Arrays;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myapplication2";
    HRChart mHRChart;
    Camera mCamera;
    Preview mPreview;
    Button mButton;
    PhotoView mPartOfPhoto;
    LinearLayout mLinearLayout;
    HRChartSurface mSurface;
    SurfaceHolder mHolder;
    int startX, startY, stopX, stopY;
    Paint mPaint = new Paint();
    public static final int SAMPLE_RATE = 50;
    public static final int SAMPLE_INTERVAL = 20;

    Camera.PreviewCallback mPreviewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {

            int picw = camera.getParameters().getPictureSize().width;
            int pich = camera.getParameters().getPictureSize().height;
            Log.i(TAG, "onPreviewFrame: picw = "+picw + " pich = "+pich);
//            int[] pix = new int[pich * picw];
//            int[] grayScale = new int[SAMPLE_RATE*SAMPLE_RATE];
//            int[] red = new int[SAMPLE_RATE * SAMPLE_RATE];
            int[] hue = new int[SAMPLE_RATE * SAMPLE_RATE];
            //Yuv method
//            YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21, picw, pich, null);
            //Bitmap method
            //Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
//            if (yuvImage != null) {
//                Toast.makeText(MainActivity.this, "Oooooops, cannot decode image data", Toast.LENGTH_SHORT).show();
//                return;
//            }
            //bitmap.getPixels(pix, 0, picw, 0, 0, picw, pich);
            //convert raw data
            int numPixels = picw * pich;

            // Set the alpha for the image: 0 is transparent, 255 fully opaque
            final byte alpha = (byte) 255;

            // Get each pixel, one at a time
            int hueIndex = 0;
            for (int y = 0; y < SAMPLE_RATE * SAMPLE_INTERVAL; y += SAMPLE_INTERVAL) {
                for (int x = 0; x < SAMPLE_RATE * SAMPLE_INTERVAL; x += SAMPLE_INTERVAL) {
                    // Get the Y value, stored in the first block of data
                    // The logical "AND 0xff" is needed to deal with the signed issue
                    int Y = data[y*picw + x] & 0xff;

                    // Get U and V values, stored after Y values, one per 2x2 block
                    // of pixels, interleaved. Prepare them as floats with correct range
                    // ready for calculation later.
                    int xby2 = x/2;
                    int yby2 = y/2;

                    // make this V for NV12/420SP
                    float U = (float)(data[numPixels + 2*xby2 + yby2*picw] & 0xff) - 128.0f;

                    // make this U for NV12/420SP
                    float V = (float)(data[numPixels + 2*xby2 + 1 + yby2*picw] & 0xff) - 128.0f;

                    // Do the YUV -> RGB conversion
                    float Yf = 1.164f*((float)Y) - 16.0f;
                    float R = (Yf + 1.596f*V);
                    float G = (Yf - 0.813f*V - 0.391f*U);
                    float B = (Yf            + 2.018f*U);

                    // Clip rgb values to 0-255
                    R = R < 0 ? 0 : R > 255 ? 255 : R;
                    G = G < 0 ? 0 : G > 255 ? 255 : G;
                    B = B < 0 ? 0 : B > 255 ? 255 : B;
                    //calculate gray scale
                    //grayScale[y*SAMPLE_RATE + x] = (G*6 + R*3 + B)/5;
                    //only use red
                    //red[y*SAMPLE_RATE + x] = R;
                    //calculate hue, use HSV color space
                    float r = R / 255;
                    float g = G / 255;
                    float b = B / 255;
                    float max = Math.max(Math.max(r, g), b);
                    float min = Math.min(Math.min(r, g), b);
                    float delta = max - min;
                    int h;
                    if(delta == 0){
                        h = 0;
                    } else if(max == r){
                        h = (int)((((g - b) / delta) % 6) * 60);
                    } else if(max == g){
                        h = (int)((((b - r) / delta) + 2) * 60);
                    } else {
                        h = (int)((((r - g) / delta) + 4) * 60);
                    }
                    hue[hueIndex++] = h;
                    Log.i(TAG, "onPreviewFrame: r="+r+" g="+g+" b="+b+" h="+h);
                }
            }
//            int[] grayScale = new int[90000];
//            for(int h = 0; h < 300; h++) {
//                for(int w = 0; w < 300; w++) {
//                    int index = h * picw + w;
//                    int R = (pix[index] >> 16) & 0xff;     //bitwise shifting
//                    int G = (pix[index] >> 8) & 0xff;
//                    int B = pix[index] & 0xff;
//                    grayScale[h * 300 + w] = (R * 3 + G * 6 + B) / 5;
//                }
//            }
            //calculate average gray scale
//            int sum = 0;
//            for(int i: grayScale)
//                sum+=i;
//            int averageGrayScale = sum / (SAMPLE_RATE*SAMPLE_RATE);
//            stopY = averageGrayScale;
            //find median gray scale
//            Arrays.sort(grayScale);
//            stopY = grayScale[SAMPLE_RATE*SAMPLE_RATE/2];
            //calculate average red
//            int sum = 0;
//            for (int i : red) {
//                sum+=i;
//            }
//            stopY = sum / (SAMPLE_RATE * SAMPLE_RATE);
            //calculate average hue
            int sum = 0;
            for(int i: hue)
                sum += i;
            stopY = sum / (SAMPLE_RATE * SAMPLE_RATE);

            Canvas canvas = mHolder.lockCanvas();
            Log.i(TAG, "onPictureTaken: " + stopX + " " + stopY);
            canvas.drawLine(startX, startY, stopX, stopY, mPaint);
            mHolder.unlockCanvasAndPost(canvas);

            startX = stopX;
            startY = stopY;
            stopX += 15;
        }
    };

    public void setCamera(Camera camera) {
        mCamera = camera;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mHRChart = (HRChart) findViewById(R.id.HRChart);
        mPreview = (Preview) findViewById(R.id.Preview);
        mButton = (Button) findViewById(R.id.button);
        mLinearLayout = (LinearLayout) findViewById(R.id.container);
        mSurface = (HRChartSurface) findViewById(R.id.hrChartSurface);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                mHolder = mSurface.mHolder;
                if(mCamera != null)
                    mCamera.setPreviewCallback(mPreviewCallback);
            }
        });

        mPaint.setColor(0xffffffff);
        mPaint.setStrokeWidth(2);
    }

}
