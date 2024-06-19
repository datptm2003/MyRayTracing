package com.example.myraytracing;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SphereGLRenderer implements GLSurfaceView.Renderer {

//    private RayModel rayModel;
//    private SphereModel sphereModel;
    private Ray ray;
    private Sphere sphere;
    private float[] mViewMatrix = new float[16];
    private float[] mProjectionMatrix = new float[16];
    private float[] mModelMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    private SphereRayIntersection intersection;

    public SphereGLRenderer(Sphere sphere, Ray ray) {
//        sphereModel = new SphereModel(sphere);
//        rayModel = new RayModel(ray);
        this.sphere = sphere;
        this.ray = ray;
//        Log.d("testSphere",Float.toString(sphere.getRadius()));
        intersection = new SphereRayIntersection(sphere, ray);
        intersection.solve();
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

//        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

//        float[] start = {0.0f, 0.0f, 0.0f};     // start point of ray
//        float[] direction = {0.0f, 1.0f, 1.0f}; // vector d
//        float radius = 1.0f;                    // sphere's radius
//        float[] center = {0.0f, 0.0f, 0.0f};    // sphere's center
//        rayModel = new RayModel(new Ray(start, direction));
//        sphereModel = new SphereModel(new Sphere(radius, center, new float[]{1.0f,1.0f,1.0f,1.0f}));
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0.0f, 8.0f, 0, 0, 0, 0, 1, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

//        int viewMatrixHandle = GLES20.glGetUniformLocation(rayModel.mProgram, "u_ViewMatrix");
//
//        GLES20.glUniformMatrix4fv(viewMatrixHandle, 1, false, mViewMatrix, 0);
        SphereModel sphereModel = new SphereModel(sphere);
        RayModel rayModel = new RayModel(ray);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
        sphereModel.draw(mMVPMatrix);
//        Log.d("b",Float.toString(intersection.b));
//        Log.d("c",Float.toString(intersection.c));
//        Log.d("point0",Float.toString(intersection.sol()[0]));
//        Log.d("point1",Float.toString(intersection.sol()[1]));
//        Log.d("point2",Float.toString(intersection.sol()[2]));
        SphereModel point1 = new SphereModel(new Sphere(0.04f,intersection.sol()[0],new float[]{1.0f,0f,0f,1.0f}));
        SphereModel point2 = new SphereModel(new Sphere(0.04f,intersection.sol()[1],new float[]{1.0f,0f,0f,1.0f}));
        point1.draw(mMVPMatrix);
        point2.draw(mMVPMatrix);
        rayModel.draw(mMVPMatrix);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        float near = 1.0f;
        float far = 10.0f;
        Matrix.frustumM(mProjectionMatrix, 0,-ratio, ratio, -1, 1, near, far);
    }


}
