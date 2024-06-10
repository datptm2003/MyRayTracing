package com.example.myraytracing;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    public GLSurfaceView glSurfaceView;
//    public static boolean inputAcceptance = false;

    private SphereRayIO sphereRayView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);

        sphereRayView = new SphereRayIO(this);
        setContentView(sphereRayView);

        // Set the Renderer for drawing on the GLSurfaceView
//        glSurfaceView.setEGLContextClientVersion(2);
//        glSurfaceView.setRenderer(new GLRenderer());
        Log.d("surface", String.valueOf(glSurfaceView));
        sphereRayView.getGenerateButton().setOnClickListener(v -> {
            glSurfaceView.setEGLContextClientVersion(2);
            glSurfaceView.setRenderer(new GLRenderer(sphereRayView.getSphere(),sphereRayView.getRay()));
//            Log.d("surface", String.valueOf(sphereRayView.getSphere()));
            setContentView(glSurfaceView);
        });
//        if (inputAcceptance) {
//            setContentView(glSurfaceView);
//        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        glSurfaceView.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        glSurfaceView.onResume();
//
//
//    }
}
