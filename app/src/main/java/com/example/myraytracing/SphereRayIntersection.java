package com.example.myraytracing;

import static java.lang.Math.sqrt;

import android.util.Log;

public class SphereRayIntersection {
    public float b, c;
    private Sphere sphere;
    private Ray ray;
    SphereRayIntersection(Sphere sphere, Ray ray){
        this.sphere = sphere;
        this.ray = ray;
    }
    public int solve(){
        float[] start = ray.getStart();
        float[] direction = ray.getDirection();
        float[] center = sphere.getCenter();
        float radius = sphere.getRadius();

        float sum = 0;
        for (int i = 0; i < 3; ++i){
            sum += direction[i] * direction[i];
        }
        for (int i = 0; i < 3; ++i){
            direction[i] = direction[i] / (float) Math.sqrt(sum);
        }

        b = 2*(direction[0]*(start[0] - center[0])
                + direction[1]*(start[1] - center[1])
                + direction[2]*(start[2] - center[2]));
        c = (start[0]-center[0])*(start[0]-center[0])
                + (start[1]-center[1])*(start[1]-center[1])
                + (start[2]-center[2])*(start[2]-center[2]) - radius * radius;

        float delta = b*b - 4*c;
        float epsilon = 0.00001f;
        if (delta > 0) return 2;
        else if (delta - epsilon < 0) return 1;
        else return 0;
    }

//    public float single_sol(){
//        return (-b/2);
//    }

    public float[][] sol()
    {
        float[] sol = new float[2];
        float delta = b*b - 4*c;
//        Log.d("delta",Float.toString(delta));
        sol[0] = (float) ((-b-sqrt(delta))/2);
        sol[1] = (float) ((-b+sqrt(delta))/2);
        Log.d("Solution", "" + sol[0] + "," + sol[1]);
        return new float[][]{new float[]{ray.getStart()[0] + sol[0] * ray.getDirection()[0], ray.getStart()[1] + sol[0] * ray.getDirection()[1], ray.getStart()[2] + sol[0] * ray.getDirection()[2]}, new float[]{ray.getStart()[0] + sol[1] * ray.getDirection()[0], ray.getStart()[1] + sol[1] * ray.getDirection()[1], ray.getStart()[2] + sol[1] * ray.getDirection()[2]}};
    }

}
