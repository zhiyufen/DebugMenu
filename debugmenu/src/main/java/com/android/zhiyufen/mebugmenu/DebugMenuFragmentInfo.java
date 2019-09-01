package com.android.zhiyufen.mebugmenu;

/**
 * This class will store the information of debug menu fragment which user create,
 */
public class DebugMenuFragmentInfo {
    /**
     * The Title of PreferenceScreen.
     */
    private String mTitle;

    /**
     * The class name of PreferenceFragment;
     */
    private String mFragment;

    public DebugMenuFragmentInfo(String title, String fragment) {
        mTitle = title;
        mFragment = fragment;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFragment() {
        return mFragment;
    }
}
