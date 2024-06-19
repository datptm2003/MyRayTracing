package com.example.myraytracing;

import android.util.Log;

public class Interpolation {
    private float[] vertex;
    private Ray[] rayList = new Ray[3];

    public Interpolation(float[] vertex, Ray[] rayList) {
        this.vertex = vertex;
        for (int i = 0; i < rayList.length; ++i) {
            this.rayList[i] = rayList[i];
        }
    }

    public Ray solve() {
        float[][] startVertices = new float[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                startVertices[i][j] = rayList[i].getStart()[j];
            }
        }
        float[] faceEdge01 = Utils.subtract(startVertices[1],startVertices[0]);
        float[] faceEdge02 = Utils.subtract(startVertices[2],startVertices[0]);
        float surfaceArea = Utils.surfaceArea(faceEdge01,faceEdge02);

        float[] vecTo0 = Utils.subtract(startVertices[0],vertex);
        float[] vecTo1 = Utils.subtract(startVertices[1],vertex);
        float[] vecTo2 = Utils.subtract(startVertices[2],vertex);

        float surfaceArea01 = Utils.surfaceArea(vecTo0,vecTo1);
        float surfaceArea02 = Utils.surfaceArea(vecTo0,vecTo2);
        float surfaceArea12 = Utils.surfaceArea(vecTo1,vecTo2);

        float ratio01 = surfaceArea01 / surfaceArea;
        float ratio02 = surfaceArea02 / surfaceArea;
        float ratio12 = surfaceArea12 / surfaceArea;

        float[] res = Utils.add(Utils.scale(rayList[0].getDirection(),ratio01),Utils.add(Utils.scale(rayList[1].getDirection(),ratio12),Utils.scale(rayList[2].getDirection(),ratio02)));

//        Log.d("interpolation",Utils.length(vecTo0) + "");
        Log.d("interpolation",vecTo0[0] + "," + vecTo0[1] + ";" + vecTo1[0] + "," + vecTo1[1]);
        return new Ray(vertex,res,new float[]{0.0f,0.0f,1.0f,1.0f});
    }
}
