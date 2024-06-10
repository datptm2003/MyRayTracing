package com.example.myraytracing;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RayModel {

    private FloatBuffer vertexBuffer;
    final int mProgram;
    private int positionHandle;
    private int colorHandle;
    private int vertexCount;

    // Vertex Shader code
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    // Fragment Shader code
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    // Number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;

    public Ray ray;

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 0.6f, 0.7f, 0.2f, 1.0f };

    public RayModel(Ray ray) {
        this.ray = ray;

        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();

        Log.d("vertexCount",Integer.toString(vertexCount));

        float[] rayCoords = new float[] {
                ray.start[0], ray.start[1], ray.start[2],
                ray.start[0] + 10*ray.direction[0], ray.start[1] + 10*ray.direction[1], ray.start[2] + 10*ray.direction[2]
        };
        vertexCount = rayCoords.length / 2;
        // Initialize vertex byte buffer for shape coordinates

        vertexBuffer.put(rayCoords);
        vertexBuffer.position(0);


        int vertexShader = GLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // Create empty OpenGL Program
        GLES20.glAttachShader(mProgram, vertexShader);   // Add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader); // Add the fragment shader to program
        GLES20.glLinkProgram(mProgram);                  // Create OpenGL program executables
    }

    public void draw() {
        // Add program to OpenGL environment

        GLES20.glUseProgram(mProgram);
//        Log.d("ray","ray");
        // Get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, 3,
                GLES20.GL_FLOAT, false,
                3, vertexBuffer);

        // Get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
        GLES20.glLineWidth(3.0f); // Thay đổi độ dày của đường vẽ
        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_LINES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
