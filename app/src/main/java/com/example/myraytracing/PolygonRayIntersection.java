package com.example.myraytracing;

import android.util.Log;

public class PolygonRayIntersection {
    private Polygon polygon;
    private Ray ray;
    PolygonRayIntersection(Polygon polygon, Ray ray){
        this.polygon = polygon;
        this.ray = ray;
    }


    public float[] sol()
    {
        float epsilon = 1e-6f;

        float[] edge1 = Utils.subtract(polygon.getVertices()[1], polygon.getVertices()[0]);
        float[] edge2 = Utils.subtract(polygon.getVertices()[2], polygon.getVertices()[0]);
        float[] faceNorm = Utils.crossProduct(edge1, edge2);
        float a = Utils.dotProduct(ray.getDirection(), faceNorm);

        Log.d("dotProductA",Float.toString(a));

        if (a > -epsilon && a < epsilon) {
            return null; // This ray is parallel to the triangle.
        }

        float d = -Utils.dotProduct(faceNorm,polygon.getVertices()[0]);
        float t = -(Utils.dotProduct(ray.getStart(),faceNorm) + d) / a;

        Log.d("dotProductA",Float.toString(t));

        if (t < 0) {
            return null;
        } else {
            return Utils.add(ray.getStart(), Utils.scale(ray.getDirection(), t));
        }

//        float f = 1.0f / a;
//        float[] s = subtract(ray.getStart(), polygon.getVertices()[0]);
//        float u = f * dotProduct(s, h);
//
//        if (u < 0.0 || u > 1.0) {
//            return null; // The intersection lies outside of the triangle.
//        }
//
//        float[] q = crossProduct(s, edge1);
//        float v = f * dotProduct(ray.getDirection(), q);
//
//        if (v < 0.0 || u + v > 1.0) {
//            return null; // The intersection lies outside of the triangle.
//        }
//
//        float t = f * dotProduct(edge2, q);
//
//        if (t > 1e-8) { // ray intersection
//            return add(ray.getStart(), scale(ray.getDirection(), t));
//        } else { // This means that there is a line intersection but not a ray intersection.
//            return null;
//        }

//        return new float[][]{new float[]{ray.getStart()[0] + sol[0] * ray.getDirection()[0], ray.getStart()[1] + sol[0] * ray.getDirection()[1], ray.getStart()[2] + sol[0] * ray.getDirection()[2]}, new float[]{ray.getStart()[0] + sol[1] * ray.getDirection()[0], ray.getStart()[1] + sol[1] * ray.getDirection()[1], ray.getStart()[2] + sol[1] * ray.getDirection()[2]}};
    }

}
