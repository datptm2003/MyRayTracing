package com.example.myraytracing;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    public float[][] vertices = new float[3][3];

    public Polygon(float[][] vertices) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.vertices[i][j] = vertices[i][j];
            }

        }
    }
}
