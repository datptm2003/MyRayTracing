package com.example.myraytracing;

public class Ray {
    private float[] start = new float[3];
    private float[] direction = new float[3];
    private float[] color = new float[3];

    public Ray(float[] start, float[] direction, float[] color) {
        this.start = start;
        this.direction = direction;
        this.color = color;
    }
    public float[] getStart(){
        return this.start;
    }
    public float[] getDirection(){
        return this.direction;
    }
    public float[] getColor(){
        return this.color;
    }
}
