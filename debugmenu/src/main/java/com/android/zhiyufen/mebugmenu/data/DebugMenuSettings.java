package com.android.zhiyufen.mebugmenu.data;

import android.content.Context;
import androidx.annotation.Nullable;

import java.util.Set;

public class DebugMenuSettings {
    private static final String TAG = "DebugMenuSettings";
    private static final String SHARE_PREFERENCE_FILE_NAME = "debug_menu_share_preference_file";


    private final static Object INSTANCE_LOCK = new Object();
    private static DebugMenuSettings sInstance = null;

    private DebugMenuSettings() {}

    public static DebugMenuSettings getInstance() {
        if (null == sInstance) {
            synchronized (INSTANCE_LOCK) {
                if (null == sInstance) {
                    sInstance = new DebugMenuSettings();
                }
            }
        }
        return sInstance;
    }

    public static void putString(Context context, String key, String value){
        context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply();
    }

    public static void putBoolean(Context context, String key, boolean value){
        context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(key, value)
                .apply();
    }

    public static void putFloat(Context context, String key, float value){
        context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putFloat(key, value)
                .apply();
    }

    public static void putInt(Context context, String key, int value){
        context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(key, value)
                .apply();
    }

    public static void putLong(Context context, String key, long value){
        context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putLong(key, value)
                .apply();
    }

    public static void putStringSet(Context context, String key, @Nullable Set<String> values){
        context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putStringSet(key, values)
                .apply();
    }
}
