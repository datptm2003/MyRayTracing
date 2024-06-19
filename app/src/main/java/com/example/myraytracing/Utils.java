package com.example.myraytracing;

public class Utils {
    public static float dotProduct(float[] vec1, float[] vec2) {
        return vec1[0]*vec2[0] + vec1[1]*vec2[1] + vec1[2]*vec2[2];
    }
    public static float[] crossProduct(float[] vec1, float[] vec2) {
        float[] crossProduct = new float[3];
        crossProduct[0] = vec1[1] * vec2[2] - vec1[2] * vec2[1];
        crossProduct[1] = vec1[2] * vec2[0] - vec1[0] * vec2[2];
        crossProduct[2] = vec1[0] * vec2[1] - vec1[1] * vec2[0];

        return crossProduct;
    }

    public static float[] subtract(float[] vec1, float[] vec2) {
        return new float[]{vec1[0] - vec2[0], vec1[1] - vec2[1], vec1[2] - vec2[2]};
    }

    public static float[] add(float[] vec1, float[] vec2) {
        return new float[]{vec1[0] + vec2[0], vec1[1] + vec2[1], vec1[2] + vec2[2]};
    }

    public static float[] scale(float[] vec, float scalar) {
        return new float[]{vec[0] * scalar, vec[1] * scalar, vec[2] * scalar};
    }

    public static float length(float[] vec) {
        return (float) Math.sqrt(vec[0]*vec[0] + vec[1]*vec[1] + vec[2]*vec[2]);
    }

    public static float surfaceArea(float[] vec1, float[] vec2) {
        return 1/2 * Utils.length(vec1) * Utils.length(vec2) * (float) Math.sqrt(1 - Utils.dotProduct(vec1,vec2) * Utils.dotProduct(vec1,vec2));
    }
}
