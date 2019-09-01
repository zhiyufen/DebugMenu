package com.android.zhiyufen.mebugmenu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

import com.android.zhiyufen.mebugmenu.R;
import com.android.zhiyufen.mebugmenu.data.DebugMenuSettings;

public class DebugMenuSwitchPreference extends SwitchPreference{
    private String mDebugKey;
    private Context mContext;


    public DebugMenuSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DebugPreference);
        mDebugKey = a.getString(R.styleable.DebugPreference_debugKey);
        setTitle(mDebugKey);
        a.recycle();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        DebugMenuSettings.getInstance()
                .putBoolean(mContext, mDebugKey, checked);
    }
}
