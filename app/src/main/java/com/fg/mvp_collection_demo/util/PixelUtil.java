package com.fg.mvp_collection_demo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 像素转换工具
 */
public class PixelUtil {
    public static float dp2Px(Context context,float value)
    {
        if(context==null)
            return 0;
        TypedValue typedValue=new TypedValue();
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        return typedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,metrics);
    }

    public static float sp2Px(Context context,float value)
    {
        if(context==null)
            return 0;
        TypedValue typedValue=new TypedValue();
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        return typedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,value,metrics);
    }

    public static int dpToPx(float dp, Resources resources)
    {
        float px=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,resources.getDisplayMetrics());
        return (int)px;
    }

    /**
     * dp转px
     * @param value
     * @param context
     * @return
     */
    public static int dp2Px(float value,Context context)
    {
        final  float scale=context.getResources().getDisplayMetrics().densityDpi;
        return (int)(value*(scale/160)+0.5f);
    }

    /**
     * sp转px
     * @param value
     * @param context
     * @return
     */
    public static int sp2Px(float value,Context context)
    {
        Resources r;
        if(context==null)
        {
            r=Resources.getSystem();
        }else{
            r=context.getResources();
        }
        float spvalue=value*r.getDisplayMetrics().densityDpi;
        return (int)(spvalue+0.5f);
    }
    /**
     * px转sp
     * @param value
     * @param context
     * @return
     */
    public static int px2sp(float value,Context context)
    {
        final  float scale=context.getResources().getDisplayMetrics().densityDpi;
        return (int)(value/scale+0.5f);
    }
}