package com.fg.mvp_collection_demo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * APP相关的辅助类
 */
public class AppUtil {
    /**
     * 获取应用程序名称
     * @param context
     * @return
     */
    public static String getAppName(Context context)
    {
        try {
            PackageManager packageManager=context.getPackageManager();
            PackageInfo packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            int labelRes=packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        }catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取app版本名称
     * @param context
     * @return
     */
    public static String getAppVeresionName(Context context)
    {
        try {
            PackageManager packageManager=context.getPackageManager();
            PackageInfo info=packageManager.getPackageInfo(context.getPackageName(),0);
            return info.versionName;
        }catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取app版本号
     * @param context
     * @return
     */
    public static int getAppVeresionCode(Context context)
    {
        try {
            PackageManager packageManager=context.getPackageManager();
            PackageInfo info=packageManager.getPackageInfo(context.getPackageName(),0);
            return info.versionCode;
        }catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 获取包名
     * @param context
     * @return
     */
    public static String getAppPackageName(Context context)
    {
        return context.getPackageName();
    }

    /**
     * 获取包 信息
     * @param context
     * @return
     */
    public static PackageInfo getAppPackageInfo(Context context)
    {
        try{
            PackageManager manager=context.getPackageManager();
            PackageInfo info=manager.getPackageInfo(context.getPackageName(),0);
            return info;
        }catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
