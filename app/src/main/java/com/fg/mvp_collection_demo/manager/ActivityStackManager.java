package com.fg.mvp_collection_demo.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Process;

import java.util.Stack;

/**
 * Activity栈管理
 */
public class ActivityStackManager {
    private static ActivityStackManager instance=null;
    private static Stack<Activity> activityStack;//栈

    /**
     * 私有构造
     */
    private ActivityStackManager(){
        activityStack=new Stack<>();
    }

    /**
     * 单例
     * @return
     */
    public static ActivityStackManager getManager(){
        if(instance==null)
        {
            synchronized (ActivityStackManager.class)
            {
                if(instance==null)
                    instance=new ActivityStackManager();
            }
        }
        return instance;
    }

    /**
     * 压栈
     * @param activity
     */
    public void puch(Activity activity)
    {
        activityStack.push(activity);
    }

    /**
     * 出栈
     * @return
     */
    public Activity pop(){
        if(activityStack.isEmpty())
            return null;
        return activityStack.pop();
    }

    /**
     * 栈顶
     * @return
     */
    public Activity peek(){
        if(activityStack.isEmpty())
            return null;
        return activityStack.peek();
    }

    /**
     * 用于异地登录或者退出时清除activity
     */
    public void clearActivity(){
        while (!activityStack.isEmpty())
        {
            Activity activity=activityStack.pop();
        }
    }

    /**
     * 移除
     * @param activity
     */
    public void remove(Activity activity)
    {
        if(activityStack.size()>0&&activity==activityStack.peek())
        {
            activityStack.pop();
        }else
            activityStack.remove(activity);
    }

    /**
     * 是否存在栈
     * @param activity
     * @return
     */
    public boolean contains(Activity activity)
    {
        return activityStack.contains(activity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        while (!activityStack.isEmpty())
        {
            activityStack.pop().finish();
        }
    }

    /**
     * 退出应用程序
     * @param context
     */
    public void exitApp(Context context)
    {
        try{
            finishAllActivity();
            ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            //清除通知栏
            NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
            Process.killProcess(Process.myPid());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}