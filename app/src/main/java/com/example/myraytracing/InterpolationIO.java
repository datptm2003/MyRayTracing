package com.example.myraytracing;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class InterpolationIO extends LinearLayout {
    private EditText vertexXInput;
    private EditText vertexYInput;
    private EditText vertexZInput;

    private EditText[] startXInput = new EditText[3];
    private EditText[] startYInput = new EditText[3];
    private EditText[] startZInput = new EditText[3];
    private EditText[] directionXInput = new EditText[3];
    private EditText[] directionYInput = new EditText[3];
    private EditText[] directionZInput = new EditText[3];

    private Button generateButton;
    private float[] vertex;
    private Ray[] rayList = new Ray[3];

    public InterpolationIO(Context context) {
        super(context);
        init(context);
    }

    public InterpolationIO(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InterpolationIO(Context context, AttributeSet attrs, int defStyle) {
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

        vertexXInput = new EditText(context);
        vertexXInput.setHint("Enter vertex X");
        addView(vertexXInput);

        vertexYInput = new EditText(context);
        vertexYInput.setHint("Enter vertex Y");
        addView(vertexYInput);

        vertexZInput = new EditText(context);
        vertexZInput.setHint("Enter vertex Z");
        addView(vertexZInput);

        for (int i = 0; i < 3; ++i) {
            LinearLayout startLayout = new LinearLayout(context);
            startLayout.setOrientation(HORIZONTAL);

            startXInput[i] = new EditText(context);
            startXInput[i].setHint("Enter start X" + (i + 1));
            startLayout.addView(startXInput[i]);

            startYInput[i] = new EditText(context);
            startYInput[i].setHint("Enter start Y" + (i + 1));
            startLayout.addView(startYInput[i]);

            startZInput[i] = new EditText(context);
            startZInput[i].setHint("Enter start Z" + (i + 1));
            startLayout.addView(startZInput[i]);

            addView(startLayout);

            LinearLayout directionLayout = new LinearLayout(context);
            directionLayout.setOrientation(HORIZONTAL);

            directionXInput[i] = new EditText(context);
            directionXInput[i].setHint("Enter dir X" + (i + 1));
            directionLayout.addView(directionXInput[i]);

            directionYInput[i] = new EditText(context);
            directionYInput[i].setHint("Enter dir Y" + (i + 1));
            directionLayout.addView(directionYInput[i]);

            directionZInput[i] = new EditText(context);
            directionZInput[i].setHint("Enter dir Z" + (i + 1));
            directionLayout.addView(directionZInput[i]);

            addView(directionLayout);
        }



        generateButton = new Button(context);
        generateButton.setText("Generate Interpolation");

//        addView(stacksInput);
//        addView(slicesInput);


//        for (int i = 0; i < 3; ++i) {
//            addView(startXInput[i]);
//            addView(startYInput[i]);
//            addView(startZInput[i]);
//            addView(directionXInput[i]);
//            addView(directionYInput[i]);
//            addView(directionZInput[i]);
//            Log.d("i----",Integer.toString(i));
//        }


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

    public Ray[] getRayList() {

        for (int i = 0; i < 3; ++i) {
            Ray ray = new Ray(new float[]{Float.parseFloat(startXInput[i].getText().toString()), Float.parseFloat(startYInput[i].getText().toString()), Float.parseFloat(startZInput[i].getText().toString())}, new float[]{Float.parseFloat(directionXInput[i].getText().toString()), Float.parseFloat(directionYInput[i].getText().toString()), Float.parseFloat(directionZInput[i].getText().toString())}, new float[]{0.0f,1.0f,0.0f,1.0f});
            rayList[i] = ray;
        }

        return rayList;
    }
    public float[] getVertex() {
        float vertexX = Float.parseFloat(vertexXInput.getText().toString());
        float vertexY = Float.parseFloat(vertexYInput.getText().toString());
        float vertexZ = Float.parseFloat(vertexZInput.getText().toString());
        vertex = new float[]{vertexX, vertexY, vertexZ};



        return vertex;
    }

    public Button getGenerateButton() {
        return generateButton;
    }
}
