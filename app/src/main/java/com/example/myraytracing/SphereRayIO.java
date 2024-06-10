package com.example.myraytracing;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SphereRayIO extends LinearLayout {
//    private EditText stacksInput;
//    private EditText slicesInput;

    private EditText radiusInput;
    private EditText centerXInput;
    private EditText centerYInput;
    private EditText centerZInput;
    private EditText startXInput;
    private EditText startYInput;
    private EditText startZInput;
    private EditText directionXInput;
    private EditText directionYInput;
    private EditText directionZInput;
    private Button generateButton;
    private Sphere sphere;
    private Ray ray;

    public SphereRayIO(Context context) {
        super(context);
        init(context);
    }

    public SphereRayIO(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SphereRayIO(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

//        stacksInput = new EditText(context);
//        stacksInput.setHint("Enter stacks");
//
//        slicesInput = new EditText(context);
//        slicesInput.setHint("Enter slices");

        radiusInput = new EditText(context);
        radiusInput.setHint("Enter radius");

        centerXInput = new EditText(context);
        centerXInput.setHint("Enter center X");

        centerYInput = new EditText(context);
        centerYInput.setHint("Enter center Y");

        centerZInput = new EditText(context);
        centerZInput.setHint("Enter center Z");

        startXInput = new EditText(context);
        startXInput.setHint("Enter start X");

        startYInput = new EditText(context);
        startYInput.setHint("Enter start Y");

        startZInput = new EditText(context);
        startZInput.setHint("Enter start Z");

        directionXInput = new EditText(context);
        directionXInput.setHint("Enter direction X");

        directionYInput = new EditText(context);
        directionYInput.setHint("Enter direction Y");

        directionZInput = new EditText(context);
        directionZInput.setHint("Enter direction Z");

        generateButton = new Button(context);
        generateButton.setText("Generate Sphere");

//        addView(stacksInput);
//        addView(slicesInput);
        addView(radiusInput);
        addView(centerXInput);
        addView(centerYInput);
        addView(centerZInput);
        addView(startXInput);
        addView(startYInput);
        addView(startZInput);
        addView(directionXInput);
        addView(directionYInput);
        addView(directionZInput);
        addView(generateButton);

//        generateButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
////                    int stacks = Integer.parseInt(stacksInput.getText().toString());
////                    int slices = Integer.parseInt(slicesInput.getText().toString());
//                    float radius = Float.parseFloat(radiusInput.getText().toString());
//                    float centerX = Float.parseFloat(centerXInput.getText().toString());
//                    float centerY = Float.parseFloat(centerYInput.getText().toString());
//                    float centerZ = Float.parseFloat(centerZInput.getText().toString());
//                    float[] center = {centerX, centerY, centerZ};
//
//                    sphere = new Sphere(radius, center, new float[]{1.0f,1.0f,1.0f,1.0f});
//                    Toast.makeText(context, "Sphere generated successfully", Toast.LENGTH_SHORT).show();
////                    MainActivity.glSurfaceView.setVisibility(View.VISIBLE);
////                    throw new InputAcceptance();
////                    MainActivity.inputAcceptance = true;
//                    Log.d("testSphere","0");
//                } catch (NumberFormatException e) {
//                    Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    public Sphere getSphere() {
        Log.d("sphere",String.valueOf(sphere));
        float radius = Float.parseFloat(radiusInput.getText().toString());
        float centerX = Float.parseFloat(centerXInput.getText().toString());
        float centerY = Float.parseFloat(centerYInput.getText().toString());
        float centerZ = Float.parseFloat(centerZInput.getText().toString());
        float[] center = new float[]{centerX, centerY, centerZ};
        Log.d("radius",Float.toString(radius));
        Log.d("centerX",Float.toString(centerX));
        Log.d("centerY",Float.toString(centerY));
        Log.d("centerZ",Float.toString(centerZ));

        sphere = new Sphere(radius, center, new float[]{1.0f,1.0f,1.0f,1.0f});
        return sphere;
    }
    public Ray getRay() {
//        Log.d("sphere",String.valueOf(sphere));
        float startX = Float.parseFloat(startXInput.getText().toString());
        float startY = Float.parseFloat(startYInput.getText().toString());
        float startZ = Float.parseFloat(startZInput.getText().toString());
        float directionX = Float.parseFloat(directionXInput.getText().toString());
        float directionY = Float.parseFloat(directionYInput.getText().toString());
        float directionZ = Float.parseFloat(directionZInput.getText().toString());
        float[] start = new float[]{startX, startY, startZ};
        float[] direction = new float[]{directionX, directionY, directionZ};
//        Log.d("radius",Float.toString(radius));
//        Log.d("centerX",Float.toString(centerX));
//        Log.d("centerY",Float.toString(centerY));
//        Log.d("centerZ",Float.toString(centerZ));

        ray = new Ray(start,direction);
        return ray;
    }

    public Button getGenerateButton() {
        return generateButton;
    }
}
