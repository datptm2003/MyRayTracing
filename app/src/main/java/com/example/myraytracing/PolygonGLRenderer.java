package com.example.myraytracing;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class PolygonGLRenderer implements GLSurfaceView.Renderer {
//    private RayModel rayModel;
//    private PolygonModel polygonModel;
    private Ray ray;
    private Polygon polygon;
    private float[] intersectionSolution;
    private float[] mModelMatrix = new float[16];
    private float[] mViewMatrix = new float[16];
    private float[] mProjectionMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    public PolygonGLRenderer(Polygon polygon, Ray ray) {
//        polygonModel = new PolygonModel(polygon);
//        rayModel = new RayModel(ray);
        this.polygon = polygon;
        this.ray = ray;
//        Log.d("testSphere",Float.toString(polygon.getRadius()));
        PolygonRayIntersection intersection = new PolygonRayIntersection(polygon, ray);
//        intersection.solve();
//        Log.d("polygonIntersection",Float.toString(intersection.sol()[0]));
        intersectionSolution = intersection.sol();
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
        PolygonModel polygonModel = new PolygonModel(polygon);
        RayModel rayModel = new RayModel(ray);

        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
        polygonModel.draw(mMVPMatrix);

        if (intersectionSolution != null) {
            SphereModel point = new SphereModel(new Sphere(0.04f,intersectionSolution,new float[]{1.0f,0f,0f,1.0f}));
            point.draw(mMVPMatrix);
            rayModel.draw(mMVPMatrix);
        }

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
