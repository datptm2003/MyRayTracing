package com.example.myraytracing;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private float[][] vertices = new float[3][3];
    private float[] color = new float[4];

    public Polygon(float[][] vertices, float[] color) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.vertices[i][j] = vertices[i][j];
            }

        }
        this.color = color;
    }

    public float[][] getVertices() {
        return vertices;
    }

    public float[] getColor() {
        return color;
    }
}
