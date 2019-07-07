package com.android.zhiyufen.mebugmenu;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

public class DebugMenuUtils {
    private final static String TAG = "DebugMenuUtils";

    /**
     * Returns height of window display
     *
     * @param context context
     * @return window display height value
     */
    public static int getWindowHeight(Context context) {
        int windowHeight = 0;
        try {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (wm != null) {
                Point point = new Point();
                wm.getDefaultDisplay().getSize(point);
                windowHeight = point.y;
            }
        } catch (Exception e) {
            Log.e(TAG, "cannot get window width");
        }
        return windowHeight;
    }

    /**
     * Check if the orientation is landscape mode
     *
     * @param context context
     */
    public static boolean isLandscape(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * Change dp to px value
     *
     * @param context context
     * @param dp      The dp value
     * @return px
     */
    public static int dpToPx(Context context, float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
