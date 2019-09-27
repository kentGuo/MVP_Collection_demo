package com.fg.mvp_collection_demo.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * 屏幕适配
 */
public class ScreenAdaptation {
    private static float sNoncompatDensity;
    private static float sNoncompatScaleDensity;

    /**
     * 通过修改density值，强行把所有不同尺寸分辨率的手机的宽度dp值改成一个统一的值
     * @param activity
     * @param application
     */
    public static void  setCustomDensity(Activity activity, final Application application)
    {
        DisplayMetrics appDisplayMetrics=application.getResources().getDisplayMetrics();
        if(sNoncompatDensity==0)
        {
            sNoncompatDensity=appDisplayMetrics.density;
            sNoncompatScaleDensity=appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig!=null&&newConfig.fontScale>0){
                        sNoncompatDensity=application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        float targetDensity=(float)appDisplayMetrics.widthPixels/160;
        float targetScaleDensity=targetDensity*(sNoncompatScaleDensity/sNoncompatDensity);
        int targetDensityDpi=(int)(160*targetDensity);
        appDisplayMetrics.density=targetDensity;
        appDisplayMetrics.scaledDensity=targetScaleDensity;
        appDisplayMetrics.densityDpi=targetDensityDpi;
        final DisplayMetrics activityDisplayMetrics=activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density=targetDensity;
        activityDisplayMetrics.scaledDensity=targetScaleDensity;
        activityDisplayMetrics.densityDpi=targetDensityDpi;
    }
}