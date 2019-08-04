package com.android.zhiyufen.mebugmenu.data;

public class DebugMenuDataManager {
    private final static Object INSTANCE_LOCK = new Object();
    private static DebugMenuDataManager sInstance = null;

    private DebugMenuDataManager() {}

    public static DebugMenuDataManager getInstance() {
        if (null == sInstance) {
            synchronized (INSTANCE_LOCK) {
                if (null == sInstance) {
                    sInstance = new DebugMenuDataManager();
                }
            }
        }
        return sInstance;
    }

}
