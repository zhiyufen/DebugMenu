package com.android.zhiyufen.debugmenu.demo.DebugMenuDome;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import androidx.annotation.Nullable;

import com.android.zhiyufen.debugmenu.annotation.DebugMenuFragment;
import com.android.zhiyufen.debugmenu.demo.R;

@DebugMenuFragment(title = "我是测试菜单")
public class DemoDebugMenuFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.demo_debug_menu_fragment_layout);
    }
}
