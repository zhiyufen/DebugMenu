package com.android.zhiyufen.mebugmenu.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import androidx.annotation.Nullable;

public class MainPreferenceFragment extends PreferenceFragment{

    public MainPreferenceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen ps = getPreferenceManager().createPreferenceScreen(getActivity());
        PreferenceScreen switchPreference = getPreferenceManager().createPreferenceScreen(getActivity());
        ps.addPreference(switchPreference);
        switchPreference.setSummary("可选择你想要的方式");
        setPreferenceScreen(ps);
    }
}
