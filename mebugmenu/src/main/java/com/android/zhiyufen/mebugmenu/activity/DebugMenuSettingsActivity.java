package com.android.zhiyufen.mebugmenu.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.zhiyufen.mebugmenu.R;

/**
 * DebugMenuSettingsActivity
 */
public class DebugMenuSettingsActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    public static final String TAG = "ToolbarDemoActivity";
    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private TextView mTitleContainer;
    private TextView mTitle;

    private int mPrevOrientation = Configuration.ORIENTATION_UNDEFINED;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_menu_settings_activity_layout);

        mToolbar = findViewById(R.id.debug_settings_toolbar);
        mAppBarLayout = findViewById(R.id.debug_settings_app_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mTitleContainer = findViewById(R.id.collapsing_bar_title);
        mTitle.setText("我是标题");
        mTitleContainer.setText("我是标题");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getActionBar() != null) {
            getActionBar().setDisplayOptions(
                    ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        }
        mAppBarLayout.addOnOffsetChangedListener(this);
        mAppBarLayout.setExpanded(false);
        resetAppBarHeight();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mPrevOrientation != newConfig.orientation) {
            mPrevOrientation = newConfig.orientation;
            mAppBarLayout.setExpanded(false);
            resetAppBarHeight();
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int totalScrollRange = appBarLayout.getTotalScrollRange();
        float offsetRatio = (float) Math.abs(verticalOffset) / (float) totalScrollRange;
        mTitleContainer.setAlpha(1.5f - (offsetRatio * 2));
        mTitle.setAlpha((offsetRatio * 2) - 1);
    }

    private void resetAppBarHeight() {
        ViewGroup.LayoutParams layoutParams = mAppBarLayout.getLayoutParams();
        int screenHeight = getWindowHeight(this);
        if (isLandscape()) {
            mTitleContainer.setVisibility(View.GONE);
            TypedArray array = null;
            try {
                array = getTheme().obtainStyledAttributes(new int[] {R.attr.actionBarSize});
                String string = array.getString(0);
                String number = string.replaceAll("[^[0-9|.]]", "");
                float dpValue = Float.parseFloat(number);
                float pxValue = dpToPx(dpValue);
                layoutParams.height = (int) pxValue;
            } catch (Exception e) {
                layoutParams.height =
                        (int) getResources().getDimension(R.dimen.action_bar_button_height);
            } finally {
                array.recycle();
            }
        } else {
            mTitleContainer.setVisibility(View.VISIBLE);
            layoutParams.height = (int) (screenHeight * 0.38f);
        }
        mAppBarLayout.setLayoutParams(layoutParams);
    }

    /**
     * Returns height of window display
     * @param context context
     * @return window display height value
     */
    private static int getWindowHeight(Context context) {
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
     */
    private boolean isLandscape() {
        Configuration configuration = getResources().getConfiguration();
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private int dpToPx(float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}