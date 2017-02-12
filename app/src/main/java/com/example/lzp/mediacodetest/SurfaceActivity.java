package com.example.lzp.mediacodetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by lzp on 2017/2/11.
 */

public class SurfaceActivity extends Activity implements SurfaceHolder.Callback, CameraWrapper.CamOpenOverCallback {
    private SurfaceView mPreview;
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_activity);
        mPreview = (SurfaceView) findViewById(R.id.surface);
        mPreview.getHolder().addCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CameraWrapper.getInstance().doOpenCamera(SurfaceActivity.this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;
        CameraWrapper.getInstance().doStartPreview(holder, 0);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mHolder = null;
        CameraWrapper.getInstance().doStopCamera();
    }

    @Override
    public void cameraHasOpened() {
        if (mHolder != null) {
            CameraWrapper.getInstance().doStartPreview(mHolder, 0);
        }
    }
}
