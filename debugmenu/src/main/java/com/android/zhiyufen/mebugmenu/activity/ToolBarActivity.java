package com.android.zhiyufen.mebugmenu.activity;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.zhiyufen.mebugmenu.DebugMenuUtils;
import com.android.zhiyufen.mebugmenu.R;
import com.google.android.material.appbar.AppBarLayout;

/**
 * custom tool bar feature for activity.
 */
class ToolBarActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    public static final String TAG = "ToolBarActivity";

    private AppBarLayout mAppBarLayout;
    private TextView mTitleContainer;
    private TextView mTitle;

    private int mPrevOrientation = Configuration.ORIENTATION_UNDEFINED;

    protected void initToolbar() {
        Toolbar toolbar = findViewById(R.id.debug_settings_toolbar);
        mAppBarLayout = findViewById(R.id.debug_settings_app_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mTitleContainer = findViewById(R.id.collapsing_bar_title);
        mTitle.setText(R.string.app_name);
        mTitleContainer.setText(R.string.app_name);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
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
        int screenHeight = DebugMenuUtils.getWindowHeight(this);
        if (DebugMenuUtils.isLandscape(this)) {
            mTitleContainer.setVisibility(View.GONE);
            TypedArray array = null;
            try {
                array = getTheme().obtainStyledAttributes(new int[] {R.attr.actionBarSize});
                String string = array.getString(0);
                String number = string.replaceAll("[^[0-9|.]]", "");
                float dpValue = Float.parseFloat(number);
                float pxValue = DebugMenuUtils.dpToPx(this, dpValue);
                layoutParams.height = (int) pxValue;
            } catch (Exception e) {
                layoutParams.height =
                        (int) getResources().getDimension(R.dimen.action_bar_button_height);
            } finally {
                if (array != null)
                    array.recycle();
            }
        } else {
            mTitleContainer.setVisibility(View.VISIBLE);
            layoutParams.height = (int) (screenHeight * 0.38f);
        }
        mAppBarLayout.setLayoutParams(layoutParams);
    }
}
