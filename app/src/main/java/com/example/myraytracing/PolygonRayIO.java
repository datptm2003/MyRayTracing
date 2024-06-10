package com.example.myraytracing;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class PolygonRayIO extends LinearLayout {
//    private EditText stacksInput;
//    private EditText slicesInput;

    private EditText vertex1XInput;
    private EditText vertex1YInput;
    private EditText vertex1ZInput;
    private EditText vertex2XInput;
    private EditText vertex2YInput;
    private EditText vertex2ZInput;
    private EditText vertex3XInput;
    private EditText vertex3YInput;
    private EditText vertex3ZInput;
    private EditText startXInput;
    private EditText startYInput;
    private EditText startZInput;
    private EditText directionXInput;
    private EditText directionYInput;
    private EditText directionZInput;
    private Button generateButton;
    private Polygon polygon;
    private Ray ray;

    public PolygonRayIO(Context context) {
        super(context);
        init(context);
    }

    public PolygonRayIO(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PolygonRayIO(Context context, AttributeSet attrs, int defStyle) {
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

        vertex1XInput = new EditText(context);
        vertex1XInput.setHint("Enter vertex 1 X");

        vertex1YInput = new EditText(context);
        vertex1YInput.setHint("Enter vertex 1 Y");

        vertex1ZInput = new EditText(context);
        vertex1ZInput.setHint("Enter vertex 1 Z");

        vertex2XInput = new EditText(context);
        vertex2XInput.setHint("Enter vertex 2 X");

        vertex2YInput = new EditText(context);
        vertex2YInput.setHint("Enter vertex 2 Y");

        vertex2ZInput = new EditText(context);
        vertex2ZInput.setHint("Enter vertex 2 Z");

        vertex3XInput = new EditText(context);
        vertex3XInput.setHint("Enter vertex 3 X");

        vertex3YInput = new EditText(context);
        vertex3YInput.setHint("Enter vertex 3 Y");

        vertex3ZInput = new EditText(context);
        vertex3ZInput.setHint("Enter vertex 3 Z");

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
        generateButton.setText("Generate Polygon");

//        addView(stacksInput);
//        addView(slicesInput);
        addView(vertex1XInput);
        addView(vertex1YInput);
        addView(vertex1ZInput);
        addView(vertex2XInput);
        addView(vertex2YInput);
        addView(vertex2ZInput);
        addView(vertex3XInput);
        addView(vertex3YInput);
        addView(vertex3ZInput);
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

    public Polygon getPolygon() {
//        float radius = Float.parseFloat(radiusInput.getText().toString());
        float vertex1X = Float.parseFloat(vertex1XInput.getText().toString());
        float vertex1Y = Float.parseFloat(vertex1YInput.getText().toString());
        float vertex1Z = Float.parseFloat(vertex1ZInput.getText().toString());
        float[] vertex1 = new float[]{vertex1X, vertex1Y, vertex1Z};

        float vertex2X = Float.parseFloat(vertex2XInput.getText().toString());
        float vertex2Y = Float.parseFloat(vertex2YInput.getText().toString());
        float vertex2Z = Float.parseFloat(vertex2ZInput.getText().toString());
        float[] vertex2 = new float[]{vertex2X, vertex2Y, vertex2Z};


        float vertex3X = Float.parseFloat(vertex3XInput.getText().toString());
        float vertex3Y = Float.parseFloat(vertex3YInput.getText().toString());
        float vertex3Z = Float.parseFloat(vertex3ZInput.getText().toString());
        float[] vertex3 = new float[]{vertex3X, vertex3Y, vertex3Z};

        polygon = new Polygon(new float[][]{vertex1,vertex2,vertex3});
        return polygon;
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
