package com.android.zhiyufen.mebugmenu.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import androidx.annotation.Nullable;

import com.android.zhiyufen.mebugmenu.DebugMenuPreferenceManager;

import java.util.List;

public class MainPreferenceFragment extends PreferenceFragment{

    public MainPreferenceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<PreferenceScreen> debugMenuList = DebugMenuPreferenceManager.getInstance()
                .getAllScreen(getActivity(), getPreferenceManager());
        PreferenceScreen mainPreferenceScreen = getPreferenceManager().createPreferenceScreen(getActivity());

        for (PreferenceScreen screen : debugMenuList) {
            if (screen != null) {
                mainPreferenceScreen.addPreference(screen);
            }
        }

        setPreferenceScreen(mainPreferenceScreen);
    }
}
