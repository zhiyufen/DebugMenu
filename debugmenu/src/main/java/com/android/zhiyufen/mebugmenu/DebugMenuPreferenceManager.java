package com.android.zhiyufen.mebugmenu;

import android.preference.PreferenceScreen;

/**
 * 该类负责管理所有Preference screen.
 */
public class DebugMenuPreferenceManager {
    private final static Object INSTANCE_LOCK = new Object();
    private static DebugMenuPreferenceManager sInstance = null;

    private DebugMenuPreferenceManager() {}

    public static DebugMenuPreferenceManager getInstance() {
        synchronized (INSTANCE_LOCK) {
            if (null == sInstance) {
                sInstance = new DebugMenuPreferenceManager();
            }
        }
        return sInstance;
    }

    /*public PreferenceScreen getAllScreen() {

    }*/
}
