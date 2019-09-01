package com.android.zhiyufen.mebugmenu.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.android.zhiyufen.mebugmenu.DebugMenuConstants;
import com.android.zhiyufen.mebugmenu.fragment.MainPreferenceFragment;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.TextUtils;

import com.android.zhiyufen.mebugmenu.R;

/**
 * This activity will display all debug menu fragment and handler start new fragment action.
 */
public class DebugMenuSettingsActivity extends ToolBarActivity
        implements PreferenceFragment.OnPreferenceStartFragmentCallback {
    public static final String TAG = "ToolbarDemoActivity";
    private String sRunningFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_menu_settings_activity_layout);

        initToolbar();
        initFragment(savedInstanceState);
    }

    /**
     * Init the first fragment object and open it in this activity.
     *
     * @param savedInstanceState save instance state.
     */
    private void initFragment(@Nullable Bundle savedInstanceState) {
        String initialFragment = getIntent().getStringExtra(DebugMenuConstants.EXTRA_FRAGMENT_NAME);
        Bundle initialArguments = getIntent()
                .getBundleExtra(DebugMenuConstants.EXTRA_FRAGMENT_ARGS);

        if (savedInstanceState == null ) {
            if (initialFragment == null) initialFragment = MainPreferenceFragment.class.getName();
            Fragment fragment = Fragment.instantiate(
                    this, initialFragment, initialArguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.debug_menu_fragment, fragment, initialFragment)
                    .commit();
            sRunningFragment = initialFragment;
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        String fragmentClass = pref.getFragment();
        if (TextUtils.equals(sRunningFragment, fragmentClass))
            return true;

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClass(this, getClass());
        intent.putExtra(DebugMenuConstants.EXTRA_FRAGMENT_NAME, fragmentClass);
        intent.putExtra(DebugMenuConstants.EXTRA_FRAGMENT_ARGS, caller.getArguments());
        sRunningFragment = fragmentClass;

        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}