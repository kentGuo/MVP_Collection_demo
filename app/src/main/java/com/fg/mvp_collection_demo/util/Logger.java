package com.fg.mvp_collection_demo.util;

import android.util.Log;

public class Logger {
    //设为false关闭日志
    private static final boolean LOG_ENABLE=true;

    public static void i(String tag,String msg)
    {
        if(LOG_ENABLE)
        {
            Log.i(tag,msg);
        }
    }

    public static void v(String tag,String msg)
    {
        if(LOG_ENABLE)
        {
            Log.i(tag,msg);
        }
    }
    public static void d(String tag,String msg)
    {
        if(LOG_ENABLE)
        {
            Log.i(tag,msg);
        }
    }
    public static void e(String tag,String msg)
    {
        if(LOG_ENABLE)
        {
            Log.i(tag,msg);
        }
    }
    public static void w(String tag,String msg)
    {
        if(LOG_ENABLE)
        {
            Log.i(tag,msg);
        }
    }
}
