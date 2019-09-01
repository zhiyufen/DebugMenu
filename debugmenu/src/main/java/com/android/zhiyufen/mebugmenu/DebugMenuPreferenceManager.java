package com.android.zhiyufen.mebugmenu;

import android.content.Context;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类负责管理所有Preference screen.
 */
public class DebugMenuPreferenceManager {
    private final static String ALL_PREFERENCE_SCREEN_CLASS_NAME = "com.android.zhiyufen.mebugmenu";
    private final static Object INSTANCE_LOCK = new Object();
    private static DebugMenuPreferenceManager sInstance = null;

    List<PreferenceScreen> mPreScreenLists;

    private DebugMenuPreferenceManager() {}

    public static DebugMenuPreferenceManager getInstance() {
        synchronized (INSTANCE_LOCK) {
            if (null == sInstance) {
                sInstance = new DebugMenuPreferenceManager();
            }
        }
        return sInstance;
    }

    /**
     * It will make all debug menu preference screen from @DebugMenuFragment.
     *
     * @param context Context
     * @param pm The PreferenceManager of current Activity.
     * @return The array list of preference screen.
     */
    public List<PreferenceScreen> getAllScreen(Context context, PreferenceManager pm) {
        if (mPreScreenLists == null || mPreScreenLists.isEmpty()) {
            mPreScreenLists = new ArrayList<>();

            List<DebugMenuFragmentInfo> fragmentEntities = null;
            try {
                Class allPreferenceScreenClass = Class.forName(ALL_PREFERENCE_SCREEN_CLASS_NAME);
                Method method = allPreferenceScreenClass.getMethod("getAllDebugFragmentInfo");
                fragmentEntities = (List<DebugMenuFragmentInfo>)method.invoke(null);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            for (DebugMenuFragmentInfo entity : fragmentEntities) {
                PreferenceScreen newFreScreen = pm.createPreferenceScreen(context);
                newFreScreen.setTitle(entity.getTitle());
                newFreScreen.setFragment(entity.getFragment());
                mPreScreenLists.add(newFreScreen);
            }
        }
        return mPreScreenLists;
    }
}
