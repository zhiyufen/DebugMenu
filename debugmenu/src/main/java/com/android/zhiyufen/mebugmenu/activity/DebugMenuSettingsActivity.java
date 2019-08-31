package com.android.zhiyufen.mebugmenu.activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.android.zhiyufen.mebugmenu.fragment.MainPreferenceFragment;
import com.google.android.material.appbar.AppBarLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.zhiyufen.mebugmenu.DebugMenuUtils;
import com.android.zhiyufen.mebugmenu.R;

/**
 * DebugMenuSettingsActivity
 */
public class DebugMenuSettingsActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    public static final String TAG = "ToolbarDemoActivity";
    public static final String EXTRA_FRAGMENT_NAME = "extra_fragment_name";
    public static final String EXTRA_FRAGMENT_ARGS = "extra_fragment_args";

    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private TextView mTitleContainer;
    private TextView mTitle;
    private String mInitialFragment;

    private int mPrevOrientation = Configuration.ORIENTATION_UNDEFINED;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_menu_settings_activity_layout);

        initToolbar();
        initFragment(savedInstanceState);
    }

    private void initFragment(@Nullable Bundle savedInstanceState) {
        mInitialFragment = getIntent().getStringExtra(EXTRA_FRAGMENT_NAME);
        Bundle initialArguments = getIntent().getBundleExtra(EXTRA_FRAGMENT_ARGS);

        if (savedInstanceState == null ) {
            if (mInitialFragment == null) mInitialFragment = MainPreferenceFragment.class.getName();
            Fragment fragment = Fragment.instantiate(this, mInitialFragment, initialArguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.debug_menu_fragment, fragment, mInitialFragment)
                    .commit();
        }

    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.debug_settings_toolbar);
        mAppBarLayout = findViewById(R.id.debug_settings_app_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mTitleContainer = findViewById(R.id.collapsing_bar_title);
        mTitle.setText(R.string.app_name);
        mTitleContainer.setText(R.string.app_name);

        setSupportActionBar(mToolbar);
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
                if (TextUtils.isEmpty(string)) return;
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