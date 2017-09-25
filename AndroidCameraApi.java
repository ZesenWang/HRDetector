Please support this website by adding us to your whitelist in your ad blocker. Ads are what helps us bring you premium content! Thank you!
NOW TRENDING:
ANDROID ONLINE QUIZ WITH WEB BASED ADMIN PANEL – PROJECT IDEA SOURCE CODE FOR STUDENTS
ANDROID MOBILE ECOMMERCE ONLINE SHOPPING APP – PROJECT IDEA SOURCE CODE
USING BRAINTREE PAYMENT IN ANDROID TO ACCEPT PAYMENT
ANDROID CAR RENTAL APP WITH WEB ADMIN PANEL – PROJECT IDEA SOURCE CODE FOR STUDENTS
  
InduceSmile - Android Tutorial, Android Apps, Android Studio, Android SDK, Android Development
ANDROIDIOS (SWIFT)HYBRID MOBILE TIPSMOBILE APP PROJECT IDEASPROGRAMMING HELPBUY SOURCE CODE
 HomeAndroidAndroid Camera2 API Example Tutorial
ANDROID CAMERA2 API EXAMPLE TUTORIAL
In this tutorial, we are going to learn how to implement android camera 2 API. This example tutorial will focus on the new android camera api, if you are looking for the old camera api then I will suggest you read my previous tutorial on android camera api tutorial.

If you have worked with android camera before and then you want to try your hand in android camera2 API introduce in android API level 21 you will understand how difficult it is to understand how to use and implement android camera2 api in your app.

It replaces the deprecated Camera class

The sample code project by Google for the android camera2 API is also intimidating for beginner android developers.

One of the advantages of the android camera2 API is the amount control and addition features you can use to capture and manipulate your captured images.

If you want to read more about android camera2 api, I will suggest you go to the documentation. I also found an interesting article that explain the detail architecture of android camera 2 api.

According to android guide – “This package models a camera device as a pipeline, which takes in input requests for capturing a single frame, captures the single image per the request, and then outputs one capture result metadata packet, plus a set of output image buffers for the request. The requests are processed in-order, and multiple requests can be in flight at once. Since the camera device is a pipeline with multiple stages, having multiple requests in flight is required to maintain full framerate on most Android devices.”

THE FOLLOWING STEPS ARE TAKEN WHEN USING ANDROID CAMERA2 API
1. The android CameraManager class is used to manage all the camera devices in our android device

2. Each camera device has a range of properties and settings that describe the device. It can be obtained through the camera characteristics.

3. To capture or stream images from a camera device, the application must first create a camera capture session

4. The camera capture needs a surface to output what has been captured or being previewed. A target Surface can be obtained from a variety of classes, including SurfaceView, SurfaceTexture via Surface(SurfaceTexture), MediaCodec, MediaRecorder, Allocation, and ImageReader

5. The application then needs to construct a CaptureRequest, which defines all the capture parameters needed by a camera device to capture a single image.

6. Once the request has been set up, it can be handed to the active capture session either for a one-shot capture or for an endlessly repeating use

7. After processing a request, the camera device will produce a TotalCaptureResult object, which contains information about the state of the camera device at time of capture, and the final settings used.

Since we are working with android camera2 API introduced in level 21, our minimum android SDK for this project will be 21.

Before we dive into more details, it is important for us to understand what we are planning to achieve. Below is the screen-shot of the application we will be creating.

android camera2

Lets start to soil our hands in code. Start up your IDE. For this tutorial, I am using the following tools and environment, feel free to use what works for you.

Windows 7

Android Studio

Sony Xperia XA Dual

Min SDK 21

Target SDK 23

To create a new android application project, follow the steps as stipulated below.

Go to File menu

Click on New menu

Click on Android Application

Enter Project name: AndroidCamera2API

Package: com.inducesmile.androidcamera2api

Select Blank Activity

Name your activity : AndroidCamera2API

Keep other default selections

Continue to click on next button until Finish button is active, then click on Finish Button.

ANDROID PERMISSIONS

For us to take a picture and save it in our device external storage, we will add android permission for camera2 and for write access in our project Manifest.xml file.

Open the Manifest.xml file located in Manifest folder. Add the code below to the file.

package="com.inducesmile.androidcameraapi2"
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.inducesmile.androidcameraapi2">
    <uses-sdk
        android:minSdkVersion="21"
        android:targetSdkVersion="21" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-feature android:name="android.hardware.camera2.full" />
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".AndroidCameraApi">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
These two permissions will be requested when the app is installed for android device running on android versions less that 6 while android version 6 and later versions will require it as a run-time permission.

STRINGS.XML

We are going to update our project strings.xml file located in the values folder inside the res folder. Open the file and add the code below to it.

<resources>
    <string name="app_name">Android Camera API 2</string>
    <string name="take_picture">Take picture</string>
</resources>
COLORS.XML

Open the colors.xml file in the same location as the strings.xml file and add the code below to the file.

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#3F51B5</color>
    <color name="colorPrimaryDark">#303F9F</color>
    <color name="colorAccent">#FF4081</color>
</resources>
ACTIVITY_ANDROID_CAMERA_API.XML

Open the main layout file of our project. Here we are going to use two View controls – a TextureView and Button widgets. The TextureView will be used as the output surface for the camera while the Button widget is used to capture images.

Open the layout file and add the below code inside the file.

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.inducesmile.androidcameraapi2.AndroidCameraApi">
    <TextureView
        android:id="@+id/texture"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_above="@+id/btn_takepicture"
        android:layout_alignParentTop="true"/>
    <Button
        android:id="@+id/btn_takepicture"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="16dp"
        android:layout_marginTop="16dp"
        android:text="@string/take_picture" />
</RelativeLayout>
ANDROIDCAMERAAPI ACTIVITY PAGE

In the Activity class, we will first get the instances of the View controls. We will follow the steps we have described above. Open the AndroidCameraApi java file and add the codes below.

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AndroidCameraApi extends AppCompatActivity {
    private static final String TAG = "AndroidCameraApi";
    private Button takePictureButton;
    private TextureView textureView;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }
    private String cameraId;
    protected CameraDevice cameraDevice;
    protected CameraCaptureSession cameraCaptureSessions;
    protected CaptureRequest captureRequest;
    protected CaptureRequest.Builder captureRequestBuilder;
    private Size imageDimension;
    private ImageReader imageReader;
    private File file;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private boolean mFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_camera_api);
        textureView = (TextureView) findViewById(R.id.texture);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);
        takePictureButton = (Button) findViewById(R.id.btn_takepicture);
        assert takePictureButton != null;
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //open your camera here
            openCamera();
        }
        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            // Transform you image captured size according to the surface width and height
        }
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    };
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            //This is called when the camera is open
            Log.e(TAG, "onOpened");
            cameraDevice = camera;
            createCameraPreview();
        }
        @Override
        public void onDisconnected(CameraDevice camera) {
            cameraDevice.close();
        }
        @Override
        public void onError(CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice = null;
        }
    };
    final CameraCaptureSession.CaptureCallback captureCallbackListener = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            Toast.makeText(AndroidCameraApi.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
            createCameraPreview();
        }
    };
    protected void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }
    protected void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void takePicture() {
        if(null == cameraDevice) {
            Log.e(TAG, "cameraDevice is null");
            return;
        }
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes = null;
            if (characteristics != null) {
                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
            }
            int width = 640;
            int height = 480;
            if (jpegSizes != null && 0 < jpegSizes.length) {
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();
            }
            ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);
            List<Surface> outputSurfaces = new ArrayList<Surface>(2);
            outputSurfaces.add(reader.getSurface());
            outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
            final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
            // Orientation
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
            final File file = new File(Environment.getExternalStorageDirectory()+"/pic.jpg");
            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    Image image = null;
                    try {
                        image = reader.acquireLatestImage();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];
                        buffer.get(bytes);
                        save(bytes);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (image != null) {
                            image.close();
                        }
                    }
                }
                private void save(byte[] bytes) throws IOException {
                    OutputStream output = null;
                    try {
                        output = new FileOutputStream(file);
                        output.write(bytes);
                    } finally {
                        if (null != output) {
                            output.close();
                        }
                    }
                }
            };
            reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
            final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    Toast.makeText(AndroidCameraApi.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
                    createCameraPreview();
                }
            };
            cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        session.capture(captureBuilder.build(), captureListener, mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    protected void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                            //The camera is already closed
                            if (null == cameraDevice) {
                                return;
                            }
                            // When the session is ready, we start displaying the preview.
                            cameraCaptureSessions = cameraCaptureSession;
                            updatePreview();
                        }
                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                            Toast.makeText(AndroidCameraApi.this, "Configuration change", Toast.LENGTH_SHORT).show();
                        }
                    }, null);
            } catch (CameraAccessException e) {
                 e.printStackTrace();
            }
    }
    private void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        Log.e(TAG, "is camera open");
        try {
            cameraId = manager.getCameraIdList()[0];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            // Add permission for camera and let user grant the permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AndroidCameraApi.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "openCamera X");
    }
    protected void updatePreview() {
        if(null == cameraDevice) {
            Log.e(TAG, "updatePreview error, return");
        }
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        try {
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private void closeCamera() {
        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }
        if (null != imageReader) {
            imageReader.close();
            imageReader = null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
           if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(AndroidCameraApi.this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
           }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        startBackgroundThread();
        if (textureView.isAvailable()) {
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        //closeCamera();
        stopBackgroundThread();
        super.onPause();
    }
}
This brings us to the end of this tutorial. I hope that you have learn something. The next tutorial will be on Android RecyclerView Infinite Scroll.

You can download the code for this tutorial below. If you are having hard time downloading the tutorials, kindly contact me.

Remember to subscribe with your email address so that you will be among the first to receive my new android blog post once it is published.

Please if you love this tutorial, kindly download my android app – Complete Mathematics – in Google Play Store and let me know what you think about it.

OTHER INTERESTING POSTS:

Android GridView Vs GridLayout Example Tutorial
How to get Android RAM, Internal and External Memory Information
Android RecyclerView Filter Search Functionality with Search Suggest
Android MapView Example Tutorial
Android SQLite Database Example Tutorial
Android DataBinding Example Tutorial
Tweet Pin It
ABOUT THE AUTHOR

Henry
Henry
I learn and write about Android, iOS, Slim, Node.js, Angular 2, React Native, Ionic and Framework7
 profile for Inducesmile at Stack Overflow, Q&A for professional and enthusiast programmers
41 COMMENTS


dinu May 13, 2016 Log in to Reply
Hi,
First we have to say Thank you .It’s working fine in API 21 & above.
Error occurs in API 21 below. We are excepting the code working for API 18 to latest version .
Do needful . Please share your valuable comments or feedback.
View Comment
Henry
Henry May 13, 2016 Log in to Reply
Android Camera2 API was introduced in android API 21 so if you are supporting older API, you can check the device version for implementation.
View Comment

techronium July 13, 2016 Log in to Reply
Hi Henry, love your blog! I have a question, is it possible to record a live stream video with overlay static PNG graphics or animated graphics using Android’s Camera2 API?
Or, is it better to stick with Camera(1) API?
View Comment
Henry
Henry July 14, 2016 Log in to Reply
I think it can be achieved. But working with Camera2 API is a little challenging than Camera API. You need to understand all the classes that come with it. When I have time I might try to write a tutorial on this area. Thanks for your kind words.
View Comment

MacZZ July 21, 2016 Log in to Reply
Hey, thanks for such a useful tutorial. I was wondering that if i wanted to present the image in a new screen, instead of storing it in the phone, how would I access the image to present it. Also, do you have any similar tutorials for recording videos. Thanks a lot
View Comment
Henry
Henry July 22, 2016 Log in to Reply
Thanks. You can go through my android tutorial here https://inducesmile.com/android/using-android-horizontalscrollview-to-create-android-horizontal-carousel/ to see how you can achieve it. I have not written a tutorial on recording video with Camera2 API but I have written a video tutorial before just search my blog.
View Comment

piya August 4, 2016 Log in to Reply
Hello Henry,I really loved you blog! But i am requesting you to add blog which include both click image and capture video using camera2 API.Waiting for your reply and blog too :).
Thank you
View Comment
Henry
Henry August 4, 2016 Log in to Reply
Thanks for your kind words. I will publish it soon. Just keep visiting.
View Comment

Raji August 5, 2016 Log in to Reply
Hi Henry. Thank you so much for your tutorial. Please tell me how to find the distance of the object using camera 2 api.
View Comment

jr August 9, 2016 Log in to Reply
Thank you so much for this camera tutorial 🙂 looking forward to your other tutorials. it is very detailed and well written . thx for ur efforts
View Comment
Henry
Henry August 9, 2016 Log in to Reply
Glad it is of help to you.
View Comment

jr August 9, 2016 Log in to Reply
i would be very grateful if you could include a flash button toggle. Ive been working around this and I cannot seem to figure it out 🙂
CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
manager.setTorchMode(cameraId, true);
seems to only toggle the flash for one second .
thank you
View Comment

scallejo September 18, 2016 Log in to Reply
Great tutorial. Do you have an example for using Camera 2 API to record video ?
View Comment
Henry
Henry September 18, 2016 Log in to Reply
I will publish a post on Camera2 API for Video Recording soon
View Comment

LavanArul September 30, 2016 Log in to Reply
Hi Henry,
I followed your tutorials.It was very easy to learn.I got this error when i run above code.
Caused by: java.lang.NullPointerException: Attempt to invoke virtual method ‘void android.view.TextureView.setSurfaceTextureListener(android.view.TextureView$SurfaceTextureListener)’ on a null object reference
Please help me to resolve this
View Comment
Henry
Henry October 1, 2016 Log in to Reply
It works perfectly with different devices I used to test it. Please can you test it in real device because at the end, the application will run on real device not in emulator. Kindly get back to me with the result.
View Comment

Elia October 14, 2016 Log in to Reply
hi henry thanks a lot for this benificial tutorial.
i need your help with something. can i add a burst node to this code (lets say take 20 pictures as fast as possible and save them as “pic”+counter+”.jpg”)
i tried adding a for loop in the takepicture() method. but the program froze, saved 3 pictures (pic 1, pic 2 and pic 9 (??) then it crashed.
thank in advance for your help
View Comment
Henry
Henry October 14, 2016 Log in to Reply
Hello Elia, I will look into your question and get back to you.
View Comment

Ahmet Urun October 14, 2016 Log in to Reply
Hello Henry,
Thanks for the tutorial. I need to take picture from both front and back camera at the same time. I know this is not allowed at the deprecated camera API. Could you tell me is it possible with the camera2 API. Thanks again.
View Comment

Nour October 18, 2016 Log in to Reply
Hi there,
I have followed your tutorial, it works fine except I keep getting this exception whenever I return to AndroidCameraApi activity.
java.lang.NullPointerException: Attempt to invoke virtual method ‘void android.hardware.camera2.CameraDevice.close()’ on a null object reference
View Comment
Henry
Henry October 18, 2016 Log in to Reply
It is hard to know where the issues is from. you can send the error logcat for better understand.
View Comment

sanidhya November 13, 2016 Log in to Reply
how to switch between photo and video in the same camera view?
Also can you please provide the download link for the above code.
View Comment
Henry
Henry November 13, 2016 Log in to Reply
Video tutorial is coming soon with Android Camera2 API. Thanks for coming around
View Comment

johnnie November 25, 2016 Log in to Reply
Hi Henry! It’s a great tutorial. A huge problem is making a FULL SCREEN preview (and save the image that way).
Do you have any advice. It’s really a struggle getting a full screen preview. I’ve tried everything.
I look through all the preview sizes, and find the correct one – but it just doesn’t seem to work :/
Any help is really appreciated. thanks!!
View Comment

Ka Illescas January 23, 2017 Log in to Reply
Hi Henry! Thank you for this tutorial! I have one question, in theory the image taken must be saved in the phone? Because in my phone doesn’t save the image in the gallery, is that okay?
Thanks!
View Comment
Henry
Henry January 25, 2017 Log in to Reply
It will save the captured image in your device. Please use a File manager app and check all you internal folders to see if it is saved in any one of them
View Comment

devYasin January 26, 2017 Log in to Reply
please i want to open front camera!
what should i change in this code?
View Comment
Henry
Henry January 26, 2017 Log in to Reply
You can change this code from
cameraId = manager.getCameraIdList()[0];
to
cameraId = manager.getCameraIdList()[1];
Let me know if it works for you
View Comment

devYasin January 26, 2017 Log in to Reply
yes thank you so much for help!!
View Comment

Skittles January 28, 2017 Log in to Reply
Mine Doesn’t Work
unfortunately camera stopped working
View Comment

LED January 28, 2017 Log in to Reply
Hi Henry!
It’s a nice tutorial, thanks!
(The second on the Google results page 🙂 )
I’ve played some with the camera 2 API around, but I cannot figure out the purpose of the background thread. Is that really necessary?
View Comment

kunal bishnoi February 5, 2017 Log in to Reply
only one image is saved.
how to save multiple photo.
View Comment

devnull69 February 21, 2017 Log in to Reply
Hi there,
I like this tutorial very much, but using the camera2 API compared to camera1 is a mess for me.
Following your tutorial exactly (copy/paste) leads to black pictures every now and then. It seems to be connected to phone orientation somehow. Portrait pictures are almost always black, whereas landscape pictures are mostly ok … can you help me with this?
View Comment
Henry
Henry February 22, 2017 Log in to Reply
I did not experience the Black background color when I wrote the tutorial. I will look at it once more to see what might be the issue. Thanks
View Comment

devnull69 February 23, 2017 Log in to Reply
Hi there, and thank you for your feedback
Would you try to add a random string to the filename so that you can see the result of every single “Take Photo” attempt of one app session afterwards?
Doing this I can see that almost 50% of the images are completely black…
I’m looking forward to seeing your results
Thanks!
View Comment

tetxi March 8, 2017 Log in to Reply
Hello Henry!
Followed your steps to build a camera in a second activity (main activity has a push button to enter camera). It is working but with an issue I hope you know how to solve!
After taking and storing the picture, it stays in the screen for a few seconds and then it quits the activity, going back to the main activity.
Instead, I wanted that after the picture was saved it would reopen the camera preview and not the main activity (so that I don’t have to press ‘Go to Camera’ button before taking another picture).
How do I implement this with your methods?
Thank you in advance!
tetxi
View Comment

Ruben May 3, 2017 Log in to Reply
Hi Henry,
The program works perfectly thank you.
But the tutorial does not teach a man to fish, it only gives me a fish 😉
I was hoping you could point me in the direction of a book / resource which I can work through to teach myself how to do this… since I do not believe in just copy / paste work which I do not fully understand. I have done extensive searching on the web, but most resources that I have come across are a “closed buy” where I do not fully know if the resource itself will be of good quality and in turn, much help.
Any help would be much appreciated…
Cheers and Beers \m/
View Comment

Shashwat May 4, 2017 Log in to Reply
Hey Henry,
Thanks for the great tutorial.
Though have I met an issue, I am not able to figure out, there is some issue with the screen size of different devices that “camera device” is not able to initiate a capture session and onConfigureFailed is being called instead of onConfigured.
help is appreciated
Thanks
View Comment

talalz94 June 17, 2017 Log in to Reply
Hello,
Awesome tutorial Henry. I have gained a lot of insight into the camera2 API and your code is working on my device. However the dimensions are a little messed up dont you think? The image is a bit squished in the Y-Axis. Do you have a possible fix for that?
Thanks again buddy!
View Comment

dzulfi June 19, 2017 Log in to Reply
hey henry!
thanks for the tutorial, its relly works! 😀
but, i cant attach the picture that i take with the apps 🙁
how can i fix it?
https://ibb.co/hQ1xA5
thanks for help
View Comment

Confused June 27, 2017 Log in to Reply
As others have pointed out I am seeing black pictures when I take a picture. The preview either front facing or back facing camera looks fine. But I am unable to capture the image from the preview. It’s always black.
What must I do to fix it? Can I send you my code?
Thank you.
View Comment
ADD A COMMENT

You must be logged in to post a comment.

 
Search the site

 
EMAIL SUBSCRIPTION

Enter your email address:



Subscribe
Delivered by FeedBurner

LIKE US ON FACEBOOK

RECENT POSTS

Android Car Rental App with Web Admin Panel – Project Idea Source Code For Students
Android Online Quiz With Web Based Admin Panel – Project Idea Source Code for Students
Android Mobile Ecommerce Online Shopping App – Project Idea Source Code
Day 9 of 100 Days Android App Development Challenge
Day 8 of 100 Days Android App Development Challenge for Beginners
Android – RecyclerView inside NestedScrollView Example
Using Braintree Payment in Android to accept Payment
Android – How to Strike Through or Cross out a Text in Android
Android GraphView – How to Plot Graph from CSV File in Android using GraphView
Android – How to read CSV file from Remote Server or Assets Folder in Android
INDUCESMILE – ANDROID TUTORIAL, ANDROID APPS, ANDROID STUDIO, ANDROID SDK, ANDROID DEVELOPMENT COPYRIGHT © 2017.
PROGRAMMING HELPANDROID TUTORIALSCONTACT US
This site uses cookies: Find out more.
Okay, thanks
