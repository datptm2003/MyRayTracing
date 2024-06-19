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
            "attribute vec4 a_Position;" +
                    "uniform mat4 u_MVPMatrix;" +
                    "void main() {" +
                    "  gl_Position = u_MVPMatrix * a_Position;" +
                    "}";

    // Fragment Shader code
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 u_Color;" +
                    "void main() {" +
                    "  gl_FragColor = u_Color;" +
                    "}";

    // Number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;

    public Ray ray;

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 0f, 0f, 1f, 1.0f };

    public RayModel(Ray ray) {
        this.ray = ray;

        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();

//        Log.d("vertexCount",Integer.toString(vertexCount));

        float[] rayCoords = new float[] {
                ray.getStart()[0], ray.getStart()[1], ray.getStart()[2],
                ray.getStart()[0] + 1000*ray.getDirection()[0], ray.getStart()[1] + 1000*ray.getDirection()[1], ray.getStart()[2] + 1000*ray.getDirection()[2]
        };
        vertexCount = 3;
        // Initialize vertex byte buffer for shape coordinates

        vertexBuffer.put(rayCoords);
        vertexBuffer.position(0);


        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // Create empty OpenGL Program
        GLES20.glAttachShader(mProgram, vertexShader);   // Add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader); // Add the fragment shader to program
        GLES20.glLinkProgram(mProgram);                  // Create OpenGL program executables
    }

    public void draw(float[] mvpMatrix) {
        GLES20.glUseProgram(mProgram);

        int positionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position");
        int mvpMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");
        int colorHandle = GLES20.glGetUniformLocation(mProgram, "u_Color");

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glUniform4fv(colorHandle, 1, new float[]{0.0f, 1.0f, 0.0f, 1.0f}, 0);

        GLES20.glLineWidth(5.0f); // Set the line width to 5.0 units

        GLES20.glDrawArrays(GLES20.GL_LINES, 0, 2);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

}


