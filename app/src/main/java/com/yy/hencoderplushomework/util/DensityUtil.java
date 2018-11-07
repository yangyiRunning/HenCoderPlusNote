package com.yy.hencoderplushomework.util;

import android.content.res.Resources;
import android.util.TypedValue;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

public class DensityUtil {

    public static float dp2px(int dp) {
        return TypedValue.applyDimension(COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static float getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
