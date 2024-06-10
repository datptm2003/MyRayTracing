package com.example.myraytracing;

import android.opengl.GLES30;
import android.opengl.Matrix;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Model {
    protected FloatBuffer vertexBuffer;
//    protected FloatBuffer normalBuffer;
    protected int vertexCount;
    protected int program;

    protected int positionHandle;
//    protected int normalHandle;
    protected int mvpMatrixHandle;
    protected int modelMatrixHandle;

    public float[] modelMatrix = new float[16];

    protected final String VERTEX_SHADER_CODE =
            "uniform mat4 uMVPMatrix;\n" +
            "uniform mat4 uModelMatrix;\n" +
            "in vec4 aPosition;\n" +
            "in vec3 vNormal;\n" +
            "void main() {\n" +
            "  gl_Position = uMVPMatrix * uModelMatrix * aPosition;\n" +
            "}";

    protected final String FRAGMENT_SHADER_CODE =
            "precision mediump float;\n" +
            "out vec4 flagColor;\n" +
            "void main() {\n" +
            "  fragColor = vec4(1.0,1.0,1.0, 1.0);\n" +
            "}";

    public Model() {
        initOpenGL();
    }

    protected void initOpenGL() {
        int vertexShader = loadShader(GLES30.GL_VERTEX_SHADER, VERTEX_SHADER_CODE);
        int fragmentShader = loadShader(GLES30.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);

        program = GLES30.glCreateProgram();
        GLES30.glAttachShader(program, vertexShader);
        GLES30.glAttachShader(program, fragmentShader);
        GLES30.glLinkProgram(program);

        GLES30.glUseProgram(program);

        positionHandle = GLES30.glGetAttribLocation(program, "aPosition");
//        normalHandle = GLES30.glGetAttribLocation(program, "aNormal");
        mvpMatrixHandle = GLES30.glGetUniformLocation(program, "uMVPMatrix");
        modelMatrixHandle = GLES30.glGetUniformLocation(program, "uModelMatrix");
    }
    public void draw(float[] mvpMatrix) {}
    protected int loadShader(int type, String shaderCode) {
        int shader = GLES30.glCreateShader(type);
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);
        return shader;
    }

    public void setTranslation(float x, float y, float z) {
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, x, y, z);
    }

    public void setRotation(float angle, float x, float y, float z) {
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.rotateM(modelMatrix, 0, angle, x, y, z);
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.scaleM(modelMatrix, 0, scaleX, scaleY, scaleZ);
    }
}
