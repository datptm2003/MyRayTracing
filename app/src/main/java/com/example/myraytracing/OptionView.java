package com.example.myraytracing;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

public class OptionView extends LinearLayout {
    private Button generateSphereButton;
    private Button generatePolygonButton;
    private Button generateInterpolationButton;

    public OptionView(Context context) {
        super(context);
        init(context);
    }

    public OptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OptionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

        generateSphereButton = new Button(context);
        generateSphereButton.setText("Generate Sphere Intersection");

        generatePolygonButton = new Button(context);
        generatePolygonButton.setText("Generate Polygon Intersection");

        generateInterpolationButton = new Button(context);
        generateInterpolationButton.setText("Generate Triangle Interpolation");

        addView(generateSphereButton);
        addView(generatePolygonButton);
        addView(generateInterpolationButton);
    }

    public Button getGenerateSphereButton() {
        return generateSphereButton;
    }

    public Button getGeneratePolygonButton() {
        return generatePolygonButton;
    }

    public Button getGenerateInterpolationButton() {
        return generateInterpolationButton;
    }
}
