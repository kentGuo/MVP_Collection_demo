package com.fg.mvp_collection_demo.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import static com.squareup.leakcanary.LeakCanary.*;


public class MyApplication extends Application {
    private static Context context;
    private static RefWatcher refWatcher;
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        application=this;
        refWatcher=setupLeakCanary();//内存泄漏检测
    }

    public static Context getContext() {
        return context;
    }

    public static Application getApplication() {
        return application;
    }

    /**
     * 内存泄漏检测
     * @return
     */
    private RefWatcher setupLeakCanary(){
        if (!isInAnalyzerProcess(this)) {
            ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults().instanceField("android.view.inputmethod.InputMethodManager", "sInstance")
                    .instanceField("android.view.inputmethod.InputMethodManager", "mLastSrvView")
                    .instanceField("android.view.inputmethod.InputMethodManager", "mCurRootView")
                    .instanceField("com.android.internal.policy.PhoneWindow$DecorView", "mContext")
                    .instanceField("android.support.v7.widget.SearchView$SearchAutoComplete", "mContext")
                    .build();
            return refWatcher(this).listenerServiceClass(DisplayLeakService.class).excludedRefs(excludedRefs).buildAndInstall();
        } else {
            return RefWatcher.DISABLED;
        }
    }
    public static RefWatcher getRefWatcher(Context context)
    {
        MyApplication leakApplication=(MyApplication)context.getApplicationContext();
        return MyApplication.refWatcher;
    }
}