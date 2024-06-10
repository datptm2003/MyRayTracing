package com.example.myraytracing;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

public class OptionView extends LinearLayout {
    private Button generateSphereButton;
    private Button generatePolygonButton;

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

    }
}
