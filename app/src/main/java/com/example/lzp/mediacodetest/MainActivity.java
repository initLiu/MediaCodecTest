package com.example.lzp.mediacodetest;

import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;

public class MainActivity extends AppCompatActivity implements CameraWrapper.CamOpenOverCallback, TextureView.SurfaceTextureListener {

    private TextureView mPreview;
    private SurfaceTexture mSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreview = (TextureView) findViewById(R.id.preview);
        mPreview.setSurfaceTextureListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CameraWrapper.getInstance().doOpenCamera(MainActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.e("Test", "onSurfaceTextureAvailable");
        mSurface = surface;
        CameraWrapper.getInstance().doStartPreview(mSurface, 0);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.e("Test", "onSurfaceTextureSizeChanged");
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Log.e("Test", "onSurfaceTextureDestroyed");
        CameraWrapper.getInstance().doStopCamera();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Log.e("Test", "onSurfaceTextureUpdated");
    }

    @Override
    public void cameraHasOpened() {
        Log.e("Test", "cameraHasOpened");
        if (mSurface != null) {
            CameraWrapper.getInstance().doStartPreview(mSurface, 0);
        }
    }
}
