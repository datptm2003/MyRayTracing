package com.example.myraytracing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Sphere {
    private float[] center;
    private float radius;
    private float[] color;

    public Sphere(float radius, float[] center, float[] color) {
        this.radius = radius;
        this.center = center;
        this.color = color;
//        generateSphere(stacks, slices, radius);
    }

//    private void generateSphere(int stacks, int slices, float radius) {
//        int vertexCount = (stacks + 1) * (slices + 1);
//        vertices = new float[vertexCount * 3];
//        drawOrder = new short[stacks * slices * 6];
//
//        int vertexIndex = 0;
//        int drawIndex = 0;
//
//        for (int i = 0; i <= stacks; i++) {
//            double phi = Math.PI / 2 - i * Math.PI / stacks;
//            double cosPhi = Math.cos(phi);
//            double sinPhi = Math.sin(phi);
//
//            for (int j = 0; j <= slices; j++) {
//                double theta = j * 2 * Math.PI / slices;
//                double cosTheta = Math.cos(theta);
//                double sinTheta = Math.sin(theta);
//
//                vertices[vertexIndex++] = center[0] + (float) (radius * cosPhi * cosTheta);
//                vertices[vertexIndex++] = center[1] + (float) (radius * cosPhi * sinTheta);
//                vertices[vertexIndex++] = center[2] + (float) (radius * sinPhi);
//
//                if (i < stacks && j < slices) {
//                    int first = (i * (slices + 1)) + j;
//                    int second = first + slices + 1;
//
//                    drawOrder[drawIndex++] = (short) first;
//                    drawOrder[drawIndex++] = (short) second;
//                    drawOrder[drawIndex++] = (short) (first + 1);
//
//                    drawOrder[drawIndex++] = (short) (first + 1);
//                    drawOrder[drawIndex++] = (short) second;
//                    drawOrder[drawIndex++] = (short) (second + 1);
//                }
//            }
//        }
//
//        ByteBuffer vb = ByteBuffer.allocateDirect(vertices.length * 4);
//        vb.order(ByteOrder.nativeOrder());
//        vertexBuffer = vb.asFloatBuffer();
//        vertexBuffer.put(vertices);
//        vertexBuffer.position(0);
//
//        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
//        dlb.order(ByteOrder.nativeOrder());
//        drawListBuffer = dlb.asShortBuffer();
//        drawListBuffer.put(drawOrder);
//        drawListBuffer.position(0);
//    }
//
//    public FloatBuffer getVertexBuffer() {
//        return vertexBuffer;
//    }
//
//    public ShortBuffer getDrawListBuffer() {
//        return drawListBuffer;
//    }
//
//    public int getDrawOrderLength() {
//        return drawOrder.length;
//    }

    public float[] getCenter(){
        return this.center;
    }
    public float getRadius(){
        return this.radius;
    }
    public float[] getColor(){
        return this.color;
    }
}
