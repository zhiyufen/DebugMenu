package com.android.zhiyufen.mebugmenu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

import com.android.zhiyufen.mebugmenu.R;
import com.android.zhiyufen.mebugmenu.data.DebugMenuSettings;

public class DebugMenuSwitchPreference extends SwitchPreference
        implements Preference.OnPreferenceClickListener {
    private String mDebugKey = null;
    private Context mContext;

    public DebugMenuSwitchPreference(Context context,
                                     AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DebugPreference);
        mDebugKey = a.getString(R.styleable.DebugPreference_debugKey);
        setTitle(mDebugKey);
        a.recycle();
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (null == mDebugKey && mDebugKey.equals(preference.getKey())) {
            DebugMenuSettings.getInstance()
                    .putBoolean(mContext, mDebugKey, ((SwitchPreference) preference).isChecked());
        }
        return false;
    }

}
