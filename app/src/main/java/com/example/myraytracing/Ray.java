package com.example.myraytracing;

public class Ray {
    public float[] start = new float[3];
    public float[] direction = new float[3];

    public Ray(float[] start, float[] direction) {
        this.start = start;
        this.direction = direction;
    }
    public float[] getStart(){
        return this.start;
    }
    public float[] getDirection(){
        return this.direction;
    }
}
