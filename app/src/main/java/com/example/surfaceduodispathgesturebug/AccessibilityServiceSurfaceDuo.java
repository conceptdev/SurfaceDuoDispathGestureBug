package com.example.surfaceduodispathgesturebug;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class AccessibilityServiceSurfaceDuo extends AccessibilityService {

    private static final GestureResultCallback gestureResultCallback = new GestureResultCallback() {
        @Override
        public void onCompleted(GestureDescription gestureDescription) {
            Log.d("AccessibilityServiceSurfaceDuo", "Success");
            super.onCompleted(gestureDescription);
        }

        @Override
        public void onCancelled(GestureDescription gestureDescription) {
            Log.d("AccessibilityServiceSurfaceDuo", "Cancelled");
            super.onCancelled(gestureDescription);
        }
    };

    private void dispatchClickGesture(int x, int y) {
        GestureDescription.Builder gesture = new GestureDescription.Builder();
        Path clickPath = new Path();
        clickPath.moveTo(x, y);
        clickPath.lineTo(x + 5, y + 5);
        gesture.addStroke(new GestureDescription.StrokeDescription(clickPath, 0, 5));

        boolean dispatched = this.dispatchGesture(gesture.build(), gestureResultCallback, null);

        if (dispatched) {
            Toast.makeText(this, "Click(x=" + x + ", y=" + y + ") dispatched.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onServiceConnected() {
        new ButtonOverlay(this, 400, 100, "Click(300, 300)", () -> {
            this.dispatchClickGesture(300, 300);
        });

        new ButtonOverlay(this, 800, 100, "Click(1350+300, 300)", () -> {
            this.dispatchClickGesture(1350+300, 300);
        });
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    @Override
    public void onInterrupt() {

    }
}
