package com.example.myraytracing;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class InterpolationGLRenderer implements GLSurfaceView.Renderer {
    private Ray[] rayList;
    private float[] point;
    private Ray interpolationRay;
    private float[] mModelMatrix = new float[16];
    private float[] mViewMatrix = new float[16];
    private float[] mProjectionMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    public InterpolationGLRenderer(Ray[] rayList, float[] point) {
        this.rayList = new Ray[rayList.length];
        for (int i = 0; i < rayList.length; ++i) {
            this.rayList[i] = rayList[i];
        }
        this.point = point;

        Interpolation interpolation = new Interpolation(point,rayList);
        interpolationRay = interpolation.solve();
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

//        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0.0f, 8.0f, 0, 0, 0, 0, 1, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

//        int viewMatrixHandle = GLES20.glGetUniformLocation(rayModel.mProgram, "u_ViewMatrix");
//
//        GLES20.glUniformMatrix4fv(viewMatrixHandle, 1, false, mViewMatrix, 0);
//        PolygonModel polygonModel = new PolygonModel(polygon);
        RayModel[] rayModelList = new RayModel[this.rayList.length];
        for (int i = 0; i < this.rayList.length; ++i) {
            rayModelList[i] = new RayModel(this.rayList[i]);
        }

        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        for (int i = 0; i < rayModelList.length; ++i) {
            rayModelList[i].draw(mMVPMatrix);
        }
        RayModel interpolationRayModel = new RayModel(interpolationRay);
        interpolationRayModel.draw(mMVPMatrix);

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
