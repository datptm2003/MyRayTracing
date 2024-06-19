package com.example.myraytracing;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    public GLSurfaceView glSphereSurfaceView;
    public GLSurfaceView glPolygonSurfaceView;
    public GLSurfaceView glInterpolationSurfaceView;
//    public static boolean inputAcceptance = false;

    private SphereRayIO sphereRayView;
    private PolygonRayIO polygonRayView;
    private InterpolationIO interpolationView;
    private OptionView optionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSphereSurfaceView = new GLSurfaceView(this);
        glPolygonSurfaceView = new GLSurfaceView(this);
        glInterpolationSurfaceView = new GLSurfaceView(this);

        sphereRayView = new SphereRayIO(this);
        polygonRayView = new PolygonRayIO(this);
        interpolationView = new InterpolationIO(this);
        optionView = new OptionView(this);
        setContentView(optionView);

        optionView.getGenerateSphereButton().setOnClickListener(v -> {
            setContentView(sphereRayView);
        });
        optionView.getGeneratePolygonButton().setOnClickListener(v -> {
            setContentView(polygonRayView);
        });
        optionView.getGenerateInterpolationButton().setOnClickListener(v -> {
            setContentView(interpolationView);
        });

//        Log.d("surface", String.valueOf(glSurfaceView));
        sphereRayView.getGenerateButton().setOnClickListener(v -> {
            glSphereSurfaceView.setEGLContextClientVersion(2);
            glSphereSurfaceView.setRenderer(new SphereGLRenderer(sphereRayView.getSphere(),sphereRayView.getRay()));
            setContentView(glSphereSurfaceView);
        });

        polygonRayView.getGenerateButton().setOnClickListener(v -> {
            glPolygonSurfaceView.setEGLContextClientVersion(2);
            glPolygonSurfaceView.setRenderer(new PolygonGLRenderer(polygonRayView.getPolygon(),polygonRayView.getRay()));
            setContentView(glPolygonSurfaceView);
        });

        interpolationView.getGenerateButton().setOnClickListener(v -> {
            glInterpolationSurfaceView.setEGLContextClientVersion(2);
            glInterpolationSurfaceView.setRenderer(new InterpolationGLRenderer(interpolationView.getRayList(), interpolationView.getVertex()));
            setContentView(glInterpolationSurfaceView);
        });
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
