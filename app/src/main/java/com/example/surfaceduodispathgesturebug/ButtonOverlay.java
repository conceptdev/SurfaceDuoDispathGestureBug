package com.example.surfaceduodispathgesturebug;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

public class ButtonOverlay extends androidx.appcompat.widget.AppCompatButton {

    public ButtonOverlay(AccessibilityServiceSurfaceDuo accessibilityServiceSurfaceDuo, int x, int y, String text, Runnable runnable) {
        super(accessibilityServiceSurfaceDuo);

        int flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                350,
                100,
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                flags,
                PixelFormat.TRANSLUCENT
        );

        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.x = x;
        layoutParams.y = y;

        this.setLayoutParams(layoutParams);
        this.setBackgroundColor(Color.RED);

        ((WindowManager) accessibilityServiceSurfaceDuo.getSystemService(Context.WINDOW_SERVICE)).addView(this, layoutParams);

        this.setText("pos(" + x + "," + y + ")\n" + text);
        this.setOnClickListener(view -> runnable.run());
    }
}
