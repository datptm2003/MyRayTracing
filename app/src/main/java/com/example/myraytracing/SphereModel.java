package com.example.myraytracing;
import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class SphereModel {

    private FloatBuffer vertexBuffer;
    private final int mProgram;
    private int positionHandle;
    private int colorHandle;
    private int mvpMatrixHandle;

    private static final int COORDS_PER_VERTEX = 3;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "uniform mat4 uMVPMatrix;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    public Sphere sphere;

    private int numVertices;

    public SphereModel(Sphere sphere) {
        this.sphere = sphere;

        numVertices = createSphere();


        // Compile shader code
        int vertexShader = GLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        // Create OpenGL program
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    private int createSphere() {
        final int slices = 60;
        final int stacks = 60;

        float[] vertices = new float[(slices + 1) * (stacks + 1) * 3];

        int count = 0;
        for (int stackNumber = 0; stackNumber <= stacks; ++stackNumber) {
            double stackAngle = Math.PI /2 - stackNumber * Math.PI / stacks;
            double xy = sphere.getRadius() * Math.cos(stackAngle);
            float z = (float) (sphere.getRadius() * Math.sin(stackAngle) + sphere.getCenter()[2]);

            for (int sliceNumber = 0; sliceNumber <= slices; ++sliceNumber) {
                double sliceAngle = sliceNumber * 2 * Math.PI / slices;
//                double prevSliceAngle = (sliceNumber - 1) * 2 * Math.PI / slices;
                float x = (float) (xy * Math.cos(sliceAngle) + sphere.getCenter()[0]);
                float y = (float) (xy * Math.sin(sliceAngle) + sphere.getCenter()[1]);

                vertices[count++] = x;
                vertices[count++] = y;
                vertices[count++] = z;
            }
        }

        float[] newVertices = new float[(slices + 1) * (stacks + 1) * 3 * 3 * 9];
        int[] arr = {0,3,stacks,0,3,stacks+3,3,stacks,stacks+3};
        int index = 0;
        for (int i = 0; i < vertices.length; i+=3) {
            for (int k = 0; k < 9; ++k) {
                if (i + arr[k] >= vertices.length) {
                    continue;
                }
                for (int j = 0; j < 3; ++j) {
//                    Log.d("a",Integer.toString(index));
                    newVertices[index] = vertices[i + arr[k] +j];
                    index++;
                }
            }

        }

        // Initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 3 * 3 * 9 * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(newVertices);
        vertexBuffer.position(0);

        return newVertices.length / COORDS_PER_VERTEX;
    }

    public void draw(float[] mvpMatrix) {
        // Add program to OpenGL environment
        GLES20.glUseProgram(mProgram);

        // Get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // Get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, sphere.getColor(), 0);

        // Pass the projection and view transformation to the shader
        mvpMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        Log.d("sphereMat",Integer.toString(positionHandle));
        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);

        // Draw the sphere
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, numVertices);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

}

